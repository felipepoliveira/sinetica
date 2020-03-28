package io.felipepoliveira.sinetica;

/**
 * 
 * Represent an instrument that produces sound.
 * @author Felipe Oliveira
 *
 */
public abstract class Instrument extends SoundEmitter {
	
	/**
	 * Create an audio buffer to be executed
	 * @param frequency
	 * @param duration
	 * @return
	 */
	public abstract byte[] createBuffer(double frequency, long duration);

}
