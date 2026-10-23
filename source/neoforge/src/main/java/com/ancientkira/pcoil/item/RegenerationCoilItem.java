package com.ancientkira.pcoil.item;

import java.util.List;
import com.ancientkira.pcoil.procedures.RegenerationCoilSpecialInformationProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Item.TooltipContext;
import net.neoforged.api.distmarker.Dist;

public class RegenerationCoilItem extends Item {
   public RegenerationCoilItem(Properties properties) {
      super(properties.stacksTo(1).rarity(Rarity.COMMON));
   }

   public void appendHoverText(ItemStack itemstack, TooltipContext context, net.minecraft.world.item.component.TooltipDisplay display, java.util.function.Consumer<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, context, display, list, flag);

      list.accept(Component.literal(RegenerationCoilSpecialInformationProcedure.execute()));
   }
}
