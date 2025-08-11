package com.sejuice.sejuice_itemplus;

import net.fabricmc.api.ModInitializer;

import com.sejuice.sejuice_itemplus.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class SejuiceItemPlusMod implements ModInitializer {
    public static final String MOD_ID = "sejuice_itemplus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        ModItems.registerModItems(); // static 로딩 + 탭 노출
    }
}