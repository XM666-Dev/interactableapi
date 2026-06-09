package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

@OnlyIn(Dist.CLIENT)
@Mixin(ItemStack.class)
public class VirtualItemStackMixin {
    @WrapMethod(method = "split")
    private ItemStack split(int amount, Operation<ItemStack> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(amount);
    }

    @WrapMethod(method = "copyAndClear")
    private ItemStack copyAndClear(Operation<ItemStack> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call();
    }

    @WrapMethod(method = "finishUsingItem")
    private ItemStack finishUsingItem(Level level, LivingEntity livingEntity, Operation<ItemStack> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(level, livingEntity);
    }

    @WrapMethod(method = "setDamageValue")
    private void setDamageValue(int damage, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(damage);
    }

    @WrapMethod(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V")
    private void hurtAndBreak(int damage, ServerLevel level, ServerPlayer player, Consumer<Item> onBreak, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(damage, level, player, onBreak);
    }

    @WrapMethod(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V")
    private void hurtAndBreak(int p_220158_, ServerLevel p_346256_, LivingEntity p_220160_, Consumer<Item> p_348596_, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(p_220158_, p_346256_, p_220160_, p_348596_);
    }

    @WrapMethod(method = "hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V")
    private void hurtAndBreak(int amount, LivingEntity entity, EquipmentSlot slot, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(amount, entity, slot);
    }

    @WrapMethod(method = "hurtAndConvertOnBreak")
    private ItemStack hurtAndConvertOnBreak(int amount, ItemLike item, LivingEntity entity, EquipmentSlot slot, Operation<ItemStack> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(amount, item, entity, slot);
    }

    @WrapMethod(method = "overrideStackedOnOther")
    private boolean overrideStackedOnOther(Slot slot, ClickAction action, Player player, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return false;
        return original.call(slot, action, player);
    }

    @WrapMethod(method = "overrideOtherStackedOnMe")
    private boolean overrideOtherStackedOnMe(ItemStack stack, Slot slot, ClickAction action, Player player, SlotAccess access, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return false;
        return original.call(stack, slot, action, player, access);
    }

    @WrapMethod(method = "postHurtEnemy")
    private void postHurtEnemy(LivingEntity target, Player attacker, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(target, attacker);
    }

    @WrapMethod(method = "mineBlock")
    private void mineBlock(Level level, BlockState state, BlockPos pos, Player player, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(level, state, pos, player);
    }

    @WrapMethod(method = "inventoryTick")
    private void inventoryTick(Level level, Entity entity, int inventorySlot, boolean isCurrentItem, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(level, entity, inventorySlot, isCurrentItem);
    }

    @WrapMethod(method = "onCraftedBy")
    private void onCraftedBy(Level level, Player player, int amount, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(level, player, amount);
    }

    @WrapMethod(method = "onCraftedBySystem")
    private void onCraftedBySystem(Level level, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(level);
    }

    @WrapMethod(method = "releaseUsing")
    private void releaseUsing(Level level, LivingEntity livingEntity, int timeLeft, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(level, livingEntity, timeLeft);
    }

    @WrapMethod(method = "set")
    private <T> T set(DataComponentType<? super T> component, T value, Operation<T> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(component, value);
    }

    @WrapMethod(method = "update(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;")
    private <T, U> T update(DataComponentType<T> component, T defaultValue, U updateValue, BiFunction<T, U, T> updater, Operation<T> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(component, defaultValue, updateValue, updater);
    }

    @WrapMethod(method = "update(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;Ljava/util/function/UnaryOperator;)Ljava/lang/Object;")
    private <T> T update(DataComponentType<T> component, T defaultValue, UnaryOperator<T> updater, Operation<T> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(component, defaultValue, updater);
    }

    @WrapMethod(method = "remove")
    private <T> T remove(DataComponentType<? extends T> component, Operation<T> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(component);
    }

    @WrapMethod(method = "applyComponentsAndValidate")
    private void applyComponentsAndValidate(DataComponentPatch components, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(components);
    }

    @WrapMethod(method = "applyComponents(Lnet/minecraft/core/component/DataComponentPatch;)V")
    private void applyComponents(DataComponentPatch components, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(components);
    }

    @WrapMethod(method = "applyComponents(Lnet/minecraft/core/component/DataComponentMap;)V")
    private void applyComponents(DataComponentMap components, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(components);
    }

    @WrapMethod(method = "enchant")
    private void enchant(Holder<Enchantment> enchantment, int level, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(enchantment, level);
    }

    @WrapMethod(method = "setEntityRepresentation")
    private void setEntityRepresentation(Entity entity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(entity);
    }

    @WrapMethod(method = "setPopTime")
    private void setPopTime(int popTime, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(popTime);
    }

    @WrapMethod(method = "setCount")
    private void setCount(int count, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(count);
    }

    @WrapMethod(method = "limitSize")
    private void limitSize(int maxSize, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(maxSize);
    }

    @WrapMethod(method = "grow")
    private void grow(int increment, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(increment);
    }

    @WrapMethod(method = "shrink")
    private void shrink(int decrement, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(decrement);
    }

    @WrapMethod(method = "consume")
    private void consume(int amount, LivingEntity entity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(amount, entity);
    }

    @WrapMethod(method = "consumeAndReturn")
    private ItemStack consumeAndReturn(int amount, LivingEntity entity, Operation<ItemStack> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(amount, entity);
    }

    @WrapMethod(method = "onUseTick")
    private void onUseTick(Level level, LivingEntity livingEntity, int count, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(level, livingEntity, count);
    }

    @WrapMethod(method = "onDestroyed")
    private void onDestroyed(ItemEntity itemEntity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(itemEntity);
    }
}
