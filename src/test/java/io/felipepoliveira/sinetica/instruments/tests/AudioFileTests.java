package io.felipepoliveira.sinetica.instruments.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.felipepoliveira.sinetica.AudioBuffer;
import io.felipepoliveira.sinetica.Instrument;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.Pitch;
import io.felipepoliveira.sinetica.instruments.AudioFile;
import io.felipepoliveira.sinetica.instruments.synths.SineWave;
import io.felipepoliveira.sinetica.instruments.synths.SynthWave;

public class AudioFileTests {

	public static void main(String[] args) {
		File file = new File("C:/tmp/sinetica/wav_sample_1.wav");
		try {
			Instrument instrument = new SineWave();
			instrument.setVolume(0.2);
			AudioFile afMp31 = new AudioFile(file);
			
			System.out.println(afMp31.getAudioFormat().getFrameSize());
			
			System.out.println("Hz sine: " + SynthWave.AUDIO_FORMAT.getSampleRate());
			System.out.println("Hz wav: " + afMp31.getAudioFormat().getSampleRate());
			
			MasterSoundPlayer.getInstance().playSync(
					AudioBuffer.mix(
							afMp31.getRawBuffer(), 
							instrument.createBuffer(Pitch.getPitch(Pitch.ORD_D, 4), 5000), 
							0),
					afMp31.getAudioFormat()
					);
			
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
