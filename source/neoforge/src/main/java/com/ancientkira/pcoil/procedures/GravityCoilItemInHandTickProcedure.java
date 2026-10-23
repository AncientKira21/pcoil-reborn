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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class GravityCoilItemInHandTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!(Boolean)PowerCoilsServerConfigConfiguration.MUTE_DEPLOY_SOUNDS.get()
            && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(PowerCoilsModMobEffects.GRAVITY_COIL_EFFECT))) {
            if ((Boolean)PowerCoilsServerConfigConfiguration.ANTISPAM.get()) {
               if (!((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).GravityCoilSoundCooldown) {
                  PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
                  _vars.GravityCoilSoundCooldown = true;
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
                        _varsx.GravityCoilSoundCooldown = false;
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
            if (!entity.isInWater()
               && !(entity instanceof LivingEntity _livEnt10 && _livEnt10.isFallFlying())
               && !(entity instanceof Player player && player.getAbilities().flying)
               && !((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).FusionCoil
               && !((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).SnowyGravityCoil
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
                     new MobEffectInstance(MobEffects.JUMP_BOOST, 2, (int)((Double)PowerCoilsServerConfigConfiguration.GRAVITY_COIL_JUMP.get() - 1.0), false, false)
                  );
               }

               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
               _vars.GravityCoil = true;
               _vars.syncPlayerVariables(entity);
            }
         } else if (!entity.isInWater()
            && !(entity instanceof LivingEntity _livEnt24 && _livEnt24.isFallFlying())
            && !(entity instanceof Player player && player.getAbilities().flying)
            && !((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).FusionCoil
            && !((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).SnowyGravityCoil
            && !(entity instanceof LivingEntity _livEnt26 && _livEnt26.hasEffect(PowerCoilsModMobEffects.DUAL_GRAVITY_COIL_EFFECT))) {
            if (entity.getDeltaMovement().y() != 0.0) {
               entity.setDeltaMovement(
                  new Vec3(entity.getDeltaMovement().x(), (entity.getDeltaMovement().y() / 0.98 + 0.08 - 0.028) * 1.0, entity.getDeltaMovement().z())
               );
            }

            entity.fallDistance = (float)(entity.fallDistance * 0.35);
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(
                  new MobEffectInstance(MobEffects.JUMP_BOOST, 2, (int)((Double)PowerCoilsServerConfigConfiguration.GRAVITY_COIL_JUMP.get() - 1.0), false, false)
               );
            }

            PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
            _vars.GravityCoil = true;
            _vars.syncPlayerVariables(entity);
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(PowerCoilsModMobEffects.GRAVITY_COIL_EFFECT, 2, 0, false, false));
         }
      }
   }
}
