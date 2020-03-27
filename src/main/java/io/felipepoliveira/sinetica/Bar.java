package io.felipepoliveira.sinetica;

/**
 * Represents an music Bar
 * @author Felipe Oliveira
 */
public class Bar {
	
	private int numberOfBeats;
	
	private int measure;
	
	/**
	 * Create an default 4/4 Bar
	 */
	public Bar() {
		this(4, 4);
	}
	
	/**
	 * Create an Bar with the given number of beats and measure
	 * @param numberOfBeats
	 * @param measure
	 */
	public Bar(int numberOfBeats, int measure) {
		setNumberOfBeats(numberOfBeats);
		setMeasure(measure);
	}

	public int getNumberOfBeats() {
		return numberOfBeats;
	}

	public void setNumberOfBeats(int numberOfBeats) {
		if (numberOfBeats < 0) numberOfBeats = 1;
		this.numberOfBeats = numberOfBeats;
	}

	public int getMeasure() {
		return measure;
	}

	public void setMeasure(int measure) {
		if (measure < 1) measure = 1;
		this.measure = measure;
	}
	
	

}
