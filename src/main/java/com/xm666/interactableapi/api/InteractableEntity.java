package com.xm666.interactableapi.api;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public interface InteractableEntity {
    InteractionResult interactableapi$tryInteract(Player player, InteractionHand hand);

    InteractionResult interactableapi$tryInteractAt(Player player, Vec3 vec, InteractionHand hand);
}
