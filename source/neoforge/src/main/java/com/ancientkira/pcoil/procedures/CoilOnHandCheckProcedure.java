package com.ancientkira.pcoil.procedures;

import javax.annotation.Nullable;
import com.ancientkira.pcoil.init.PowerCoilsModItems;
import com.ancientkira.pcoil.network.PowerCoilsModVariables;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Post;

@EventBusSubscriber
public class CoilOnHandCheckProcedure {
   @SubscribeEvent
   public static void onPlayerTick(Post event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
               .is(ItemTags.create(Identifier.parse("pcoil:pcoil")))
            && !(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
               .is(ItemTags.create(Identifier.parse("pcoil:pcoil")))) {
            PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
            _vars.GravityCoil = false;
            _vars.syncPlayerVariables(entity);
            _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
            _vars.FusionCoil = false;
            _vars.syncPlayerVariables(entity);
            _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
            _vars.SnowyGravityCoil = false;
            _vars.syncPlayerVariables(entity);
         } else {
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.GRAVITY_COIL.get()
               && (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.GRAVITY_COIL.get()) {
               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
               _vars.GravityCoil = false;
               _vars.syncPlayerVariables(entity);
            } else {
               GravityCoilItemInHandTickProcedure.execute(world, x, y, z, entity);
            }

            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == PowerCoilsModItems.SPEED_COIL.get()
               || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == PowerCoilsModItems.SPEED_COIL.get()) {
               SpeedCoilItemInHandTickProcedure.execute(world, x, y, z, entity);
            }

            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == PowerCoilsModItems.REGENERATION_COIL.get()
               || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == PowerCoilsModItems.REGENERATION_COIL.get()
               )
             {
               RegenerationCoilItemInHandTickProcedure.execute(world, x, y, z, entity);
            }

            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.FUSION_COIL.get()
               && (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.FUSION_COIL.get()) {
               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
               _vars.FusionCoil = false;
               _vars.syncPlayerVariables(entity);
            } else {
               FusionCoilItemInHandTickProcedure.execute(world, x, y, z, entity);
            }

            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.SNOWY_GRAVITY_COIL.get()
               && (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem()
                  != PowerCoilsModItems.SNOWY_GRAVITY_COIL.get()) {
               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)entity.getData(PowerCoilsModVariables.PLAYER_VARIABLES);
               _vars.SnowyGravityCoil = false;
               _vars.syncPlayerVariables(entity);
            } else {
               SnowyGravityCoilItemInHandTickProcedure.execute(world, x, y, z, entity);
            }
         }
      }
   }
}
