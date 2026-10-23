package com.ancientkira.pcoil.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class PowerCoilsConfigConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ModConfigSpec SPEC;
   public static final ConfigValue<Boolean> ALT_COIL_MODEL = BUILDER.comment("If true, the handheld coil model is replaced for a bigger and slimmer version")
      .define("Alt Coil Model", false);

   static {
      BUILDER.push("Visual");
      BUILDER.pop();
      SPEC = BUILDER.build();
   }
}
