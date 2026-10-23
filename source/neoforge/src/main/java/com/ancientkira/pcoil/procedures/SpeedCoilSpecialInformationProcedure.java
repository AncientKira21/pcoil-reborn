package com.ancientkira.pcoil.procedures;

import net.minecraft.client.gui.screens.Screen;

public class SpeedCoilSpecialInformationProcedure {
   public static String execute() {
      return net.minecraft.client.Minecraft.getInstance().hasShiftDown() ? "§7When on your hand, makes you run twice as fast" : "§7Press §bSHIFT §7for information";
   }
}
