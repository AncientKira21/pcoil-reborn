package com.ancientkira.pcoil.shared.init;
import java.util.function.*;
import com.ancientkira.pcoil.shared.item.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.Item;
public final class PowerCoilsModItems {
 public record ItemRef(Item item) implements Supplier<Item> { public Item get() { return item; } }
 private static ItemRef register(String name,Function<Item.Properties,Item> factory) {
  Identifier id=Identifier.fromNamespaceAndPath("pcoil",name);
  Item item=factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,id)));
  return new ItemRef(Registry.register(BuiltInRegistries.ITEM,id,item));
 }
 public static final ItemRef GRAVITY_COIL=register("gravity_coil",GravityCoilItem::new);
 public static final ItemRef SPEED_COIL=register("speed_coil",SpeedCoilItem::new);
 public static final ItemRef REGENERATION_COIL=register("regeneration_coil",RegenerationCoilItem::new);
 public static final ItemRef FUSION_COIL=register("fusion_coil",FusionCoilItem::new);
 public static final ItemRef SNOWY_GRAVITY_COIL=register("snowy_gravity_coil",SnowyGravityCoilItem::new);
 public static final ItemRef DUAL_GRAVITY_COIL_BOOTS=register("dual_gravity_coil_boots",DualGravityCoilItem.Boots::new);
 public static void bootstrap() {}
}
