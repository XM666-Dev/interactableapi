package com.xm666.interactableapi.api;

import com.xm666.interactableapi.InteractableAPI;
import com.xm666.interactableapi.virtual.VirtualClientLevel;
import com.xm666.interactableapi.virtual.VirtualLocalPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.TriState;

import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber(modid = InteractableAPI.MODID, value = Dist.CLIENT)
public class InteractableHandler {
    public static final List<String> ENTITY_WHITELIST = List.of("net.minecraft.world.entity");
    public static final List<String> BLOCK_WHITELIST = List.of("net.minecraft.world.level.block");
    public static final List<String> ITEM_WHITELIST = List.of("net.minecraft.world.item");
    public static ThreadLocal<Boolean> protectEntity = ThreadLocal.withInitial(() -> false);
    private static VirtualClientLevel virtualLevel;
    private static VirtualLocalPlayer virtualPlayer;

    @SubscribeEvent
    public static void onClientJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        virtualLevel = new VirtualClientLevel();
        virtualPlayer = new VirtualLocalPlayer(virtualLevel);
    }

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

    public static boolean protectsEntity() {
        return protectEntity.get();
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
        if (!virtualLevel.getWorldBorder().isWithinBounds(target.blockPosition())) return InteractionResult.FAIL;

        for (var hand : InteractionHand.values()) {
            var interactionResult = callWithProtect(() -> interactAt(virtualPlayer, target, entityHitResult, hand));
            if (interactionResult.consumesAction()) return interactionResult;

            interactionResult = callWithProtect(() -> interact(virtualPlayer, target, hand));
            if (interactionResult.consumesAction()) return interactionResult;
        }

        return InteractionResult.PASS;
    }

    private static InteractionResult interactAt(Player player, Entity target, EntityHitResult ray, InteractionHand hand) {
        var gameMode = getGameMode();
        if (gameMode.getPlayerMode() == GameType.SPECTATOR) return InteractionResult.PASS;

        var cancelResult = CommonHooks.onInteractEntityAt(player, target, ray, hand);
        if (cancelResult != null) return cancelResult;

        var vec = ray.getLocation().subtract(target.getX(), target.getY(), target.getZ());
        return InteractableEntityHandler.tryInteractAt(target, player, vec, hand);
    }

    private static InteractionResult interact(Player player, Entity target, InteractionHand hand) {
        return getGameMode().getPlayerMode() == GameType.SPECTATOR ? InteractionResult.PASS : interactOn(player, target, hand);
    }

    private static InteractionResult interactOn(Player player, Entity entity, InteractionHand hand) {
        if (player.isSpectator()) {
            return InteractionResult.PASS;
        } else {
            var cancelResult = CommonHooks.onInteractEntity(player, entity, hand);
            if (cancelResult != null) {
                return cancelResult;
            } else {
                var stack = player.getItemInHand(hand);
                var interactionResult = InteractableEntityHandler.tryInteract(entity, player, hand);
                if (interactionResult.consumesAction()) {
                    return interactionResult;
                } else {
                    if (!stack.isEmpty() && entity instanceof LivingEntity) {
                        var livingInteractionResult = InteractableEntityHandler.tryInteractLivingEntity(stack, player, (LivingEntity) entity, hand);
                        if (livingInteractionResult.consumesAction()) {
                            return livingInteractionResult;
                        }
                    }

                    return InteractionResult.PASS;
                }
            }
        }
    }

    private static InteractionResult interactsBlock(HitResult hitResult) {
        if (!(hitResult instanceof BlockHitResult blockHitResult)) return InteractionResult.PASS;

        for (var hand : InteractionHand.values()) {
            var interactionResult = useItemOn(virtualPlayer, hand, blockHitResult);
            if (interactionResult.consumesAction() || interactionResult == InteractionResult.FAIL)
                return interactionResult;
        }

        return InteractionResult.PASS;
    }

    private static InteractionResult useItemOn(LocalPlayer player, InteractionHand hand, BlockHitResult result) {
        if (!virtualLevel.getWorldBorder().isWithinBounds(result.getBlockPos())) return InteractionResult.FAIL;

        return performUseItemOn(player, hand, result);
    }

    private static InteractionResult performUseItemOn(LocalPlayer player, InteractionHand hand, BlockHitResult blockHitResult) {
        var gameMode = getGameMode();
        var blockPos = blockHitResult.getBlockPos();
        var event = CommonHooks.onRightClickBlock(player, hand, blockPos, blockHitResult);
        if (event.isCanceled()) {
            return event.getCancellationResult();
        } else if (gameMode.getPlayerMode() == GameType.SPECTATOR) {
            return InteractionResult.SUCCESS;
        } else {
            var useOnContext = new UseOnContext(player, hand, blockHitResult);
            var item = player.getItemInHand(hand);
            if (event.getUseItem() != TriState.FALSE) {
                var result = item.onItemUseFirst(useOnContext);
                if (result != InteractionResult.PASS) {
                    return result;
                }
            }

            var allowSecondaryUse = !player.getMainHandItem().doesSneakBypassUse(player.level(), blockPos, player) || !player.getOffhandItem().doesSneakBypassUse(player.level(), blockPos, player);
            var secondaryUse = player.isSecondaryUseActive() && allowSecondaryUse;
            if (event.getUseBlock().isTrue() || event.getUseBlock().isDefault() && !secondaryUse) {
                var blockState = virtualLevel.getBlockState(blockPos);
                if (!getConnection().isFeatureEnabled(blockState.getBlock().requiredFeatures())) {
                    return InteractionResult.FAIL;
                }

                var itemInteractionResult = InteractableBlockHandler.tryUseItemOn(blockState, player.getItemInHand(hand), virtualLevel, player, hand, blockHitResult);
                if (itemInteractionResult.consumesAction()) {
                    return itemInteractionResult.result();
                }

                if (itemInteractionResult == ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION && hand == InteractionHand.MAIN_HAND) {
                    var interactionResult = InteractableBlockHandler.tryUseWithoutItem(blockState, virtualLevel, player, blockHitResult);
                    if (interactionResult.consumesAction()) {
                        return interactionResult;
                    }
                }
            }

            if (event.getUseItem().isFalse()) {
                return InteractionResult.PASS;
            } else if (event.getUseItem().isTrue() || !item.isEmpty() && !player.getCooldowns().isOnCooldown(item.getItem())) {
                return InteractableItemHandler.tryUseOn(item, useOnContext);
            }
            return InteractionResult.PASS;
        }
    }

    public static InteractionResult interactsItem() {
        for (var hand : InteractionHand.values()) {
            var item = virtualPlayer.getItemInHand(hand);
            if (item.isEmpty()) continue;

            var interactionResult = useItem(virtualPlayer, hand);
            if (interactionResult.consumesAction()) return interactionResult;
        }

        return InteractionResult.PASS;
    }

    private static InteractionResult useItem(Player player, InteractionHand hand) {
        if (getGameMode().getPlayerMode() == GameType.SPECTATOR) return InteractionResult.PASS;

        var item = player.getItemInHand(hand);
        if (player.getCooldowns().isOnCooldown(item.getItem())) return InteractionResult.PASS;

        var cancelResult = CommonHooks.onItemRightClick(player, hand);
        if (cancelResult != null) return cancelResult;

        var interactionResultHolder = InteractableItemHandler.tryUse(item, virtualLevel, player, hand);
        return interactionResultHolder.getResult();
    }

    private static MultiPlayerGameMode getGameMode() {
        return Minecraft.getInstance().gameMode;
    }

    private static ClientPacketListener getConnection() {
        return Minecraft.getInstance().getConnection();
    }

    private static <T> T callWithProtect(Supplier<T> supplier) {
        protectEntity.set(true);
        var result = supplier.get();
        protectEntity.set(false);
        return result;
    }
}
