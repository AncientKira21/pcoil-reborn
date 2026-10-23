package com.ancientkira.pcoil.procedures;

import com.ancientkira.pcoil.PowerCoilsMod;
import com.ancientkira.pcoil.configuration.PowerCoilsServerConfigConfiguration;
import com.ancientkira.pcoil.init.PowerCoilsModMobEffects;
import com.ancientkira.pcoil.network.PowerCoilsModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class SpeedCoilItemInHandTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!(Boolean)PowerCoilsServerConfigConfiguration.MUTE_DEPLOY_SOUNDS.get()
            && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(PowerCoilsModMobEffects.SPEED_COIL_EFFECT))) {
            if ((Boolean)PowerCoilsServerConfigConfiguration.ANTISPAM.get()) {
               if (!((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).SpeedCoilSoundCooldown) {
                  PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
                  _vars.SpeedCoilSoundCooldown = true;
                  _vars.syncPlayerVariables(entity);
                  if (!world.isClientSide() && world instanceof Level _level) {
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:speed_coil")),
                           SoundSource.PLAYERS,
                           1.5F,
                           1.0F
                        );
                     } else {
                        _level.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:speed_coil")),
                           SoundSource.PLAYERS,
                           1.5F,
                           1.0F,
                           false
                        );
                     }
                  }

                  PowerCoilsMod.queueServerWork(
                     18,
                     () -> {
                        PowerCoilsModVariables.PlayerVariables _varsx = (PowerCoilsModVariables.PlayerVariables)entity.getData(
                           PowerCoilsModVariables.PLAYER_VARIABLES
                        );
                        _varsx.SpeedCoilSoundCooldown = false;
                        _varsx.syncPlayerVariables(entity);
                     }
                  );
               }
            } else if (!world.isClientSide() && world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:speed_coil")),
                     SoundSource.PLAYERS,
                     1.5F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:speed_coil")),
                     SoundSource.PLAYERS,
                     1.5F,
                     1.0F,
                     false
                  );
               }
            }
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(
               new MobEffectInstance(
                  MobEffects.SPEED, 2, (int)((Double)PowerCoilsServerConfigConfiguration.SPEED_COIL_SPEED.get() - 1.0), false, false
               )
            );
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(PowerCoilsModMobEffects.SPEED_COIL_EFFECT, 2, 0, false, false));
         }

         if ((Boolean)PowerCoilsServerConfigConfiguration.SPEED_COIL_PARTICLES.get()
            && entity.isSprinting()
            && !(entity instanceof LivingEntity _livEnt13 && _livEnt13.isFallFlying())
            && !entity.isInWater()
            && world instanceof ServerLevel _level) {
            _level.sendParticles(ParticleTypes.POOF, x, y + 0.25, z, 1, 0.1, 0.1, 0.1, 0.05);
         }
      }
   }
}
