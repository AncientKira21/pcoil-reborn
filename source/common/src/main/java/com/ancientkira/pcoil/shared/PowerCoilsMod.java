package com.ancientkira.pcoil.shared;
import java.util.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import com.ancientkira.pcoil.shared.init.*;
import com.ancientkira.pcoil.shared.procedures.*;
import com.ancientkira.pcoil.shared.network.PowerCoilsModVariables;
public final class PowerCoilsMod {
 private static final List<Work> QUEUE=new ArrayList<>();
 private static final ThreadLocal<Boolean> SERVER=ThreadLocal.withInitial(()->false);
 public static void initialize() {
  for (String sound:List.of("gravity_coil","speed_coil","fusion_coil","regen_coil")) {
   Identifier id=Identifier.parse("pcoil:"+sound);
   Registry.register(BuiltInRegistries.SOUND_EVENT,id,SoundEvent.createVariableRangeEvent(id));
  }
  PowerCoilsModMobEffects.bootstrap();
  PowerCoilsModItems.bootstrap();
 }
 public static void tick(Player player) {
  SERVER.set(!player.level().isClientSide());
  try {
   CoilOnHandCheckProcedure.execute(player.level(),player.getX(),player.getY(),player.getZ(),player);
   var boots=player.getItemBySlot(EquipmentSlot.FEET);
   if (boots.is(PowerCoilsModItems.DUAL_GRAVITY_COIL_BOOTS.get()))
    DualGravityCoilTickEventProcedure.execute(player.level(),player.getX(),player.getY(),player.getZ(),player,boots);
  } finally { SERVER.remove(); }
 }
 public static void queueServerWork(int ticks,Runnable action) {
  if (SERVER.get()) QUEUE.add(new Work(ticks,action));
 }
 public static void endServerTick() {
  var ready=new ArrayList<Work>();
  for (Work work:QUEUE) if (--work.ticks<=0) ready.add(work);
  QUEUE.removeAll(ready);
  ready.forEach(work->work.action.run());
 }
 public static void stop() { QUEUE.clear(); PowerCoilsModVariables.clear(); }
 private static final class Work {
  int ticks; final Runnable action;
  Work(int ticks,Runnable action) { this.ticks=ticks; this.action=action; }
 }
}
