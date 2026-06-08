package com.xm666.interactableapi.virtual;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.StatsCounter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BaseCommandBlock;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings({"DataFlowIssue"})
public class VirtualLocalPlayer extends LocalPlayer {
    @SuppressWarnings("DataFlowIssue")
    public VirtualLocalPlayer(ClientLevel clientLevel, Minecraft mc) {
        super(mc, clientLevel, mc.getConnection(), mc.player.getStats(), mc.player.getRecipeBook(), mc.player.isShiftKeyDown(), mc.player.isSprinting());
        this.foodData = new VirtualFoodData(Minecraft.getInstance().player.getFoodData());
    }

    public VirtualLocalPlayer(ClientLevel clientLevel) {
        this(clientLevel, Minecraft.getInstance());
    }

    @Override
    public ItemStack getItemInHand(InteractionHand hand) {
        return new VirtualItemStack(Minecraft.getInstance().player.getItemInHand(hand));
    }

    @Override
    public ItemCooldowns getCooldowns() {
        return new VirtualItemCooldowns(Minecraft.getInstance().player.getCooldowns());
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return true;
    }

    @Override
    public void heal(float healAmount) {
    }

    @Override
    public boolean startRiding(Entity entity, boolean force) {
        return true;
    }

    @Override
    public void removeVehicle() {
    }

    @Override
    public float getViewXRot(float partialTick) {
        return Minecraft.getInstance().player.getViewXRot(partialTick);
    }

    @Override
    public float getViewYRot(float partialTick) {
        return Minecraft.getInstance().player.getViewYRot(partialTick);
    }

    @Override
    public void tick() {
    }

    @Override
    public float getCurrentMood() {
        return Minecraft.getInstance().player.getCurrentMood();
    }

    @Override
    public boolean drop(boolean fullStack) {
        return true;
    }

    @Override
    public void swing(InteractionHand hand) {
    }

    @Override
    public void respawn() {
    }

