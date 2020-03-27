package io.felipepoliveira.sinetica.synths;

import io.felipepoliveira.sinetica.SoundEmitter;

/**
 * Represents an synth sound wave
 * @author Felipe Oliveira
 */
public abstract class SynthWave extends SoundEmitter{
	
	
	
	/**
	 * Create an synth audio buffer
	 * @param frequency
	 * @param duration
	 * @return
	 */
	public abstract byte[] createSynthBuffer(double frequency, long duration);

}
