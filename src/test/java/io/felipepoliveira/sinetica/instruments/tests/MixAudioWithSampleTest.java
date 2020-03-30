package io.felipepoliveira.sinetica.instruments.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.felipepoliveira.sinetica.AudioBuffer;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.composer.NoteQueue;
import io.felipepoliveira.sinetica.instruments.AudioFile;
import io.felipepoliveira.sinetica.instruments.synths.SineWave;

public class MixAudioWithSampleTest {

	public static void main(String[] args) {
		File file = new File("C:/tmp/sinetica/wav_sample_1.wav");
		SineWave sineWave = new SineWave();
		sineWave.setVolume(1);
		byte[] sine = 
		NoteQueue.create()
			.add(Pitch.getPitch(Pitch.ORD_F, 4), 1000)
			.add(Pitch.getPitch(Pitch.ORD_A, 4), 5000)
			.buildSheetMusic(sineWave).mix();
		
		AudioFile afMp31 = null;
		try {
			afMp31 = new AudioFile(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedAudioFileException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		try {
			MasterSoundPlayer.getInstance().playSync(AudioBuffer.mix(sine, afMp31.getRawBuffer(), 0), afMp31.getAudioFormat());
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
