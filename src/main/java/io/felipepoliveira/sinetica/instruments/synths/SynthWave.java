package io.felipepoliveira.sinetica.instruments.synths;

import javax.sound.sampled.AudioFormat;

import io.felipepoliveira.sinetica.Instrument;

/**
 * Represents an synth sound wave
 * @author Felipe Oliveira
 */
public abstract class SynthWave extends Instrument {
	
	public static final AudioFormat AUDIO_FORMAT = new AudioFormat(44100, 16, 2, true, true);
	
	@Override
	public AudioFormat getAudioFormat() {
		return AUDIO_FORMAT;
	}
	

}
