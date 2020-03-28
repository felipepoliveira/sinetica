package io.felipepoliveira.sinetica;

/**
 * 
 * Represents an entity that can mix {@link Instrument} into one byte[]
 * @author Felipe Oliveira
 *
 */
public interface Mixable {

	/**
	 * Return an mixed buffer of all instruments of this entity
	 * @return
	 */
	public byte[] mix();
}
