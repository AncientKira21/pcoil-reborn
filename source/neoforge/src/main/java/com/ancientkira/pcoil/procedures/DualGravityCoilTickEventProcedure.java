package com.ancientkira.pcoil.procedures;

import com.ancientkira.pcoil.PowerCoilsMod;
import com.ancientkira.pcoil.configuration.PowerCoilsServerConfigConfiguration;
import com.ancientkira.pcoil.init.PowerCoilsModMobEffects;
import com.ancientkira.pcoil.network.PowerCoilsModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class DualGravityCoilTickEventProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         itemstack.setDamageValue(0);
         if (!(Boolean)PowerCoilsServerConfigConfiguration.MUTE_DEPLOY_SOUNDS.get()
            && !(entity instanceof LivingEntity _livEnt3 && _livEnt3.hasEffect(PowerCoilsModMobEffects.DUAL_GRAVITY_COIL_EFFECT))) {
            if ((Boolean)PowerCoilsServerConfigConfiguration.ANTISPAM.get()) {
               if (!((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).DualGravityCoilSoundCooldown) {
                  PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
                  _vars.DualGravityCoilSoundCooldown = true;
                  _vars.syncPlayerVariables(entity);
                  if (!world.isClientSide() && world instanceof Level _level) {
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:gravity_coil")),
                           SoundSource.PLAYERS,
                           0.6F,
                           1.0F
                        );
                     } else {
                        _level.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:gravity_coil")),
                           SoundSource.PLAYERS,
                           0.6F,
                           1.0F,
                           false
                        );
                     }
                  }

                  PowerCoilsMod.queueServerWork(
                     60,
                     () -> {
                        PowerCoilsModVariables.PlayerVariables _varsx = (PowerCoilsModVariables.PlayerVariables)entity.getData(
                           PowerCoilsModVariables.PLAYER_VARIABLES
                        );
                        _varsx.DualGravityCoilSoundCooldown = false;
                        _varsx.syncPlayerVariables(entity);
                     }
                  );
               }
            } else if (!world.isClientSide() && world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:gravity_coil")),
                     SoundSource.PLAYERS,
                     0.6F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:gravity_coil")),
                     SoundSource.PLAYERS,
                     0.6F,
                     1.0F,
                     false
                  );
               }
            }
         }

         if ((Boolean)PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_SNEAKING.get()) {
            if (!entity.isShiftKeyDown()) {
               if (!entity.isInWater()
                  && !(entity instanceof LivingEntity _livEnt13 && _livEnt13.isFallFlying())
                  && !(entity instanceof Player player && player.getAbilities().flying)) {
                  if (entity.getDeltaMovement().y() != 0.0) {
                     entity.setDeltaMovement(
                        new Vec3(entity.getDeltaMovement().x(), (entity.getDeltaMovement().y() / 0.98 + 0.08 - 0.0184) * 1.0, entity.getDeltaMovement().z())
                     );
                  }

                  entity.fallDistance = (float)(entity.fallDistance * 0.23);
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(
                     new MobEffectInstance(
                        MobEffects.JUMP_BOOST, 2, (int)((Double)PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_JUMP.get() - 1.0), false, false
                     )
                  );
               }
            }
         } else {
            if (!entity.isInWater()
               && !(entity instanceof LivingEntity _livEnt25 && _livEnt25.isFallFlying())
               && !(entity instanceof Player player && player.getAbilities().flying)) {
               if (entity.getDeltaMovement().y() != 0.0) {
                  entity.setDeltaMovement(
                     new Vec3(entity.getDeltaMovement().x(), (entity.getDeltaMovement().y() / 0.98 + 0.08 - 0.0184) * 1.0, entity.getDeltaMovement().z())
                  );
               }

               entity.fallDistance = (float)(entity.fallDistance * 0.23);
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(
                  new MobEffectInstance(MobEffects.JUMP_BOOST, 2, (int)((Double)PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_JUMP.get() - 1.0), false, false)
               );
            }
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(PowerCoilsModMobEffects.DUAL_GRAVITY_COIL_EFFECT, 2, 0, false, false));
         }
      }
   }
}
