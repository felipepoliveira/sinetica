package io.felipepoliveira.sinetica.audio;

import io.felipepoliveira.sinetica.SoundPlayer;

/**
 * Platform abstract for audio clips
 * @author Felipe Oliveira
 *
 */
public interface AudioClip {
	
	/**
	 * Play an audio clip
	 * @param player - The sound player that will execute the clip
	 */
	public abstract void play(SoundPlayer player);
	
	/**
	 * Stop audio clip
	 */
	public abstract void stop();

}
