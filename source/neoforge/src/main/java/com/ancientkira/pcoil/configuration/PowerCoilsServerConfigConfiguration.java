package com.ancientkira.pcoil.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class PowerCoilsServerConfigConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ModConfigSpec SPEC;
   public static final ConfigValue<Boolean> ANTISPAM = BUILDER.comment("If true, all deploy sounds will have a cooldown based on the sound duration")
      .define("Antispam", true);
   public static final ConfigValue<Boolean> MUTE_DEPLOY_SOUNDS = BUILDER.comment("If true, all deploy sounds will be muted")
      .define("Mute All Deploy Sounds", false);
   public static final ConfigValue<Double> GRAVITY_COIL_JUMP = BUILDER.comment("This number defines the jump boost level given by the Gravity Coil")
      .define("Gravity Coil Jump Boost Level", 3.0);
   public static final ConfigValue<Double> SNOWY_GRAVITY_COIL_JUMP = BUILDER.comment("This number defines the jump boost level given by the Snowy Gravity Coil")
      .define("Snowy Gravity Coil Jump Boost Level", 3.0);
   public static final ConfigValue<Boolean> SNOWY_GRAVITY_COIL_PARTICLES = BUILDER.comment(
         "If true, the Snowy Gravity Coil will leave particles when on the player's hand"
      )
      .define("Snowy Gravity Coil Particles", true);
   public static final ConfigValue<Double> DUAL_GRAVITY_COIL_JUMP = BUILDER.comment("This number defines the jump boost level given by the Dual Gravity Coil")
      .define("Dual Gravity Coil Jump Boost Level", 4.0);
   public static final ConfigValue<Boolean> DUAL_GRAVITY_COIL_SNEAKING = BUILDER.comment(
         "If true, the Dual Gravity Coil will deactivate when the player sneaks while using it"
      )
      .define("Dual Gravity Coil Deactivates by Sneaking", true);
   public static final ConfigValue<Double> SPEED_COIL_SPEED = BUILDER.comment("This number defines the speed level given by the Speed Coil")
      .define("Speed Coil Speed Level", 5.0);
   public static final ConfigValue<Boolean> SPEED_COIL_PARTICLES = BUILDER.comment(
         "If true, the Speed Coil will leave particles when the player sprints while having it on hand"
      )
      .define("Speed Coil Particles", true);
   public static final ConfigValue<Double> REGEN_COIL_HEAL = BUILDER.comment(
         "This number defines the healing rate per second (in half hearts) given by the Regen Coil"
      )
      .define("Regen Coil Healing Rate Per Second", 2.0);
   public static final ConfigValue<Double> FUSION_COIL_JUMP = BUILDER.comment("This number defines the jump boost level given by the Fusion Coil")
      .define("Fusion Coil Jump Boost Level", 3.0);
   public static final ConfigValue<Double> FUSION_COIL_SPEED = BUILDER.comment("This number defines the speed level given by the Fusion Coil")
      .define("Fusion Coil Speed Level", 5.0);

   static {
      BUILDER.push("Sounds");
      BUILDER.pop();
      BUILDER.push("Coil Effects");
      BUILDER.pop();
      SPEC = BUILDER.build();
   }
}
