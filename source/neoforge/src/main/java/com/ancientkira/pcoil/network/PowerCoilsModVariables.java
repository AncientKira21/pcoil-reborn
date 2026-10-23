package com.ancientkira.pcoil.network;

import java.util.function.Supplier;
import com.ancientkira.pcoil.PowerCoilsMod;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.Clone;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

@EventBusSubscriber
public class PowerCoilsModVariables {
   public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(Keys.ATTACHMENT_TYPES, "pcoil");
   public static final Supplier<AttachmentType<PowerCoilsModVariables.PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register(
      "player_variables", () -> AttachmentType.serializable(() -> new PowerCoilsModVariables.PlayerVariables()).build()
   );

   @SubscribeEvent
   public static void init(FMLCommonSetupEvent event) {
      PowerCoilsMod.addNetworkMessage(
         PowerCoilsModVariables.PlayerVariablesSyncMessage.TYPE,
         PowerCoilsModVariables.PlayerVariablesSyncMessage.STREAM_CODEC,
         PowerCoilsModVariables.PlayerVariablesSyncMessage::handleData
      );
   }

   @EventBusSubscriber
   public static class EventBusVariableHandlers {
      @SubscribeEvent
      public static void onPlayerLoggedInSyncPlayerVariables(PlayerLoggedInEvent event) {
         if (event.getEntity() instanceof ServerPlayer player) {
            ((PowerCoilsModVariables.PlayerVariables)player.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).syncPlayerVariables(event.getEntity());
         }
      }

      @SubscribeEvent
      public static void onPlayerRespawnedSyncPlayerVariables(PlayerRespawnEvent event) {
         if (event.getEntity() instanceof ServerPlayer player) {
            ((PowerCoilsModVariables.PlayerVariables)player.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).syncPlayerVariables(event.getEntity());
         }
      }

      @SubscribeEvent
      public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerChangedDimensionEvent event) {
         if (event.getEntity() instanceof ServerPlayer player) {
            ((PowerCoilsModVariables.PlayerVariables)player.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).syncPlayerVariables(event.getEntity());
         }
      }

