package io.felipepoliveira.sinetica.composer;

import java.util.SortedSet;
import java.util.TreeSet;

import io.felipepoliveira.sinetica.Instrument;

/**
 * Note factory class
 * @author Felipe Oliveira
 *
 */
public class NoteQueue {
	
	/**
	 * All added notes
	 */
	private SortedSet<Note> notes;
	
	/**
	 * Store the last note
	 */
	private Note lastNote;
	
	/**
	 * Default note duration
	 */
	private static final long defaultDuration = 1000;
	
	
	private NoteQueue() {
		notes = new TreeSet<Note>();
	}
	
	/**
	 * Builder method
	 * @return
	 */
	public static NoteQueue build() {
		return new NoteQueue();
	}
	
	
	/**
	 * Add an note into the notes queue
	 * @param frequency - The note frequency
	 * @param duration - The note duration
	 * @param timestamp - The note start timestamp
	 * @return
	 */
	public NoteQueue add(double frequency, long duration, long timestamp) {
		lastNote = new Note(frequency, duration, timestamp);
		notes.add(lastNote);
		
		return this;
	}
	
	/**
	 * Add an note into the notes queue. It will be added after the last added note or int timestamp 0
	 * @param frequency - The note frequency
	 * @param duration - The note duration
	 * @return
	 */
	public NoteQueue add(double frequency, long duration) {
		this.add(frequency, duration, (lastNote != null) ? lastNote.getEndTimestamp() : 0);
		
		return this;
	}
	
	/**
	 * Add an note into the notes queue. It will be added after the last added note or int timestamp 0. The duration will be 
	 * the default 1000 or the last added not duration
	 * @param frequency - The note frequency
	 * @return
	 */
	public NoteQueue add(double frequency) {
		this.add(frequency, (lastNote != null) ? lastNote.getDuration() : defaultDuration);
		
		return this;
	}
	
	/**
	 * Add an note at the same time of the last one (or at time 0 if last is null)
	 * @param frequency - The note frequency
	 * @param duration - The note duration
	 * @return
	 */
	public NoteQueue with(double frequency, long duration) {
		this.add(frequency, duration, (lastNote != null) ? lastNote.getTimestamp() : 0);
		
		return this;
	}
	
	/**
	 * Add an note at the same time of the last one (or at time 0 if last is null) and the same duration (or 100 if last is null)
	 * @param frequency - The note frequency
	 * @return
	 */
	public NoteQueue with(double frequency) {
		this.with(frequency, (lastNote != null) ? lastNote.getDuration() : 0);
		
		return this;
	}
	
	/**
	 * Build an {@link SheetMusic} with the this notes
	 * @param instrument
	 * @return
	 */
	public SheetMusic buildSheetMusic(Instrument instrument) {
		SheetMusic sm = new SheetMusic(instrument);
		sm.getNotes().addAll(this.notes);
		
		return sm;
	}

}
