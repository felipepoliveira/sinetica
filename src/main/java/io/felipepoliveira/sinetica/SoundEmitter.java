package io.felipepoliveira.sinetica;

/**
 * Represents an device that reproduces sound
 * @author Felipe Oliveira
 *
 */
public class SoundEmitter {
	
	/*
	 * Sound emitter volume
	 */
	private double volume = 1;
	
	/**
	 * Modify the given byte with the sound emitter volume value
	 * @param output - The output to be changed
	 * @return
	 */
	public byte applyVolumeParameter(byte output) {
		return (byte) (output * volume);
	}
	
	/**
	 * Apply all sound emitters parameter into an raw audio buffer
	 * @param rawBuffer
	 * @return
	 */
	public byte[] applyParameters(byte[] rawBuffer) {
		for (int i = 0; i < rawBuffer.length; i++) {
			rawBuffer[i] = applyVolumeParameter(rawBuffer[i]);
		}
		
		return rawBuffer;
	}
	
	/**
	 * Return the value of this sound emitter
	 * @return
	 */
	public double getVolume() {
		return volume;
	}
	
	/**
	 * Define the volume of this sound emitter
	 * @param volume - An decimal value between 0 and 1
	 */
	public void setVolume(double volume) {
		if (volume < 0) volume = 0;
		else if (volume > 1) volume = 1;
		this.volume = volume;
	}

}
