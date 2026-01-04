package io.github.ex_supercharger.random_shtick.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;

//This is taken from the Freecam mod
@Mixin(GameRenderer.class)
abstract class GameRendererMixin {
    // Makes mouse clicks come from the player instead of the Camera Entity
    @ModifyVariable(method = "pick(F)V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/Minecraft;getCameraEntity()Lnet/minecraft/world/entity/Entity;"))
    private Entity onUpdateTargetedEntity(Entity entity) {
        if (Minecraft.getInstance().player != Minecraft.getInstance().getCameraEntity()) {
            return Minecraft.getInstance().player;
        }
        return entity;
    }
}
