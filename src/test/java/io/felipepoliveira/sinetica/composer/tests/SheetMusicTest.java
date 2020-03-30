package io.felipepoliveira.sinetica.composer.tests;

import javax.sound.sampled.LineUnavailableException;

import io.felipepoliveira.sinetica.AudioBuffer;
import io.felipepoliveira.sinetica.Instrument;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.composer.NoteQueue;
import io.felipepoliveira.sinetica.composer.SheetMusic;
import io.felipepoliveira.sinetica.instruments.synths.SineWave;
import io.felipepoliveira.sinetica.instruments.synths.SynthWave;

public class SheetMusicTest {

	public static void main(String[] args) {

		
		Instrument instrument = new SineWave();
		instrument.setVolume(1);
		
		SheetMusic sheet = 
		NoteQueue.create()
			.add(Pitch.getPitch(Pitch.ORD_C, 3), 250)
			.add(Pitch.getPitch(Pitch.ORD_D, 3))
			.add(Pitch.getPitch(Pitch.ORD_E, 3))
			.add(Pitch.getPitch(Pitch.ORD_F, 3))
			.add(0)
			.add(Pitch.getPitch(Pitch.ORD_F, 4))
			.add(Pitch.getPitch(Pitch.ORD_F, 4))
			.add(0)
			.add(Pitch.getPitch(Pitch.ORD_C, 4))
			.add(Pitch.getPitch(Pitch.ORD_D, 4))
			.add(Pitch.getPitch(Pitch.ORD_C, 4))
			.add(Pitch.getPitch(Pitch.ORD_D, 4))
			.add(0)
			.add(Pitch.getPitch(Pitch.ORD_D, 4))
			.add(Pitch.getPitch(Pitch.ORD_D, 4))
			.add(0)
			.add(Pitch.getPitch(Pitch.ORD_C, 4))
			.add(Pitch.getPitch(Pitch.ORD_G, 4))
			.add(Pitch.getPitch(Pitch.ORD_A, 4))
			.add(Pitch.getPitch(Pitch.ORD_E, 4))
			.add(0)
			.add(Pitch.getPitch(Pitch.ORD_E, 4))
			.add(Pitch.getPitch(Pitch.ORD_E, 4))
			.add(0)
			.add(Pitch.getPitch(Pitch.ORD_C, 4))
			.add(Pitch.getPitch(Pitch.ORD_D, 4))
			.add(Pitch.getPitch(Pitch.ORD_E, 4))
			.add(Pitch.getPitch(Pitch.ORD_F, 4))
			.add(0)
			.add(Pitch.getPitch(Pitch.ORD_F, 4))
			.add(Pitch.getPitch(Pitch.ORD_F, 4))
			.buildSheetMusic(instrument);
		
		//Pegar o audio do sheet music
		byte[] sheetMusicBuffer = sheet.mix();
		
		//Pegando o buffer de um audio normal do instrumento
		byte[] audioSine = instrument.createBuffer(Pitch.getPitch(Pitch.ORD_C, 3), 7000);
		
		
		
			
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 4), 2000, 0);
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_G, 4), 2000, 0);
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 2), 2000, 2000);
//		sheetMusic.addNote(Pitch.getPitch(Pitch.ORD_C, 2), 2000, 2000);
		
		
		
		try {
			MasterSoundPlayer.getInstance().playSync(AudioBuffer.mix(sheetMusicBuffer, audioSine, 0), SynthWave.AUDIO_FORMAT);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
