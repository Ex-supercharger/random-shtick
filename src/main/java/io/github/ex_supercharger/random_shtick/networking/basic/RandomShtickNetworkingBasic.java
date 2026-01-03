package io.github.ex_supercharger.random_shtick.networking.basic;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class RandomShtickNetworkingBasic implements ModInitializer {
    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playS2C().register(ResetCameraEntityS2CPayload.ID, ResetCameraEntityS2CPayload.CODEC);
    }
}
