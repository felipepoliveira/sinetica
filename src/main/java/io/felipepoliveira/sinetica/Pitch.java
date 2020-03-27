package io.felipepoliveira.sinetica;

public class Pitch {
	
	private Pitch() {}
	
	public static final double 
		C_O0 = 16.35,
		CS_O0 = 17.32,
		D_O0 = 18.35,
		DS_O0 = 19.45,
		E_O0 = 20.60,
		F_O0 = 21.83,
		FS_O0 = 23.12,
		G_O0 = 24.50,
		GS_O0 = 25.96,
		A_O0 = 27.50F,
		AS_O0 = 29.14,
		B_O0 = 30.87;
	
	public static final double[] FULL_O0 = {
			C_O0, CS_O0, 
			D_O0, DS_O0,
			E_O0,
			F_O0, FS_O0,
			FS_O0,
			G_O0, GS_O0,
			A_O0, AS_O0,
			B_O0
	};
	
	public static final double[] FLATS_O0 = {
			C_O0, 
			D_O0,
			E_O0,
			F_O0,
			G_O0,
			A_O0,
			B_O0
	};
	
	/**
	 * Return the frequency of an key note ordinal of an octave
	 * @param keyNoteOrdinal
	 * @param octave
	 * @return
	 */
	public static final double getPitch(int keyNoteOrdinal, int octave) {
		keyNoteOrdinal %= 12;
		return octave(FULL_O0[keyNoteOrdinal], octave);
	}
	
	/**
	 * Calculate major scale notes from an base frequency
	 * @param baseFrequency
	 * @return
	 */
	public static final double[] majorScale(double baseFrequency) {
		double[] majorScale = new double[7];
		majorScale[0] = baseFrequency;
		for (int i = 1; i < 7; i++) {
			majorScale[i] = majorScale[i-1] * (FLATS_O0[i] / FLATS_O0[i-1]);
		}
		
		return majorScale;
	}
	
	
	/**
	 * Calculate the octave of an base frequency
	 * @param frequency - The base frequency
	 * @param octaves - The amount of octaves to add. Must be an value between infinite negative to infinite positive
	 * @return
	 */
	public static final double octave(double frequency, int octaves) {
		//If octaves is 0, return the same given value
		if (octaves == 0) return frequency;
		
		//Multiply or divide the frequency if the octaves is positive or negative
		if (octaves > 0) {
			for (int i = 0; i < octaves; i++) {
				frequency *= 2;
			}
		}
		else {
			for (int i = 0; i < -octaves; i++) {
				frequency /= 2;
			}
		}
		
		return (frequency > 0) ? frequency : 0;
	}
	

}
