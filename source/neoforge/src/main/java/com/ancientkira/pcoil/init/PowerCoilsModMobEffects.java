package com.ancientkira.pcoil.init;

import com.ancientkira.pcoil.potion.DualGravityCoilEffectMobEffect;
import com.ancientkira.pcoil.potion.FusionCoilEffectMobEffect;
import com.ancientkira.pcoil.potion.GravityCoilEffectMobEffect;
import com.ancientkira.pcoil.potion.RegenCoilEffectMobEffect;
import com.ancientkira.pcoil.potion.SnowyGravityCoilEffectMobEffect;
import com.ancientkira.pcoil.potion.SpeedCoilEffectMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PowerCoilsModMobEffects {
   public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, "pcoil");
   public static final DeferredHolder<MobEffect, MobEffect> GRAVITY_COIL_EFFECT = REGISTRY.register(
      "gravity_coil_effect", () -> new GravityCoilEffectMobEffect()
   );
   public static final DeferredHolder<MobEffect, MobEffect> SPEED_COIL_EFFECT = REGISTRY.register("speed_coil_effect", () -> new SpeedCoilEffectMobEffect());
   public static final DeferredHolder<MobEffect, MobEffect> REGEN_COIL_EFFECT = REGISTRY.register("regen_coil_effect", () -> new RegenCoilEffectMobEffect());
   public static final DeferredHolder<MobEffect, MobEffect> FUSION_COIL_EFFECT = REGISTRY.register("fusion_coil_effect", () -> new FusionCoilEffectMobEffect());
   public static final DeferredHolder<MobEffect, MobEffect> SNOWY_GRAVITY_COIL_EFFECT = REGISTRY.register(
      "snowy_gravity_coil_effect", () -> new SnowyGravityCoilEffectMobEffect()
   );
   public static final DeferredHolder<MobEffect, MobEffect> DUAL_GRAVITY_COIL_EFFECT = REGISTRY.register(
      "dual_gravity_coil_effect", () -> new DualGravityCoilEffectMobEffect()
   );
}
