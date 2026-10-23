package com.ancientkira.pcoil.shared.procedures;

import com.ancientkira.pcoil.shared.PowerCoilsMod;
import com.ancientkira.pcoil.shared.configuration.PowerCoilsServerConfigConfiguration;
import com.ancientkira.pcoil.shared.init.PowerCoilsModMobEffects;
import com.ancientkira.pcoil.shared.network.PowerCoilsModVariables;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class FusionCoilItemInHandTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!(Boolean)PowerCoilsServerConfigConfiguration.MUTE_DEPLOY_SOUNDS.get()
            && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(PowerCoilsModMobEffects.FUSION_COIL_EFFECT))) {
            if ((Boolean)PowerCoilsServerConfigConfiguration.ANTISPAM.get()) {
               if (!((PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity)).FusionCoilSoundCooldown) {
                  PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
                  _vars.FusionCoilSoundCooldown = true;
                  _vars.syncPlayerVariables(entity);
                  if (!world.isClientSide() && world instanceof Level _level) {
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:fusion_coil")),
                           SoundSource.PLAYERS,
                           1.5F,
                           1.0F
                        );
                     } else {
                        _level.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:fusion_coil")),
                           SoundSource.PLAYERS,
                           1.5F,
                           1.0F,
                           false
                        );
                     }
                  }

                  PowerCoilsMod.queueServerWork(
                     24,
                     () -> {
                        PowerCoilsModVariables.PlayerVariables _varsx = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
                        _varsx.FusionCoilSoundCooldown = false;
                        _varsx.syncPlayerVariables(entity);
                     }
                  );
               }
            } else if (!world.isClientSide() && world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:fusion_coil")),
                     SoundSource.PLAYERS,
                     1.5F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:fusion_coil")),
                     SoundSource.PLAYERS,
                     1.5F,
                     1.0F,
                     false
                  );
               }
            }
         }

         if ((Boolean)PowerCoilsServerConfigConfiguration.DUAL_GRAVITY_COIL_SNEAKING.get()) {
            if (!entity.isInWater()
               && !(entity instanceof LivingEntity _livEnt10 && _livEnt10.isFallFlying())
               && !(entity instanceof Player player && player.getAbilities().flying)
               && !((PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity)).GravityCoil
               && !((PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity)).SnowyGravityCoil
               && (
                  !(!entity.isShiftKeyDown() && entity instanceof LivingEntity _livEnt13)
                     || !_livEnt13.hasEffect(PowerCoilsModMobEffects.DUAL_GRAVITY_COIL_EFFECT)
               )) {
               if (entity.getDeltaMovement().y() != 0.0) {
                  entity.setDeltaMovement(
                     new Vec3(entity.getDeltaMovement().x(), (entity.getDeltaMovement().y() / 0.98 + 0.08 - 0.028) * 1.0, entity.getDeltaMovement().z())
                  );
               }

               entity.fallDistance = (float)(entity.fallDistance * 0.35);
               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(
                     new MobEffectInstance(MobEffects.JUMP_BOOST, 2, (int)((Double)PowerCoilsServerConfigConfiguration.FUSION_COIL_JUMP.get() - 1.0), false, false)
                  );
               }

               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
               _vars.FusionCoil = true;
               _vars.syncPlayerVariables(entity);
            }
         } else if (!entity.isInWater()
            && !(entity instanceof LivingEntity _livEnt24 && _livEnt24.isFallFlying())
            && !(entity instanceof Player player && player.getAbilities().flying)
            && !((PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity)).GravityCoil
            && !((PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity)).SnowyGravityCoil
            && !(entity instanceof LivingEntity _livEnt26 && _livEnt26.hasEffect(PowerCoilsModMobEffects.DUAL_GRAVITY_COIL_EFFECT))) {
            if (entity.getDeltaMovement().y() != 0.0) {
               entity.setDeltaMovement(
                  new Vec3(entity.getDeltaMovement().x(), (entity.getDeltaMovement().y() / 0.98 + 0.08 - 0.028) * 1.0, entity.getDeltaMovement().z())
               );
            }

            entity.fallDistance = (float)(entity.fallDistance * 0.35);
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(
                  new MobEffectInstance(MobEffects.JUMP_BOOST, 2, (int)((Double)PowerCoilsServerConfigConfiguration.FUSION_COIL_JUMP.get() - 1.0), false, false)
               );
            }

            PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
            _vars.FusionCoil = true;
            _vars.syncPlayerVariables(entity);
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(
               new MobEffectInstance(
                  MobEffects.SPEED, 2, (int)((Double)PowerCoilsServerConfigConfiguration.FUSION_COIL_SPEED.get() - 1.0), false, false
               )
            );
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(PowerCoilsModMobEffects.FUSION_COIL_EFFECT, 2, 4, false, false));
         }
      }
   }
}
