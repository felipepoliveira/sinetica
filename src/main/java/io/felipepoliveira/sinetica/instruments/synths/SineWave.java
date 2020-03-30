package io.felipepoliveira.sinetica.instruments.synths;

import io.felipepoliveira.sinetica.codec.DynamicAudioBuffer;

/**
 * Represents an sine Synth Wave
 * @author Felipe Oliveira
 *
 */
public class SineWave extends SynthWave {

	@Override
	public byte[] createRawBuffer(double frequency, long duration) {
		DynamicAudioBuffer outputBuffer = createDynamicAudioBuffer(duration);
		int totalFrames = outputBuffer.framesCapacity();
		double period = this.calculatePeriod(frequency);
				
		for (int i = 0; i < totalFrames; i++) {
			double angle = 2.0 * Math.PI * i / period;
			outputBuffer.put((long) (outputBuffer.getMaxValue() * Math.sin(angle)));
		}
		return outputBuffer.toByteArray();
	}

}
