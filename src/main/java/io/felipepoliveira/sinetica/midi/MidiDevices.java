package io.felipepoliveira.sinetica.midi;

import java.util.ArrayList;
import java.util.Collection;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import javax.sound.sampled.LineUnavailableException;

import io.felipepoliveira.sinetica.MasterSoundPlayer;
import io.felipepoliveira.sinetica.synths.SineWave;
import io.felipepoliveira.sinetica.synths.WhiteNoiseWave;

public class MidiDevices implements Receiver {
	
	private static MidiDevices instance;
	
	public static final int 
		MIDI_KEY_RELEASED = -128,
		MIDI_KEY_PRESSED = -112;
	
	public static final int[] MIDI_KEY_EVENTS = {MIDI_KEY_PRESSED, MIDI_KEY_RELEASED};
	
	private MidiKeyEventArgs midiKeyEventArgs = new MidiKeyEventArgs();
	
	private Collection<MidiDevice> devices;
	
	private Collection<MidiDevice> receivers;
	
	private Collection<MidiDevice> transmitters;
	
	private MidiDevices() {
		searchAndOpenDevices();
	}
	
	public static MidiDevices getInstance() {
		if (instance == null) {
			instance = new MidiDevices();
		}
		
		return instance;
	}
	
	/**
	 * Return an flag indicating if the given MIDI device has an {@link Transmitter}
	 * @param device
	 * @return
	 */
	public static boolean hasTransmitter(MidiDevice device) {
		try {
			device.getTransmitter();
			return true;
		} catch (MidiUnavailableException e) {
			return false;
		}
	}
	
	/**
	 * Return an flag indicating if the given MIDI device has an {@link Receiver}
	 * @param device
	 * @return
	 */
	public static boolean hasReceiver(MidiDevice device) {
		try {
			device.getReceiver();
			return true;
		} catch (MidiUnavailableException e) {
			return false;
		}
	}
	
	/**
	 * Add an {@link Receiver} {@link MidiDevice} adding the default
	 * @param midiDevice
	 */
	private void addReceiver(MidiDevice midiDevice) {
		this.receivers.add(midiDevice);
	}
	
	private void addTransmitter(MidiDevice midiDevice) {
		this.transmitters.add(midiDevice);
		
		try {
			midiDevice.getTransmitter().setReceiver(this);
		} catch (MidiUnavailableException e) {
			throw new RuntimeException("An error occur while fetching the MIDI transmitter for device " + midiDevice.getDeviceInfo().getName() + ""
					+ ".\nError code: MI-0001");
		}
	}
	
	/**
	 * Close all devices channels
	 */
	public void closeDevices() {
		for (MidiDevice device : getDevices()) {
			device.close();
		}
	}
	
	/**
	 * Search for MIDI devices connected on the computer
	 */
	public void searchAndOpenDevices() {
		//Clear devices list
		devices = new ArrayList<MidiDevice>(10);
		receivers = new ArrayList<MidiDevice>(10);
		transmitters  = new ArrayList<MidiDevice>(10);
		
		//Iterate over all MIDI devices
		for (MidiDevice.Info midiDeviceInfo : MidiSystem.getMidiDeviceInfo()) {
			
			//Get the current MIDI device
			MidiDevice midiDevice = null;
			try {
				midiDevice = MidiSystem.getMidiDevice(midiDeviceInfo);
				
				devices.add(midiDevice);
				if (MidiDevices.hasReceiver(midiDevice)) addReceiver(midiDevice);;
				if (MidiDevices.hasTransmitter(midiDevice)) addTransmitter(midiDevice);
				
				midiDevice.open();
				
				System.out.println(String.format("Successfully loaded \"%s\" device", midiDevice.getDeviceInfo().getName()));
				
			} catch (MidiUnavailableException e) {
				System.out.println(String.format("Failed to load \"%s\" MIDI Device", 
						((midiDevice != null) ? midiDevice.getDeviceInfo().getName() : "[NOT IDENTIFIED]")));
			}
		}
	}

	public Collection<MidiDevice> getDevices() {
		return devices;
	}

	public Collection<MidiDevice> getReceivers() {
		return receivers;
	}

	public Collection<MidiDevice> getTransmitters() {
		return transmitters;
	}
	
	private boolean isKeyEvent(MidiMessage msg) {
		if (msg.getMessage().length < 3) return false;
		
		for (int keyEvent : MIDI_KEY_EVENTS) {
			if (msg.getMessage()[0] == keyEvent) return true;
		}
		
		return false;
	}
	
	private boolean isKeyPressed(MidiMessage msg) {
		return (msg.getMessage().length >= 1 && msg.getMessage()[0] == MIDI_KEY_PRESSED);
	}
	
//	private boolean isKeyReleased(MidiMessage msg) {
//		return (msg.getMessage().length >= 1 && msg.getMessage()[0] == MIDI_KEY_RELEASED);
//	}

	WhiteNoiseWave whiteNoiseWave = new WhiteNoiseWave();
	SineWave sineWave = new SineWave();
	public void send(MidiMessage message, long timeStamp) {
		
		//KEY_EVENT
		if (isKeyEvent(message)) {
			midiKeyEventArgs.keyCode = message.getMessage()[1];
			midiKeyEventArgs.intensityCode = message.getMessage()[2];
			
			if (isKeyPressed(message)) {
				try {
					MasterSoundPlayer.getInstance().playSync(whiteNoiseWave, midiKeyEventArgs.getPitch(), 1000);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void close() {
		
	}
	
	@Override
	public String toString() {
		return String.format("{Devices: %s; Receivers: %s, Transmitters: %s}", devices.size(), receivers.size(), transmitters.size()); 
	}
}
