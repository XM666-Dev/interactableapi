package com.xm666.interactableapi.virtual;

import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.ticks.LevelTickAccess;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelDataManager;
import net.neoforged.neoforge.entity.PartEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

@SuppressWarnings({"DataFlowIssue", "UnstableApiUsage"})
public class VirtualClientLevel extends ClientLevel {
    public VirtualClientLevel(Minecraft mc) {
        super(mc.getConnection(), mc.level.getLevelData(), mc.level.dimension(), mc.level.dimensionTypeRegistration(), 0, mc.level.getServerSimulationDistance(), mc::getProfiler, mc.levelRenderer, mc.level.isDebug(), 0L);
    }

    public VirtualClientLevel() {
        this(Minecraft.getInstance());
    }

    @Override
    public void handleBlockChangedAck(int sequence) {
    }

    @Override
    public void setServerVerifiedBlockState(BlockPos pos, BlockState state, int flags) {
    }

    @Override
    public void syncBlockState(BlockPos pos, BlockState state, Vec3 playerPos) {
    }

    @Override
    public boolean setBlock(BlockPos pos, BlockState state, int flags, int recursionLeft) {
        return true;
    }

    @Override
    public void queueLightUpdate(Runnable task) {
    }

    @Override
    public void pollLightUpdates() {
    }

    @Override
    public boolean isLightUpdateQueueEmpty() {
        return Minecraft.getInstance().level.isLightUpdateQueueEmpty();
    }

    @Override
    public DimensionSpecialEffects effects() {
        return Minecraft.getInstance().level.effects();
    }

    @Override
    public void tick(BooleanSupplier hasTimeLeft) {
    }

    @Override
    public Iterable<Entity> entitiesForRendering() {
        return Minecraft.getInstance().level.entitiesForRendering();
    }

    @Override
    public void tickEntities() {
    }

    @Override
    public boolean shouldTickDeath(Entity entity) {
        return Minecraft.getInstance().level.shouldTickDeath(entity);
    }

    @Override
    public void tickNonPassenger(Entity p_entity) {
    }

    @Override
    public void unload(LevelChunk chunk) {
    }

    @Override
    public void onChunkLoaded(ChunkPos chunkPos) {
    }

    @Override
    public void clearTintCaches() {
    }

    @Override
    public boolean hasChunk(int chunkX, int chunkZ) {
        return Minecraft.getInstance().level.hasChunk(chunkX, chunkZ);
    }

    @Override
    public int getEntityCount() {
        return Minecraft.getInstance().level.getEntityCount();
    }

    @Override
    public void addEntity(Entity entity) {
    }

    @Override
    public void removeEntity(int entityId, Entity.RemovalReason reason) {
    }

