package com.sejuice.sejuice_itemplus.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

/**
 * 포션 효과 없이 섭취 완료 시 직접 HP를 회복시키는 아이템.
 * 먹는 동작/시간은 ConsumableComponent로 설정하고, 실제 회복은 finishUsing에서 처리.
 */
public class HealingOnUseItem extends Item {
    private final float healAmount; // HP 단위 (하트 1칸 = 2.0F)

    public HealingOnUseItem(Settings settings, float healAmount) {
        super(settings);
        this.healAmount = healAmount;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            user.heal(this.healAmount); // 직접 체력 회복
        }
        return super.finishUsing(stack, world, user);
    }
}
