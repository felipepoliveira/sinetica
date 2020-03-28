package io.felipepoliveira.sinetica;

/**
 * Represents an device that reproduces sound
 * @author Felipe Oliveira
 *
 */
public class SoundEmitter {
	
	private double volume = 1;
	
	public byte adjustOutputWithVolume(byte output) {
		return (byte) (output * volume);
	}
	
	public double getVolume() {
		return volume;
	}
	
	public void setVolume(double volume) {
		if (volume < 0) volume = 0;
		else if (volume > 1) volume = 1;
		this.volume = volume;
	}

}
