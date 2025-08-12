package com.sejuice.sejuice_itemplus.item;

import com.sejuice.sejuice_itemplus.SejuiceItemPlusMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.UseAction;
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

    // ── 일반 음식 ─────────────────────────────────────────────
    // 라면: 허기 8(=4칸), 포만감 0.6
    private static final FoodComponent FOOD_RAMEN = new FoodComponent.Builder()
            .nutrition(8)
            .saturationModifier(0.6F)
            .build();

    // 참치캔: 허기 5(=2.5칸), 포만감 0.4
    private static final FoodComponent FOOD_TUNA = new FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.4F)
            .build();

    // ── 알약 3종: 먹는 속도 빠르게(≈0.6초), EAT 애니메이션 ─────────────
    private static final float PILL_SECONDS = 0.6F;

    // 파랑 약: 허기 3(=1.5칸). 섭취 완료 시 HP 1하트(=2.0) 직접 회복.
    private static final FoodComponent FOOD_BLUE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.2F)
            .alwaysEdible()
            .build();
    private static final ConsumableComponent CONSUMABLE_BLUE = ConsumableComponents.food()
            .useAction(UseAction.EAT)
            .consumeSeconds(PILL_SECONDS)
            .build();

    // 초록 약: 허기 0. 섭취 완료 시 HP 2.5하트(=5.0) 직접 회복.
    private static final FoodComponent FOOD_GREEN = new FoodComponent.Builder()
            .nutrition(0)
            .saturationModifier(0.0F)
            .alwaysEdible()
            .build();
    private static final ConsumableComponent CONSUMABLE_GREEN = ConsumableComponents.food()
            .useAction(UseAction.EAT)
            .consumeSeconds(PILL_SECONDS)
            .build();

    // 빨강 약: 허기 0. (감염도 시스템은 외부 로직 가정) 먹는 시간만 알약 속도로.
    private static final FoodComponent FOOD_RED = new FoodComponent.Builder()
            .nutrition(0)
            .saturationModifier(0.0F)
            .alwaysEdible()
            .build();
    private static final ConsumableComponent CONSUMABLE_RED = ConsumableComponents.food()
            .useAction(UseAction.EAT)
            .consumeSeconds(PILL_SECONDS)
            .build();

    // ── 아이템 등록 ───────────────────────────────────────────
    // 먹는 아이템(라면/참치)
    public static final Item RAMEN    = register("ramen",    Item::new, new Item.Settings().food(FOOD_RAMEN));
    public static final Item TUNA_CAN = register("tuna_can", Item::new, new Item.Settings().food(FOOD_TUNA));

    // 일반 아이템
    public static final Item MEDICINE_BOTTLE = register("medicine_bottle", Item::new, new Item.Settings());
    public static final Item SYRINGE         = register("syringe",         Item::new, new Item.Settings());
    public static final Item BANDAGE         = register("bandage",         Item::new, new Item.Settings());
    public static final Item KITCHEN_KNIFE   = register("kitchen_knife",   Item::new, new Item.Settings());
    public static final Item FIRST_AID_KIT   = register("first_aid_kit",   Item::new, new Item.Settings());

    // 알약 3종 (파랑/초록은 직접 HP 회복용 커스텀 아이템 사용)
    public static final Item BLUE_MEDICINE = register("blue_medicine",
            s -> new HealingOnUseItem(s, 2.0F), // 1하트
            new Item.Settings()
                    .food(FOOD_BLUE)
                    .component(DataComponentTypes.CONSUMABLE, CONSUMABLE_BLUE)
                    .component(DataComponentTypes.LORE, new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.blue_medicine.tooltip.1").formatted(Formatting.AQUA),
                            Text.translatable("item.sejuice_itemplus.blue_medicine.tooltip.2").formatted(Formatting.GRAY, Formatting.ITALIC)
                    )))
    );

    public static final Item GREEN_MEDICINE = register("green_medicine",
            s -> new HealingOnUseItem(s, 5.0F), // 2.5하트
            new Item.Settings()
                    .food(FOOD_GREEN)
                    .component(DataComponentTypes.CONSUMABLE, CONSUMABLE_GREEN)
                    .component(DataComponentTypes.LORE, new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.green_medicine.tooltip.1").formatted(Formatting.GREEN),
                            Text.translatable("item.sejuice_itemplus.green_medicine.tooltip.2").formatted(Formatting.GRAY, Formatting.ITALIC)
                    )))
    );

    public static final Item RED_MEDICINE = register("red_medicine",
            Item::new,
            new Item.Settings()
                    .food(FOOD_RED)
                    .component(DataComponentTypes.CONSUMABLE, CONSUMABLE_RED)
                    .component(DataComponentTypes.LORE, new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.red_medicine.tooltip.1").formatted(Formatting.RED),
                            Text.translatable("item.sejuice_itemplus.red_medicine.tooltip.2").formatted(Formatting.GRAY, Formatting.ITALIC)
                    )))
    );

    // 마약성 진통제(툴팁만, 별도 효과/치유는 이후 원하면 동일 패턴으로 확장)
    public static final Item NARCOTIC_ANALGESIC = register("narcotic_analgesic", Item::new,
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(List.of(
                            Text.translatable("item.sejuice_itemplus.narcotic_analgesic.tooltip.1").formatted(Formatting.YELLOW),
                            Text.translatable("item.sejuice_itemplus.narcotic_analgesic.tooltip.2").formatted(Formatting.DARK_RED, Formatting.ITALIC)
                    )))
    );

    // ── 등록 유틸 (1.21.2+) ──────────────────────────────────
    private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Identifier id = Identifier.of(SejuiceItemPlusMod.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        SejuiceItemPlusMod.LOGGER.info("Registering Mod Items for " + SejuiceItemPlusMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAMEN);
            entries.add(TUNA_CAN);
            entries.add(MEDICINE_BOTTLE);
            entries.add(SYRINGE);
            entries.add(BANDAGE);
            entries.add(KITCHEN_KNIFE);
            entries.add(FIRST_AID_KIT);
            entries.add(BLUE_MEDICINE);
            entries.add(GREEN_MEDICINE);
            entries.add(RED_MEDICINE);
            entries.add(NARCOTIC_ANALGESIC);
        });
    }
}
