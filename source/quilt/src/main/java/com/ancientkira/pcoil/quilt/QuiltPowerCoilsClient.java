package com.ancientkira.pcoil.quilt;
import net.fabricmc.api.ClientModInitializer;
import com.ancientkira.pcoil.fabric.FabricPowerCoilsClient;
public final class QuiltPowerCoilsClient implements ClientModInitializer {
 @Override public void onInitializeClient() { FabricPowerCoilsClient.initialize(); }
}
