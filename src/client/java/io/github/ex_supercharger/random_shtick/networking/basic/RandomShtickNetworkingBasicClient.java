package io.github.ex_supercharger.random_shtick.networking.basic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class RandomShtickNetworkingBasicClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ResetCameraEntityS2CPayload.ID, (payload, context) -> {
            ClientLevel world = context.client().level;

            if (world == null) {
                return;
            }

            Entity entity = context.player().level().getEntity(payload.entityId());

            Minecraft.getInstance().setCameraEntity(entity);
        });
    }

}
