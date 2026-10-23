package com.ancientkira.pcoil.init;

import com.ancientkira.pcoil.item.DualGravityCoilItem;
import com.ancientkira.pcoil.item.FusionCoilItem;
import com.ancientkira.pcoil.item.GravityCoilItem;
import com.ancientkira.pcoil.item.RegenerationCoilItem;
import com.ancientkira.pcoil.item.SnowyGravityCoilItem;
import com.ancientkira.pcoil.item.SpeedCoilItem;
import com.ancientkira.pcoil.procedures.GravityCoilPropertyValueProviderProcedure;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Items;

public class PowerCoilsModItems {
   public static final Items REGISTRY = DeferredRegister.createItems("pcoil");
   public static final DeferredItem<Item> GRAVITY_COIL = REGISTRY.registerItem("gravity_coil", GravityCoilItem::new);
   public static final DeferredItem<Item> SPEED_COIL = REGISTRY.registerItem("speed_coil", SpeedCoilItem::new);
   public static final DeferredItem<Item> REGENERATION_COIL = REGISTRY.registerItem("regeneration_coil", RegenerationCoilItem::new);
   public static final DeferredItem<Item> FUSION_COIL = REGISTRY.registerItem("fusion_coil", FusionCoilItem::new);
   public static final DeferredItem<Item> SNOWY_GRAVITY_COIL = REGISTRY.registerItem("snowy_gravity_coil", SnowyGravityCoilItem::new);
   public static final DeferredItem<Item> DUAL_GRAVITY_COIL_BOOTS = REGISTRY.registerItem("dual_gravity_coil_boots", DualGravityCoilItem.Boots::new);

}
