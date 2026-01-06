package io.github.ex_supercharger.random_shtick;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.client.Minecraft;

public class Random_ShtickClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
			Minecraft MC = Minecraft.getInstance();
			if (entity.equals(MC.getCameraEntity())) {
				MC.setCameraEntity(MC.player);
			}
		});
	}
}