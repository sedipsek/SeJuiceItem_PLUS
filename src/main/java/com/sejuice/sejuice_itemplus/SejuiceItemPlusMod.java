package com.sejuice.sejuice_itemplus;

import net.fabricmc.api.ModInitializer;

public class SejuiceItemPlusMod implements ModInitializer {
    public static final String MOD_ID = "sejuice_itemplus";

    @Override
    public void onInitialize() {
        ModItems.register();
    }
}
