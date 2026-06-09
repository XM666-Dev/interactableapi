package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT)
@Mixin(Mob.class)
public class VirtualMobMixin {
    @WrapMethod(method = "registerGoals")
    private void registerGoals(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setPathfindingMalus")
    private void setPathfindingMalus(PathType pathType, float malus, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pathType, malus);
    }

    @WrapMethod(method = "onPathfindingStart")
    private void onPathfindingStart(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onPathfindingDone")
    private void onPathfindingDone(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setTarget")
    private void setTarget(LivingEntity target, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(target);
    }

    @WrapMethod(method = "ate")
    private void ate(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playAmbientSound")
    private void playAmbientSound(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "baseTick")
    private void baseTick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playHurtSound")
    private void playHurtSound(DamageSource source, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(source);
    }

    @WrapMethod(method = "resetAmbientSoundTime")
    private void resetAmbientSoundTime(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "spawnAnim")
    private void spawnAnim(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "handleEntityEvent")
    private void handleEntityEvent(byte id, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(id);
    }

    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateControlFlags")
    private void updateControlFlags(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "tickHeadTurn")
    private float tickHeadTurn(float yRot, float animStep, Operation<Float> original) {
        if (InteractableHandler.virtualizesInteract()) return yRot;
        original.call(yRot, animStep);
        return yRot;
    }

    @WrapMethod(method = "readAdditionalSaveData")
    private void readAdditionalSaveData(CompoundTag compound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(compound);
    }

    @WrapMethod(method = "dropFromLootTable")
    private void dropFromLootTable(DamageSource damageSource, boolean attackedRecently, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, attackedRecently);
    }

    @WrapMethod(method = "setZza")
    private void setZza(float amount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(amount);
    }

    @WrapMethod(method = "setYya")
    private void setYya(float amount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(amount);
    }

    @WrapMethod(method = "setXxa")
    private void setXxa(float amount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(amount);
    }

    @WrapMethod(method = "setSpeed")
    private void setSpeed(float speed, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(speed);
    }

    @WrapMethod(method = "stopInPlace")
    private void stopInPlace(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "aiStep")
    private void aiStep(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "pickUpItem")
    private void pickUpItem(ItemEntity itemEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(itemEntity);
    }

    @WrapMethod(method = "equipItemIfPossible")
    private ItemStack equipItemIfPossible(ItemStack stack, Operation<ItemStack> original) {
        if (InteractableHandler.virtualizesInteract()) return stack;
        original.call(stack);
        return stack;
    }

    @WrapMethod(method = "setItemSlotAndDropWhenKilled")
    private void setItemSlotAndDropWhenKilled(EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot, stack);
    }

    @WrapMethod(method = "setGuaranteedDrop")
    private void setGuaranteedDrop(EquipmentSlot slot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot);
    }

