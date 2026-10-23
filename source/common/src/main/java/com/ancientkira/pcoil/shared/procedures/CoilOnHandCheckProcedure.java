package com.ancientkira.pcoil.shared.procedures;
import com.ancientkira.pcoil.shared.init.PowerCoilsModItems;
import com.ancientkira.pcoil.shared.network.PowerCoilsModVariables;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
public class CoilOnHandCheckProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
               .is(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ITEM,Identifier.parse("pcoil:pcoil")))
            && !(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
               .is(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ITEM,Identifier.parse("pcoil:pcoil")))) {
            PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
            _vars.GravityCoil = false;
            _vars.syncPlayerVariables(entity);
            _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
            _vars.FusionCoil = false;
            _vars.syncPlayerVariables(entity);
            _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
            _vars.SnowyGravityCoil = false;
            _vars.syncPlayerVariables(entity);
         } else {
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.GRAVITY_COIL.get()
               && (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.GRAVITY_COIL.get()) {
               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
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
               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
               _vars.FusionCoil = false;
               _vars.syncPlayerVariables(entity);
            } else {
               FusionCoilItemInHandTickProcedure.execute(world, x, y, z, entity);
            }

            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() != PowerCoilsModItems.SNOWY_GRAVITY_COIL.get()
               && (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem()
                  != PowerCoilsModItems.SNOWY_GRAVITY_COIL.get()) {
               PowerCoilsModVariables.PlayerVariables _vars = (PowerCoilsModVariables.PlayerVariables)PowerCoilsModVariables.get(entity);
               _vars.SnowyGravityCoil = false;
               _vars.syncPlayerVariables(entity);
            } else {
               SnowyGravityCoilItemInHandTickProcedure.execute(world, x, y, z, entity);
            }
         }
      }
   }
}
