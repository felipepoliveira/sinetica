package io.felipepoliveira.sinetica.instruments.synths;

import io.felipepoliveira.sinetica.codec.DynamicAudioBuffer;

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
		DynamicAudioBuffer outputBuffer = createDynamicAudioBuffer(duration);
		int frames = outputBuffer.framesCapacity();
		for (int i = 0; i < frames; i++) {
			outputBuffer.put((long) (Math.random() * outputBuffer.getMaxValue()));
		}
		
		return outputBuffer.toByteArray();
	}

}
