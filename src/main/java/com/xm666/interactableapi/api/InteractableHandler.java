package com.xm666.interactableapi.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;
import java.util.function.Supplier;

public class InteractableHandler {
    public static final List<String> ENTITY_WHITELIST = List.of("net.minecraft.world.entity");
    public static final List<String> BLOCK_WHITELIST = List.of("net.minecraft.world.level.block");
    public static final List<String> ITEM_WHITELIST = List.of("net.minecraft.world.item");
    public static ThreadLocal<Boolean> virtualizeInteract = ThreadLocal.withInitial(() -> false);

    public static boolean isInWhitelist(Entity entity) {
        var className = entity.getClass().getName();
        for (var packageName : ENTITY_WHITELIST) {
            if (className.startsWith(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInWhitelist(Block block) {
        var className = block.getClass().getName();
        for (var packageName : BLOCK_WHITELIST) {
            if (className.startsWith(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInWhitelist(Item item) {
        var className = item.getClass().getName();
        for (var packageName : ITEM_WHITELIST) {
            if (className.startsWith(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean virtualizesInteract() {
        return virtualizeInteract.get();
    }

    public static InteractionResult interacts(HitResult hitResult) {
        var entityInteractionResult = interactsEntity(hitResult);
        if (entityInteractionResult.consumesAction() || entityInteractionResult == InteractionResult.FAIL)
            return entityInteractionResult;

        var blockInteractionResult = interactsBlock(hitResult);
        if (blockInteractionResult.consumesAction() || blockInteractionResult == InteractionResult.FAIL)
            return blockInteractionResult;

        return interactsItem();
    }

    public static InteractionResult interactsEntity(HitResult hitResult) {
        if (!(hitResult instanceof EntityHitResult entityHitResult)) return InteractionResult.PASS;

        var target = entityHitResult.getEntity();
        if (!getLevel().getWorldBorder().isWithinBounds(target.blockPosition())) return InteractionResult.FAIL;

        var gameMode = getGameMode();
        var player = getPlayer();
        for (var hand : InteractionHand.values()) {
            var interactionResult = callWithProtect(() -> gameMode.interactAt(player, target, entityHitResult, hand));
            if (interactionResult.consumesAction()) return interactionResult;

            interactionResult = callWithProtect(() -> gameMode.interact(player, target, hand));
            if (interactionResult.consumesAction()) return interactionResult;
        }

        return InteractionResult.PASS;
    }

    private static InteractionResult interactsBlock(HitResult hitResult) {
        if (!(hitResult instanceof BlockHitResult blockHitResult)) return InteractionResult.PASS;

        var gameMode = getGameMode();
        var player = getPlayer();
        for (var hand : InteractionHand.values()) {
            var interactionResult = callWithProtect(() -> gameMode.useItemOn(player, hand, blockHitResult));
            if (interactionResult.consumesAction() || interactionResult == InteractionResult.FAIL)
                return interactionResult;
        }

        return InteractionResult.PASS;
    }

    public static InteractionResult interactsItem() {
        var gameMode = getGameMode();
        var player = getPlayer();
        for (var hand : InteractionHand.values()) {
            var item = player.getItemInHand(hand);
            if (item.isEmpty()) continue;

            var interactionResult = gameMode.useItem(player, hand);
            if (interactionResult.consumesAction()) return interactionResult;
        }

        return InteractionResult.PASS;
    }

    private static ClientLevel getLevel() {
        return Minecraft.getInstance().level;
    }

    private static LocalPlayer getPlayer() {
        return Minecraft.getInstance().player;
    }

    private static MultiPlayerGameMode getGameMode() {
        return Minecraft.getInstance().gameMode;
    }

    private static <T> T callWithProtect(Supplier<T> supplier) {
        virtualizeInteract.set(true);
        var result = supplier.get();
        virtualizeInteract.set(false);
        return result;
    }
}
