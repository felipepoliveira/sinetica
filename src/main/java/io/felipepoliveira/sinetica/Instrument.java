package io.felipepoliveira.sinetica;

/**
 * 
 * Represent an instrument that produces sound.
 * @author Felipe Oliveira
 *
 */
public abstract class Instrument extends SoundEmitter {
	
	
	/**
	 * Create instrument sound buffer changing it with the defined parameters as volume
	 * @param frequency
	 * @param duration
	 * @return
	 */
	public byte[] createBuffer(double frequency, long duration) {
		return applyParameters(createRawBuffer(frequency, duration));
	}
	
	/**
	 * Create the intrument raw buffer without any effects
	 * @param frequency
	 * @param duration
	 * @return
	 */
	public abstract byte[] createRawBuffer(double frequency, long duration);

}