    @Override
    public Entity getEntity(int id) {
        return Minecraft.getInstance().level.getEntity(id);
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void animateTick(int posX, int posY, int posZ) {
    }

    @Override
    public void doAnimateTick(int posX, int posY, int posZ, int range, RandomSource random, @Nullable Block block, BlockPos.MutableBlockPos blockPos) {
    }

    @Override
    public CrashReportCategory fillReportDetails(CrashReport report) {
        return Minecraft.getInstance().level.fillReportDetails(report);
    }

    @Override
    public void playSeededSound(@Nullable Player player, double x, double y, double z, Holder<SoundEvent> sound, SoundSource category, float volume, float pitch, long seed) {
    }

    @Override
    public void playSeededSound(@Nullable Player player, Entity entity, Holder<SoundEvent> sound, SoundSource category, float volume, float pitch, long seed) {
    }

    @Override
    public void playLocalSound(Entity entity, SoundEvent sound, SoundSource category, float volume, float pitch) {
    }

    @Override
    public void playLocalSound(double x, double y, double z, SoundEvent sound, SoundSource category, float volume, float pitch, boolean distanceDelay) {
    }

    @Override
    public void createFireworks(double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, List<FireworkExplosion> explosions) {
    }

    @Override
    public void sendPacketToServer(Packet<?> packet) {
    }

    @Override
    public RecipeManager getRecipeManager() {
        return Minecraft.getInstance().level.getRecipeManager();
    }

    @Override
    public TickRateManager tickRateManager() {
        return Minecraft.getInstance().level.tickRateManager();
    }

    @Override
    public LevelTickAccess<Block> getBlockTicks() {
        return Minecraft.getInstance().level.getBlockTicks();
    }

    @Override
    public LevelTickAccess<Fluid> getFluidTicks() {
        return Minecraft.getInstance().level.getFluidTicks();
    }

    @Override
    public ClientChunkCache getChunkSource() {
        return Minecraft.getInstance().level.getChunkSource();
    }

    @Override
    public MapItemSavedData getMapData(MapId mapId) {
        return Minecraft.getInstance().level.getMapData(mapId);
    }

    @Override
    public void overrideMapData(MapId mapId, MapItemSavedData mapData) {
    }

    @Override
    public void setMapData(MapId mapId, MapItemSavedData mapData) {
    }

    @Override
    public MapId getFreeMapId() {
        return Minecraft.getInstance().level.getFreeMapId();
    }

    @Override
    public Scoreboard getScoreboard() {
        return Minecraft.getInstance().level.getScoreboard();
    }

    @Override
    public void sendBlockUpdated(BlockPos pos, BlockState oldState, BlockState newState, int flags) {
    }

    @Override
    public void setBlocksDirty(BlockPos blockPos, BlockState oldState, BlockState newState) {
    }

    @Override
    public void setSectionDirtyWithNeighbors(int sectionX, int sectionY, int sectionZ) {
    }

    @Override
    public void destroyBlockProgress(int breakerId, BlockPos pos, int progress) {
    }

    @Override
    public void globalLevelEvent(int id, BlockPos pos, int data) {
    }

    @Override
    public void levelEvent(@Nullable Player player, int type, BlockPos pos, int data) {
    }

    @Override
    public void addParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    @Override
    public void addParticle(ParticleOptions particleData, boolean forceAlwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    @Override
    public void addAlwaysVisibleParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    @Override
    public void addAlwaysVisibleParticle(ParticleOptions particleData, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    @Override
    public List<AbstractClientPlayer> players() {
        return new ArrayList<>();
    }

    @Override
    public Holder<Biome> getUncachedNoiseBiome(int x, int y, int z) {
        return Minecraft.getInstance().level.getUncachedNoiseBiome(x, y, z);
    }

    @Override
    public float getSkyDarken(float partialTick) {
        return Minecraft.getInstance().level.getSkyDarken(partialTick);
    }

    @Override
    public Vec3 getSkyColor(Vec3 pos, float partialTick) {
        return Minecraft.getInstance().level.getSkyColor(pos, partialTick);
    }

    @Override
    public Vec3 getCloudColor(float partialTick) {
        return Minecraft.getInstance().level.getCloudColor(partialTick);
    }

    @Override
    public float getStarBrightness(float partialTick) {
        return Minecraft.getInstance().level.getStarBrightness(partialTick);
    }

    @Override
    public int getSkyFlashTime() {
        return Minecraft.getInstance().level.getSkyFlashTime();
    }

    @Override
    public void setSkyFlashTime(int timeFlash) {
    }

    @Override
    public float getShade(Direction direction, boolean shade) {
        return Minecraft.getInstance().level.getShade(direction, shade);
    }

    @Override
    public int getBlockTint(BlockPos blockPos, ColorResolver colorResolver) {
        return Minecraft.getInstance().level.getBlockTint(blockPos, colorResolver);
    }

    @Override
    public int calculateBlockTint(BlockPos blockPos, ColorResolver colorResolver) {
        return Minecraft.getInstance().level.calculateBlockTint(blockPos, colorResolver);
    }

    @Override
    public void setDefaultSpawnPos(BlockPos spawnPos, float spawnAngle) {
    }

    @Override
    public String toString() {
        return Minecraft.getInstance().level.toString();
    }

    @Override
    public ClientLevelData getLevelData() {
        return Minecraft.getInstance().level.getLevelData();
    }

    @Override
    public void gameEvent(Holder<GameEvent> gameEvent, Vec3 pos, GameEvent.Context context) {
    }

    @Override
    public Map<MapId, MapItemSavedData> getAllMapData() {
        return Minecraft.getInstance().level.getAllMapData();
    }

    @Override
    protected void addMapData(Map<MapId, MapItemSavedData> map) {
    }

    @Override
    public LevelEntityGetter<Entity> getEntities() {
        return Minecraft.getInstance().level.getEntities();
    }

    @Override
    public String gatherChunkSourceStats() {
        return Minecraft.getInstance().level.gatherChunkSourceStats();
    }

    @Override
    public void addDestroyBlockEffect(BlockPos pos, BlockState state) {
    }

    @Override
    public int getServerSimulationDistance() {
        return Minecraft.getInstance().level.getServerSimulationDistance();
    }

    @Override
    public void setServerSimulationDistance(int serverSimulationDistance) {
    }

    @Override
    public FeatureFlagSet enabledFeatures() {
        return Minecraft.getInstance().level.enabledFeatures();
    }

    @Override
    public PotionBrewing potionBrewing() {
        return Minecraft.getInstance().level.potionBrewing();
    }

    @Override
    public Collection<PartEntity<?>> getPartEntities() {
        return Minecraft.getInstance().level.getPartEntities();
    }

    @Override
    public ModelDataManager getModelDataManager() {
        return Minecraft.getInstance().level.getModelDataManager();
    }

    @Override
    public ModelData getModelData(BlockPos pos) {
        return Minecraft.getInstance().level.getModelData(pos);
    }

    @Override
    public float getShade(float normalX, float normalY, float normalZ, boolean shade) {
        return Minecraft.getInstance().level.getShade(normalX, normalY, normalZ, shade);
    }

    @Override
    public float getDayTimeFraction() {
        return Minecraft.getInstance().level.getDayTimeFraction();
    }

    @Override
    public void setDayTimeFraction(float dayTimeFraction) {
    }

    @Override
    public float getDayTimePerTick() {
        return Minecraft.getInstance().level.getDayTimePerTick();
    }

    @Override
    public void setDayTimePerTick(float dayTimePerTick) {
    }

    @Override
    public long getGameTime() {
        return Minecraft.getInstance().level.getGameTime();
    }

    @Override
    public void setGameTime(long time) {
    }

    @Override
    public long getDayTime() {
        return Minecraft.getInstance().level.getDayTime();
    }

    @Override
    public void setDayTime(long time) {
    }

    @Override
    public boolean isThundering() {
        return Minecraft.getInstance().level.isThundering();
    }

    @Override
    public boolean isRaining() {
        return Minecraft.getInstance().level.isRaining();
    }

    @Override
    public GameRules getGameRules() {
        return Minecraft.getInstance().level.getGameRules();
    }

    @Override
    public Difficulty getDifficulty() {
        return Minecraft.getInstance().level.getDifficulty();
    }
}

