package com.ancientkira.pcoil.shared.procedures;

import net.minecraft.client.gui.screens.Screen;

public class RegenerationCoilSpecialInformationProcedure {
   public static String execute() {
      return net.minecraft.client.Minecraft.getInstance().hasShiftDown() ? "§7When on your hand, makes you regen a heart each second" : "§7Press §bSHIFT §7for information";
   }
}
