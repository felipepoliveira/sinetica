package io.felipepoliveira.sinetica.codec;

/**
 * All supported sample in bits sizes
 * @author Felipe Oliveira
 */
public enum SamplesInBits {
	
	$8,
	
	$16,
	
	$24,
	
	$32,
	
	;
	
	public int bytesSize() {
		switch (this) {
		case $8: return 1;
		case $16: return 2;
		case $24: return 4;
		case $32: return 4;
		default: throw new RuntimeException("Unexpected value for enumeration: " + this);
		}
	}

}
