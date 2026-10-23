package com.ancientkira.pcoil.quilt;
import net.fabricmc.api.ModInitializer;
import com.ancientkira.pcoil.fabric.FabricPowerCoils;
/** Quilt Loader invokes this through its Fabric entrypoint compatibility. */
public final class QuiltPowerCoils implements ModInitializer {
 @Override public void onInitialize() { FabricPowerCoils.initialize(); }
}
