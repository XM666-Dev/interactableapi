package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityInLevelCallback;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
@Mixin(Entity.class)
public class VirtualEntityMixin {
    @WrapMethod(method = "setViewScale")
    private static void setViewScale(double renderDistWeight, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(renderDistWeight);
    }

    @WrapMethod(method = "unRide")
    private void unRide(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "syncPacketPositionCodec")
    private void syncPacketPositionCodec(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "setId")
    private void setId(int id, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(id);
    }

    @WrapMethod(method = "addTag")
    private boolean addTag(String tag, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(tag);
    }

    @WrapMethod(method = "removeTag")
    private boolean removeTag(String tag, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(tag);
    }

    @WrapMethod(method = "kill")
    private void kill(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "discard")
    private void discard(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "remove")
    private void remove(Entity.RemovalReason reason, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(reason);
    }

    @WrapMethod(method = "setPose")
    private void setPose(Pose pose, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pose);
    }

    @WrapMethod(method = "setRot")
    private void setRot(float yRot, float xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(yRot, xRot);
    }

    @WrapMethod(method = "setPos(Lnet/minecraft/world/phys/Vec3;)V")
    private void setPos(Vec3 pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "setPos(DDD)V")
    private void setPos(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "reapplyPosition")
    private void reapplyPosition(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "turn")
    private void turn(double yRot, double xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(yRot, xRot);
    }

    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "baseTick")
    private void baseTick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setSharedFlagOnFire")
    private void setSharedFlagOnFire(boolean isOnFire, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(isOnFire);
    }

    @WrapMethod(method = "checkBelowWorld")
    private void checkBelowWorld(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setPortalCooldown()V")
    private void setPortalCooldown(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setPortalCooldown(I)V")
    private void setPortalCooldown(int portalCooldown, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(portalCooldown);
    }

    @WrapMethod(method = "processPortalCooldown")
    private void processPortalCooldown(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "lavaHurt")
    private void lavaHurt(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "igniteForSeconds")
    private void igniteForSeconds(float seconds, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(seconds);
    }

    @WrapMethod(method = "igniteForTicks")
    private void igniteForTicks(int ticks, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(ticks);
    }

    @WrapMethod(method = "setRemainingFireTicks")
    private void setRemainingFireTicks(int remainingFireTicks, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(remainingFireTicks);
    }

    @WrapMethod(method = "clearFire")
    private void clearFire(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onBelowWorld")
    private void onBelowWorld(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setOnGround")
    private void setOnGround(boolean onGround, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(onGround);
    }

    @WrapMethod(method = "setOnGroundWithMovement")
    private void setOnGroundWithMovement(boolean onGround, Vec3 movement, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(onGround, movement);
    }

    @WrapMethod(method = "checkSupportingBlock")
    private void checkSupportingBlock(boolean onGround, Vec3 movement, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(onGround, movement);
    }

    @WrapMethod(method = "move")
    private void move(MoverType type, Vec3 pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(type, pos);
    }

    @WrapMethod(method = "vibrationAndSoundEffectsFromBlock")
    private boolean vibrationAndSoundEffectsFromBlock(BlockPos pos, BlockState state, boolean playStepSound, boolean broadcastGameEvent, Vec3 entityPos, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return playStepSound;
        return original.call(pos, state, playStepSound, broadcastGameEvent, entityPos);
    }

    @WrapMethod(method = "tryCheckInsideBlocks")
    private void tryCheckInsideBlocks(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playEntityOnFireExtinguishedSound")
    private void playEntityOnFireExtinguishedSound(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "extinguishFire")
    private void extinguishFire(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "processFlappingMovement")
    private void processFlappingMovement(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "limitPistonMovement")
    private Vec3 limitPistonMovement(Vec3 pos, Operation<Vec3> original) {
        if (InteractableHandler.virtualizesInteract()) return Vec3.ZERO;
        return original.call(pos);
    }

    @WrapMethod(method = "applyPistonMovementRestriction")
    private double applyPistonMovementRestriction(Direction.Axis axis, double distance, Operation<Double> original) {
        if (InteractableHandler.virtualizesInteract()) return distance;
        return original.call(axis, distance);
    }

    @WrapMethod(method = "checkInsideBlocks")
    private void checkInsideBlocks(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "adjustSpawnLocation")
    private BlockPos adjustSpawnLocation(ServerLevel level, BlockPos pos, Operation<BlockPos> original) {
        if (InteractableHandler.virtualizesInteract()) return pos;
        return original.call(level, pos);
    }

    @WrapMethod(method = "gameEvent(Lnet/minecraft/core/Holder;)V")
    private void gameEvent(Holder<GameEvent> gameEvent, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(gameEvent);
    }

    @WrapMethod(method = "gameEvent(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/Entity;)V")
    private void gameEvent(Holder<GameEvent> gameEvent, Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(gameEvent, entity);
    }

    @WrapMethod(method = "walkingStepSound")
    private void walkingStepSound(BlockPos pos, BlockState state, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, state);
    }

    @WrapMethod(method = "waterSwimSound")
    private void waterSwimSound(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playCombinationStepSounds")
    private void playCombinationStepSounds(BlockState primaryState, BlockState secondaryState, BlockPos primaryPos, BlockPos secondaryPos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(primaryState, secondaryState, primaryPos, secondaryPos);
    }

    @WrapMethod(method = "playMuffledStepSound")
    private void playMuffledStepSound(BlockState state, BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(state, pos);
    }

    @WrapMethod(method = "playStepSound")
    private void playStepSound(BlockPos pos, BlockState state, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, state);
    }

    @WrapMethod(method = "playAmethystStepSound")
    private void playAmethystStepSound(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "playSwimSound")
    private void playSwimSound(float volume, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(volume);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/sounds/SoundEvent;FF)V")
    private void playSound(SoundEvent sound, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(sound, volume, pitch);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/sounds/SoundEvent;)V")
    private void playSound(SoundEvent sound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(sound);
    }

    @WrapMethod(method = "setSilent")
    private void setSilent(boolean isSilent, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(isSilent);
    }

    @WrapMethod(method = "setNoGravity")
    private void setNoGravity(boolean noGravity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(noGravity);
    }

    @WrapMethod(method = "applyGravity")
    private void applyGravity(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "checkFallDamage")
    private void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(y, onGround, state, pos);
    }

    @WrapMethod(method = "causeFallDamage")
    private boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(fallDistance, multiplier, source);
    }

    @WrapMethod(method = "updateSwimming")
    private void updateSwimming(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateInWaterStateAndDoFluidPushing")
    private boolean updateInWaterStateAndDoFluidPushing(Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call();
    }

    @WrapMethod(method = "updateInWaterStateAndDoWaterCurrentPushing")
    private void updateInWaterStateAndDoWaterCurrentPushing(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "updateFluidOnEyes")
    private void updateFluidOnEyes(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "doWaterSplashEffect")
    private void doWaterSplashEffect(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "spawnSprintParticle")
    private void spawnSprintParticle(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "moveRelative")
    private void moveRelative(float amount, Vec3 relative, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(amount, relative);
    }

    @WrapMethod(method = "absMoveTo(DDDFF)V")
    private void absMoveTo(double x, double y, double z, float yRot, float xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z, yRot, xRot);
    }

    @WrapMethod(method = "absRotateTo")
    private void absRotateTo(float yRot, float xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(yRot, xRot);
    }

    @WrapMethod(method = "absMoveTo(DDD)V")
    private void absMoveTo(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "moveTo(Lnet/minecraft/world/phys/Vec3;)V")
    private void moveTo(Vec3 pos, Operation<Vec3> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "moveTo(DDD)V")
    private void moveTo(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "moveTo(Lnet/minecraft/core/BlockPos;FF)V")
    private void moveTo(BlockPos pos, float yRot, float xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, yRot, xRot);
    }

    @WrapMethod(method = "moveTo(Lnet/minecraft/world/phys/Vec3;FF)V")
    private void moveTo(Vec3 pos, float yRot, float xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, yRot, xRot);
    }

    @WrapMethod(method = "moveTo(DDDFF)V")
    private void moveTo(double x, double y, double z, float yRot, float xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z, yRot, xRot);
    }

    @WrapMethod(method = "setOldPosAndRot")
    private void setOldPosAndRot(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "push(Lnet/minecraft/world/entity/Entity;)V")
    private void push(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "push(Lnet/minecraft/world/phys/Vec3;)V")
    private void push(Vec3 pos, Operation<Vec3> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "push(DDD)V")
    private void push(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "markHurt")
    private void markHurt(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "hurt")
    private boolean hurt(DamageSource source, float amount, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(source, amount);
    }

    @WrapMethod(method = "awardKillScore")
    private void awardKillScore(Entity killed, int scoreValue, DamageSource source, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(killed, scoreValue, source);
    }

    @WrapMethod(method = "load")
    private void load(CompoundTag compound, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(compound);
    }

    @WrapMethod(method = "spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;")
    private ItemEntity spawnAtLocation(ItemLike item, Operation<ItemEntity> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(item);
    }

    @WrapMethod(method = "spawnAtLocation(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/entity/item/ItemEntity;")
    private ItemEntity spawnAtLocation(ItemLike item, int offsetY, Operation<ItemEntity> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(item, offsetY);
    }

    @WrapMethod(method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/item/ItemEntity;")
    private ItemEntity spawnAtLocation(ItemStack stack, Operation<ItemEntity> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(stack);
    }

    @WrapMethod(method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;")
    private ItemEntity spawnAtLocation(ItemStack stack, float offsetY, Operation<ItemEntity> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(stack, offsetY);
    }

    @WrapMethod(method = "rideTick")
    private void rideTick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "positionRider(Lnet/minecraft/world/entity/Entity;)V")
    private void positionRider(Entity passenger, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(passenger);
    }

    @WrapMethod(method = "positionRider(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity$MoveFunction;)V")
    private void positionRider(Entity passenger, Entity.MoveFunction callback, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(passenger, callback);
    }

    @WrapMethod(method = "startRiding(Lnet/minecraft/world/entity/Entity;)Z")
    private boolean startRiding(Entity vehicle, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(vehicle);
    }

    @WrapMethod(method = "startRiding(Lnet/minecraft/world/entity/Entity;Z)Z")
    private boolean startRiding(Entity vehicle, boolean force, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(vehicle, force);
    }

    @WrapMethod(method = "ejectPassengers")
    private void ejectPassengers(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "removeVehicle")
    private void removeVehicle(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "stopRiding")
    private void stopRiding(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "addPassenger")
    private void addPassenger(Entity passenger, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(passenger);
    }

    @WrapMethod(method = "removePassenger")
    private void removePassenger(Entity passenger, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(passenger);
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

    @WrapMethod(method = "setAsInsidePortal")
    private void setAsInsidePortal(Portal portal, BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(portal, pos);
    }

    @WrapMethod(method = "handlePortal")
    private void handlePortal(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "lerpMotion")
    private void lerpMotion(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "handleEntityEvent")
    private void handleEntityEvent(byte id, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(id);
    }

    @WrapMethod(method = "setShiftKeyDown")
    private void setShiftKeyDown(boolean keyDown, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(keyDown);
    }

    @WrapMethod(method = "setSprinting")
    private void setSprinting(boolean sprinting, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(sprinting);
    }

    @WrapMethod(method = "setSwimming")
    private void setSwimming(boolean swimming, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(swimming);
    }

    @WrapMethod(method = "setGlowingTag")
    private void setGlowingTag(boolean hasGlowingTag, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hasGlowingTag);
    }

    @WrapMethod(method = "setInvisible")
    private void setInvisible(boolean invisible, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(invisible);
    }

    @WrapMethod(method = "setSharedFlag")
    private void setSharedFlag(int flag, boolean set, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(flag, set);
    }

    @WrapMethod(method = "setAirSupply")
    private void setAirSupply(int air, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(air);
    }

    @WrapMethod(method = "setTicksFrozen")
    private void setTicksFrozen(int ticksFrozen, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(ticksFrozen);
    }

    @WrapMethod(method = "thunderHit")
    private void thunderHit(ServerLevel level, LightningBolt lightning, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level, lightning);
    }

    @WrapMethod(method = "onAboveBubbleCol")
    private void onAboveBubbleCol(boolean downwards, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(downwards);
    }

    @WrapMethod(method = "onInsideBubbleColumn")
    private void onInsideBubbleColumn(boolean downwards, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(downwards);
    }

    @WrapMethod(method = "checkSlowFallDistance")
    private void checkSlowFallDistance(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "resetFallDistance")
    private void resetFallDistance(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "moveTowardsClosestSpace")
    private void moveTowardsClosestSpace(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "makeStuckInBlock")
    private void makeStuckInBlock(BlockState state, Vec3 motionMultiplier, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(state, motionMultiplier);
    }

    @WrapMethod(method = "setInvulnerable")
    private void setInvulnerable(boolean isInvulnerable, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(isInvulnerable);
    }

    @WrapMethod(method = "copyPosition")
    private void copyPosition(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "restoreFrom")
    private void restoreFrom(Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity);
    }

    @WrapMethod(method = "changeDimension")
    private Entity changeDimension(DimensionTransition transition, Operation<Entity> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(transition);
    }

    @WrapMethod(method = "placePortalTicket")
    private void placePortalTicket(BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "removeAfterChangingDimensions")
    private void removeAfterChangingDimensions(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setUUID")
    private void setUUID(UUID uniqueId, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(uniqueId);
    }

    @WrapMethod(method = "setCustomName")
    private void setCustomName(Component name, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(name);
    }

    @WrapMethod(method = "setCustomNameVisible")
    private void setCustomNameVisible(boolean alwaysRenderNameTag, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(alwaysRenderNameTag);
    }

    @WrapMethod(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z")
    private boolean teleportTo(ServerLevel level, double x, double y, double z, Set<RelativeMovement> relativeMovements, float yRot, float xRot, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(level, x, y, z, relativeMovements, yRot, xRot);
    }

    @WrapMethod(method = "dismountTo")
    private void dismountTo(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "teleportTo(DDD)V")
    private void teleportTo(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "teleportPassengers")
    private void teleportPassengers(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "teleportRelative")
    private void teleportRelative(double dx, double dy, double dz, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(dx, dy, dz);
    }

    @WrapMethod(method = "onSyncedDataUpdated(Lnet/minecraft/network/syncher/EntityDataAccessor;)V")
    private void onSyncedDataUpdated(EntityDataAccessor<?> key, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(key);
    }

    @WrapMethod(method = "refreshDimensions")
    private void refreshDimensions(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "fudgePositionAfterSizeChange")
    private boolean fudgePositionAfterSizeChange(EntityDimensions dimensions, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(dimensions);
    }

    @WrapMethod(method = "setBoundingBox")
    private void setBoundingBox(AABB bb, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(bb);
    }

    @WrapMethod(method = "lookAt")
    private void lookAt(EntityAnchorArgument.Anchor anchor, Vec3 target, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(anchor, target);
    }

    @WrapMethod(method = "updateFluidHeightAndDoFluidPushing()V")
    private void updateFluidHeightAndDoFluidPushing(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V")
    private void setDeltaMovement(Vec3 deltaMovement, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(deltaMovement);
    }

    @WrapMethod(method = "addDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V")
    private void addDeltaMovement(Vec3 addend, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(addend);
    }

    @WrapMethod(method = "setDeltaMovement(DDD)V")
    private void setDeltaMovement(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "setPosRaw(DDD)V")
    private void setPosRaw(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "recreateFromPacket")
    private void recreateFromPacket(ClientboundAddEntityPacket packet, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(packet);
    }

    @WrapMethod(method = "setIsInPowderSnow")
    private void setIsInPowderSnow(boolean isInPowderSnow, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(isInPowderSnow);
    }

    @WrapMethod(method = "setYRot")
    private void setYRot(float yRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(yRot);
    }

    @WrapMethod(method = "setXRot")
    private void setXRot(float xRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(xRot);
    }

    @WrapMethod(method = "setRemoved")
    private void setRemoved(Entity.RemovalReason removalReason, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(removalReason);
    }

    @WrapMethod(method = "unsetRemoved")
    private void unsetRemoved(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setLevelCallback")
    private void setLevelCallback(EntityInLevelCallback levelCallback, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(levelCallback);
    }

    @WrapMethod(method = "captureDrops(Ljava/util/Collection;)Ljava/util/Collection;")
    private Collection<ItemEntity> captureDrops(Collection<ItemEntity> value, Operation<Collection<ItemEntity>> original) {
        if (InteractableHandler.virtualizesInteract()) return value;
        return original.call(value);
    }

    @WrapMethod(method = "getPersistentData")
    private CompoundTag getPersistentData(Operation<CompoundTag> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call();
    }

    @WrapMethod(method = "canTrample")
    private boolean canTrample(BlockState state, BlockPos pos, float fallDistance, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return false;
        return original.call(state, pos, fallDistance);
    }

    @WrapMethod(method = "onAddedToLevel")
    private void onAddedToLevel(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "onRemovedFromLevel")
    private void onRemovedFromLevel(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "revive")
    private void revive(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setFluidTypeHeight")
    private void setFluidTypeHeight(FluidType type, double height, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(type, height);
    }

    @WrapMethod(method = "setData")
    private <T> T setData(AttachmentType<T> type, T data, Operation<T> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(type, data);
    }

    @WrapMethod(method = "syncData")
    private void syncData(AttachmentType<?> type, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(type);
    }

    @WrapMethod(method = "setLevel")
    private void setLevel(Level level, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(level);
    }

    @WrapMethod(method = "lerpPositionAndRotationStep")
    private void lerpPositionAndRotationStep(int steps, double targetX, double targetY, double targetZ, double targetYRot, double targetXRot, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(steps, targetX, targetY, targetZ, targetYRot, targetXRot);
    }
}
