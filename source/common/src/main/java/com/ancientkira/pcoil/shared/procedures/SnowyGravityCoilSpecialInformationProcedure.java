package com.ancientkira.pcoil.shared.procedures;

import net.minecraft.client.gui.screens.Screen;

public class SnowyGravityCoilSpecialInformationProcedure {
   public static String execute() {
      return net.minecraft.client.Minecraft.getInstance().hasShiftDown()
         ? "§7When on your hand, makes you jump higher, fall slower and leave a snowy trail as you move"
         : "§7Press §bSHIFT §7for information";
   }
}
