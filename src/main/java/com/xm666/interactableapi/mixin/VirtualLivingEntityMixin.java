package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.EffectCure;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mixin(LivingEntity.class)
public class VirtualLivingEntityMixin {
    @WrapMethod(method = "kill")
    private void kill(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "checkFallDamage")
    private void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(y, onGround, state, pos);
    }

    @WrapMethod(method = "baseTick")
    private void baseTick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "removeFrost")
    private void removeFrost(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "tryAddFrost")
    private void tryAddFrost(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onChangedBlock")
    private void onChangedBlock(ServerLevel level, BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, pos);
    }

    @WrapMethod(method = "tickDeath")
    private void tickDeath(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setLastHurtByPlayer")
    private void setLastHurtByPlayer(Player player, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player);
    }

    @WrapMethod(method = "setLastHurtByMob")
    private void setLastHurtByMob(LivingEntity livingEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(livingEntity);
    }

    @WrapMethod(method = "setLastHurtMob")
    private void setLastHurtMob(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "setNoActionTime")
    private void setNoActionTime(int idleTime, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(idleTime);
    }

    @WrapMethod(method = "setDiscardFriction")
    private void setDiscardFriction(boolean discardFriction, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(discardFriction);
    }

    @WrapMethod(method = "onEquipItem")
    private void onEquipItem(EquipmentSlot slot, ItemStack oldItem, ItemStack newItem, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot, oldItem, newItem);
    }

    @WrapMethod(method = "remove")
    private void remove(Entity.RemovalReason reason, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(reason);
    }

    @WrapMethod(method = "triggerOnDeathMobEffects")
    private void triggerOnDeathMobEffects(Entity.RemovalReason removalReason, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(removalReason);
    }

    @WrapMethod(method = "readAdditionalSaveData")
    private void readAdditionalSaveData(CompoundTag compound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(compound);
    }

    @WrapMethod(method = "tickEffects")
    private void tickEffects(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateInvisibilityStatus")
    private void updateInvisibilityStatus(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateSynchronizedMobEffectParticles")
    private void updateSynchronizedMobEffectParticles(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateGlowingStatus")
    private void updateGlowingStatus(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "removeEffectParticles")
    private void removeEffectParticles(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "removeAllEffects")
    private boolean removeAllEffects(Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call();
    }

    @WrapMethod(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z")
    private boolean addEffect(MobEffectInstance effectInstance, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(effectInstance);
    }

    @WrapMethod(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")
    private boolean addEffect(MobEffectInstance effectInstance, Entity entity, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(effectInstance, entity);
    }

    @WrapMethod(method = "forceAddEffect")
    private void forceAddEffect(MobEffectInstance instance, Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(instance, entity);
    }

    @WrapMethod(method = "removeEffectNoUpdate")
    private MobEffectInstance removeEffectNoUpdate(Holder<MobEffect> effect, Operation<MobEffectInstance> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(effect);
    }

    @WrapMethod(method = "removeEffect")
    private boolean removeEffect(Holder<MobEffect> effect, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(effect);
    }

    @WrapMethod(method = "onEffectAdded")
    private void onEffectAdded(MobEffectInstance effectInstance, Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(effectInstance, entity);
    }

    @WrapMethod(method = "sendEffectToPassengers")
    private void sendEffectToPassengers(MobEffectInstance effectInstance, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(effectInstance);
    }

    @WrapMethod(method = "onEffectUpdated")
    private void onEffectUpdated(MobEffectInstance effectInstance, boolean forced, Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(effectInstance, forced, entity);
    }

    @WrapMethod(method = "onEffectRemoved")
    private void onEffectRemoved(MobEffectInstance effectInstance, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(effectInstance);
    }

    @WrapMethod(method = "refreshDirtyAttributes")
    private void refreshDirtyAttributes(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onAttributeUpdated")
    private void onAttributeUpdated(Holder<Attribute> attribute, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(attribute);
    }

    @WrapMethod(method = "heal")
    private void heal(float healAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(healAmount);
    }

    @WrapMethod(method = "setHealth")
    private void setHealth(float health, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(health);
    }

    @WrapMethod(method = "hurt")
    private boolean hurt(DamageSource source, float amount, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(source, amount);
    }

    @WrapMethod(method = "blockUsingShield")
    private void blockUsingShield(LivingEntity attacker, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(attacker);
    }

    @WrapMethod(method = "blockedByShield")
    private void blockedByShield(LivingEntity defender, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(defender);
    }

    @WrapMethod(method = "checkTotemDeathProtection")
    private boolean checkTotemDeathProtection(DamageSource damageSource, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(damageSource);
    }

    @WrapMethod(method = "playHurtSound")
    private void playHurtSound(DamageSource source, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(source);
    }

    @WrapMethod(method = "makeSound")
    private void makeSound(SoundEvent sound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(sound);
    }

    @WrapMethod(method = "breakItem")
    private void breakItem(ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stack);
    }

    @WrapMethod(method = "die")
    private void die(DamageSource damageSource, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource);
    }

    @WrapMethod(method = "createWitherRose")
    private void createWitherRose(LivingEntity entitySource, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entitySource);
    }

    @WrapMethod(method = "dropAllDeathLoot")
    private void dropAllDeathLoot(ServerLevel p_level, DamageSource damageSource, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(p_level, damageSource);
    }

    @WrapMethod(method = "dropEquipment")
    private void dropEquipment(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "dropExperience")
    private void dropExperience(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "dropCustomDeathLoot")
    private void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, damageSource, recentlyHit);
    }

    @WrapMethod(method = "dropFromLootTable")
    private void dropFromLootTable(DamageSource damageSource, boolean hitByPlayer, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, hitByPlayer);
    }

    @WrapMethod(method = "knockback")
    private void knockback(double strength, double x, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(strength, x, z);
    }

    @WrapMethod(method = "indicateDamage")
    private void indicateDamage(double xDistance, double zDistance, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(xDistance, zDistance);
    }

    @WrapMethod(method = "skipDropExperience")
    private void skipDropExperience(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onClimbable")
    private boolean onClimbable(Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call();
    }

    @WrapMethod(method = "causeFallDamage")
    private boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(fallDistance, multiplier, source);
    }

    @WrapMethod(method = "playBlockFallSound")
    private void playBlockFallSound(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "animateHurt")
    private void animateHurt(float yaw, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(yaw);
    }

    @WrapMethod(method = "hurtArmor")
    private void hurtArmor(DamageSource damageSource, float damageAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, damageAmount);
    }

    @WrapMethod(method = "hurtHelmet")
    private void hurtHelmet(DamageSource damageSource, float damageAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, damageAmount);
    }

    @WrapMethod(method = "hurtCurrentlyUsedShield")
    private void hurtCurrentlyUsedShield(float damageAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageAmount);
    }

    @WrapMethod(method = "doHurtEquipment")
    private void doHurtEquipment(DamageSource damageSource, float damageAmount, EquipmentSlot[] slots, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, damageAmount, slots);
    }

    @WrapMethod(method = "getDamageAfterArmorAbsorb")
    private float getDamageAfterArmorAbsorb(DamageSource damageSource, float damageAmount, Operation<Float> original) {
        if (InteractableHandler.virtualizesInteract()) return damageAmount;
        original.call(damageSource, damageAmount);
        return damageAmount;
    }

    @WrapMethod(method = "getDamageAfterMagicAbsorb")
    private float getDamageAfterMagicAbsorb(DamageSource damageSource, float damageAmount, Operation<Float> original) {
        if (InteractableHandler.virtualizesInteract()) return damageAmount;
        original.call(damageSource, damageAmount);
        return damageAmount;
    }

    @WrapMethod(method = "actuallyHurt")
    private void actuallyHurt(DamageSource damageSource, float damageAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource, damageAmount);
    }

    @WrapMethod(method = "setArrowCount")
    private void setArrowCount(int count, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(count);
    }

    @WrapMethod(method = "setStingerCount")
    private void setStingerCount(int stingerCount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stingerCount);
    }

    @WrapMethod(method = "swing(Lnet/minecraft/world/InteractionHand;)V")
    private void swing(InteractionHand hand, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hand);
    }

    @WrapMethod(method = "swing(Lnet/minecraft/world/InteractionHand;Z)V")
    private void swing(InteractionHand hand, boolean updateSelf, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hand, updateSelf);
    }

    @WrapMethod(method = "handleDamageEvent")
    private void handleDamageEvent(DamageSource damageSource, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(damageSource);
    }

    @WrapMethod(method = "handleEntityEvent")
    private void handleEntityEvent(byte id, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(id);
    }

    @WrapMethod(method = "makePoofParticles")
    private void makePoofParticles(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "swapHandItems")
    private void swapHandItems(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onBelowWorld")
    private void onBelowWorld(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateSwingTime")
    private void updateSwingTime(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setItemInHand")
    private void setItemInHand(InteractionHand hand, ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hand, stack);
    }

    @WrapMethod(method = "setItemSlot")
    private void setItemSlot(EquipmentSlot par1, ItemStack par2, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(par1, par2);
    }

    @WrapMethod(method = "verifyEquippedItem")
    private void verifyEquippedItem(ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stack);
    }

    @WrapMethod(method = "setSprinting")
    private void setSprinting(boolean sprinting, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(sprinting);
    }

    @WrapMethod(method = "push")
    private void push(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "dismountVehicle")
    private void dismountVehicle(Entity vehicle, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(vehicle);
    }

    @WrapMethod(method = "jumpFromGround")
    private void jumpFromGround(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "goDownInWater")
    private void goDownInWater(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "jumpInLiquid")
    private void jumpInLiquid(TagKey<Fluid> fluidTag, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(fluidTag);
    }

    @WrapMethod(method = "travel")
    private void travel(Vec3 travelVector, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(travelVector);
    }

    @WrapMethod(method = "travelRidden")
    private void travelRidden(Player player, Vec3 travelVector, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, travelVector);
    }

    @WrapMethod(method = "tickRidden")
    private void tickRidden(Player player, Vec3 travelVector, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, travelVector);
    }

    @WrapMethod(method = "calculateEntityAnimation")
    private void calculateEntityAnimation(boolean includeHeight, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(includeHeight);
    }

    @WrapMethod(method = "updateWalkAnimation")
    private void updateWalkAnimation(float partialTick, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(partialTick);
    }

    @WrapMethod(method = "handleRelativeFrictionAndCalculateMovement")
    private Vec3 handleRelativeFrictionAndCalculateMovement(Vec3 deltaMovement, float friction, Operation<Vec3> original) {
        if (InteractableHandler.virtualizesInteract()) return deltaMovement;
        original.call(deltaMovement, friction);
        return deltaMovement;
    }

    @WrapMethod(method = "handleOnClimbable")
    private Vec3 handleOnClimbable(Vec3 deltaMovement, Operation<Vec3> original) {
        if (InteractableHandler.virtualizesInteract()) return deltaMovement;
        original.call(deltaMovement);
        return deltaMovement;
    }

    @WrapMethod(method = "setSpeed")
    private void setSpeed(float speed, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(speed);
    }

    @WrapMethod(method = "doHurtTarget")
    private boolean doHurtTarget(Entity target, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(target);
    }

    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "detectEquipmentUpdates")
    private void detectEquipmentUpdates(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "collectEquipmentChanges")
    private Map<EquipmentSlot, ItemStack> collectEquipmentChanges(Operation<Map<EquipmentSlot, ItemStack>> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call();
    }

    @WrapMethod(method = "handleHandSwap")
    private void handleHandSwap(Map<EquipmentSlot, ItemStack> hands, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hands);
    }

    @WrapMethod(method = "handleEquipmentChanges")
    private void handleEquipmentChanges(Map<EquipmentSlot, ItemStack> equipments, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(equipments);
    }

    @WrapMethod(method = "setLastArmorItem")
    private void setLastArmorItem(EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot, stack);
    }

    @WrapMethod(method = "setLastHandItem")
    private void setLastHandItem(EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(slot, stack);
    }

    @WrapMethod(method = "tickHeadTurn")
    private float tickHeadTurn(float yRot, float animStep, Operation<Float> original) {
        if (InteractableHandler.virtualizesInteract()) return yRot;
        original.call(yRot, animStep);
        return yRot;
    }

    @WrapMethod(method = "aiStep")
    private void aiStep(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateFallFlying")
    private void updateFallFlying(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "serverAiStep")
    private void serverAiStep(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "pushEntities")
    private void pushEntities(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "checkAutoSpinAttack")
    private void checkAutoSpinAttack(AABB boundingBoxBeforeSpin, AABB boundingBoxAfterSpin, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(boundingBoxBeforeSpin, boundingBoxAfterSpin);
    }

    @WrapMethod(method = "doPush")
    private void doPush(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "doAutoAttackOnTouch")
    private void doAutoAttackOnTouch(LivingEntity target, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(target);
    }

    @WrapMethod(method = "stopRiding")
    private void stopRiding(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "rideTick")
    private void rideTick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "lerpTo")
    private void lerpTo(double x, double y, double z, float yRot, float xRot, int steps, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z, yRot, xRot, steps);
    }

    @WrapMethod(method = "lerpHeadTo")
    private void lerpHeadTo(float yaw, int pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(yaw, pitch);
    }

    @WrapMethod(method = "setJumping")
    private void setJumping(boolean jumping, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(jumping);
    }

    @WrapMethod(method = "onItemPickup")
    private void onItemPickup(ItemEntity itemEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(itemEntity);
    }

    @WrapMethod(method = "take")
    private void take(Entity entity, int amount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity, amount);
    }

    @WrapMethod(method = "setYHeadRot")
    private void setYHeadRot(float rotation, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(rotation);
    }

    @WrapMethod(method = "setYBodyRot")
    private void setYBodyRot(float offset, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(offset);
    }

    @WrapMethod(method = "setAbsorptionAmount")
    private void setAbsorptionAmount(float absorptionAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(absorptionAmount);
    }

    @WrapMethod(method = "internalSetAbsorptionAmount")
    private void internalSetAbsorptionAmount(float absorptionAmount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(absorptionAmount);
    }

    @WrapMethod(method = "onEnterCombat")
    private void onEnterCombat(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onLeaveCombat")
    private void onLeaveCombat(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateEffectVisibility")
    private void updateEffectVisibility(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updatingUsingItem")
    private void updatingUsingItem(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateUsingItem")
    private void updateUsingItem(ItemStack usingItem, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(usingItem);
    }

    @WrapMethod(method = "updateSwimAmount")
    private void updateSwimAmount(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setLivingEntityFlag")
    private void setLivingEntityFlag(int key, boolean value, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(key, value);
    }

    @WrapMethod(method = "startUsingItem")
    private void startUsingItem(InteractionHand hand, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hand);
    }

    @WrapMethod(method = "onSyncedDataUpdated")
    private void onSyncedDataUpdated(EntityDataAccessor<?> key, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(key);
    }

    @WrapMethod(method = "lookAt")
    private void lookAt(EntityAnchorArgument.Anchor anchor, Vec3 target, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(anchor, target);
    }

    @WrapMethod(method = "triggerItemUseEffects")
    private void triggerItemUseEffects(ItemStack stack, int amount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stack, amount);
    }

    @WrapMethod(method = "spawnItemParticles")
    private void spawnItemParticles(ItemStack stack, int amount, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(stack, amount);
    }

    @WrapMethod(method = "completeUsingItem")
    private void completeUsingItem(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "releaseUsingItem")
    private void releaseUsingItem(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "stopUsingItem")
    private void stopUsingItem(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "randomTeleport")
    private boolean randomTeleport(double x, double y, double z, boolean broadcastTeleport, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return broadcastTeleport;
        original.call(x, y, z, broadcastTeleport);
        return broadcastTeleport;
    }

    @WrapMethod(method = "setRecordPlayingNearby")
    private void setRecordPlayingNearby(BlockPos jukebox, boolean partyParrot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(jukebox, partyParrot);
    }

    @WrapMethod(method = "setSleepingPos")
    private void setSleepingPos(BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "clearSleepingPos")
    private void clearSleepingPos(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "startSleeping")
    private void startSleeping(BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "setPosToBed")
    private void setPosToBed(BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "stopSleeping")
    private void stopSleeping(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;")
    private ItemStack eat(Level level, ItemStack food, Operation<ItemStack> original) {
        if (InteractableHandler.virtualizesInteract()) return food;
        original.call(level, food);
        return food;
    }

    @WrapMethod(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;")
    private ItemStack eat(Level level, ItemStack food, FoodProperties foodProperties, Operation<ItemStack> original) {
        if (InteractableHandler.virtualizesInteract()) return food;
        original.call(level, food, foodProperties);
        return food;
    }

    @WrapMethod(method = "addEatEffect")
    private void addEatEffect(FoodProperties foodProperties, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(foodProperties);
    }

    @WrapMethod(method = "onEquippedItemBroken")
    private void onEquippedItemBroken(Item item, EquipmentSlot slot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(item, slot);
    }

    @WrapMethod(method = "removeEffectsCuredBy")
    private boolean removeEffectsCuredBy(EffectCure cure, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(cure);
    }

    @WrapMethod(method = "recreateFromPacket")
    private void recreateFromPacket(ClientboundAddEntityPacket packet, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(packet);
    }

    @WrapMethod(method = "lerpHeadRotationStep")
    private void lerpHeadRotationStep(int lerpHeadSteps, double lerpYHeadRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(lerpHeadSteps, lerpYHeadRot);
    }

    @WrapMethod(method = "igniteForTicks")
    private void igniteForTicks(int ticks, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(ticks);
    }
}
