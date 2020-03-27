package io.felipepoliveira.sinetica.tests;

import java.util.Scanner;

import javax.sound.midi.MidiUnavailableException;

import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.midi.MidiDevices;

public class MidiTests {
	
	public static MidiDevices midiDevices;

	public static void main(String[] args) throws MidiUnavailableException {
		midiDevices = MidiDevices.getInstance();
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("Write youd command");
			String cmd = scanner.nextLine().toLowerCase();
			if (cmd.startsWith("vol")) {
				try {
					double vol = Double.parseDouble(cmd.split(" ")[1]) / 100;
					MasterSoundPlayer.getInstance().setVolume(vol);
				} catch (Exception e) {
					System.out.println("Vol command must be: vol <int:0-100>");
				}
			}
			else if (cmd.startsWith("close")) {
				break;
			}
		}
		
		scanner.close();
		
	}

}
