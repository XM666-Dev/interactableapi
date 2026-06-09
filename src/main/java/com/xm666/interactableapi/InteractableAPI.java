package com.xm666.interactableapi;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.slf4j.Logger;

@Mod(InteractableAPI.MODID)
@EventBusSubscriber(modid = InteractableAPI.MODID, value = Dist.CLIENT)
public class InteractableAPI {
    public static final String MODID = "interactableapi";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        var mc = Minecraft.getInstance();
        if (mc.isPaused() || mc.level == null) return;
        //var result = InteractableHandler.interacts(mc.hitResult);
        var success = interacts(mc.hitResult);
        LOGGER.info("{}", success);
    }

    public static boolean interacts(HitResult hitResult) {
        return interactsEntity(hitResult) || interactsBlock(hitResult) || interactsItem();
    }

    private static boolean interactsEntity(HitResult hitResult) {
        if (!(hitResult instanceof EntityHitResult entityHitResult)) return false;

        var target = entityHitResult.getEntity();
        var targetClass = target.getClass();
        return declaresMethod(targetClass, "interactAt", Player.class, Vec3.class, InteractionHand.class)
                || declaresMethod(targetClass, "interact", Player.class, InteractionHand.class)
                || declaresMethod(targetClass, "mobInteract", Player.class, InteractionHand.class)
                || interactsLivingEntity(target);
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean interactsLivingEntity(Entity target) {
        if (!(target instanceof LivingEntity)) return false;

        var mc = Minecraft.getInstance();
        var player = mc.player;
        for (var hand : InteractionHand.values()) {
            var stack = player.getItemInHand(hand);
            var item = stack.getItem();
            var itemClass = item.getClass();
            if (declaresMethod(itemClass, "interactLivingEntity", ItemStack.class, Player.class, LivingEntity.class, InteractionHand.class))
                return true;
        }

        return false;
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean interactsBlock(HitResult hitResult) {
        if (!(hitResult instanceof BlockHitResult blockHitResult)) return false;

        var mc = Minecraft.getInstance();
        var level = mc.level;
        var blockState = level.getBlockState(blockHitResult.getBlockPos());
        var block = blockState.getBlock();
        var blockClass = block.getClass();
        return usesItemFirst()
                || declaresMethod(blockClass, "useItemOn", ItemStack.class, BlockState.class, Level.class, BlockPos.class, Player.class, InteractionHand.class, BlockHitResult.class)
                || declaresMethod(blockClass, "useWithoutItem", BlockState.class, Level.class, BlockPos.class, Player.class, BlockHitResult.class)
                || usesItem();
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean usesItemFirst() {
        var mc = Minecraft.getInstance();
        var player = mc.player;
        for (var hand : InteractionHand.values()) {
            var stack = player.getItemInHand(hand);
            var item = stack.getItem();
            var itemClass = item.getClass();
            if (declaresMethod(itemClass, "onItemUseFirst", ItemStack.class, UseOnContext.class))
                return true;
        }

        return false;
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean usesItem() {
        var mc = Minecraft.getInstance();
        var player = mc.player;
        for (var hand : InteractionHand.values()) {
            var stack = player.getItemInHand(hand);
            var item = stack.getItem();
            var itemClass = item.getClass();
            if (declaresMethod(itemClass, "useOn", UseOnContext.class))
                return true;
        }

        return false;
    }

    @SuppressWarnings("DataFlowIssue")
    private static boolean interactsItem() {
        var mc = Minecraft.getInstance();
        var player = mc.player;
        for (var hand : InteractionHand.values()) {
            var stack = player.getItemInHand(hand);
            var item = stack.getItem();
            var itemClass = item.getClass();
            if (declaresMethod(itemClass, "use", Level.class, Player.class, InteractionHand.class))
                return true;
        }

        return false;
    }

    private static boolean declaresMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            clazz.getDeclaredMethod(name, parameterTypes);
            return true;
        } catch (NoSuchMethodException exception) {
            return false;
        }
    }
}
