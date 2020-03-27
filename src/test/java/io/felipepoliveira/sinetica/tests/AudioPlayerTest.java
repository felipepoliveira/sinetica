package io.felipepoliveira.sinetica.tests;

import javax.sound.sampled.LineUnavailableException;

import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.synths.SineWave;

public class AudioPlayerTest {

	public static void main(String[] args) throws LineUnavailableException {
		SineWave sineWave = new SineWave();
		for (double freq : Pitch.majorScale(Pitch.octave(Pitch.C_O0, 2))) {
			MasterSoundPlayer.getInstance().playSync(sineWave, freq, 4000);
		}
	}

}
