package ropy.captchachime;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.IOException;

public class CaptchaChime implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("captcha-chime");
	@Override
	public void onInitialize() {

	}

	public static synchronized void playChime() {
		new Thread(() -> {
			try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(new CaptchaChime().getClass().getResource("/chime-sound-7143.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}).start();
	}
}