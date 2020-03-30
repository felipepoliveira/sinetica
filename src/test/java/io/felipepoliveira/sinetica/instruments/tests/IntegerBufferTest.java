package io.felipepoliveira.sinetica.instruments.tests;

import javax.sound.sampled.LineUnavailableException;

import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.instruments.synths.SineWave;
import io.felipepoliveira.sinetica.instruments.synths.WhiteNoiseWave;

public class IntegerBufferTest {

	public static void main(String[] args) throws LineUnavailableException {
		
		SineWave sine = new SineWave();
		WhiteNoiseWave whiteNoise = new WhiteNoiseWave();
		MasterSoundPlayer.getInstance().playSync(whiteNoise, 54, 1000);
//		MasterSoundPlayer.getInstance().playSync(sine, Pitch.getPitch(Pitch.ORD_C, 3), 1000);

	}

}
