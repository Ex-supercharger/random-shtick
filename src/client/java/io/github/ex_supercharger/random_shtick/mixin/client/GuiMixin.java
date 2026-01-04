package io.github.ex_supercharger.random_shtick.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;

//This is taken from the Freecam mod
@Mixin(Gui.class)
abstract class GuiMixin {
    // Makes HUD correspond to the player instead of the Camera Entity
    @Inject(method = "getCameraPlayer", at = @At("Head"), cancellable = true)
    private void onGetCameraPlayer(CallbackInfoReturnable<Player> cir) {
        if (Minecraft.getInstance().player != Minecraft.getInstance().getCameraEntity()) {
            cir.setReturnValue(Minecraft.getInstance().player);
        }
    }
}
