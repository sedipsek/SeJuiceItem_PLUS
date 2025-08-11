package com.sejuice.sejuice_itemplus;

import com.sejuice.sejuice_itemplus.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SejuiceItemPlusMod implements ModInitializer {
    public static final String MOD_ID = "sejuice_itemplus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems(); // 아이템 Group(탭) 노출 등 트리거
    }

    // (선택) id 헬퍼
    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
