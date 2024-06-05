package ropy.captchachime;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CaptchaChimeClient implements ClientModInitializer {

	//public static final Identifier CHIME_ID = new Identifier("mymod", "chime");
	//public static SoundEvent CHIME_EVENT = SoundEvent.of(CHIME_ID);

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		//Registry.register(Registries.SOUND_EVENT, ExampleMod.MY_SOUND_ID, MY_SOUND_EVENT);
	}
}