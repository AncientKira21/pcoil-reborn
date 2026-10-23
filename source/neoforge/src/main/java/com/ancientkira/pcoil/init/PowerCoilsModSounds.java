package com.ancientkira.pcoil.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PowerCoilsModSounds {
   public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, "pcoil");
   public static final DeferredHolder<SoundEvent, SoundEvent> GRAVITY_COIL = REGISTRY.register(
      "gravity_coil", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pcoil", "gravity_coil"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> SPEED_COIL = REGISTRY.register(
      "speed_coil", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pcoil", "speed_coil"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> FUSION_COIL = REGISTRY.register(
      "fusion_coil", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pcoil", "fusion_coil"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> REGEN_COIL = REGISTRY.register(
      "regen_coil", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pcoil", "regen_coil"))
   );
}
