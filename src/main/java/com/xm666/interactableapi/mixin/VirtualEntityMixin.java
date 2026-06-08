package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
@Mixin(Entity.class)
public class VirtualEntityMixin {
    @WrapMethod(method = "unRide")
    private void wrapUnRide(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "addTag")
    private boolean wrapAddTag(String tag, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return true;
        return original.call(tag);
    }

    @WrapMethod(method = "removeTag")
    private boolean wrapRemoveTag(String tag, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return false;
        return original.call(tag);
    }

    @WrapMethod(method = "kill")
    private void wrapKill(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "remove")
    private void wrapRemove(Entity.RemovalReason reason, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(reason);
    }

    @WrapMethod(method = "removeVehicle")
    private void wrapRemoveVehicle(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "syncPacketPositionCodec")
    private void wrapSyncPacketPositionCodec(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "onClientRemoval")
    private void wrapOnClientRemoval(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "setId")
    private void wrapSetId(int id, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(id);
    }

    @WrapMethod(method = "setPose")
    private void wrapSetPose(Pose pose, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(pose);
    }

    @WrapMethod(method = "setPos(Lnet/minecraft/world/phys/Vec3;)V")
    private void wrapSetPos(Vec3 pos, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(pos);
    }

    @WrapMethod(method = "setPos(DDD)V")
    private void wrapSetPos(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "turn")
    private void wrapTurn(double yRot, double xRot, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(yRot, xRot);
    }

    @WrapMethod(method = "tick")
    private void wrapTick(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "baseTick")
    private void wrapBaseTick(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "setSharedFlagOnFire")
    private void wrapSetSharedFlagOnFire(boolean isOnFire, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(isOnFire);
    }

    @WrapMethod(method = "checkBelowWorld")
    private void wrapCheckBelowWorld(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "setPortalCooldown()V")
    private void wrapSetPortalCooldown(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "setPortalCooldown(I)V")
    private void wrapSetPortalCooldownInt(int portalCooldown, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(portalCooldown);
    }

    @WrapMethod(method = "setRemainingFireTicks")
    private void wrapSetRemainingFireTicks(int remainingFireTicks, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(remainingFireTicks);
    }

    @WrapMethod(method = "setOnGround")
    private void wrapSetOnGround(boolean onGround, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(onGround);
    }

    @WrapMethod(method = "setOnGroundWithMovement")
    private void wrapSetOnGroundWithMovement(boolean onGround, Vec3 movement, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(onGround, movement);
    }

    @WrapMethod(method = "setSilent")
    private void wrapSetSilent(boolean isSilent, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(isSilent);
    }

    @WrapMethod(method = "setNoGravity")
    private void wrapSetNoGravity(boolean noGravity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(noGravity);
    }

    @WrapMethod(method = "setAsInsidePortal")
    private void wrapSetAsInsidePortal(Portal portal, BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(portal, pos);
    }

    @WrapMethod(method = "updateSwimming")
    private void wrapUpdateSwimming(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "push(Lnet/minecraft/world/entity/Entity;)V")
    private void wrapPushEntity(Entity entity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(entity);
    }

    @WrapMethod(method = "push(Lnet/minecraft/world/phys/Vec3;)V")
    private void wrapPushVec3(Vec3 vector, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(vector);
    }

    @WrapMethod(method = "push(DDD)V")
    private void wrapPushDouble(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z")
    private boolean wrapTeleportTo(ServerLevel level, double x, double y, double z, Set<RelativeMovement> relativeMovements, float yRot, float xRot, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return true;
        return original.call(level, x, y, z, relativeMovements, yRot, xRot);
    }

    @WrapMethod(method = "teleportTo(DDD)V")
    private void wrapTeleportTo(double x, double y, double z, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, y, z);
    }

    @WrapMethod(method = "teleportRelative")
    private void wrapTeleportRelative(double dx, double dy, double dz, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(dx, dy, dz);
    }

    @WrapMethod(method = "setShiftKeyDown")
    private void wrapSetShiftKeyDown(boolean keyDown, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(keyDown);
    }

    @WrapMethod(method = "setSprinting")
    private void wrapSetSprinting(boolean sprinting, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(sprinting);
    }

    @WrapMethod(method = "setSwimming")
    private void wrapSetSwimming(boolean swimming, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(swimming);
    }

    @WrapMethod(method = "setInvisible")
    private void wrapSetInvisible(boolean invisible, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(invisible);
    }

    @WrapMethod(method = "setAirSupply")
    private void wrapSetAirSupply(int air, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(air);
    }

    @WrapMethod(method = "setTicksFrozen")
    private void wrapSetTicksFrozen(int ticksFrozen, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(ticksFrozen);
    }

    @WrapMethod(method = "setYHeadRot")
    private void wrapSetYHeadRot(float yHeadRot, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(yHeadRot);
    }

    @WrapMethod(method = "setYBodyRot")
    private void wrapSetYBodyRot(float yBodyRot, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(yBodyRot);
    }

    @WrapMethod(method = "applyGravity")
    private void wrapApplyGravity(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "checkFallDamage")
    private void wrapCheckFallDamage(double y, boolean onGround, BlockState state, BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(y, onGround, state, pos);
    }
}
