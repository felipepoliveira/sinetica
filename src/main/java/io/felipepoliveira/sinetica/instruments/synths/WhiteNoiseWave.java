package io.felipepoliveira.sinetica.instruments.synths;

import io.felipepoliveira.sinetica.MasterSoundPlayer;

/**
 * Represents an white noise wave
 * @author Felipe Oliveira
 *
 */
public class WhiteNoiseWave extends SynthWave {
	
	public WhiteNoiseWave() {
		setVolume(0.5);
	}

	@Override
	public byte[] createRawBuffer(double frequency, long duration) {
		byte[] output = new byte[MasterSoundPlayer.getInstance().calculateSamplesInSecond(duration)];
		
		for (int i = 0; i < output.length; i++) {
			output[i] = (byte) (Math.random() * 127 * ((i % 2 == 0) ? 1 : -1));
		}
		
		return output;
	}

}
