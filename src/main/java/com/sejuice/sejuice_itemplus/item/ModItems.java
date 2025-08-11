package com.sejuice.sejuice_itemplus.item;

import com.sejuice.sejuice_itemplus.SejuiceItemPlusMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public final class ModItems {
    // 모두 "그냥 아이템" (원하면 maxCount 등은 자유롭게 추가)
    public static final Item RAMEN           = register("ramen",           Item::new, new Item.Settings());
    public static final Item TUNA_CAN        = register("tuna_can",        Item::new, new Item.Settings());
    public static final Item MEDICINE_BOTTLE = register("medicine_bottle", Item::new, new Item.Settings());
    public static final Item SYRINGE         = register("syringe",         Item::new, new Item.Settings());
    public static final Item BANDAGE         = register("bandage",         Item::new, new Item.Settings());
    public static final Item KITCHEN_KNIFE   = register("kitchen_knife",   Item::new, new Item.Settings());
    public static final Item FIRST_AID_KIT   = register("first_aid_kit",   Item::new, new Item.Settings());
    public static final Item BLUE_MEDICINE   = register("blue_medicine",   Item::new, new Item.Settings());
    public static final Item RED_MEDICINE   = register("red_medicine",   Item::new, new Item.Settings());
    public static final Item GREEN_MEDICINE   = register("green_medicine",   Item::new, new Item.Settings());

    // 1.21.2+ 필수 패턴: RegistryKey → settings.registryKey(key) → register
    private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Identifier id = Identifier.of(SejuiceItemPlusMod.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        SejuiceItemPlusMod.LOGGER.info("Registering Mod Items for " + SejuiceItemPlusMod.MOD_ID);

        // 재료 탭(INGREDIENTS)에만 노출
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAMEN);
            entries.add(TUNA_CAN);
            entries.add(MEDICINE_BOTTLE);
            entries.add(SYRINGE);
            entries.add(BANDAGE);
            entries.add(KITCHEN_KNIFE);
            entries.add(FIRST_AID_KIT);
            entries.add(BLUE_MEDICINE);
            entries.add(RED_MEDICINE);
            entries.add(GREEN_MEDICINE);
        });
    }
}
