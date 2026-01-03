package io.github.ex_supercharger.random_shtick.item;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import io.github.ex_supercharger.random_shtick.networking.basic.ResetCameraEntityS2CPayload;

public class CameraStick extends Item {
    public CameraStick(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (world.isClientSide()) {
            return InteractionResult.PASS;
        }

        ResetCameraEntityS2CPayload payload = new ResetCameraEntityS2CPayload(user.getId());

        ServerPlayNetworking.send((ServerPlayer) user, payload);
        
        return InteractionResult.SUCCESS;
    }
}
