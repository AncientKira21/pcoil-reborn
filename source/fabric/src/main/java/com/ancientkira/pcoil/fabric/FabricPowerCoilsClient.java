package com.ancientkira.pcoil.fabric;
import java.util.*;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import com.ancientkira.pcoil.shared.PowerCoilsMod;
import com.ancientkira.pcoil.shared.client.model.Modeldual_gravity_coil;
import com.ancientkira.pcoil.shared.configuration.*;
import com.ancientkira.pcoil.shared.init.PowerCoilsModItems;
import com.ancientkira.pcoil.shared.network.*;
import com.ancientkira.pcoil.fabric.mixin.RangePropertiesAccessor;
public class FabricPowerCoilsClient implements ClientModInitializer {
 private static boolean initialized;
 @Override public void onInitializeClient() { initialize(); }
 public static synchronized void initialize() {
  if (initialized) return;
  initialized=true;
  RangePropertiesAccessor.pcoil$idMapper().put(Identifier.parse("pcoil:alternate_model"),AlternateModelProperty.CODEC);
  ClientPlayNetworking.registerGlobalReceiver(CoilStatePayload.TYPE,(data,context)->context.client().execute(()->{
   if (context.client().player!=null) PowerCoilsModVariables.get(context.client().player).setBits(data.flags());
  }));
  ClientPlayNetworking.registerGlobalReceiver(CoilConfigPayload.TYPE,(data,context)->context.client().execute(()->SharedConfig.applyServerJson(data.json())));
  ClientTickEvents.END_CLIENT_TICK.register(client->{ if (client.player!=null && !client.isPaused()) PowerCoilsMod.tick(client.player); });
  ClientPlayConnectionEvents.DISCONNECT.register((handler,client)->{
   PowerCoilsModVariables.clear();
   SharedConfig.load(net.fabricmc.loader.api.FabricLoader.getInstance().getConfigDir());
  });
  ModelLayerRegistry.registerModelLayer(Modeldual_gravity_coil.LAYER_LOCATION,Modeldual_gravity_coil::createBodyLayer);
  ArmorRenderer.register((ArmorRenderer.Factory) context->{
   var coils=new Modeldual_gravity_coil(context.bakeLayer(Modeldual_gravity_coil.LAYER_LOCATION));
   var model=new HumanoidModel<HumanoidRenderState>(new ModelPart(Collections.emptyList(),Map.of(
    "left_leg",coils.LeftBoot,"right_leg",coils.RightBoot,"head",empty(),"hat",empty(),
    "body",empty(),"left_arm",empty(),"right_arm",empty())));
   return (poses,collector,stack,state,slot,light,original)->{
    // Fabric copies the original armor pose while using the recovered coil geometry.
    ArmorBridge.submit(original,model,state,collector,poses,
     RenderTypes.armorCutoutNoCull(Identifier.parse("pcoil:textures/entities/dual_gravity_coil_texture.png")),light);
   };
  },PowerCoilsModItems.DUAL_GRAVITY_COIL_BOOTS.get());
 }
 private static ModelPart empty() { return new ModelPart(Collections.emptyList(),Collections.emptyMap()); }
 public record AlternateModelProperty() implements RangeSelectItemModelProperty {
  public static final MapCodec<AlternateModelProperty> CODEC=MapCodec.unit(new AlternateModelProperty());
  @Override public float get(ItemStack stack,ClientLevel level,ItemOwner owner,int seed) { return PowerCoilsConfigConfiguration.ALT_COIL_MODEL.get()?1:0; }
  @Override public MapCodec<AlternateModelProperty> type() { return CODEC; }
 }
}
