package io.felipepoliveira.sinetica.tests;

import java.util.Scanner;

import javax.sound.midi.MidiUnavailableException;

import io.felipepoliveira.sinetica.midi.MidiDevices;

public class MidiTests {
	
	public static MidiDevices MIDI_DEVICES;

	public static void main(String[] args) throws MidiUnavailableException {
		MIDI_DEVICES = MidiDevices.getInstance();
		System.out.println(MIDI_DEVICES);
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
	}

}
