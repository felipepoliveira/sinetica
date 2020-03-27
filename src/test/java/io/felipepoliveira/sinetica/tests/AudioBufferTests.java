package io.felipepoliveira.sinetica.tests;

import javax.sound.sampled.LineUnavailableException;

import io.felipepoliveira.sinetica.AudioBuffer;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.synths.SineWave;
import io.felipepoliveira.sinetica.synths.WhiteNoiseWave;

public class AudioBufferTests {

	public static void main(String[] args) throws LineUnavailableException {
		SineWave sineWave = new SineWave();
		sineWave.setVolume(1);
		byte[] sineBuffer =  sineWave.createSynthBuffer(Pitch.octave(Pitch.C_O0, 3), 5000);
		
		
		WhiteNoiseWave whiteNoiseWave = new WhiteNoiseWave();
		byte[] whiteNoiseBuffer = whiteNoiseWave.createSynthBuffer(2000, 2000);
		
		byte[] rbuf = AudioBuffer.mix(sineBuffer, whiteNoiseBuffer);
		
		byte[] playBuf = rbuf;
		MasterSoundPlayer.getInstance().playSync(playBuf);
		System.out.println(AudioBuffer.toString(playBuf));
	}

}
