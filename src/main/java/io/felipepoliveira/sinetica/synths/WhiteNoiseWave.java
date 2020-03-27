package io.felipepoliveira.sinetica.synths;

import io.felipepoliveira.sinetica.MasterSoundPlayer;

public class WhiteNoiseWave extends SynthWave {
	
	public WhiteNoiseWave() {
		setVolume(0.5);
	}

	@Override
	public byte[] createSynthBuffer(double frequency, long duration) {
		byte[] output = new byte[MasterSoundPlayer.getInstance().calculateSamplesInSecond(duration)];
		
		for (int i = 0; i < output.length; i++) {
			byte bOutput = (byte) (Math.random() * 127 * ((i % 2 == 0) ? 1 : -1));
			output[i] = adjustOutputWithVolume(bOutput);
		}
		
		return output;
	}

}
