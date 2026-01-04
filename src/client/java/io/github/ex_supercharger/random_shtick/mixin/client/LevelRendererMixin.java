package io.github.ex_supercharger.random_shtick.mixin.client;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//This is taken from the Freecam mod
@Mixin(LevelRenderer.class)
abstract class LevelRendererMixin {

	@Shadow protected abstract EntityRenderState extractEntity(Entity e, float g);

	//Makes players visible when looking through an entity that is not themselves
	@Inject(method = "extractVisibleEntities", at = @At(value = "RETURN"))
	private void onExtractVisibleEntities(Camera camera, Frustum frustum, DeltaTracker deltaTracker, LevelRenderState renderState, CallbackInfo ci) {
		if (Minecraft.getInstance().level != null && Minecraft.getInstance().player != Minecraft.getInstance().getCameraEntity()) {
            Entity player = Minecraft.getInstance().player;
            TickRateManager tickRateManager = Minecraft.getInstance().level.tickRateManager();
            float g = deltaTracker.getGameTimeDeltaPartialTick(!tickRateManager.isEntityFrozen(player));
            EntityRenderState entityRenderState = this.extractEntity(player, g);
            renderState.entityRenderStates.add(entityRenderState);
        }
	}
}