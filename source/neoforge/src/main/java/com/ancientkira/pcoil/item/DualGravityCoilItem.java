package com.ancientkira.pcoil.item;

import java.util.Map;
import java.util.function.Consumer;
import com.ancientkira.pcoil.procedures.DualGravityCoilSpecialInformationProcedure;
import com.ancientkira.pcoil.procedures.DualGravityCoilTickEventProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

public abstract class DualGravityCoilItem extends Item {
    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(
        1000, Map.of(ArmorType.BOOTS, 2), 0, net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY),
        0.0F, 0.0F, ItemTags.create(Identifier.parse("pcoil:repairs_dual_gravity_coil")),
        ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.parse("pcoil:dualgravitycoil")));

    protected DualGravityCoilItem(Properties properties) {
        // Keep the original zero enchantability without constructing an invalid
        // Enchantable(0) component through Properties.humanoidArmor.
        super(properties.durability(ArmorType.BOOTS.getDurability(ARMOR_MATERIAL.durability()))
            .attributes(ARMOR_MATERIAL.createAttributes(ArmorType.BOOTS))
            .component(net.minecraft.core.component.DataComponents.EQUIPPABLE,
                net.minecraft.world.item.equipment.Equippable.builder(EquipmentSlot.FEET)
                    .setEquipSound(ARMOR_MATERIAL.equipSound()).setAsset(ARMOR_MATERIAL.assetId()).build())
            .repairable(ARMOR_MATERIAL.repairIngredient()));
    }

    public static class Boots extends DualGravityCoilItem {
        public Boots(Properties properties) { super(properties); }

        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
                                    Consumer<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, context, display, tooltip, flag);
            tooltip.accept(Component.literal(DualGravityCoilSpecialInformationProcedure.execute()));
        }

        @Override
        public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
            super.inventoryTick(stack, level, entity, slot);
            if (slot == EquipmentSlot.FEET) {
                DualGravityCoilTickEventProcedure.execute(level, entity.getX(), entity.getY(), entity.getZ(), entity, stack);
            }
        }
    }
}
