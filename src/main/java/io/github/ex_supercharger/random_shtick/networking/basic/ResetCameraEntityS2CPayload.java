package io.github.ex_supercharger.random_shtick.networking.basic;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import io.github.ex_supercharger.random_shtick.Random_Shtick;

public record ResetCameraEntityS2CPayload(int entityId) implements CustomPacketPayload {
    public static final Identifier RESET_CAMERA_ENTITY_PAYLOAD_ID = Identifier.fromNamespaceAndPath(Random_Shtick.MOD_ID, "reset_camera_entity_s2c");
    public static final CustomPacketPayload.Type<ResetCameraEntityS2CPayload> ID = new CustomPacketPayload.Type<>(RESET_CAMERA_ENTITY_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ResetCameraEntityS2CPayload> CODEC = StreamCodec.composite(ByteBufCodecs.INT, ResetCameraEntityS2CPayload::entityId, ResetCameraEntityS2CPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}