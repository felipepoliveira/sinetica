package io.felipepoliveira.sinetica;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Represents Master sound
 * @author Felipe Oliveira
 */
public class MasterSoundPlayer extends SoundPlayer {
	
	/**
	 * Master audio player singleton
	 */
	private static MasterSoundPlayer masterAudioPlayer;
	
	/*
	 * Only singleton allowed
	 */
	private MasterSoundPlayer() {};
	
	/**
	 * The Master Sound player bar
	 */
	private Bar bar;
	
	/**
	 * Store the Master audio BPM
	 */
	private int bpm = 120;
	
	/**
	 * Store the sample rate
	 */
	private int sampleRate;
	
	private AudioFormat audioFormat = null;
	
	/**
	 * Cerate an return the singleton instance of MasterAudioPlayer
	 * @return
	 */
	public static MasterSoundPlayer getInstance() {
		if (masterAudioPlayer == null) {
			masterAudioPlayer = new MasterSoundPlayer();
			masterAudioPlayer.bar = new Bar(4, 4);
			masterAudioPlayer.setSampleRate(16 * 1024);
		}
		
		return masterAudioPlayer;
	}
	
	public SourceDataLine createSourceDataLine() throws LineUnavailableException {
		return AudioSystem.getSourceDataLine(audioFormat);
	}
	
	/**
	 * Calculate the number of samples to be executed in an specific duration based on the master sample rate
	 * @param duration
	 * @return
	 */
	public int calculateSamplesInSecond(long duration) {
		return (int) (duration * getSampleRate() / 1000);
	}
	
	/**
	 * Calculate the period based on the sample rate and the given frequency
	 * @param frequency
	 * @return
	 */
	public double calculatePeriod(double frequency) {
		return getSampleRate() / frequency;
	}

	/**
	 * Return the Master Audio Player BPM
	 * @return
	 */
	public int getBpm() {
		return bpm;
	}

	/**
	 * Define the Master Audio Player BPM
	 * @param bpm
	 */
	public void setBpm(int bpm) {
		if (bpm < 1) bpm = 1;
		this.bpm = bpm;
	}
	
	public Bar getBar() {
		return bar;
	}
	
	public int getSampleRate() {
		return sampleRate;
	}
	
	private void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
		this.audioFormat = new AudioFormat(sampleRate, 8, 1, true, true);
	}
	
}
