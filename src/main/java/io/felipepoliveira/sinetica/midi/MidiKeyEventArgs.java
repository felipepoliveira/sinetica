package io.felipepoliveira.sinetica.midi;

import io.felipepoliveira.sinetica.Pitch;

/**
 * Represents the events from an key pressed or released
 * @author Felipe Olvieira
 */
public class MidiKeyEventArgs {
	
	int keyCode;
	
	int intensityCode;

	/**
	 * The code of the key pressed
	 * @return
	 */
	public int getKeyCode() {
		return keyCode;
	}
	
	/**
	 * Return the key note pitch frequency
	 * @return
	 */
	public double getPitch() {
		return Pitch.getPitch(getKeyNoteOrdinal(), getOctave());
	}
	
	
	/**
	 * Return the ordinal from 0 to 12 on the key octave
	 * @return
	 */
	public int getKeyNoteOrdinal() {
		return keyCode % 12;
	}
	
	/**
	 * Get the octave that the key is derived
	 * @return
	 */
	public int getOctave() {
		return getKeyCode() / 12;
	}

	/**
	 * Get the intensity code
	 * @return
	 */
	public int getIntensityCode() {
		return intensityCode;
	}
	
	/**
	 * Get the intensity of the key pressed on an value between 0 to 1
	 * @return
	 */
	public double getIntensity() {
		return intensityCode / 127d; 
	}
}
