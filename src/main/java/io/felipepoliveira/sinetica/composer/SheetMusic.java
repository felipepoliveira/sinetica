package io.felipepoliveira.sinetica.composer;

import java.util.Set;
import java.util.TreeSet;

import io.felipepoliveira.sinetica.AudioBuffer;
import io.felipepoliveira.sinetica.Instrument;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Mixable;

/**
 * Represent an collection of musical notes
 * @author Felipe Oliveira
 *
 */
public class SheetMusic implements AudioTrack, Mixable{
	
	/**
	 * Store the note sheet in time
	 */
	private Set<Note> notes;
	
	/**
	 * The instrument that will play this sheet music
	 */
	private Instrument instrument;
	
	/**
	 * Store the duration of this sheet music
	 */
	private long duration = 0;
	
	public SheetMusic(Instrument instrument) {
		setInstrument(instrument);
		notes = new TreeSet<Note>();
	}
	
	/**
	 * Add an note into this sheet music
	 * @param note
	 */
	public void addNote(Note note) {
		
		//Store the duration of this sheet music
		if (this.duration < note.getEndTimestamp()) {
			this.duration = note.getEndTimestamp();
		}
		
		notes.add(note);
	}
	
	/**
	 * Add an note into this sheet music
	 * @param frequency - The frequency of the note
	 * @param duration - The duration of the note
	 * @param timestamp - When the note must be played
	 */
	public void addNote(double frequency, long duration, long timestamp) {
		this.addNote(new Note(frequency, duration, timestamp));
	}
	
	/**
	 * Remove an from this sheet music
	 * @param note
	 * @return
	 */
	public boolean removeNote(Note note) {
		return notes.remove(note);
	}
	
	public Set<Note> getNotes() {
		return notes;
	}
	
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Instrument getInstrument() {
		return instrument;
	}
	
	public long getDuration() {
		return duration;
	}

	public byte[] mix() {
		//Create an buffer with the size of this track duration
		byte[] trackBuffer = new byte[MasterSoundPlayer.getInstance().calculateSamplesInSecond(getDuration())];
		
		int offset = 0;
		long lastTimestamp = 0;
		
		//Iterate over each note...
		for (Note note : notes) {
			
			//If the timestamp of the current note is greater than the last timestamp add empty bytes into it
			if (note.getTimestamp() > lastTimestamp) {
				offset = MasterSoundPlayer.getInstance().calculateSamplesInSecond(note.getTimestamp());
			}
			lastTimestamp = note.getTimestamp();
			
			//Create the buffer with the sheet instrument with the current note
			byte[] noteBuffer = this.instrument.createBuffer(note.getFrequency(), note.getDuration());
			
			//Add the noteBuffer into the final track buffer
			trackBuffer =  AudioBuffer.mix(trackBuffer, noteBuffer, offset);
		}
		
		return trackBuffer;
		
	}
}
