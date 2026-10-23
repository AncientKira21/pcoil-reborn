package com.ancientkira.pcoil.client;

import java.util.Collections;
import java.util.Map;
import com.mojang.serialization.MapCodec;
import com.ancientkira.pcoil.client.model.Modeldual_gravity_coil;
import com.ancientkira.pcoil.configuration.PowerCoilsConfigConfiguration;
import com.ancientkira.pcoil.init.PowerCoilsModItems;
import com.ancientkira.pcoil.init.PowerCoilsModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions;

@EventBusSubscriber(modid = "pcoil", value = Dist.CLIENT)
public class PowerCoilsClient {
    public record AlternateModelProperty() implements RangeSelectItemModelProperty {
        public static final MapCodec<AlternateModelProperty> CODEC = MapCodec.unit(new AlternateModelProperty());
        @Override public float get(ItemStack stack, ClientLevel level, ItemOwner owner, int seed) {
            return PowerCoilsConfigConfiguration.ALT_COIL_MODEL.get() ? 1.0F : 0.0F;
        }
        @Override public MapCodec<AlternateModelProperty> type() { return CODEC; }
    }

    @SubscribeEvent
    public static void registerProperties(RegisterRangeSelectItemModelPropertyEvent event) {
        event.register(Identifier.parse("pcoil:alternate_model"), AlternateModelProperty.CODEC);
    }

    @SubscribeEvent
    public static void registerExtensions(RegisterClientExtensionsEvent event) {
        var hidden = new IClientMobEffectExtensions() {
            @Override public boolean isVisibleInInventory(MobEffectInstance instance) { return false; }
            @Override public boolean isVisibleInGui(MobEffectInstance instance) { return false; }
        };
        event.registerMobEffect(hidden, PowerCoilsModMobEffects.GRAVITY_COIL_EFFECT.get(),
            PowerCoilsModMobEffects.SPEED_COIL_EFFECT.get(), PowerCoilsModMobEffects.REGEN_COIL_EFFECT.get(),
            PowerCoilsModMobEffects.FUSION_COIL_EFFECT.get(), PowerCoilsModMobEffects.SNOWY_GRAVITY_COIL_EFFECT.get(),
            PowerCoilsModMobEffects.DUAL_GRAVITY_COIL_EFFECT.get());
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel<HumanoidRenderState> model;
            @Override
            public Model getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType layerType, Model original) {
                if (model == null) {
                    var coils = new Modeldual_gravity_coil(Minecraft.getInstance().getEntityModels().bakeLayer(Modeldual_gravity_coil.LAYER_LOCATION));
                    model = new HumanoidModel<>(new ModelPart(Collections.emptyList(), Map.of(
                        "left_leg", coils.LeftBoot, "right_leg", coils.RightBoot,
                        "head", empty(), "hat", empty(), "body", empty(), "right_arm", empty(), "left_arm", empty())));
                }
                return model;
            }
            @Override
            public Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier fallback) {
                return Identifier.parse("pcoil:textures/entities/dual_gravity_coil_texture.png");
            }
        }, PowerCoilsModItems.DUAL_GRAVITY_COIL_BOOTS.get());
    }

    private static ModelPart empty() { return new ModelPart(Collections.emptyList(), Collections.emptyMap()); }
}
