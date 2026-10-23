package com.ancientkira.pcoil.init;

import com.ancientkira.pcoil.client.model.Modeldual_gravity_coil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

@EventBusSubscriber(value = Dist.CLIENT)
public class PowerCoilsModModels {
   @SubscribeEvent
   public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
      event.registerLayerDefinition(Modeldual_gravity_coil.LAYER_LOCATION, Modeldual_gravity_coil::createBodyLayer);
   }
}
