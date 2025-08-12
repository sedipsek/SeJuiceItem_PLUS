package com.sejuice.sejuice_itemplus.item;

import com.sejuice.sejuice_itemplus.SejuiceItemPlusMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;      // ★ 1.21.x 경로
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Function;

public final class ModItems {
    // ── Food 설정 (라면=4칸=8, 참치=2.5칸=5) ───────────────────────────────
    private static final FoodComponent FOOD_RAMEN = new FoodComponent.Builder()
            .nutrition(8)            // 4칸
            .saturationModifier(0.6F)
            .build();

    private static final FoodComponent FOOD_TUNA = new FoodComponent.Builder()
            .nutrition(5)            // 2.5칸
            .saturationModifier(0.4F)
            .build();

    // ── 아이템 등록 ─────────────────────────────────────────────────────
    // 먹는 아이템
    public static final Item RAMEN    = register("ramen",    Item::new, new Item.Settings().food(FOOD_RAMEN));
    public static final Item TUNA_CAN = register("tuna_can", Item::new, new Item.Settings().food(FOOD_TUNA));

    // 일반 아이템
    public static final Item MEDICINE_BOTTLE = register("medicine_bottle", Item::new, new Item.Settings());
    public static final Item SYRINGE         = register("syringe",         Item::new, new Item.Settings());
    public static final Item BANDAGE         = register("bandage",         Item::new, new Item.Settings());
    public static final Item KITCHEN_KNIFE   = register("kitchen_knife",   Item::new, new Item.Settings());
    public static final Item FIRST_AID_KIT   = register("first_aid_kit",   Item::new, new Item.Settings());

    // [color]_medicine 3종: 기본 로어(툴팁)
    public static final Item BLUE_MEDICINE = register("blue_medicine", Item::new,
            new Item.Settings().component(
                    DataComponentTypes.LORE,
                    new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.blue_medicine.tooltip.1").formatted(Formatting.AQUA),
                            Text.translatable("item.sejuice_itemplus.blue_medicine.tooltip.2").formatted(Formatting.GRAY, Formatting.ITALIC)
                    ))
            )
    );

    public static final Item RED_MEDICINE = register("red_medicine", Item::new,
            new Item.Settings().component(
                    DataComponentTypes.LORE,
                    new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.red_medicine.tooltip.1").formatted(Formatting.RED),
                            Text.translatable("item.sejuice_itemplus.red_medicine.tooltip.2").formatted(Formatting.GRAY, Formatting.ITALIC)
                    ))
            )
    );

    public static final Item GREEN_MEDICINE = register("green_medicine", Item::new,
            new Item.Settings().component(
                    DataComponentTypes.LORE,
                    new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.green_medicine.tooltip.1").formatted(Formatting.GREEN),
                            Text.translatable("item.sejuice_itemplus.green_medicine.tooltip.2").formatted(Formatting.GRAY, Formatting.ITALIC)
                    ))
            )
    );

    // 마약성 진통제 (설명 2줄)
    public static final Item NARCOTIC_ANALGESIC = register("narcotic_analgesic", Item::new,
            new Item.Settings().component(
                    DataComponentTypes.LORE,
                    new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.narcotic_analgesic.tooltip.1").formatted(Formatting.YELLOW),
                            Text.translatable("item.sejuice_itemplus.narcotic_analgesic.tooltip.2").formatted(Formatting.DARK_RED, Formatting.ITALIC)
                    ))
            )
    );

    // 1.21.2+ 필수: RegistryKey -> settings.registryKey(key) -> Registry.register
    private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Identifier id = Identifier.of(SejuiceItemPlusMod.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        SejuiceItemPlusMod.LOGGER.info("Registering Mod Items for " + SejuiceItemPlusMod.MOD_ID);

        // 전부 재료(INGREDIENTS) 탭에만 노출
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
            entries.add(NARCOTIC_ANALGESIC);
        });
    }
}
