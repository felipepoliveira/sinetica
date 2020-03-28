package io.felipepoliveira.sinetica.composer;

import java.util.LinkedList;
import java.util.List;

import io.felipepoliveira.sinetica.Instrument;
import io.felipepoliveira.sinetica.SoundEmitter;

/**
 * Represents an mixer of instruments
 * @author Felipe Oliveira
 */
public class Mixer extends SoundEmitter {
	
	/**
	 * The instruments of this mixer
	 */
	private List<Instrument> instruments;
	
	/**
	 * Create an instance of an mixer
	 */
	public Mixer() {
		instruments = new LinkedList<Instrument>();
	}
	
	/**
	 * Add an {@link Instrument} into this mixer
	 * @param instrument
	 */
	public void addInstrument(Instrument instrument) {
		this.instruments.add(instrument);
	}
	
	/**
	 * Remove an instrument from this mixer
	 * @param instrument
	 * @return
	 */
	public boolean removeInstrument(Instrument instrument) {
		return this.instruments.remove(instrument);
	}

}
