package io.felipepoliveira.sinetica.codec;

import java.nio.ByteBuffer;

import io.felipepoliveira.sinetica.Instrument;

/**
 * Create an class that dynamically create an audio output
 * @author Felipe Oliveira
 */
public class DynamicAudioBuffer {
	
	/**
	 * Samples in bits sizes
	 */
	private SamplesInBits samplesInBitsSize;
	
	/**
	 * The audio buffer
	 */
	private ByteBuffer byteBuffer;
	
	/**
	 * The instrument used to create the dynamyc audio buffer
	 */
	private Instrument instrument;
	
	/**
	 * The duration of the audio output
	 */
	private long duration;
	
	/**
	 * Store the max value used in the sample output
	 */
	private long maxValue;
	
	/**
	 * Create an instance of {@link DynamicAudioBuffer} based on an audio format and output duration
	 * @param audioFormat
	 * @param duration
	 */
	public DynamicAudioBuffer(Instrument instrument, long duration) {
		this.instrument = instrument;
		this.duration = duration;
		this.byteBuffer = ByteBuffer.allocate(instrument.calculateSamplesInSecond(duration));
		this.setMaxValue();
	}
	
	/**
	 * Define the maximum value used in reference to allocate audio output
	 */
	private void setMaxValue() {
		if (this.instrument.getAudioFormat().getSampleSizeInBits() == 8) {
			this.maxValue = Byte.MAX_VALUE;
			this.samplesInBitsSize = SamplesInBits.$8;
		}
		else if (this.instrument.getAudioFormat().getSampleSizeInBits() == 16) {
			this.maxValue = Short.MAX_VALUE;
			this.samplesInBitsSize = SamplesInBits.$16;
		}
		else if (this.instrument.getAudioFormat().getSampleSizeInBits() == 24) {
			this.maxValue = (long) (Math.pow(2, 23) - 1);
			this.samplesInBitsSize = SamplesInBits.$32;
		}
		else if (this.instrument.getAudioFormat().getSampleSizeInBits() == 32) {
			this.maxValue = Integer.MAX_VALUE;
			this.samplesInBitsSize = SamplesInBits.$32;
		}
		else {
			throw new SampleSizeNotSupportedException(
					String.format(
							"Sample size in bits \"%s\" no supported by the platform", 
							this.instrument.getAudioFormat().getSampleSizeInBits()));
		}
	}
	
	public void put(long value) {
		switch (samplesInBitsSize) {
		case $8:
			this.byteBuffer.put((byte) value);
			break;
		case $16:
			this.byteBuffer.putShort((short) value);
			break;
		case $24:
			this.byteBuffer.putInt((int) value);
			break;
		case $32:
			this.byteBuffer.putInt((int) value);
			break;
		default:
			throw new SampleSizeNotSupportedException(
					String.format(
							"Sample size in bits \"%s\" no supported by the platform", 
							this.instrument.getAudioFormat().getSampleSizeInBits()));
		}
	}
	
	/**
	 * Return the (total number of bytes) / (Samples byte sizes) 
	 * @return
	 */
	public int framesCapacity() {
		return this.byteBuffer.capacity() / this.samplesInBitsSize.bytesSize() ;
	}
	
	/**
	 * Return total number of bytes used in this buffer
	 * @return
	 */
	public int totalCapacity() {
		return this.byteBuffer.capacity();
	}
	
	/**
	 * Return this buffer to byte array
	 * @return
	 */
	public byte[] toByteArray() {
		return this.byteBuffer.array();
	}
	
	/**
	 * The duration in milliseconds of the audio output
	 * @return
	 */
	public long getDuration() {
		return duration;
	}
	
	/**
	 * The maximum value that can be used to add into this dynamic audio buffer. The max value depends on the
	 * AudioFormat.sampleSizeInBits paramter
	 * @return
	 */
	public long getMaxValue() {
		return maxValue;
	}
	
	/**
	 * The byte buffer used to dynamically allocate audio data 
	 * @return
	 */
	public ByteBuffer getByteBuffer() {
		return byteBuffer;
	}
	
	public SamplesInBits getSamplesInBitsSize() {
		return samplesInBitsSize;
	}

}
