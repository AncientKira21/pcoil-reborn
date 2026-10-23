package com.ancientkira.pcoil.init;

import com.ancientkira.pcoil.configuration.PowerCoilsConfigConfiguration;
import com.ancientkira.pcoil.compat.PowerCoilsBuildTarget;
import com.ancientkira.pcoil.configuration.PowerCoilsServerConfigConfiguration;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;

@EventBusSubscriber(modid = "pcoil")
public class PowerCoilsModConfigs {
   @SubscribeEvent
   public static void register(FMLConstructModEvent event) {
      event.enqueueWork(
         () -> {
            ((ModContainer)ModList.get().getModContainerById("pcoil").get())
               .registerConfig(Type.CLIENT, PowerCoilsConfigConfiguration.SPEC, "PowerCoils-Client.toml");
            ((ModContainer)ModList.get().getModContainerById("pcoil").get())
               .registerConfig(PowerCoilsBuildTarget.SERVER_CONFIG_TYPE, PowerCoilsServerConfigConfiguration.SPEC, "PowerCoils-Server.toml");
         }
      );
   }
}
