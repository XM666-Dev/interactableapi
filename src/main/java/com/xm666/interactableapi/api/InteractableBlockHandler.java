package com.xm666.interactableapi.api;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

public class InteractableBlockHandler {
    public static ItemInteractionResult tryUseItemOn(BlockState blockState, ItemStack stack, Level level, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var block = blockState.getBlock();
        if (block instanceof InteractableBlock)
            return useItemOn(blockState, stack, level, player, hand, hitResult);

        if (InteractableHandler.isInWhitelist(block))
            return blockState.useItemOn(stack, level, player, hand, hitResult);

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static InteractionResult tryUseWithoutItem(BlockState blockState, Level level, Player player, BlockHitResult hitResult) {
        var block = blockState.getBlock();
        if (block instanceof InteractableBlock)
            return useWithoutItem(blockState, level, player, hitResult);

        if (InteractableHandler.isInWhitelist(block)) return blockState.useWithoutItem(level, player, hitResult);

        return InteractionResult.PASS;
    }

    private static ItemInteractionResult useItemOn(BlockState blockState, ItemStack stack, Level level, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var useOnContext = new UseOnContext(level, player, hand, player.getItemInHand(hand).copy(), hitResult);
        var e = NeoForge.EVENT_BUS.post(new UseItemOnBlockEvent(useOnContext, UseItemOnBlockEvent.UsePhase.BLOCK));
        return e.isCanceled() ? e.getCancellationResult() : ((InteractableBlock) blockState.getBlock()).interactableapi$tryUseItemOn(stack, blockState, level, hitResult.getBlockPos(), player, hand, hitResult);
    }

    private static InteractionResult useWithoutItem(BlockState blockState, Level level, Player player, BlockHitResult hitResult) {
        return ((InteractableBlock) blockState.getBlock()).interactableapi$tryUseWithoutItem(blockState, level, hitResult.getBlockPos(), player, hitResult);
    }
}
