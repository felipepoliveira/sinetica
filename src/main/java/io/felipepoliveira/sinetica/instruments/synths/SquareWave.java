package io.felipepoliveira.sinetica.instruments.synths;

/**
 * Represents an square wave
 * @author Felipe Oliveira
 *
 */
public class SquareWave extends SynthWave {

	@Override
	public byte[] createRawBuffer(double frequency, long duration) {
		byte[] output = new byte[this.calculateSamplesInSecond(duration)];
		
		return output;
	}

}
