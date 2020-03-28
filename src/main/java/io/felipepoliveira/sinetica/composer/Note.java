package io.felipepoliveira.sinetica.composer;

/**
 * Represents an music note (the frequency and duration)
 * @author Felipe Oliveira
 */
public class Note implements Comparable<Note>{
	
	/**
	 * The frequency of the note
	 */
	private double frequency;
	
	/**
	 * The duration of the note
	 */
	private long duration;
	
	/**
	 * The timestamp that this note must be executed
	 */
	private long timestamp;
	
	/**
	 * Create an instance of an music note
	 * @param frequency
	 * @param duration
	 */
	public Note(double frequency, long duration, long timestamp) {
		setFrequency(frequency);
		setDuration(duration);
		setTimestamp(timestamp);
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public long getEndTimestamp() {
		return timestamp + duration;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int compareTo(Note o) {
		if (this.timestamp < o.timestamp) return -1;
		else if (this.timestamp == o.timestamp) return 1;
		else return 1;
	}
}
