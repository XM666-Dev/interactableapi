package com.xm666.interactableapi.api;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

import java.util.function.Function;

public class InteractableItemHandler {
    public static InteractionResult tryUseOn(ItemStack stack, UseOnContext context) {
        var item = stack.getItem();
        if (item instanceof InteractableItem) return useOn(stack, context);

        if (InteractableHandler.isInWhitelist(item)) return stack.useOn(context);

        return InteractionResult.PASS;
    }

    public static InteractionResultHolder<ItemStack> tryUse(ItemStack stack, Level level, Player player, InteractionHand hand) {
        var item = stack.getItem();
        if (item instanceof InteractableItem) return use(stack, level, player, hand);

        if (InteractableHandler.isInWhitelist(item)) return stack.use(level, player, hand);

        return InteractionResultHolder.pass(stack);
    }

    private static InteractionResult useOn(ItemStack stack, UseOnContext context) {
        var event = NeoForge.EVENT_BUS.post(new UseItemOnBlockEvent(context, UseItemOnBlockEvent.UsePhase.ITEM_AFTER_BLOCK));
        if (event.isCanceled()) return event.getCancellationResult().result();

        return !context.getLevel().isClientSide ? CommonHooks.onPlaceItemIntoWorld(context) : onItemUse(stack, context, (c) -> ((InteractableItem) stack.getItem()).interactableapi$tryUseOn(context));
    }

    private static InteractionResult onItemUse(ItemStack stack, UseOnContext context, Function<UseOnContext, InteractionResult> callback) {
        var player = context.getPlayer();
        var blockpos = context.getClickedPos();
        if (player != null && !player.getAbilities().mayBuild && !stack.canPlaceOnBlockInAdventureMode(new BlockInWorld(context.getLevel(), blockpos, false)))
            return InteractionResult.PASS;

        return callback.apply(context);
    }

    private static InteractionResultHolder<ItemStack> use(ItemStack stack, Level level, Player player, InteractionHand usedHand) {
        return ((InteractableItem) stack.getItem()).interactableapi$tryUse(level, player, usedHand);
    }
}
