package io.felipepoliveira.sinetica.codec;

/**
 * Exception to be thrown when an sample size is not supported by the platform
 * @author Felipe Oliveira
 *
 */
public class SampleSizeNotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SampleSizeNotSupportedException() {
		super();
	}

	public SampleSizeNotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SampleSizeNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SampleSizeNotSupportedException(String message) {
		super(message);
	}

	public SampleSizeNotSupportedException(Throwable cause) {
		super(cause);
	}
	
	

}
