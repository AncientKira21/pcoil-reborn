package com.ancientkira.pcoil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.ancientkira.pcoil.init.PowerCoilsModItems;
import com.ancientkira.pcoil.init.PowerCoilsModMobEffects;
import com.ancientkira.pcoil.init.PowerCoilsModSounds;
import com.ancientkira.pcoil.init.PowerCoilsModTabs;
import com.ancientkira.pcoil.network.PowerCoilsModVariables;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent.Post;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("pcoil")
public class PowerCoilsMod {
   public static final Logger LOGGER = LogManager.getLogger(PowerCoilsMod.class);
   public static final String MODID = "pcoil";
   private static boolean networkingRegistered = false;
   private static final Map<Type<?>, PowerCoilsMod.NetworkMessage<?>> MESSAGES = new HashMap<>();
   private static final Collection<ScheduledWork> workQueue = new ConcurrentLinkedQueue<>();

   public PowerCoilsMod(IEventBus modEventBus) {
      NeoForge.EVENT_BUS.register(this);
      modEventBus.addListener(this::registerNetworking);
      PowerCoilsModSounds.REGISTRY.register(modEventBus);
      PowerCoilsModItems.REGISTRY.register(modEventBus);
      PowerCoilsModTabs.REGISTRY.register(modEventBus);
      PowerCoilsModVariables.ATTACHMENT_TYPES.register(modEventBus);
      PowerCoilsModMobEffects.REGISTRY.register(modEventBus);
   }

   public static <T extends CustomPacketPayload> void addNetworkMessage(
      Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler
   ) {
      if (networkingRegistered) {
         throw new IllegalStateException("Cannot register new network messages after networking has been registered");
      }

      MESSAGES.put(id, new PowerCoilsMod.NetworkMessage(reader, handler));
   }

   // Recover the erased generic relationship between the registry key and payload.
   @SuppressWarnings({"rawtypes", "unchecked"})
   private void registerNetworking(RegisterPayloadHandlersEvent event) {
      PayloadRegistrar registrar = event.registrar("pcoil");
      MESSAGES.forEach((id, networkMessage) -> registrar.playToClient((Type) id, (StreamCodec) networkMessage.reader(), (IPayloadHandler) networkMessage.handler()));
      networkingRegistered = true;
   }

   public static void queueServerWork(int tick, Runnable action) {
      if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
         workQueue.add(new ScheduledWork(action, tick));
      }
   }

   @SubscribeEvent
   public void tick(Post event) {
      List<ScheduledWork> ready = new ArrayList<>();
      workQueue.forEach(work -> {
         if (--work.ticks <= 0) ready.add(work);
      });
      ready.forEach(work -> work.action.run());
      workQueue.removeAll(ready);
   }

   // Keep scheduling independent of Minecraft's removed Tuple helper.
   private static final class ScheduledWork {
      private final Runnable action;
      private int ticks;
      private ScheduledWork(Runnable action, int ticks) {
         this.action = action;
         this.ticks = ticks;
      }
   }

   private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
   }
}
