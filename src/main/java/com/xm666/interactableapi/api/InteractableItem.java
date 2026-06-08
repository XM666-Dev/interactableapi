package com.xm666.interactableapi.api;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public interface InteractableItem {
    InteractionResult interactableapi$tryUseOn(UseOnContext context);

    InteractionResultHolder<ItemStack> interactableapi$tryUse(Level level, Player player, InteractionHand usedHand);

    InteractionResult interactableapi$tryInteractLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand);
}
