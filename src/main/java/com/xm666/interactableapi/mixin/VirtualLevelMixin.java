package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Collection;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
@Mixin(Level.class)
public class VirtualLevelMixin {
    @WrapMethod(method = "setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")
    private boolean setBlock(BlockPos pos, BlockState newState, int flags, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(pos, newState, flags);
    }

    @WrapMethod(method = "setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)Z")
    private boolean setBlock(BlockPos pos, BlockState state, int flags, int recursionLeft, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(pos, state, flags, recursionLeft);
    }

    @WrapMethod(method = "markAndNotifyBlock")
    private void markAndNotifyBlock(BlockPos p_46605_, LevelChunk levelchunk, BlockState blockstate, BlockState p_46606_, int p_46607_, int p_46608_, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(p_46605_, levelchunk, blockstate, p_46606_, p_46607_, p_46608_);
    }

    @WrapMethod(method = "removeBlock")
    private boolean removeBlock(BlockPos pos, boolean isMoving, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(pos, isMoving);
    }

    @WrapMethod(method = "destroyBlock")
    private boolean destroyBlock(BlockPos pos, boolean dropBlock, Entity entity, int recursionLeft, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(pos, dropBlock, entity, recursionLeft);
    }

    @WrapMethod(method = "setBlockAndUpdate")
    private boolean setBlockAndUpdate(BlockPos pos, BlockState state, Operation<Boolean> original) {
        if (InteractableHandler.virtualizesInteract()) return true;
        return original.call(pos, state);
    }

