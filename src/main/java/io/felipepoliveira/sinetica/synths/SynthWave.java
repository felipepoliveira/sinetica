package io.felipepoliveira.sinetica.synths;

/**
 * Represents an synth sound wave
 * @author Felipe Oliveira
 */
public abstract class SynthWave {
	
	
	/**
	 * 
	 * @param frequency
	 * @param duration
	 * @return
	 */
	public abstract byte[] createSynthBuffer(double frequency, long duration);

}
