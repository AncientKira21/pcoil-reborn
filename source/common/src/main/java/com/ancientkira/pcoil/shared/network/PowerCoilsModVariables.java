package com.ancientkira.pcoil.shared.network;
import java.util.*;
import java.util.function.Consumer;
import net.minecraft.world.entity.Entity;
public final class PowerCoilsModVariables {
 private static final Map<Entity,PlayerVariables> STATES=Collections.synchronizedMap(new WeakHashMap<>());
 public static Consumer<Entity> sync = entity -> {};
 public static PlayerVariables get(Entity entity) { synchronized(STATES) { return STATES.computeIfAbsent(entity,e->new PlayerVariables()); } }
 public static void remove(Entity entity) { STATES.remove(entity); }
 public static void clear() { STATES.clear(); }
 public static final class PlayerVariables {
  public boolean RegenCoil;
  public boolean GravityCoil;
  public boolean FusionCoil;
  public boolean SnowyGravityCoil;
  public boolean GravityCoilSoundCooldown;
  public boolean SpeedCoilSoundCooldown;
  public boolean RegenCoilSoundCooldown;
  public boolean FusionCoilSoundCooldown;
  public boolean SnowyGravityCoilSoundCooldown;
  public boolean DualGravityCoilSoundCooldown;
  public void syncPlayerVariables(Entity entity) { if (!entity.level().isClientSide()) sync.accept(entity); }
  public int bits() {
   int bits=0;
   if (RegenCoil) bits|=1<<0;
   if (GravityCoil) bits|=1<<1;
   if (FusionCoil) bits|=1<<2;
   if (SnowyGravityCoil) bits|=1<<3;
   if (GravityCoilSoundCooldown) bits|=1<<4;
   if (SpeedCoilSoundCooldown) bits|=1<<5;
   if (RegenCoilSoundCooldown) bits|=1<<6;
   if (FusionCoilSoundCooldown) bits|=1<<7;
   if (SnowyGravityCoilSoundCooldown) bits|=1<<8;
   if (DualGravityCoilSoundCooldown) bits|=1<<9;
   return bits;
  }
  public void setBits(int bits) {
   RegenCoil=(bits&(1<<0))!=0;
   GravityCoil=(bits&(1<<1))!=0;
   FusionCoil=(bits&(1<<2))!=0;
   SnowyGravityCoil=(bits&(1<<3))!=0;
   GravityCoilSoundCooldown=(bits&(1<<4))!=0;
   SpeedCoilSoundCooldown=(bits&(1<<5))!=0;
   RegenCoilSoundCooldown=(bits&(1<<6))!=0;
   FusionCoilSoundCooldown=(bits&(1<<7))!=0;
   SnowyGravityCoilSoundCooldown=(bits&(1<<8))!=0;
   DualGravityCoilSoundCooldown=(bits&(1<<9))!=0;
  }
 }
}
