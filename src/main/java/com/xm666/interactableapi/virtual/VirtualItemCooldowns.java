package com.xm666.interactableapi.virtual;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;

public class VirtualItemCooldowns extends ItemCooldowns {
    private final ItemCooldowns itemCooldowns;

    public VirtualItemCooldowns(ItemCooldowns itemCooldowns) {
        this.itemCooldowns = itemCooldowns;
    }

    @Override
    public boolean isOnCooldown(Item item) {
        return itemCooldowns.isOnCooldown(item);
    }

    @Override
    public float getCooldownPercent(Item item, float partialTicks) {
        return itemCooldowns.getCooldownPercent(item, partialTicks);
    }

    @Override
    public void tick() {
    }

    @Override
    public void addCooldown(Item item, int ticks) {
    }

    @Override
    public void removeCooldown(Item item) {
    }

    @Override
    protected void onCooldownStarted(Item item, int ticks) {
    }

    @Override
    protected void onCooldownEnded(Item item) {
    }
}
