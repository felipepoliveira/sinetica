package io.felipepoliveira.sinetica.tests;

import javax.sound.sampled.LineUnavailableException;

import io.felipepoliveira.sinetica.AudioBuffer;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.instruments.synths.SineWave;
import io.felipepoliveira.sinetica.instruments.synths.WhiteNoiseWave;

public class AudioBufferTests {

	public static void main(String[] args) throws LineUnavailableException {
		SineWave sineWave = new SineWave();
		sineWave.setVolume(1);
		byte[] sineBuffer =  sineWave.createBuffer(Pitch.octave(Pitch.C_O0, 6), 5000);
		
		
		WhiteNoiseWave whiteNoiseWave = new WhiteNoiseWave();
		byte[] whiteNoiseBuffer = whiteNoiseWave.createBuffer(50, 2000);
		
		byte[] rbuf = AudioBuffer.sum(sineBuffer, whiteNoiseBuffer);
		
		byte[] playBuf = rbuf;
		MasterSoundPlayer.getInstance().playSync(playBuf);
		MasterSoundPlayer.getInstance().playSync(AudioBuffer.emptyBuffer(1000));
		MasterSoundPlayer.getInstance().playSync(sineBuffer);
	}

}