    @Override
    protected void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        super.actuallyHurt(damageSrc, damageAmount);
    }

    @Override
    public void closeContainer() {
    }

    @Override
    public void clientSideCloseContainer() {
        Minecraft.getInstance().player.clientSideCloseContainer();
    }

    @Override
    public void hurtTo(float health) {
    }

    @Override
    public void onUpdateAbilities() {
    }

    @Override
    public boolean isLocalPlayer() {
        return Minecraft.getInstance().player.isLocalPlayer();
    }

    @Override
    public boolean isSuppressingSlidingDownLadder() {
        return Minecraft.getInstance().player.isSuppressingSlidingDownLadder();
    }

    @Override
    public boolean canSpawnSprintParticle() {
        return Minecraft.getInstance().player.canSpawnSprintParticle();
    }

    @Override
    protected void sendRidingJump() {
        super.sendRidingJump();
    }

    @Override
    public void sendOpenInventory() {
    }

    @Override
    public StatsCounter getStats() {
        return Minecraft.getInstance().player.getStats();
    }

    @Override
    public ClientRecipeBook getRecipeBook() {
        return Minecraft.getInstance().player.getRecipeBook();
    }

    @Override
    public void removeRecipeHighlight(RecipeHolder<?> recipe) {
    }

    @Override
    public int getPermissionLevel() {
        return Minecraft.getInstance().player.getPermissionLevel();
    }

    @Override
    public void setPermissionLevel(int permissionLevel) {
    }

    @Override
    public void displayClientMessage(Component chatComponent, boolean actionBar) {
    }

    @Override
    public void setExperienceValues(float currentXP, int maxXP, int level) {
    }

    @Override
    public void sendSystemMessage(Component component) {
    }

    @Override
    public void handleEntityEvent(byte id) {
        Minecraft.getInstance().player.handleEntityEvent(id);
    }

    @Override
    public void setShowDeathScreen(boolean show) {
    }

    @Override
    public boolean shouldShowDeathScreen() {
        return Minecraft.getInstance().player.shouldShowDeathScreen();
    }

    @Override
    public boolean getDoLimitedCrafting() {
        return Minecraft.getInstance().player.getDoLimitedCrafting();
    }

    @Override
    public void setDoLimitedCrafting(boolean doLimitedCrafting) {
    }

    @Override
    public void playSound(SoundEvent sound, float volume, float pitch) {
    }

    @Override
    public void playNotifySound(SoundEvent sound, SoundSource source, float volume, float pitch) {
    }

    @Override
    public boolean isEffectiveAi() {
        return Minecraft.getInstance().player.isEffectiveAi();
    }

    @Override
    public void startUsingItem(InteractionHand hand) {
    }

    @Override
    public boolean isUsingItem() {
        return Minecraft.getInstance().player.isUsingItem();
    }

    @Override
    public void stopUsingItem() {
    }

    @Override
    public InteractionHand getUsedItemHand() {
        return Minecraft.getInstance().player.getUsedItemHand();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
    }

    @Override
    public PlayerRideableJumping jumpableVehicle() {
        return Minecraft.getInstance().player.jumpableVehicle();
    }

    @Override
    public float getJumpRidingScale() {
        return Minecraft.getInstance().player.getJumpRidingScale();
    }

    @Override
    public boolean isTextFilteringEnabled() {
        return Minecraft.getInstance().player.isTextFilteringEnabled();
    }

    @Override
    public void openTextEdit(SignBlockEntity signEntity, boolean isFrontText) {
    }

    @Override
    public void openMinecartCommandBlock(BaseCommandBlock commandBlock) {
    }

    @Override
    public void openCommandBlock(CommandBlockEntity commandBlock) {
    }

    @Override
    public void openStructureBlock(StructureBlockEntity structure) {
    }

    @Override
    public void openJigsawBlock(JigsawBlockEntity jigsawBlockEntity) {
    }

    @Override
    public void openItemGui(ItemStack stack, InteractionHand hand) {
    }

    @Override
    public void crit(Entity entityHit) {
        Minecraft.getInstance().player.crit(entityHit);
    }

    @Override
    public void magicCrit(Entity entityHit) {
        Minecraft.getInstance().player.magicCrit(entityHit);
    }

    @Override
    public boolean isShiftKeyDown() {
        return Minecraft.getInstance().player.isShiftKeyDown();
    }

    @Override
    public boolean isCrouching() {
        return Minecraft.getInstance().player.isCrouching();
    }

    @Override
    public boolean isMovingSlowly() {
        return Minecraft.getInstance().player.isMovingSlowly();
    }

    @Override
    public void serverAiStep() {
        Minecraft.getInstance().player.serverAiStep();
    }

    @Override
    protected boolean isControlledCamera() {
        return super.isControlledCamera();
    }

    @Override
    public void resetPos() {
    }

    @Override
    public void aiStep() {
        Minecraft.getInstance().player.aiStep();
    }

    @Override
    public Portal.Transition getActivePortalLocalTransition() {
        return Minecraft.getInstance().player.getActivePortalLocalTransition();
    }

    @Override
    protected void tickDeath() {
    }

    @Override
    public void rideTick() {
        Minecraft.getInstance().player.rideTick();
    }

    @Override
    public boolean isHandsBusy() {
        return Minecraft.getInstance().player.isHandsBusy();
    }

    @Override
    public MobEffectInstance removeEffectNoUpdate(Holder<MobEffect> effect) {
        return null;
    }

    @Override
    public void move(MoverType type, Vec3 pos) {
        Minecraft.getInstance().player.move(type, pos);
    }

    @Override
    public boolean isAutoJumpEnabled() {
        return Minecraft.getInstance().player.isAutoJumpEnabled();
    }

    @Override
    protected void updateAutoJump(float movementX, float movementZ) {
    }

    @Override
    protected boolean isHorizontalCollisionMinor(Vec3 deltaMovement) {
        return super.isHorizontalCollisionMinor(deltaMovement);
    }

    @Override
    public float getWaterVision() {
        return Minecraft.getInstance().player.getWaterVision();
    }

    @Override
    public void onGameModeChanged(GameType gameMode) {
    }

    @Override
    public boolean isUnderWater() {
        return Minecraft.getInstance().player.isUnderWater();
    }

    @Override
    protected boolean updateIsUnderwater() {
        return super.updateIsUnderwater();
    }

    @Override
    public Vec3 getRopeHoldPosition(float partialTick) {
        return Minecraft.getInstance().player.getRopeHoldPosition(partialTick);
    }

    @Override
    public void updateTutorialInventoryAction(ItemStack carried, ItemStack clicked, ClickAction action) {
    }

    @Override
    public float getVisualRotationYInDegrees() {
        return Minecraft.getInstance().player.getVisualRotationYInDegrees();
    }

}
