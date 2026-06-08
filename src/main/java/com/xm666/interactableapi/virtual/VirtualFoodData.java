package com.xm666.interactableapi.virtual;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;

public class VirtualFoodData extends FoodData {
    private final FoodData foodData;

    public VirtualFoodData(FoodData foodData) {
        this.foodData = foodData;
    }

    @Override
    public void eat(int foodLevelModifier, float saturationLevelModifier) {
    }

    @Override
    public void eat(FoodProperties foodProperties) {
    }

    @Override
    public void tick(Player player) {
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
    }

    @Override
    public int getFoodLevel() {
        return this.foodData.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int foodLevel) {
    }

    @Override
    public int getLastFoodLevel() {
        return this.foodData.getLastFoodLevel();
    }

    @Override
    public boolean needsFood() {
        return this.foodData.needsFood();
    }

    @Override
    public void addExhaustion(float exhaustion) {
    }

    @Override
    public float getExhaustionLevel() {
        return this.foodData.getExhaustionLevel();
    }

    @Override
    public float getSaturationLevel() {
        return this.foodData.getSaturationLevel();
    }

    @Override
    public void setSaturation(float saturationLevel) {
    }

    @Override
    public void setExhaustion(float exhaustionLevel) {
    }
}
