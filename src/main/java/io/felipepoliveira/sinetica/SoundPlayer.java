package io.felipepoliveira.sinetica;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import io.felipepoliveira.sinetica.instruments.AudioFile;

public class SoundPlayer extends SoundEmitter {
	
	public void playSync(byte[] buffer, AudioFormat format) throws LineUnavailableException {
		SourceDataLine sdl = AudioSystem.getSourceDataLine(format);
		sdl.open();
		sdl.start();
		sdl.write(buffer, 0, buffer.length);
		sdl.drain();
		sdl.close();
	}
	
	public void playSync(Instrument instrument, double frequency, long duration) throws LineUnavailableException {
		byte[] synthWaveBuffer = instrument.createBuffer(frequency, duration);
		SourceDataLine sdl = instrument.createSourceDataLine();
		sdl.open();
		sdl.start();
		sdl.write(synthWaveBuffer, 0, synthWaveBuffer.length);
		sdl.drain();
		sdl.close();
	}
	
	public void playSync(AudioFile audioFile) throws LineUnavailableException {
		SourceDataLine sdl = audioFile.createSourceDataLine();
		sdl.open();
		sdl.start();
		sdl.write(audioFile.getRawBuffer(), 0, audioFile.getRawBuffer().length);
		sdl.drain();
		sdl.close();
	}


}
