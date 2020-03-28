package io.felipepoliveira.sinetica.instruments;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.felipepoliveira.sinetica.Instrument;

/**
 * Represents an audio file as an instrument
 * @author Felipe Oliveira
 *
 */
public class AudioFile extends Instrument {
	
	/*
	 * Store the audio file input stream
	 */
	private AudioInputStream audioInputStream;
	
	/**
	 * The format used in this audio file
	 */
	private AudioFormat audioFormat;
	
	/**
	 * Store the audio buffer
	 */
	private byte[] audioRawBuffer;
	
	/**
	 * Create an instance of audio file as an platform instrument
	 * @param audioFileIS - The audio file input stream
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public AudioFile(InputStream audioFileIS) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audioFileIS));
		readAudioInputStream();
	}
	
	/**
	 * Create an instance of audio file as an platform instrument 
	 * @param file - The file with the path to the audio file
	 * @throws FileNotFoundException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException 
	 */
	public AudioFile(File file) throws FileNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		this(new FileInputStream(file));
	}
	
	/**
	 * Read the audio input stream data and store it on this object field audioFileBuffer.
	 * The data can be read using the method AudioFile.getAudioFileBuffer()
	 * @throws IOException 
	 * @throws LineUnavailableException 
	 */
	private void readAudioInputStream() throws IOException, LineUnavailableException {
		this.audioRawBuffer = this.audioInputStream.readAllBytes();
		this.audioFormat = this.audioInputStream.getFormat();
	}
	
	/**
	 * Return the raw buffer from this audio file
	 * @return
	 */
	public byte[] getRawBuffer() {
		return audioRawBuffer;
	}
	
	/**
	 * Return the audio format of this audio file
	 * @return
	 */
	public AudioFormat getAudioFormat() {
		return this.audioFormat;
	}
	
	public AudioInputStream getAudioInputStream() {
		return audioInputStream;
	}
	
	@Override
	public byte[] createRawBuffer(double frequency, long duration) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Create an source data line for this audio file format
	 * @return
	 * @throws LineUnavailableException
	 */
	public SourceDataLine createSourceDataLine() throws LineUnavailableException {
		return AudioSystem.getSourceDataLine(this.audioFormat);
	}

}
