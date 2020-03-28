package io.felipepoliveira.sinetica.instruments.synths;

import io.felipepoliveira.sinetica.MasterSoundPlayer;

public class SineWave extends SynthWave{

	@Override
	public byte[] createBuffer(double frequency, long duration) {
		byte[] output = new byte[MasterSoundPlayer.getInstance().calculateSamplesInSecond(duration)];
		double period = MasterSoundPlayer.getInstance().calculatePeriod(frequency);
				
		for (int i = 0; i < output.length; i++) {
			double angle = 2.0 * Math.PI * i / period;
			output[i] = adjustOutputWithVolume((byte)((Math.sin(angle) * Byte.MAX_VALUE)));
		}
		
		return output;
	}

}