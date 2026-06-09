package com.xm666.interactableapi.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.xm666.interactableapi.api.InteractableHandler;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@OnlyIn(Dist.CLIENT)
@Mixin(ItemCooldowns.class)
public class VirtualItemCooldownsMixin {
    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call();
    }

    @WrapMethod(method = "addCooldown")
    private void addCooldown(Item item, int ticks, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(item, ticks);
    }

    @WrapMethod(method = "removeCooldown")
    private void removeCooldown(Item item, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(item);
    }

    @WrapMethod(method = "onCooldownStarted")
    private void onCooldownStarted(Item item, int ticks, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(item, ticks);
    }

    @WrapMethod(method = "onCooldownEnded")
    private void onCooldownEnded(Item item, Operation<Void> original) {
        if (InteractableHandler.virtualizesInteract()) return;
        original.call(item);
    }
}
