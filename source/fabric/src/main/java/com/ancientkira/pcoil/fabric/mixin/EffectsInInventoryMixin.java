package com.ancientkira.pcoil.fabric.mixin;
import java.util.Collection;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(value=EffectsInInventory.class,remap=false)
public abstract class EffectsInInventoryMixin {
 @Redirect(method="extractRenderState",at=@At(value="INVOKE",target="Lnet/minecraft/client/player/LocalPlayer;getActiveEffects()Ljava/util/Collection;"))
 private Collection<MobEffectInstance> pcoil$hideInternalMarkers(LocalPlayer player) {
  return player.getActiveEffects().stream().filter(effect->
   !BuiltInRegistries.MOB_EFFECT.getKey(effect.getEffect().value()).getNamespace().equals("pcoil")).toList();
 }
}
