package io.felipepoliveira.sinetica.composer.tests;

import javax.sound.sampled.LineUnavailableException;

import io.felipepoliveira.sinetica.Instrument;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.composer.SheetMusic;
import io.felipepoliveira.sinetica.instruments.synths.SineWave;

public class SheetMusicTest {

	public static void main(String[] args) {

		
		Instrument instrument = new SineWave();
		instrument.setVolume(1);
		SheetMusic sheetMusic = new SheetMusic(instrument);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 4), 400, 0);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_D, 4), 400, 400);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_E, 4), 400, 800);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_F, 4), 400, 1200);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_F, 4), 400, 2000);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_F, 4), 400, 2400);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 4), 400, 3200);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_D, 4), 400, 3600);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 4), 400, 4000);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_D, 4), 400, 4400);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_D, 4), 400, 5200);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_D, 4), 400, 5600);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 4), 400, 6400);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_G, 4), 400, 6800);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_A, 4), 400, 7200);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_E, 4), 400, 7600);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_E, 4), 400, 8400);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_E, 4), 400, 8800);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 4), 400, 9600);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_D, 4), 400, 10000);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_E, 4), 400, 10400);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_F, 4), 400, 10800);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_F, 4), 400, 11600);
		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_F, 4), 400, 12000);
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 4), 2000, 0);
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_G, 4), 2000, 0);
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 2), 2000, 2000);
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 2), 2000, 2000);
		
		
		
		try {
			MasterSoundPlayer.getInstance().playSync(sheetMusic.mix());
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
