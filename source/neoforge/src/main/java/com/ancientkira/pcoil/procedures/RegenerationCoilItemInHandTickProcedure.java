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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class RegenerationCoilItemInHandTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!(Boolean)PowerCoilsServerConfigConfiguration.MUTE_DEPLOY_SOUNDS.get()
            && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(PowerCoilsModMobEffects.REGEN_COIL_EFFECT))) {
            if ((Boolean)PowerCoilsServerConfigConfiguration.ANTISPAM.get()) {
               if (!((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).RegenCoilSoundCooldown) {
                  PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
                  _vars.RegenCoilSoundCooldown = true;
                  _vars.syncPlayerVariables(entity);
                  if (!world.isClientSide() && world instanceof Level _level) {
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:regen_coil")),
                           SoundSource.PLAYERS,
                           0.4F,
                           1.0F
                        );
                     } else {
                        _level.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:regen_coil")),
                           SoundSource.PLAYERS,
                           0.4F,
                           1.0F,
                           false
                        );
                     }
                  }

                  PowerCoilsMod.queueServerWork(
                     26,
                     () -> {
                        PowerCoilsModVariables.PlayerVariables _varsx = (PowerCoilsModVariables.PlayerVariables)entity.getData(
                           PowerCoilsModVariables.PLAYER_VARIABLES
                        );
                        _varsx.RegenCoilSoundCooldown = false;
                        _varsx.syncPlayerVariables(entity);
                     }
                  );
               }
            } else if (!world.isClientSide() && world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:regen_coil")),
                     SoundSource.PLAYERS,
                     0.4F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("pcoil:regen_coil")),
                     SoundSource.PLAYERS,
                     0.4F,
                     1.0F,
                     false
                  );
               }
            }
         }

         if (!((PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES)).RegenCoil) {
            PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
            _vars.RegenCoil = true;
            _vars.syncPlayerVariables(entity);
            PowerCoilsMod.queueServerWork(
               10,
               () -> {
                  if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F)
                        < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F)
                     && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F) != 0.0F
                     && entity instanceof LivingEntity _entityx) {
                     _entityx.setHealth(
                        (float)(
                           (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F)
                              + (Double)PowerCoilsServerConfigConfiguration.REGEN_COIL_HEAL.get() / 2.0
                        )
                     );
                  }

                  PowerCoilsModVariables.PlayerVariables _varsx = (PowerCoilsModVariables.PlayerVariables)entity.getData(
                     PowerCoilsModVariables.PLAYER_VARIABLES
                  );
                  _varsx.RegenCoil = false;
                  _varsx.syncPlayerVariables(entity);
               }
            );
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2, 0, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(PowerCoilsModMobEffects.REGEN_COIL_EFFECT, 2, 0, false, false));
         }
      }
   }
}
