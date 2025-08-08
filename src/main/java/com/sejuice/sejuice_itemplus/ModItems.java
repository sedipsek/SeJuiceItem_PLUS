package com.sejuice.sejuice_itemplus;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item BANDAGE; // 선언만

    public static void register() {
        BANDAGE = Registry.register(
                Registries.ITEM,
                Identifier.of(SejuiceItemPlusMod.MOD_ID, "bandage"),
                new Item(new Item.Settings()
                        .maxCount(16)
                        .translationKey("item.sejuice_itemplus.bandage")
                )
        );
    }
}
