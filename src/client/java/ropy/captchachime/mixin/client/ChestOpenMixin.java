package ropy.captchachime.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ropy.captchachime.CaptchaChime;

import javax.sound.sampled.*;
import java.io.*;

@Mixin(HandledScreen.class)
public class ChestOpenMixin extends Screen {

    protected ChestOpenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    private void onInit(CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.currentScreen instanceof GenericContainerScreen) {
            Text containerName = client.currentScreen.getTitle();
            if(containerName.contains(Text.of("Captcha"))){
                new Thread(() -> {
                    try {
                        playChime();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }).start();
                client.player.sendMessage(Text.of("Captcha detected!"), false);
            }
        }
    }

    public synchronized void playChime() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(new CaptchaChime().getClass().getResource("/chime-sound-7143.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}