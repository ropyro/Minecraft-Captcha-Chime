package ropy.captchachime.mixin.client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.thread.ThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ropy.captchachime.CaptchaChime;

@Mixin(ClientPlayNetworkHandler.class)
public class ChatMessageRecieveMixin {

    /**
     * Detects when the player's name is messaged in chat
     */
    @Inject(method = "onChatMessage", at = @At("TAIL"))
    private void onChatMessage(ChatMessageS2CPacket packet, CallbackInfo info) {
        String messageContent = packet.body().content();
        MinecraftClient mc = MinecraftClient.getInstance();
        if (messageContent.contains(mc.player.getName().getString())) {
            mc.player.sendMessage(Text.of("Your name was detected in chat."));
            CaptchaChime.playChime();
        }
    }

    /**
     * Detects when a player receives a private message
     */
    @Inject(method = "onGameMessage", at = @At("TAIL"))
    public void onGameMessage(GameMessageS2CPacket packet, CallbackInfo info) {
        String messageContent = packet.content().getString();
        MinecraftClient mc = MinecraftClient.getInstance();
        if(messageContent.contains("➡")){
            mc.player.sendMessage(Text.of("You received an msg."));
            CaptchaChime.playChime();
        }
    }

}