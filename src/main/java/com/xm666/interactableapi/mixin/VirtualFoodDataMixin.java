package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@OnlyIn(Dist.CLIENT)
@Mixin(FoodData.class)
public class VirtualFoodDataMixin {
    @WrapMethod(method = "add")
    private void add(int foodLevel, float saturationLevel, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(foodLevel, saturationLevel);
    }

    @WrapMethod(method = "eat(IF)V")
    private void eat(int foodLevelModifier, float saturationLevelModifier, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(foodLevelModifier, saturationLevelModifier);
    }

    @WrapMethod(method = "eat(Lnet/minecraft/world/food/FoodProperties;)V")
    private void eat(FoodProperties foodProperties, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(foodProperties);
    }

    @WrapMethod(method = "tick")
    private void tick(Player player, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(player);
    }

    @WrapMethod(method = "readAdditionalSaveData")
    private void readAdditionalSaveData(CompoundTag compoundTag, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(compoundTag);
    }

    @WrapMethod(method = "addAdditionalSaveData")
    private void addAdditionalSaveData(CompoundTag compoundTag, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(compoundTag);
    }

    @WrapMethod(method = "addExhaustion")
    private void addExhaustion(float exhaustion, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(exhaustion);
    }

    @WrapMethod(method = "setFoodLevel")
    private void setFoodLevel(int foodLevel, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(foodLevel);
    }

    @WrapMethod(method = "setSaturation")
    private void setSaturation(float saturationLevel, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(saturationLevel);
    }

    @WrapMethod(method = "setExhaustion")
    private void setExhaustion(float exhaustionLevel, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(exhaustionLevel);
    }
}
