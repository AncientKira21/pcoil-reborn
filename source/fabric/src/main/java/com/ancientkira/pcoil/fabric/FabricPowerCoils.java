package com.ancientkira.pcoil.fabric;
import java.util.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.*;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import com.ancientkira.pcoil.shared.PowerCoilsMod;
import com.ancientkira.pcoil.shared.configuration.SharedConfig;
import com.ancientkira.pcoil.shared.init.PowerCoilsModItems;
import com.ancientkira.pcoil.shared.network.*;
public class FabricPowerCoils implements ModInitializer {
 private static boolean initialized;
 private static final Map<UUID,Integer> LAST_STATE=new HashMap<>();
 @Override public void onInitialize() { initialize(); }
 public static synchronized void initialize() {
  if (initialized) return;
  initialized=true;
  SharedConfig.load(FabricLoader.getInstance().getConfigDir());
  PowerCoilsMod.initialize();
  PayloadTypeRegistry.clientboundPlay().register(CoilStatePayload.TYPE,CoilStatePayload.CODEC);
  PayloadTypeRegistry.clientboundPlay().register(CoilConfigPayload.TYPE,CoilConfigPayload.CODEC);
  PowerCoilsModVariables.sync=entity->{
   if (entity instanceof ServerPlayer player) {
    int flags=PowerCoilsModVariables.get(player).bits();
    if (!Objects.equals(LAST_STATE.put(player.getUUID(),flags),flags))
     ServerPlayNetworking.send(player,new CoilStatePayload(flags));
   }
  };
  ServerPlayConnectionEvents.JOIN.register((handler,sender,server)->{
   var player=handler.getPlayer();
   PowerCoilsModVariables.remove(player);
   LAST_STATE.remove(player.getUUID());
   ServerPlayNetworking.send(player,new CoilConfigPayload(SharedConfig.serverJson()));
   PowerCoilsModVariables.sync.accept(player);
  });
  ServerPlayConnectionEvents.DISCONNECT.register((handler,server)->{
   PowerCoilsModVariables.remove(handler.getPlayer()); LAST_STATE.remove(handler.getPlayer().getUUID());
  });
  ServerTickEvents.END_SERVER_TICK.register(server->{
   for (var player:server.getPlayerList().getPlayers()) PowerCoilsMod.tick(player);
   PowerCoilsMod.endServerTick();
  });
  ServerLifecycleEvents.SERVER_STOPPED.register(server->{PowerCoilsMod.stop(); LAST_STATE.clear();});
  CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries->{
   entries.accept(PowerCoilsModItems.GRAVITY_COIL.get());
   entries.accept(PowerCoilsModItems.SPEED_COIL.get());
   entries.accept(PowerCoilsModItems.REGENERATION_COIL.get());
   entries.accept(PowerCoilsModItems.FUSION_COIL.get());
   entries.accept(PowerCoilsModItems.SNOWY_GRAVITY_COIL.get());
   entries.accept(PowerCoilsModItems.DUAL_GRAVITY_COIL_BOOTS.get());
  });
 }
}
