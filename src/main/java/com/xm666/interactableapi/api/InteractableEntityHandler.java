package com.xm666.interactableapi.api;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class InteractableEntityHandler {
    public static InteractionResult tryInteractAt(Entity entity, Player player, Vec3 vec, InteractionHand hand) {
        if (entity instanceof InteractableEntity interactableEntity)
            return interactableEntity.interactableapi$tryInteractAt(player, vec, hand);

        if (InteractableHandler.isInWhitelist(entity)) return entity.interactAt(player, vec, hand);

        return InteractionResult.PASS;
    }

    public static InteractionResult tryInteract(Entity entity, Player player, InteractionHand hand) {
        if (entity instanceof InteractableEntity interactableEntity)
            return interactableEntity.interactableapi$tryInteract(player, hand);

        if (InteractableHandler.isInWhitelist(entity)) return entity.interact(player, hand);

        return InteractionResult.PASS;
    }

    public static InteractionResult tryInteractLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        var item = stack.getItem();
        if (item instanceof InteractableItem interactableItem)
            return interactableItem.interactableapi$tryInteractLivingEntity(stack, player, interactionTarget, usedHand);

        if (InteractableHandler.isInWhitelist(item))
            return stack.interactLivingEntity(player, interactionTarget, usedHand);

        return InteractionResult.PASS;
    }
}
