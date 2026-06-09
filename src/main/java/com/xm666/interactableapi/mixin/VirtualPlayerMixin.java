package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.datafixers.util.Either;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stat;
import net.minecraft.util.Unit;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.BaseCommandBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@OnlyIn(Dist.CLIENT)
@Mixin(Player.class)
public class VirtualPlayerMixin {
    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateIsUnderwater")
    private boolean updateIsUnderwater(Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call();
    }

    @WrapMethod(method = "turtleHelmetTick")
    private void turtleHelmetTick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "moveCloak")
    private void moveCloak(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updatePlayerPose")
    private void updatePlayerPose(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playSound")
    private void playSound(SoundEvent sound, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(sound, volume, pitch);
    }

    @WrapMethod(method = "playNotifySound")
    private void playNotifySound(SoundEvent sound, SoundSource source, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(sound, source, volume, pitch);
    }

    @WrapMethod(method = "handleEntityEvent")
    private void handleEntityEvent(byte id, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(id);
    }

    @WrapMethod(method = "closeContainer")
    private void closeContainer(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "doCloseContainer")
    private void doCloseContainer(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "rideTick")
    private void rideTick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "serverAiStep")
    private void serverAiStep(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "aiStep")
    private void aiStep(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playShoulderEntityAmbientSound")
    private void playShoulderEntityAmbientSound(CompoundTag entityCompound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entityCompound);
    }

    @WrapMethod(method = "touch")
    private void touch(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "setScore")
    private void setScore(int score, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(score);
    }

    @WrapMethod(method = "increaseScore")
    private void increaseScore(int score, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(score);
    }

    @WrapMethod(method = "startAutoSpinAttack")
    private void startAutoSpinAttack(int ticks, float damage, ItemStack itemStack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(ticks, damage, itemStack);
    }

    @WrapMethod(method = "die")
    private void die(DamageSource cause, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(cause);
    }

    @WrapMethod(method = "dropEquipment")
    private void dropEquipment(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "destroyVanishingCursedItems")
    private void destroyVanishingCursedItems(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "drop(Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/item/ItemEntity;")
    private ItemEntity drop(ItemStack itemStack, boolean includeThrowerName, Operation<ItemEntity> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(itemStack, includeThrowerName);
    }

    @WrapMethod(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;")
    private ItemEntity drop(ItemStack droppedItem, boolean dropAround, boolean includeThrowerName, Operation<ItemEntity> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(droppedItem, dropAround, includeThrowerName);
    }

    @WrapMethod(method = "readAdditionalSaveData")
    private void readAdditionalSaveData(CompoundTag compound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(compound);
    }

    @WrapMethod(method = "addAdditionalSaveData")
    private void addAdditionalSaveData(CompoundTag compound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(compound);
    }

    @WrapMethod(method = "hurt")
    private boolean hurt(DamageSource source, float amount, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(source, amount);
    }

    @WrapMethod(method = "blockUsingShield")
    private void blockUsingShield(LivingEntity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "hurtArmor")
    private void hurtArmor(DamageSource damageSource, float damage, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, damage);
    }

    @WrapMethod(method = "hurtHelmet")
    private void hurtHelmet(DamageSource damageSource, float damageAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, damageAmount);
    }

    @WrapMethod(method = "hurtCurrentlyUsedShield")
    private void hurtCurrentlyUsedShield(float damage, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damage);
    }

    @WrapMethod(method = "actuallyHurt")
    private void actuallyHurt(DamageSource damageSrc, float damageAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSrc, damageAmount);
    }

    @WrapMethod(method = "openTextEdit")
    private void openTextEdit(SignBlockEntity signEntity, boolean isFrontText, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(signEntity, isFrontText);
    }

    @WrapMethod(method = "openMinecartCommandBlock")
    private void openMinecartCommandBlock(BaseCommandBlock commandEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(commandEntity);
    }

    @WrapMethod(method = "openCommandBlock")
    private void openCommandBlock(CommandBlockEntity commandBlockEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(commandBlockEntity);
    }

    @WrapMethod(method = "openStructureBlock")
    private void openStructureBlock(StructureBlockEntity structureEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(structureEntity);
    }

    @WrapMethod(method = "openJigsawBlock")
    private void openJigsawBlock(JigsawBlockEntity jigsawBlockEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(jigsawBlockEntity);
    }

    @WrapMethod(method = "openHorseInventory")
    private void openHorseInventory(AbstractHorse horse, Container inventory, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(horse, inventory);
    }

    @WrapMethod(method = "openMenu")
    private OptionalInt openMenu(MenuProvider menu, Operation<OptionalInt> original) {
        if (InteractableHandler.virtualizesInteract()) return OptionalInt.empty();
        return original.call(menu);
    }

    @WrapMethod(method = "sendMerchantOffers")
    private void sendMerchantOffers(int containerId, MerchantOffers offers, int villagerLevel, int villagerXp, boolean showProgress, boolean canRestock, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(containerId, offers, villagerLevel, villagerXp, showProgress, canRestock);
    }

    @WrapMethod(method = "openItemGui")
    private void openItemGui(ItemStack stack, InteractionHand hand, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stack, hand);
    }

    @WrapMethod(method = "removeVehicle")
    private void removeVehicle(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "attack")
    private void attack(Entity target, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(target);
    }

    @WrapMethod(method = "doAutoAttackOnTouch")
    private void doAutoAttackOnTouch(LivingEntity target, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(target);
    }

    @WrapMethod(method = "disableShield")
    private void disableShield(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "crit")
    private void crit(Entity entityHit, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entityHit);
    }

    @WrapMethod(method = "magicCrit")
    private void magicCrit(Entity entityHit, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entityHit);
    }

    @WrapMethod(method = "sweepAttack")
    private void sweepAttack(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "respawn")
    private void respawn(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "remove")
    private void remove(Entity.RemovalReason reason, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(reason);
    }

    @WrapMethod(method = "updateTutorialInventoryAction")
    private void updateTutorialInventoryAction(ItemStack carried, ItemStack clicked, ClickAction action, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(carried, clicked, action);
    }

    @WrapMethod(method = "startSleepInBed")
    private Either<Player.BedSleepingProblem, Unit> startSleepInBed(BlockPos bedPos, Operation<Either<Player.BedSleepingProblem, Unit>> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(bedPos);
    }

    @WrapMethod(method = "stopSleepInBed")
    private void stopSleepInBed(boolean wakeImmediately, boolean updateLevelForSleepingPlayers, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(wakeImmediately, updateLevelForSleepingPlayers);
    }

    @WrapMethod(method = "stopSleeping")
    private void stopSleeping(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "displayClientMessage")
    private void displayClientMessage(Component chatComponent, boolean actionBar, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(chatComponent, actionBar);
    }

    @WrapMethod(method = "awardStat(Lnet/minecraft/resources/ResourceLocation;)V")
    private void awardStat(ResourceLocation statKey, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(statKey);
    }

    @WrapMethod(method = "awardStat(Lnet/minecraft/resources/ResourceLocation;I)V")
    private void awardStat(ResourceLocation stat, int increment, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stat, increment);
    }

    @WrapMethod(method = "awardStat(Lnet/minecraft/stats/Stat;)V")
    private void awardStat(Stat<?> stat, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stat);
    }

    @WrapMethod(method = "awardStat(Lnet/minecraft/stats/Stat;I)V")
    private void awardStat(Stat<?> stat, int increment, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stat, increment);
    }

    @WrapMethod(method = "resetStat")
    private void resetStat(Stat<?> stat, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stat);
    }

    @WrapMethod(method = "awardRecipes")
    private int awardRecipes(Collection<RecipeHolder<?>> recipes, Operation<Integer> original) {
        if (InteractableHandler.virtualizesInteract()) return 0;
        return original.call(recipes);
    }

    @WrapMethod(method = "triggerRecipeCrafted")
    private void triggerRecipeCrafted(RecipeHolder<?> recipe, List<ItemStack> items, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(recipe, items);
    }

    @WrapMethod(method = "awardRecipesByKey")
    private void awardRecipesByKey(List<ResourceLocation> recipes, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(recipes);
    }

    @WrapMethod(method = "resetRecipes")
    private int resetRecipes(Collection<RecipeHolder<?>> recipes, Operation<Integer> original) {
        if (InteractableHandler.virtualizesInteract()) return 0;
        return original.call(recipes);
    }

    @WrapMethod(method = "jumpFromGround")
    private void jumpFromGround(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "travel")
    private void travel(Vec3 travelVector, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(travelVector);
    }

    @WrapMethod(method = "updateSwimming")
    private void updateSwimming(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "causeFallDamage")
    private boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(fallDistance, multiplier, source);
    }

    @WrapMethod(method = "tryToStartFallFlying")
    private boolean tryToStartFallFlying(Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call();
    }

    @WrapMethod(method = "startFallFlying")
    private void startFallFlying(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "stopFallFlying")
    private void stopFallFlying(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "doWaterSplashEffect")
    private void doWaterSplashEffect(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playStepSound")
    private void playStepSound(BlockPos pos, BlockState state, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, state);
    }

    @WrapMethod(method = "killedEntity")
    private boolean killedEntity(ServerLevel level, LivingEntity entity, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(level, entity);
    }

    @WrapMethod(method = "makeStuckInBlock")
    private void makeStuckInBlock(BlockState state, Vec3 motionMultiplier, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(state, motionMultiplier);
    }

    @WrapMethod(method = "giveExperiencePoints")
    private void giveExperiencePoints(int xpPoints, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(xpPoints);
    }

    @WrapMethod(method = "onEnchantmentPerformed")
    private void onEnchantmentPerformed(ItemStack enchantedItem, int levelCost, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(enchantedItem, levelCost);
    }

    @WrapMethod(method = "giveExperienceLevels")
    private void giveExperienceLevels(int levels, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(levels);
    }

    @WrapMethod(method = "causeFoodExhaustion")
    private void causeFoodExhaustion(float exhaustion, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(exhaustion);
    }

    @WrapMethod(method = "onUpdateAbilities")
    private void onUpdateAbilities(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setItemSlot")
    private void setItemSlot(EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot, stack);
    }

    @WrapMethod(method = "addItem")
    private boolean addItem(ItemStack stack, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(stack);
    }

    @WrapMethod(method = "setEntityOnShoulder")
    private boolean setEntityOnShoulder(CompoundTag entityCompound, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(entityCompound);
    }

    @WrapMethod(method = "removeEntitiesOnShoulder")
    private void removeEntitiesOnShoulder(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "respawnEntityOnShoulder")
    private void respawnEntityOnShoulder(CompoundTag entityCompound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entityCompound);
    }

    @WrapMethod(method = "internalSetAbsorptionAmount")
    private void internalSetAbsorptionAmount(float absorptionAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(absorptionAmount);
    }

    @WrapMethod(method = "setReducedDebugInfo")
    private void setReducedDebugInfo(boolean reducedDebugInfo, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(reducedDebugInfo);
    }

    @WrapMethod(method = "setRemainingFireTicks")
    private void setRemainingFireTicks(int ticks, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(ticks);
    }

    @WrapMethod(method = "setMainArm")
    private void setMainArm(HumanoidArm hand, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hand);
    }

    @WrapMethod(method = "setShoulderEntityLeft")
    private void setShoulderEntityLeft(CompoundTag entityCompound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entityCompound);
    }

    @WrapMethod(method = "setShoulderEntityRight")
    private void setShoulderEntityRight(CompoundTag entityCompound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entityCompound);
    }

    @WrapMethod(method = "resetAttackStrengthTicker")
    private void resetAttackStrengthTicker(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "eat")
    private ItemStack eat(Level level, ItemStack food, FoodProperties foodProperties, Operation<ItemStack> original) {
        if (InteractableHandler.virtualizesInteract()) return food;
        original.call(level, food, foodProperties);
        return food;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @WrapMethod(method = "setLastDeathLocation")
    private void setLastDeathLocation(Optional<GlobalPos> lastDeathLocation, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(lastDeathLocation);
    }

    @WrapMethod(method = "animateHurt")
    private void animateHurt(float yaw, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(yaw);
    }

    @WrapMethod(method = "setIgnoreFallDamageFromCurrentImpulse")
    private void setIgnoreFallDamageFromCurrentImpulse(boolean ignoreFallDamageFromCurrentImpulse, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(ignoreFallDamageFromCurrentImpulse);
    }

    @WrapMethod(method = "tryResetCurrentImpulseContext")
    private void tryResetCurrentImpulseContext(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "resetCurrentImpulseContext")
    private void resetCurrentImpulseContext(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "refreshDisplayName")
    private void refreshDisplayName(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setForcedPose")
    private void setForcedPose(Pose pose, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pose);
    }
}