      @SubscribeEvent
      public static void clonePlayer(Clone event) {
         PowerCoilsModVariables.PlayerVariables original = (PowerCoilsModVariables.PlayerVariables)event.getOriginal()
            .getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         PowerCoilsModVariables.PlayerVariables clone = new PowerCoilsModVariables.PlayerVariables();
         clone.RegenCoil = original.RegenCoil;
         clone.GravityCoil = original.GravityCoil;
         clone.FusionCoil = original.FusionCoil;
         clone.SnowyGravityCoil = original.SnowyGravityCoil;
         clone.GravityCoilSoundCooldown = original.GravityCoilSoundCooldown;
         clone.SpeedCoilSoundCooldown = original.SpeedCoilSoundCooldown;
         clone.RegenCoilSoundCooldown = original.RegenCoilSoundCooldown;
         clone.FusionCoilSoundCooldown = original.FusionCoilSoundCooldown;
         clone.SnowyGravityCoilSoundCooldown = original.SnowyGravityCoilSoundCooldown;
         clone.DualGravityCoilSoundCooldown = original.DualGravityCoilSoundCooldown;
         if (!event.isWasDeath()) {
         }

         event.getEntity().setData(PowerCoilsModVariables.PLAYER_VARIABLES, clone);
      }
   }

   public static class PlayerVariables implements ValueIOSerializable {
      public boolean RegenCoil = false;
      public boolean GravityCoil = false;
      public boolean FusionCoil = false;
      public boolean SnowyGravityCoil = false;
      public boolean GravityCoilSoundCooldown = false;
      public boolean SpeedCoilSoundCooldown = false;
      public boolean RegenCoilSoundCooldown = false;
      public boolean FusionCoilSoundCooldown = false;
      public boolean SnowyGravityCoilSoundCooldown = false;
      public boolean DualGravityCoilSoundCooldown = false;

      @Override
      public void serialize(ValueOutput output) {
         output.putBoolean("RegenCoil", this.RegenCoil);
         output.putBoolean("GravityCoil", this.GravityCoil);
         output.putBoolean("FusionCoil", this.FusionCoil);
         output.putBoolean("SnowyGravityCoil", this.SnowyGravityCoil);
         output.putBoolean("GravityCoilSoundCooldown", this.GravityCoilSoundCooldown);
         output.putBoolean("SpeedCoilSoundCooldown", this.SpeedCoilSoundCooldown);
         output.putBoolean("RegenCoilSoundCooldown", this.RegenCoilSoundCooldown);
         output.putBoolean("FusionCoilSoundCooldown", this.FusionCoilSoundCooldown);
         output.putBoolean("SnowyGravityCoilSoundCooldown", this.SnowyGravityCoilSoundCooldown);
         output.putBoolean("DualGravityCoilSoundCooldown", this.DualGravityCoilSoundCooldown);
      }

      @Override
      public void deserialize(ValueInput input) {
         this.RegenCoil = input.getBooleanOr("RegenCoil", false);
         this.GravityCoil = input.getBooleanOr("GravityCoil", false);
         this.FusionCoil = input.getBooleanOr("FusionCoil", false);
         this.SnowyGravityCoil = input.getBooleanOr("SnowyGravityCoil", false);
         this.GravityCoilSoundCooldown = input.getBooleanOr("GravityCoilSoundCooldown", false);
         this.SpeedCoilSoundCooldown = input.getBooleanOr("SpeedCoilSoundCooldown", false);
         this.RegenCoilSoundCooldown = input.getBooleanOr("RegenCoilSoundCooldown", false);
         this.FusionCoilSoundCooldown = input.getBooleanOr("FusionCoilSoundCooldown", false);
         this.SnowyGravityCoilSoundCooldown = input.getBooleanOr("SnowyGravityCoilSoundCooldown", false);
         this.DualGravityCoilSoundCooldown = input.getBooleanOr("DualGravityCoilSoundCooldown", false);
      }

      public CompoundTag serializeNBT(Provider lookupProvider) {
         CompoundTag nbt = new CompoundTag();
         nbt.putBoolean("RegenCoil", this.RegenCoil);
         nbt.putBoolean("GravityCoil", this.GravityCoil);
         nbt.putBoolean("FusionCoil", this.FusionCoil);
         nbt.putBoolean("SnowyGravityCoil", this.SnowyGravityCoil);
         nbt.putBoolean("GravityCoilSoundCooldown", this.GravityCoilSoundCooldown);
         nbt.putBoolean("SpeedCoilSoundCooldown", this.SpeedCoilSoundCooldown);
         nbt.putBoolean("RegenCoilSoundCooldown", this.RegenCoilSoundCooldown);
         nbt.putBoolean("FusionCoilSoundCooldown", this.FusionCoilSoundCooldown);
         nbt.putBoolean("SnowyGravityCoilSoundCooldown", this.SnowyGravityCoilSoundCooldown);
         nbt.putBoolean("DualGravityCoilSoundCooldown", this.DualGravityCoilSoundCooldown);
         return nbt;
      }

      public void deserializeNBT(Provider lookupProvider, CompoundTag nbt) {
         this.RegenCoil = nbt.getBooleanOr("RegenCoil", false);
         this.GravityCoil = nbt.getBooleanOr("GravityCoil", false);
         this.FusionCoil = nbt.getBooleanOr("FusionCoil", false);
         this.SnowyGravityCoil = nbt.getBooleanOr("SnowyGravityCoil", false);
         this.GravityCoilSoundCooldown = nbt.getBooleanOr("GravityCoilSoundCooldown", false);
         this.SpeedCoilSoundCooldown = nbt.getBooleanOr("SpeedCoilSoundCooldown", false);
         this.RegenCoilSoundCooldown = nbt.getBooleanOr("RegenCoilSoundCooldown", false);
         this.FusionCoilSoundCooldown = nbt.getBooleanOr("FusionCoilSoundCooldown", false);
         this.SnowyGravityCoilSoundCooldown = nbt.getBooleanOr("SnowyGravityCoilSoundCooldown", false);
         this.DualGravityCoilSoundCooldown = nbt.getBooleanOr("DualGravityCoilSoundCooldown", false);
      }

      public void syncPlayerVariables(Entity entity) {
         if (entity instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new PowerCoilsModVariables.PlayerVariablesSyncMessage(this), new CustomPacketPayload[0]);
         }
      }
   }

   public record PlayerVariablesSyncMessage(PowerCoilsModVariables.PlayerVariables data) implements CustomPacketPayload {
      public static final Type<PowerCoilsModVariables.PlayerVariablesSyncMessage> TYPE = new Type(
         Identifier.fromNamespaceAndPath("pcoil", "player_variables_sync")
      );
      public static final StreamCodec<RegistryFriendlyByteBuf, PowerCoilsModVariables.PlayerVariablesSyncMessage> STREAM_CODEC = StreamCodec.of(
         (buffer, message) -> buffer.writeNbt(message.data().serializeNBT(buffer.registryAccess())),
         buffer -> {
            PowerCoilsModVariables.PlayerVariablesSyncMessage message = new PowerCoilsModVariables.PlayerVariablesSyncMessage(
               new PowerCoilsModVariables.PlayerVariables()
            );
            message.data.deserializeNBT(buffer.registryAccess(), buffer.readNbt());
            return message;
         }
      );

      public Type<PowerCoilsModVariables.PlayerVariablesSyncMessage> type() {
         return TYPE;
      }

      public static void handleData(PowerCoilsModVariables.PlayerVariablesSyncMessage message, IPayloadContext context) {
         if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
            context.enqueueWork(
                  () -> ((PowerCoilsModVariables.PlayerVariables)context.player().getData(PowerCoilsModVariables.PLAYER_VARIABLES))
                     .deserializeNBT(context.player().registryAccess(), message.data.serializeNBT(context.player().registryAccess()))
               )
               .exceptionally(e -> {
                  context.connection().disconnect(Component.literal(e.getMessage()));
                  return null;
               });
         }
      }
   }
}