    @WrapMethod(method = "checkDespawn")
    private void checkDespawn(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "serverAiStep")
    private void serverAiStep(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "sendDebugPackets")
    private void sendDebugPackets(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "customServerAiStep")
    private void customServerAiStep(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "clampHeadRotationToBody")
    private void clampHeadRotationToBody(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "lookAt")
    private void lookAt(Entity entity, float maxYRotIncrease, float maxXRotIncrease, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity, maxYRotIncrease, maxXRotIncrease);
    }

    @WrapMethod(method = "setBodyArmorItem")
    private void setBodyArmorItem(ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stack);
    }

    @WrapMethod(method = "setItemSlot")
    private void setItemSlot(EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot, stack);
    }

    @WrapMethod(method = "dropCustomDeathLoot")
    private void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, damageSource, recentlyHit);
    }

    @WrapMethod(method = "dropPreservedEquipment()V")
    private void dropPreservedEquipment(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "dropPreservedEquipment(Ljava/util/function/Predicate;)Ljava/util/Set;")
    private Set<EquipmentSlot> dropPreservedEquipment(Predicate<ItemStack> predicate, Operation<Set<EquipmentSlot>> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(predicate);
    }

    @WrapMethod(method = "equip(Lnet/minecraft/world/entity/EquipmentTable;)V")
    private void equip(EquipmentTable equipmentTable, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(equipmentTable);
    }

    @WrapMethod(method = "equip(Lnet/minecraft/resources/ResourceKey;Ljava/util/Map;)V")
    private void equip(ResourceKey<LootTable> equipmentLootTable, Map<EquipmentSlot, Float> slotDropChances, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(equipmentLootTable, slotDropChances);
    }

    @WrapMethod(method = "populateDefaultEquipmentSlots")
    private void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(random, difficulty);
    }

    @WrapMethod(method = "populateDefaultEquipmentEnchantments")
    private void populateDefaultEquipmentEnchantments(ServerLevelAccessor level, RandomSource random, DifficultyInstance difficulty, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, random, difficulty);
    }

    @WrapMethod(method = "enchantSpawnedWeapon")
    private void enchantSpawnedWeapon(ServerLevelAccessor level, RandomSource random, DifficultyInstance difficulty, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, random, difficulty);
    }

    @WrapMethod(method = "enchantSpawnedArmor")
    private void enchantSpawnedArmor(ServerLevelAccessor level, RandomSource random, EquipmentSlot slot, DifficultyInstance difficulty, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, random, slot, difficulty);
    }

    @WrapMethod(method = "enchantSpawnedEquipment")
    private void enchantSpawnedEquipment(ServerLevelAccessor level, EquipmentSlot slot, RandomSource random, float enchantChance, DifficultyInstance difficulty, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, slot, random, enchantChance, difficulty);
    }

    @WrapMethod(method = "finalizeSpawn")
    private SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnGroupData, Operation<SpawnGroupData> original) {
        if (InteractableHandler.virtualizesInteract()) return spawnGroupData;
        original.call(level, difficulty, spawnType, spawnGroupData);
        return spawnGroupData;
    }

    @WrapMethod(method = "setPersistenceRequired")
    private void setPersistenceRequired(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setDropChance")
    private void setDropChance(EquipmentSlot slot, float chance, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot, chance);
    }

    @WrapMethod(method = "setCanPickUpLoot")
    private void setCanPickUpLoot(boolean canPickUpLoot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(canPickUpLoot);
    }

    @WrapMethod(method = "onOffspringSpawnedFromEgg")
    private void onOffspringSpawnedFromEgg(Player player, Mob child, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, child);
    }

    @WrapMethod(method = "restrictTo")
    private void restrictTo(BlockPos pos, int distance, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, distance);
    }

    @WrapMethod(method = "clearRestriction")
    private void clearRestriction(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "convertTo")
    private <T extends Mob> T convertTo(EntityType<T> entityType, boolean transferInventory, Operation<T> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(entityType, transferInventory);
    }

    @WrapMethod(method = "setLeashData")
    private void setLeashData(Leashable.LeashData leashData, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(leashData);
    }

    @WrapMethod(method = "dropLeash")
    private void dropLeash(boolean broadcastPacket, boolean dropLeash, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(broadcastPacket, dropLeash);
    }

    @WrapMethod(method = "leashTooFarBehaviour")
    private void leashTooFarBehaviour(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "startRiding")
    private boolean startRiding(Entity entity, boolean force, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return force;
        original.call(entity, force);
        return force;
    }

    @WrapMethod(method = "setNoAi")
    private void setNoAi(boolean noAi, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(noAi);
    }

    @WrapMethod(method = "setLeftHanded")
    private void setLeftHanded(boolean leftHanded, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(leftHanded);
    }

    @WrapMethod(method = "setAggressive")
    private void setAggressive(boolean aggressive, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(aggressive);
    }

    @WrapMethod(method = "setBaby")
    private void setBaby(boolean baby, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(baby);
    }

    @WrapMethod(method = "doHurtTarget")
    private boolean doHurtTarget(Entity entity, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(entity);
    }

    @WrapMethod(method = "playAttackSound")
    private void playAttackSound(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "jumpInLiquid")
    private void jumpInLiquid(TagKey<Fluid> fluidTag, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(fluidTag);
    }

    @WrapMethod(method = "jumpInLiquidInternal")
    private void jumpInLiquidInternal(Runnable onSuper, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(onSuper);
    }

    @WrapMethod(method = "jumpInFluid")
    private void jumpInFluid(FluidType type, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(type);
    }

    @WrapMethod(method = "removeFreeWill")
    private void removeFreeWill(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "removeAllGoals")
    private void removeAllGoals(Predicate<Goal> filter, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(filter);
    }

    @WrapMethod(method = "removeAfterChangingDimensions")
    private void removeAfterChangingDimensions(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setSpawnCancelled")
    private void setSpawnCancelled(boolean cancel, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(cancel);
    }
}
