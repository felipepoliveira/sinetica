package io.felipepoliveira.sinetica;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import io.felipepoliveira.sinetica.synths.SynthWave;

public class SoundPlayer extends SoundEmitter {
	
	public void playSync(byte[] buffer) throws LineUnavailableException {
		SourceDataLine sdl = MasterSoundPlayer.getInstance().createSourceDataLine();
		sdl.open();
		sdl.start();
		sdl.write(buffer, 0, buffer.length);
		sdl.drain();
		sdl.close();
	}
	
	public void playSync(SynthWave synthWave, double frequency, long duration) throws LineUnavailableException {
		byte[] synthWaveBuffer = synthWave.createSynthBuffer(frequency, duration);
		SourceDataLine sdl = MasterSoundPlayer.getInstance().createSourceDataLine();
		sdl.open();
		sdl.start();
		sdl.write(synthWaveBuffer, 0, synthWaveBuffer.length);
		sdl.drain();
		sdl.close();
	}

}
