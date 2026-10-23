package com.ancientkira.pcoil.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber
public class PowerCoilsModTabs {
   public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "pcoil");

   @SubscribeEvent
   public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
      if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
         tabData.accept((ItemLike)PowerCoilsModItems.GRAVITY_COIL.get());
         tabData.accept((ItemLike)PowerCoilsModItems.SPEED_COIL.get());
         tabData.accept((ItemLike)PowerCoilsModItems.REGENERATION_COIL.get());
         tabData.accept((ItemLike)PowerCoilsModItems.FUSION_COIL.get());
         tabData.accept((ItemLike)PowerCoilsModItems.SNOWY_GRAVITY_COIL.get());
         tabData.accept((ItemLike)PowerCoilsModItems.DUAL_GRAVITY_COIL_BOOTS.get());
      }
   }
}
