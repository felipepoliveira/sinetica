package io.felipepoliveira.sinetica.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.felipepoliveira.sinetica.AudioBuffer;
import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.instruments.AudioFile;

public class MusicaDoGuilhermeTest {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		File[] faixas = new File("D:\\Users\\felip\\Downloads\\perdoado_faixas").listFiles();
		
		byte[] rbuf = new byte[1];
		
		AudioFormat audioFormat = null;
		for (File faixa : faixas) {
			AudioFile audioFile = new AudioFile(faixa);
			audioFormat = audioFile.getAudioFormat();
			System.out.println(audioFile.getAudioFormat().getSampleRate());
			rbuf = AudioBuffer.mix(rbuf, audioFile.getRawBuffer(), 0);
		}
		
		System.out.println(audioFormat.getSampleSizeInBits());
		
		MasterSoundPlayer.getInstance().playSync(rbuf, new AudioFormat(audioFormat.getSampleRate(), 16, 2, true, true));
		
		

	}

}