    @WrapMethod(method = "updateNeighborsAt")
    private void updateNeighborsAt(BlockPos pos, Block block, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, block);
    }

    @WrapMethod(method = "neighborShapeChanged")
    private void neighborShapeChanged(Direction direction, BlockState queried, BlockPos pos, BlockPos offsetPos, int flags, int recursionLevel, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(direction, queried, pos, offsetPos, flags, recursionLevel);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")
    private void playSound(Entity entity, BlockPos pos, SoundEvent sound, SoundSource category, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(entity, pos, sound, category, volume, pitch);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")
    private void playSound(Player player, BlockPos pos, SoundEvent sound, SoundSource category, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, pos, sound, category, volume, pitch);
    }

    @WrapMethod(method = "playSeededSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFJ)V")
    private void playSeededSound(Player player, double x, double y, double z, SoundEvent sound, SoundSource category, float volume, float pitch, long seed, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, x, y, z, sound, category, volume, pitch, seed);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;)V")
    private void playSound(Player player, double x, double y, double z, SoundEvent sound, SoundSource category, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, x, y, z, sound, category);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")
    private void playSound(Player player, double x, double y, double z, SoundEvent sound, SoundSource category, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, x, y, z, sound, category, volume, pitch);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FF)V")
    private void playSound(Player player, double x, double y, double z, Holder<SoundEvent> sound, SoundSource category, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, x, y, z, sound, category, volume, pitch);
    }

    @WrapMethod(method = "playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")
    private void playSound(Player player, Entity entity, SoundEvent event, SoundSource category, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(player, entity, event, category, volume, pitch);
    }

    @WrapMethod(method = "playLocalSound(Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V")
    private void playLocalSound(BlockPos pos, SoundEvent sound, SoundSource category, float volume, float pitch, boolean distanceDelay, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, sound, category, volume, pitch, distanceDelay);
    }

    @WrapMethod(method = "addBlockEntityTicker")
    private void addBlockEntityTicker(TickingBlockEntity ticker, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(ticker);
    }

    @WrapMethod(method = "addFreshBlockEntities")
    private void addFreshBlockEntities(Collection<BlockEntity> beList, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(beList);
    }

    @WrapMethod(method = "tickBlockEntities")
    private void tickBlockEntities(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "guardEntityTick")
    private void guardEntityTick(Consumer<Entity> consumerEntity, Entity entity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(consumerEntity, entity);
    }

    @WrapMethod(method = "explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;")
    private Explosion explode(Entity source, double x, double y, double z, float radius, Level.ExplosionInteraction explosionInteraction, Operation<Explosion> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(source, x, y, z, radius, explosionInteraction);
    }

    @WrapMethod(method = "explode(Lnet/minecraft/world/entity/Entity;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;")
    private Explosion explode(Entity source, double x, double y, double z, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction, Operation<Explosion> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(source, x, y, z, radius, fire, explosionInteraction);
    }

    @WrapMethod(method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;")
    private Explosion explode(Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, Vec3 pos, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction, Operation<Explosion> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(source, damageSource, damageCalculator, pos, radius, fire, explosionInteraction);
    }

    @WrapMethod(method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;")
    private Explosion explode(Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction, Operation<Explosion> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(source, damageSource, damageCalculator, x, y, z, radius, fire, explosionInteraction);
    }

    @WrapMethod(method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/Explosion;")
    private Explosion explode(Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction, ParticleOptions smallExplosionParticles, ParticleOptions largeExplosionParticles, Holder<SoundEvent> explosionSound, Operation<Explosion> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(source, damageSource, damageCalculator, x, y, z, radius, fire, explosionInteraction, smallExplosionParticles, largeExplosionParticles, explosionSound);
    }

    @WrapMethod(method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;ZLnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/Explosion;")
    private Explosion explode(Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction, boolean spawnParticles, ParticleOptions smallExplosionParticles, ParticleOptions largeExplosionParticles, Holder<SoundEvent> explosionSound, Operation<Explosion> original) {
        if (InteractableHandler.virtualizesInteract()) return null;
        return original.call(source, damageSource, damageCalculator, x, y, z, radius, fire, explosionInteraction, spawnParticles, smallExplosionParticles, largeExplosionParticles, explosionSound);
    }

    @WrapMethod(method = "setBlockEntity")
    private void setBlockEntity(BlockEntity blockEntity, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(blockEntity);
    }

    @WrapMethod(method = "removeBlockEntity")
    private void removeBlockEntity(BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "updateSkyBrightness")
    private void updateSkyBrightness(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "setSpawnSettings")
    private void setSpawnSettings(boolean hostile, boolean peaceful, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(hostile, peaceful);
    }

    @WrapMethod(method = "prepareWeather")
    private void prepareWeather(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "close")
    private void close(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "blockEntityChanged")
    private void blockEntityChanged(BlockPos pos, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos);
    }

    @WrapMethod(method = "blockEvent")
    private void blockEvent(BlockPos pos, Block block, int eventID, int eventParam, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, block, eventID, eventParam);
    }

    @WrapMethod(method = "setThunderLevel")
    private void setThunderLevel(float strength, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(strength);
    }

    @WrapMethod(method = "setRainLevel")
    private void setRainLevel(float strength, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(strength);
    }

    @WrapMethod(method = "globalLevelEvent")
    private void globalLevelEvent(int id, BlockPos pos, int data, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(id, pos, data);
    }

    @WrapMethod(method = "updateNeighbourForOutputSignal")
    private void updateNeighbourForOutputSignal(BlockPos pos, Block block, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(pos, block);
    }

    @WrapMethod(method = "sendPacketToServer")
    private void sendPacketToServer(Packet<?> packet, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(packet);
    }

    @WrapMethod(method = "increaseMaxEntityRadius")
    private double increaseMaxEntityRadius(double value, Operation<Double> original) {
        if (InteractableHandler.virtualizesInteract()) return value;
        return original.call(value);
    }

    @WrapMethod(method = "nextSubTickCount")
    private long nextSubTickCount(Operation<Long> original) {
        if (InteractableHandler.virtualizesInteract()) return 0;
        return original.call();
    }

    @WrapMethod(method = "advanceDaytime")
    private long advanceDaytime(Operation<Long> original) {
        if (InteractableHandler.virtualizesInteract()) return 0;
        return original.call();
    }
}
