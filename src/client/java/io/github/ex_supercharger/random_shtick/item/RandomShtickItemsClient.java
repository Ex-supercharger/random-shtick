package io.github.ex_supercharger.random_shtick.item;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;

public class RandomShtickItemsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClientSide()) {
                return InteractionResult.PASS;
            }

            ItemStack usedItemStack = player.getItemInHand(hand);

            if (usedItemStack.is(ModItems.CAMERA_STICK) && hand == InteractionHand.MAIN_HAND) {
                Minecraft.getInstance().setCameraEntity(entity);
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        });
    }

}
