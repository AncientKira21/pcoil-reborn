package com.ancientkira.pcoil.procedures;

import com.ancientkira.pcoil.configuration.PowerCoilsConfigConfiguration;

public class GravityCoilPropertyValueProviderProcedure {
   public static double execute() {
      return !PowerCoilsConfigConfiguration.ALT_COIL_MODEL.get() ? 0.0 : 1.0;
   }
}
