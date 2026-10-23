package com.ancientkira.pcoil.procedures;

import net.minecraft.client.gui.screens.Screen;

public class DualGravityCoilSpecialInformationProcedure {
   public static String execute() {
      return net.minecraft.client.Minecraft.getInstance().hasShiftDown() ? "§7When on your feet, makes you jump higher and fall slower" : "§7Press §bSHIFT §7for information";
   }
}
