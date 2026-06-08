package com.xm666.interactableapi;

import com.mojang.logging.LogUtils;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.slf4j.Logger;

@Mod(InteractableAPI.MODID)
@EventBusSubscriber(modid = InteractableAPI.MODID, value = Dist.CLIENT)
public class InteractableAPI {
    public static final String MODID = "interactableapi";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        var mc = Minecraft.getInstance();
        if (mc.isPaused() || mc.level == null) return;
        var result = InteractableHandler.interacts(mc.hitResult);
        LOGGER.info("{}", result);
    }
}
