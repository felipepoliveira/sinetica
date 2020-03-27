package io.felipepoliveira.sinetica;

public class AudioBuffer {
	
	/**
	 * 
	 */
	private AudioBuffer() {}
	
	/**
	 * Increase the size of an byte[] buffer to an target length. <br/>
	 * <b>Example</b><br/>
	 * <b>Source buffer </b>: [-5, 0, 5, 10, 15, 20] (length: 6)<br/>
	 * <b>Target length </b>: 10<br/>
	 * <b>Final result </b>: [-5, 0, 5, 10, 15, 20, <b>-5, 0, 5, 10 </b>]
	 * @param buffer
	 * @param targetSize
	 * @return
	 */
	public static byte[] increaseBufferLength(byte[] buffer, int targetLength) {
		if (targetLength <= buffer.length) throw new IllegalArgumentException("Thr target length must be greather than the current buffer length");
		
		byte[] targetBuffer = new byte[targetLength];
		
		//Copy the source buffer into the target
		for (int i = 0; i < buffer.length; i++) {
			targetBuffer[i] = buffer[i];
		}
		
		//Put in the empty indexes the values from the array
		for (int i = buffer.length; i < targetBuffer.length; i++) {
			targetBuffer[i] = buffer[i % buffer.length];
		}
		
		return targetBuffer;
		
	}
	
	/**
	 * Mix two byte[] buffers with non rotation (values between -127 and 127)
	 * @param buf1
	 * @param buf2
	 * @return
	 */
	public static byte[] mix(byte[] buf1, byte[] buf2) {
		
		//Create the result buffer with the max length between the two buffers
		byte[] rbuf = new byte[Math.max(buf1.length, buf2.length)];
		for (int i = 0; i < rbuf.length; i++) {
			int sum = (i < buf1.length) ? buf1[i] : 0; //buf1
			sum += (i < buf2.length) ? buf2[i] : 0; //buf2
			rbuf[i] = (byte) ((sum >= 0 ) ?  Math.min(Byte.MAX_VALUE, sum) : Math.max(-Byte.MAX_VALUE, sum));
		}
		
		return rbuf;
	}
	
	/**
	 * Create an String representation from an buffer as [a, b, c, d] value
	 * @param buffer
	 * @return
	 */
	public static String toString(byte[] buffer) {
		StringBuilder str = new StringBuilder();
		str.append(buffer.length + " - [");
		int c = 0;
		for (byte b : buffer) {
			str.append(b);
			if (c < buffer.length - 1) str.append(", ");
			c++;
		}
		
		str.append("]");
		
		return str.toString();
	}

}
