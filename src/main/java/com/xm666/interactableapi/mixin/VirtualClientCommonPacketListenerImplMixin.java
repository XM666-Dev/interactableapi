package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.network.protocol.Packet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@OnlyIn(Dist.CLIENT)
@Mixin(ClientCommonPacketListenerImpl.class)
public class VirtualClientCommonPacketListenerImplMixin {
    @WrapMethod(method = "send")
    private void send(Packet<?> packet, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(packet);
    }
}
