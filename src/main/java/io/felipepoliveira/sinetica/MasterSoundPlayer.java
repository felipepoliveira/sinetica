package io.felipepoliveira.sinetica;

import javax.sound.sampled.AudioFormat;

/**
 * Represents Master sound
 * 
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
	private MasterSoundPlayer() {
	};

	/**
	 * The Master Sound player bar
	 */
	private Bar bar;

	/**
	 * Store the Master audio BPM
	 */
	private int bpm = 120;

	/**
	 * Store the audio format
	 */
	private AudioFormat audioFormat = null;

	/**
	 * Cerate an return the singleton instance of MasterAudioPlayer
	 * 
	 * @return
	 */
	public static MasterSoundPlayer getInstance() {
		if (masterAudioPlayer == null) {
			masterAudioPlayer = new MasterSoundPlayer();
			masterAudioPlayer.bar = new Bar(4, 4);
		}

		return masterAudioPlayer;
	}

	/**
	 * Return the Master Audio Player BPM
	 * 
	 * @return
	 */
	public int getBpm() {
		return bpm;
	}

	/**
	 * Define the Master Audio Player BPM
	 * 
	 * @param bpm
	 */
	public void setBpm(int bpm) {
		if (bpm < 1)
			bpm = 1;
		this.bpm = bpm;
	}

	public Bar getBar() {
		return bar;
	}
	
	public AudioFormat getDefaultAudioFormat() {
		return audioFormat;
	}

}
