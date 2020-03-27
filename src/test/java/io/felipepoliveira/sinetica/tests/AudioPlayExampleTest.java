package io.felipepoliveira.sinetica.tests;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import io.felipepoliveira.sinetica.Pitch;

public class AudioPlayExampleTest {

	public static void main(String[] args) throws LineUnavailableException {
		 System.out.println("Make sound");
		 byte[] buf = new byte[2];
		 int dir = 1;
		 final int FIRST_OCT = 3;
		 final int LAST_OCT = 13;
		 byte[] outroBuf = new byte[4];
		 int valor = 0x15AEFA;
		 outroBuf[0] = (byte) (valor & 0xFF);
		 outroBuf[1] = (byte) (valor & 0xFF00 >> 8);
		 outroBuf[2] = (byte) (valor & 0x00FF00 >> 16);
		 outroBuf[3] = (byte) (valor & 0xFF0000 >> 24);
		 for (int o = FIRST_OCT; o <= LAST_OCT; o = o + dir) {
			 System.out.println("Octave: " + o);
			 for (double frequency : Pitch.majorScale(Pitch.octave(Pitch.C_O0, o))) {
					System.out.println(frequency);
					AudioFormat af = new AudioFormat((float) frequency, 16, 1, true, false);
					SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
					sdl.open();
					sdl.start();
					int durationMs = 100;
					int numberOfTimesFullSinFuncPerSec = (int) frequency / 10; // number of times in 1sec sin function repeats
					float numberOfSamplesToRepresentFullSin = (float) frequency / numberOfTimesFullSinFuncPerSec;
					for (int i = 0; i < durationMs * (float) frequency / 1000; i++) { // 1000 ms in 1 second
						double angle = i / (numberOfSamplesToRepresentFullSin / 2.0) * Math.PI; // /divide with 2 since sin goes
																								// 0PI to 2PI
						short a = (short) (Math.sin(angle) * Short.MAX_VALUE / 2); // 32767 - max value for sample to take
																					// (-32767 to 32767)
						buf[0] = (byte) (a & 0xFF); // write 8bits ________WWWWWWWW out of 16
						buf[1] = (byte) (a >> 8); // write 8bits WWWWWWWW________ out of 16
						sdl.write(buf, 0, 2);
					}
					sdl.drain();
					sdl.stop();
					
				}
			 
			 if (o == FIRST_OCT) dir = 1;
			 else if (o == LAST_OCT) dir = -1;
			
		}
		 
		    
	}

}
