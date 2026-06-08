package com.xm666.interactableapi.virtual;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class VirtualItemStack extends ItemStack {
    private final ItemStack stack;

    public VirtualItemStack(ItemStack stack) {
        super((Void) null);
        this.stack = stack;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public Item getItem() {
        return this.stack.getItem();
    }

    @Override
    public Holder<Item> getItemHolder() {
        return this.stack.getItemHolder();
    }

    @Override
    public boolean is(TagKey<Item> tag) {
        return this.stack.is(tag);
    }

    @Override
    public boolean is(Item item) {
        return this.stack.is(item);
    }

    @Override
    public boolean is(Predicate<Holder<Item>> predicate) {
        return this.stack.is(predicate);
    }

    @Override
    public boolean is(Holder<Item> holder) {
        return this.stack.is(holder);
    }

    @Override
    public boolean is(HolderSet<Item> holders) {
        return this.stack.is(holders);
    }

    @Override
    public Stream<TagKey<Item>> getTags() {
        return this.stack.getTags();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return this.stack.useOn(context);
    }

    @Override
    public InteractionResult onItemUseFirst(UseOnContext context) {
        return this.stack.onItemUseFirst(context);
    }

    @Override
    public float getDestroySpeed(BlockState state) {
        return this.stack.getDestroySpeed(state);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        return this.stack.use(level, player, usedHand);
    }

    @Override
    public ItemStack finishUsingItem(Level level, LivingEntity livingEntity) {
        return this.stack.finishUsingItem(level, livingEntity);
    }

    @Override
    public int getMaxStackSize() {
        return this.stack.getMaxStackSize();
    }

    @Override
    public boolean isStackable() {
        return this.stack.isStackable();
    }

    @Override
    public boolean isDamageableItem() {
        return this.stack.isDamageableItem();
    }

    @Override
    public boolean isDamaged() {
        return this.stack.isDamaged();
    }

    @Override
    public int getDamageValue() {
        return this.stack.getDamageValue();
    }

    @Override
    public void setDamageValue(int damage) {
    }

    @Override
    public int getMaxDamage() {
        return this.stack.getMaxDamage();
    }

    @Override
    public void hurtAndBreak(int damage, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak) {
    }

    @Override
    public void hurtAndBreak(int amount, LivingEntity entity, EquipmentSlot slot) {
    }

    @Override
    public ItemStack hurtAndConvertOnBreak(int amount, ItemLike item, LivingEntity entity, EquipmentSlot slot) {
        return this.stack.hurtAndConvertOnBreak(amount, item, entity, slot);
    }

    @Override
    public boolean isBarVisible() {
        return this.stack.isBarVisible();
    }

    @Override
    public int getBarWidth() {
        return this.stack.getBarWidth();
    }

    @Override
    public int getBarColor() {
        return this.stack.getBarColor();
    }

    @Override
    public boolean overrideStackedOnOther(Slot slot, ClickAction action, Player player) {
        return this.stack.overrideStackedOnOther(slot, action, player);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, Slot slot, ClickAction action, Player player, SlotAccess access) {
        return this.stack.overrideOtherStackedOnMe(stack, slot, action, player, access);
    }

    @Override
    public boolean hurtEnemy(LivingEntity target, Player attacker) {
        return this.stack.hurtEnemy(target, attacker);
    }

    @Override
    public void postHurtEnemy(LivingEntity target, Player attacker) {
    }

    @Override
    public void mineBlock(Level level, BlockState state, BlockPos pos, Player player) {
        this.stack.mineBlock(level, state, pos, player);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        return this.stack.isCorrectToolForDrops(state);
    }

    @Override
    public InteractionResult interactLivingEntity(Player player, LivingEntity entity, InteractionHand usedHand) {
        return this.stack.interactLivingEntity(player, entity, usedHand);
    }

    @Override
    public ItemStack copy() {
        return this.stack.copy();
    }

    @Override
    public ItemStack copyWithCount(int count) {
        return this.stack.copyWithCount(count);
    }

    @Override
    public ItemStack transmuteCopy(ItemLike item) {
        return this.stack.transmuteCopy(item);
    }

    @Override
    public ItemStack transmuteCopy(ItemLike item, int count) {
        return this.stack.transmuteCopy(item, count);
    }

    @Override
    public boolean hasFoil() {
        return this.stack.hasFoil();
    }

    @Override
    public Rarity getRarity() {
        return this.stack.getRarity();
    }

    @Override
    public boolean isEnchantable() {
        return this.stack.isEnchantable();
    }

    @Override
    public void enchant(Holder<Enchantment> enchantment, int level) {
    }

    @Override
    public boolean isEnchanted() {
        return this.stack.isEnchanted();
    }

    @Override
    public List<Component> getTooltipLines(Item.TooltipContext tooltipContext, @Nullable Player player, TooltipFlag tooltipFlag) {
        return this.stack.getTooltipLines(tooltipContext, player, tooltipFlag);
    }

    @Override
    public void inventoryTick(Level level, Entity entity, int inventorySlot, boolean isCurrentItem) {
    }

    @Override
    public void onCraftedBy(Level level, Player player, int amount) {
    }

    @Override
    public int getUseDuration(LivingEntity entity) {
        return this.stack.getUseDuration(entity);
    }

    @Override
    public UseAnim getUseAnimation() {
        return this.stack.getUseAnimation();
    }

    @Override
    public void releaseUsing(Level level, LivingEntity livingEntity, int timeLeft) {
    }

    @Override
    public boolean useOnRelease() {
        return this.stack.useOnRelease();
    }

    @Override
    public <T> T set(DataComponentType<? super T> component, @Nullable T value) {
        // no-op for mutating components
        return null;
    }

    @Override
    public <T, U> T update(DataComponentType<T> component, T defaultValue, U updateValue, BiFunction<T, U, T> updater) {
        // no-op
        return null;
    }

    @Override
    public <T> T update(DataComponentType<T> component, T defaultValue, UnaryOperator<T> updater) {
        // no-op
        return null;
    }

    @Override
    public <T> T remove(DataComponentType<? extends T> component) {
        // no-op
        return null;
    }

    @Override
    public void applyComponentsAndValidate(DataComponentPatch components) {
    }

    @Override
    public void applyComponents(DataComponentPatch components) {
    }

    @Override
    public void applyComponents(DataComponentMap components) {
    }

    @Override
    public Component getHoverName() {
        return this.stack.getHoverName();
    }

    // removed two-arg getTooltipLines override because superclass does not declare it in this environment

    @Override
    public int getPopTime() {
        return this.stack.getPopTime();
    }

    @Override
    public void setPopTime(int popTime) {
    }

    @Override
    public void setCount(int count) {
    }

    @Override
    public void grow(int increment) {
    }

    @Override
    public void shrink(int decrement) {
    }

    @Override
    public void consume(int amount, @Nullable LivingEntity entity) {
    }

    @Override
    public ItemStack consumeAndReturn(int amount, @Nullable LivingEntity entity) {
        return this.stack.consumeAndReturn(amount, entity);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, int count) {
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return this.stack.getDrinkingSound();
    }

    @Override
    public SoundEvent getEatingSound() {
        return this.stack.getEatingSound();
    }

    @Override
    public SoundEvent getBreakingSound() {
        return this.stack.getBreakingSound();
    }

    @Override
    public boolean canBeHurtBy(DamageSource damageSource) {
        return this.stack.canBeHurtBy(damageSource);
    }

    @Override
    public @Nullable Entity getEntityRepresentation() {
        return this.stack.getEntityRepresentation();
    }

    @Override
    public void setEntityRepresentation(@Nullable Entity entity) {
    }

    @Override
    public void forEachModifier(EquipmentSlotGroup slotGroup, BiConsumer<Holder<Attribute>, AttributeModifier> action) {
        this.stack.forEachModifier(slotGroup, action);
    }

    @Override
    public void forEachModifier(EquipmentSlot slot, BiConsumer<Holder<Attribute>, AttributeModifier> action) {
        this.stack.forEachModifier(slot, action);
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
