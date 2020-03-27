package io.felipepoliveira.sinetica.audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.SoundPlayer;
import io.felipepoliveira.sinetica.synths.SynthWave;

public class AsyncSynthAudioClip extends AsyncAudioClip {

	/**
	 * The audio clip synth wave
	 */
	private SynthWave synthWave;

	/**
	 * Store the frequency to execute the synth wave
	 */
	private double frequency;

	/*
	 * SDL to write sound
	 */
	private SourceDataLine sdl;

	/**
	 * Create an asynchronous synth wave audio clip based
	 * 
	 * @param synthWave - The synth wave that this audio clip will execute
	 * @param frequency - The frequency that the synth wave will use
	 * @throws LineUnavailableException
	 */
	public AsyncSynthAudioClip(SoundPlayer soundPlayer, SynthWave synthWave, double frequency)
			throws LineUnavailableException {
		super(soundPlayer);
		this.synthWave = synthWave;
		this.frequency = frequency;
	}

	/**
	 * Create an asynchronous synth wave audio clip based
	 * 
	 * @param synthWave - The synth wave that this audio clip will execute
	 * @throws LineUnavailableException
	 */
	public AsyncSynthAudioClip(SoundPlayer soundPlayer, SynthWave synthWave) throws LineUnavailableException {
		this(soundPlayer, synthWave, Pitch.C_O0);
	}

	public SynthWave getSynthWave() {
		return synthWave;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public void play(SoundPlayer player) {
		System.out.println("beep");
		try {
			this.sdl = MasterSoundPlayer.getInstance().createSourceDataLine();
			this.sdl.open();
			this.sdl.start();
			byte[] buffer = synthWave.createSynthBuffer(frequency, 1000);
			this.sdl.write(buffer, 0, buffer.length);
			this.sdl.close();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	void interrupt() {
		sdl.stop();
		sdl.close();
	}
}
