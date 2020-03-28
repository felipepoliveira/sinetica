package io.felipepoliveira.sinetica.composer;

import io.felipepoliveira.sinetica.Mixable;

/**
 * Represents an audio track
 * @author Felipe Oliveira
 *
 */
public interface AudioTrack extends Mixable {
	
	/**
	 * The duration of the audio track
	 * @return
	 */
	public long getDuration();

}
