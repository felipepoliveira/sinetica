package io.felipepoliveira.sinetica;

public class AudioBuffer {
	
	/**
	 * 
	 */
	private AudioBuffer() {}
	
	/**
	 * Create an empty buffer that represents an wait tempo
	 * @param duration
	 * @return
	 */
	public static byte[] emptyBuffer(int duration) {
		return new byte[MasterSoundPlayer.getInstance().calculateSamplesInSecond(duration)];
	}
	
	/**
	 * This method return an value based on the rules:<br/>
	 * <ul>
	 * 	<li>Both are 0: return 0</li>
	 * 	<li>Both are positive: Return the max between then</li>
	 * 	<li>Both are negative: Return the min between then</li>
	 * 	<li>Both has opposite sign: Return the sum between then </li>
	 * </ul>
	 * @param a
	 * @param b
	 * @return
	 */
	public static byte mix(byte a, byte b) {
		if (a == 0 && b == 0) return (byte) 0;
		else if (a > 0 && b > 0 ) return (byte) Math.max(a, b);
		else if (a < 0 && b < 0) return (byte) Math.min(a, b);
		else return (byte) (a + b);
	}
	
	/**
	 * Sum two byte[] buffers with non rotation (values between -127 and 127)
	 * @param buf1
	 * @param buf2
	 * @return
	 */
	public static byte[] sum(byte[] buf1, byte[] buf2) {
		//Create the result buffer with the max length between the two buffers
		byte[] rbuf = new byte[Math.max(buf1.length, buf2.length)];
		for (int i = 0; i < rbuf.length; i++) {
			
			int sum = (i < buf1.length) ? (int) buf1[i] : 0; //buf1
			sum += (i < buf2.length) ? (int) buf2[i] : 0; //buf2
			
			rbuf[i] = (byte) ((sum >= 0 ) ?  Math.min(Byte.MAX_VALUE, sum) : Math.max(-Byte.MAX_VALUE, sum));
		}
		
		return rbuf;
	}
	
	/**
	 * Mix an source audio buffer into an target audio buffer starting in an offset parameter
	 * @param target
	 * @param source
	 * @param offset
	 * @return
	 */
	public static byte[] mix(byte[] target, byte[] source, int offset) {
		byte[] rbuf;
		
		//If the source buffer + offset is greater than the target buffer, increase the result buffer size
		if (source.length + offset > target.length) {
			rbuf = new byte[source.length + offset];
		}
		//Otherwise the result buffer will have the same size as the target buffer
		else {
			rbuf = new byte[target.length];
		}

		
		//Copy the target buffer into the result buffer
		for (int i = 0; i < target.length; i++) {
			rbuf[i] = target[i];
		}
		
		//Mix source buffer into target buffer
		for (int i = offset; i < source.length + offset; i++) {
			byte tb = rbuf[i];
			byte sb = source[i - offset];
			rbuf[i] = mix(sb, tb);
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
