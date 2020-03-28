package io.felipepoliveira.sinetica.instruments.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.instruments.AudioFile;

public class AudioFileTests {

	public static void main(String[] args) {
		File file = new File("C:/tmp/sinetica/wav_sample_1.wav");
		try {
			AudioFile afMp31 = new AudioFile(file);
			
			MasterSoundPlayer.getInstance().playSync(afMp31);
			
			//MasterSoundPlayer.getInstance().playSync(afMp31);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
