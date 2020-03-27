package io.felipepoliveira.sinetica;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import io.felipepoliveira.sinetica.synths.SynthWave;

public class SoundPlayer {
	
	/**
	 * Store the amplitude of the audio track
	 */
	private short amplitude;
	
	/**
	 * The amplitude volume. From min: 0 to max: 32767
	 * @return
	 */
	public short getAmplitude() {
		return amplitude;
	}
	
	/**
	 * Define the volume of the audio player.
	 * @param volume - An decimal value from 0 to 1
	 */
	public void setVolume(float volume) {
		
		//Adjust the volume before definition
		if (volume < 0) volume = 0;
		else if (volume > 1) volume = 1;
		
		this.amplitude = (short) (Short.MAX_VALUE * volume);
	}
	
	/**
	 * Get the volume of the amplitude based on an decimal value from 0 to 1
	 * @return
	 */
	public float getVolume() {
		return ((float) this.amplitude) / Short.MAX_VALUE;
	}

	
	public void play(SynthWave synthWave, double frequency, long duration) throws LineUnavailableException {
		byte[] synthWaveBuffer = synthWave.createSynthBuffer(frequency, duration);
		SourceDataLine sdl = MasterSoundPlayer.getInstance().createSourceDataLine();
		sdl.open();
		sdl.start();
		sdl.write(synthWaveBuffer, 0, synthWaveBuffer.length);
		sdl.drain();
		sdl.close();
	}

}
