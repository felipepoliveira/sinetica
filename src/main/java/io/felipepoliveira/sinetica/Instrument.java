package io.felipepoliveira.sinetica;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import io.felipepoliveira.sinetica.codec.DynamicAudioBuffer;

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
	
	/**
	 * Return the audio format used by this instrument
	 * @return
	 */
	public abstract AudioFormat getAudioFormat();
	
	/**
	 * Create an {@link SourceDataLine} based on this instrument {@link AudioFormat}
	 * @return
	 * @throws LineUnavailableException
	 */
	public SourceDataLine createSourceDataLine() throws LineUnavailableException{
		return AudioSystem.getSourceDataLine(getAudioFormat());
	}
	
	/**
	 * Create an {@link DynamicAudioBuffer} based on this instruments {@link AudioFormat} parameters and given duration
	 * @param duration
	 * @return
	 */
	public DynamicAudioBuffer createDynamicAudioBuffer(long duration) {
		return new DynamicAudioBuffer(this, duration);
	}
	
	/**
	 * Calculate the number of samples to be executed in an specific duration based
	 * on the master sample rate
	 * 
	 * @param duration
	 * @return
	 */
	public int calculateSamplesInSecond(long duration) {
		return 	((int) (duration * getAudioFormat().getSampleRate() / 1000)) 
				* getAudioFormat().getChannels()
				* (getAudioFormat().getSampleSizeInBits() / 8);
	}
	
	/**
	 * Create an empty buffer to execute the audio of this instrument based on this
	 * audio format parameters
	 * @param duration
	 * @return
	 */
	public byte[] createEmptyBuffer(long duration) {
		return new byte[calculateSamplesInSecond(duration)];
	}

	/**
	 * Calculate the period based on the sample rate and the given frequency
	 * 
	 * @param frequency
	 * @return
	 */
	public double calculatePeriod(double frequency) {
		return getAudioFormat().getSampleRate() / frequency;
	}

}
