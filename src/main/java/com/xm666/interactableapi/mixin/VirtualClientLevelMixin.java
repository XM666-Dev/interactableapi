package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

@OnlyIn(Dist.CLIENT)
@Mixin(ClientLevel.class)
public class VirtualClientLevelMixin {
    @WrapMethod(method = "handleBlockChangedAck")
    private void handleBlockChangedAck(int sequence, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(sequence);
    }

    @WrapMethod(method = "setServerVerifiedBlockState")
    private void setServerVerifiedBlockState(BlockPos pos, BlockState state, int flags, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(pos, state, flags);
    }

    @WrapMethod(method = "syncBlockState")
    private void syncBlockState(BlockPos pos, BlockState state, Vec3 playerPos, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(pos, state, playerPos);
    }

    @WrapMethod(method = "setBlock")
    private boolean setBlock(BlockPos pos, BlockState state, int flags, int recursionLeft, Operation<Boolean> original) {
        if (InteractableHandler.protectsEntity()) return false;
        return original.call(pos, state, flags, recursionLeft);
    }

    @WrapMethod(method = "queueLightUpdate")
    private void queueLightUpdate(Runnable task, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(task);
    }

    @WrapMethod(method = "pollLightUpdates")
    private void pollLightUpdates(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "tick")
    private void tick(BooleanSupplier hasTimeLeft, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(hasTimeLeft);
    }

    @WrapMethod(method = "tickTime")
    private void tickTime(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "setGameTime")
    private void setGameTime(long time, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(time);
    }

    @WrapMethod(method = "setDayTime")
    private void setDayTime(long time, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(time);
    }

    @WrapMethod(method = "tickEntities")
    private void tickEntities(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "tickNonPassenger")
    private void tickNonPassenger(Entity p_entity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(p_entity);
    }

    @WrapMethod(method = "tickPassenger")
    private void tickPassenger(Entity mount, Entity rider, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(mount, rider);
    }

    @WrapMethod(method = "unload")
    private void unload(LevelChunk chunk, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(chunk);
    }

    @WrapMethod(method = "onChunkLoaded")
    private void onChunkLoaded(ChunkPos chunkPos, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(chunkPos);
    }

    @WrapMethod(method = "addEntity")
    private void addEntity(Entity entity, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(entity);
    }

    @WrapMethod(method = "removeEntity")
    private void removeEntity(int entityId, Entity.RemovalReason reason, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(entityId, reason);
    }

    @WrapMethod(method = "disconnect")
    private void disconnect(Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call();
    }

    @WrapMethod(method = "animateTick")
    private void animateTick(int posX, int posY, int posZ, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(posX, posY, posZ);
    }

    @WrapMethod(method = "doAnimateTick")
    private void doAnimateTick(int posX, int posY, int posZ, int range, RandomSource random, Block block, BlockPos.MutableBlockPos blockPos, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(posX, posY, posZ, range, random, block, blockPos);
    }

    @WrapMethod(method = "trySpawnDripParticles")
    private void trySpawnDripParticles(BlockPos blockPos, BlockState blockState, ParticleOptions particleData, boolean shapeDownSolid, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(blockPos, blockState, particleData, shapeDownSolid);
    }

    @WrapMethod(method = "spawnParticle")
    private void spawnParticle(BlockPos pos, ParticleOptions particleData, VoxelShape voxelShape, double y, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(pos, particleData, voxelShape, y);
    }

    @WrapMethod(method = "spawnFluidParticle")
    private void spawnFluidParticle(double xStart, double xEnd, double zStart, double zEnd, double y, ParticleOptions particleData, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(xStart, xEnd, zStart, zEnd, y, particleData);
    }

    @WrapMethod(method = "playSeededSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V")
    private void playSeededSound(Player player, double x, double y, double z, Holder<SoundEvent> sound, SoundSource category, float volume, float pitch, long seed, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(player, x, y, z, sound, category, volume, pitch, seed);
    }

    @WrapMethod(method = "playSeededSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V")
    private void playSeededSound(Player player, Entity entity, Holder<SoundEvent> sound, SoundSource category, float volume, float pitch, long seed, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(player, entity, sound, category, volume, pitch, seed);
    }

    @WrapMethod(method = "playLocalSound(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")
    private void playLocalSound(Entity entity, SoundEvent sound, SoundSource category, float volume, float pitch, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(entity, sound, category, volume, pitch);
    }

