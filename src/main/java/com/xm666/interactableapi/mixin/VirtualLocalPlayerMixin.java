package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BaseCommandBlock;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@OnlyIn(Dist.CLIENT)
@Mixin(LocalPlayer.class)
public class VirtualLocalPlayerMixin {
    @WrapMethod(method = "hurt")
    private boolean hurt(DamageSource source, float amount, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return true;
        return original.call(source, amount);
    }

    @WrapMethod(method = "heal")
    private void heal(float healAmount, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(healAmount);
    }

    @WrapMethod(method = "startRiding")
    private boolean startRiding(Entity entity, boolean force, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return force;
        original.call(entity, force);
        return force;
    }

    @WrapMethod(method = "removeVehicle")
    private void removeVehicle(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "sendPosition")
    private void sendPosition(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "sendIsSprintingIfNeeded")
    private void sendIsSprintingIfNeeded(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "drop")
    private boolean drop(boolean fullStack, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return fullStack;
        original.call(fullStack);
        return fullStack;
    }

    @WrapMethod(method = "swing")
    private void swing(InteractionHand hand, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(hand);
    }

    @WrapMethod(method = "respawn")
    private void respawn(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "actuallyHurt")
    private void actuallyHurt(DamageSource damageSrc, float damageAmount, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(damageSrc, damageAmount);
    }

    @WrapMethod(method = "closeContainer")
    private void closeContainer(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "clientSideCloseContainer")
    private void clientSideCloseContainer(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "hurtTo")
    private void hurtTo(float health, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(health);
    }

    @WrapMethod(method = "onUpdateAbilities")
    private void onUpdateAbilities(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "sendRidingJump")
    private void sendRidingJump(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "sendOpenInventory")
    private void sendOpenInventory(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "removeRecipeHighlight")
    private void removeRecipeHighlight(RecipeHolder<?> recipe, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(recipe);
    }

    @WrapMethod(method = "setPermissionLevel")
    private void setPermissionLevel(int permissionLevel, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(permissionLevel);
    }

    @WrapMethod(method = "displayClientMessage")
    private void displayClientMessage(Component chatComponent, boolean actionBar, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(chatComponent, actionBar);
    }

    @WrapMethod(method = "moveTowardsClosestSpace")
    private void moveTowardsClosestSpace(double x, double z, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, z);
    }

    @WrapMethod(method = "setExperienceValues")
    private void setExperienceValues(float currentXP, int maxXP, int level, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(currentXP, maxXP, level);
    }

    @WrapMethod(method = "sendSystemMessage")
    private void sendSystemMessage(Component component, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(component);
    }

    @WrapMethod(method = "handleEntityEvent")
    private void handleEntityEvent(byte id, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(id);
    }

    @WrapMethod(method = "setShowDeathScreen")
    private void setShowDeathScreen(boolean show, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(show);
    }

    @WrapMethod(method = "setDoLimitedCrafting")
    private void setDoLimitedCrafting(boolean doLimitedCrafting, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(doLimitedCrafting);
    }

    @WrapMethod(method = "playSound")
    private void playSound(SoundEvent sound, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(sound, volume, pitch);
    }

    @WrapMethod(method = "playNotifySound")
    private void playNotifySound(SoundEvent sound, SoundSource source, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(sound, source, volume, pitch);
    }

    @WrapMethod(method = "startUsingItem")
    private void startUsingItem(InteractionHand hand, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(hand);
    }

    @WrapMethod(method = "stopUsingItem")
    private void stopUsingItem(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "onSyncedDataUpdated")
    private void onSyncedDataUpdated(EntityDataAccessor<?> key, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(key);
    }

    @WrapMethod(method = "openTextEdit")
    private void openTextEdit(SignBlockEntity signEntity, boolean isFrontText, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(signEntity, isFrontText);
    }

    @WrapMethod(method = "openMinecartCommandBlock")
    private void openMinecartCommandBlock(BaseCommandBlock commandBlock, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(commandBlock);
    }

    @WrapMethod(method = "openCommandBlock")
    private void openCommandBlock(CommandBlockEntity commandBlock, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(commandBlock);
    }

    @WrapMethod(method = "openStructureBlock")
    private void openStructureBlock(StructureBlockEntity structure, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(structure);
    }

    @WrapMethod(method = "openJigsawBlock")
    private void openJigsawBlock(JigsawBlockEntity jigsawBlockEntity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(jigsawBlockEntity);
    }

    @WrapMethod(method = "openItemGui")
    private void openItemGui(ItemStack stack, InteractionHand hand, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(stack, hand);
    }

    @WrapMethod(method = "crit")
    private void crit(Entity entityHit, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(entityHit);
    }

    @WrapMethod(method = "magicCrit")
    private void magicCrit(Entity entityHit, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(entityHit);
    }

    @WrapMethod(method = "serverAiStep")
    private void serverAiStep(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "resetPos")
    private void resetPos(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "aiStep")
    private void aiStep(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "tickDeath")
    private void tickDeath(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "handleConfusionTransitionEffect")
    private void handleConfusionTransitionEffect(boolean useConfusion, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(useConfusion);
    }

    @WrapMethod(method = "rideTick")
    private void rideTick(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "removeEffectNoUpdate")
    private MobEffectInstance removeEffectNoUpdate(Holder<MobEffect> effect, Operation<MobEffectInstance> original) {
        if (InteractableHandler.protectsEntity()) return null;
        return original.call(effect);
    }

    @WrapMethod(method = "move")
    private void move(MoverType type, Vec3 pos, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(type, pos);
    }

    @WrapMethod(method = "updateAutoJump")
    private void updateAutoJump(float movementX, float movementZ, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(movementX, movementZ);
    }

    @WrapMethod(method = "onGameModeChanged")
    private void onGameModeChanged(GameType gameMode, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(gameMode);
    }

    @WrapMethod(method = "updateIsUnderwater")
    private boolean updateIsUnderwater(Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return false;
        return original.call();
    }

    @WrapMethod(method = "updateTutorialInventoryAction")
    private void updateTutorialInventoryAction(ItemStack carried, ItemStack clicked, ClickAction action, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(carried, clicked, action);
    }
}
