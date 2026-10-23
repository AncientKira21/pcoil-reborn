package com.ancientkira.pcoil.shared.init;
import com.ancientkira.pcoil.shared.potion.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
public final class PowerCoilsModMobEffects {
 public static final Holder.Reference<MobEffect> GRAVITY_COIL_EFFECT=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Identifier.parse("pcoil:gravity_coil_effect"),new GravityCoilEffectMobEffect());
 public static final Holder.Reference<MobEffect> SPEED_COIL_EFFECT=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Identifier.parse("pcoil:speed_coil_effect"),new SpeedCoilEffectMobEffect());
 public static final Holder.Reference<MobEffect> REGEN_COIL_EFFECT=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Identifier.parse("pcoil:regen_coil_effect"),new RegenCoilEffectMobEffect());
 public static final Holder.Reference<MobEffect> FUSION_COIL_EFFECT=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Identifier.parse("pcoil:fusion_coil_effect"),new FusionCoilEffectMobEffect());
 public static final Holder.Reference<MobEffect> SNOWY_GRAVITY_COIL_EFFECT=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Identifier.parse("pcoil:snowy_gravity_coil_effect"),new SnowyGravityCoilEffectMobEffect());
 public static final Holder.Reference<MobEffect> DUAL_GRAVITY_COIL_EFFECT=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Identifier.parse("pcoil:dual_gravity_coil_effect"),new DualGravityCoilEffectMobEffect());
 public static void bootstrap() {}
}