    @WrapMethod(method = "playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V")
    private void playLocalSound(double x, double y, double z, SoundEvent sound, SoundSource category, float volume, float pitch, boolean distanceDelay, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, y, z, sound, category, volume, pitch, distanceDelay);
    }

    @WrapMethod(method = "playSound")
    private void playSound(double x, double y, double z, SoundEvent soundEvent, SoundSource source, float volume, float pitch, boolean distanceDelay, long seed, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, y, z, soundEvent, source, volume, pitch, distanceDelay, seed);
    }

    @WrapMethod(method = "createFireworks")
    private void createFireworks(double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, List<FireworkExplosion> explosions, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(x, y, z, xSpeed, ySpeed, zSpeed, explosions);
    }

    @WrapMethod(method = "sendPacketToServer")
    private void sendPacketToServer(Packet<?> packet, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(packet);
    }

    @WrapMethod(method = "overrideMapData")
    private void overrideMapData(MapId mapId, MapItemSavedData mapData, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(mapId, mapData);
    }

    @WrapMethod(method = "setMapData")
    private void setMapData(MapId mapId, MapItemSavedData mapData, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(mapId, mapData);
    }

    @WrapMethod(method = "sendBlockUpdated")
    private void sendBlockUpdated(BlockPos pos, BlockState oldState, BlockState newState, int flags, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(pos, oldState, newState, flags);
    }

    @WrapMethod(method = "setBlocksDirty")
    private void setBlocksDirty(BlockPos blockPos, BlockState oldState, BlockState newState, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(blockPos, oldState, newState);
    }

    @WrapMethod(method = "setSectionDirtyWithNeighbors")
    private void setSectionDirtyWithNeighbors(int sectionX, int sectionY, int sectionZ, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(sectionX, sectionY, sectionZ);
    }

    @WrapMethod(method = "destroyBlockProgress")
    private void destroyBlockProgress(int breakerId, BlockPos pos, int progress, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(breakerId, pos, progress);
    }

    @WrapMethod(method = "globalLevelEvent")
    private void globalLevelEvent(int id, BlockPos pos, int data, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(id, pos, data);
    }

    @WrapMethod(method = "levelEvent")
    private void levelEvent(Player player, int type, BlockPos pos, int data, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(player, type, pos, data);
    }

    @WrapMethod(method = "addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V")
    private void addParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @WrapMethod(method = "addParticle(Lnet/minecraft/core/particles/ParticleOptions;ZDDDDDD)V")
    private void addParticle(ParticleOptions particleData, boolean forceAlwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(particleData, forceAlwaysRender, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @WrapMethod(method = "addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V")
    private void addAlwaysVisibleParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @WrapMethod(method = "addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;ZDDDDDD)V")
    private void addAlwaysVisibleParticle(ParticleOptions particleData, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(particleData, ignoreRange, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @WrapMethod(method = "setSkyFlashTime")
    private void setSkyFlashTime(int timeFlash, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(timeFlash);
    }

    @WrapMethod(method = "setDefaultSpawnPos")
    private void setDefaultSpawnPos(BlockPos spawnPos, float spawnAngle, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(spawnPos, spawnAngle);
    }

    @WrapMethod(method = "gameEvent")
    private void gameEvent(Holder<GameEvent> gameEvent, Vec3 pos, GameEvent.Context context, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(gameEvent, pos, context);
    }

    @WrapMethod(method = "addMapData")
    private void addMapData(Map<MapId, MapItemSavedData> map, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(map);
    }

    @WrapMethod(method = "addDestroyBlockEffect")
    private void addDestroyBlockEffect(BlockPos pos, BlockState state, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(pos, state);
    }

    @WrapMethod(method = "setServerSimulationDistance")
    private void setServerSimulationDistance(int serverSimulationDistance, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(serverSimulationDistance);
    }

    @WrapMethod(method = "setDayTimeFraction")
    private void setDayTimeFraction(float dayTimeFraction, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(dayTimeFraction);
    }

    @WrapMethod(method = "setDayTimePerTick")
    private void setDayTimePerTick(float dayTimePerTick, Operation<Void> original) {
        if (InteractableHandler.protectsEntity()) return;
        original.call(dayTimePerTick);
    }
}
