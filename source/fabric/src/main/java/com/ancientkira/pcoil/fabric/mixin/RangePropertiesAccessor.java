package com.ancientkira.pcoil.fabric.mixin;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(value=RangeSelectItemModelProperties.class,remap=false)
public interface RangePropertiesAccessor {
 @Accessor("ID_MAPPER")
 static ExtraCodecs.LateBoundIdMapper<Identifier,MapCodec<? extends RangeSelectItemModelProperty>> pcoil$idMapper() {
  throw new AssertionError("Accessor mixin was not applied");
 }
}
