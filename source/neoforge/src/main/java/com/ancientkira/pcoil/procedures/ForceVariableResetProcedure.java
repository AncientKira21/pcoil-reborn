package com.ancientkira.pcoil.procedures;

import javax.annotation.Nullable;
import com.ancientkira.pcoil.network.PowerCoilsModVariables;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;

@EventBusSubscriber
public class ForceVariableResetProcedure {
   @SubscribeEvent
   public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         _vars.RegenCoil = false;
         _vars.syncPlayerVariables(entity);
         _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         _vars.GravityCoilSoundCooldown = false;
         _vars.syncPlayerVariables(entity);
         _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         _vars.SpeedCoilSoundCooldown = false;
         _vars.syncPlayerVariables(entity);
         _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         _vars.RegenCoilSoundCooldown = false;
         _vars.syncPlayerVariables(entity);
         _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         _vars.FusionCoilSoundCooldown = false;
         _vars.syncPlayerVariables(entity);
         _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         _vars.SnowyGravityCoilSoundCooldown = false;
         _vars.syncPlayerVariables(entity);
         _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
         _vars.DualGravityCoilSoundCooldown = false;
         _vars.syncPlayerVariables(entity);
      }
   }
}
