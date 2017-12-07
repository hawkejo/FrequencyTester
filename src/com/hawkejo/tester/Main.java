package com.hawkejo.tester;

/**
 * The driver program for testing all of the frequency generation classes
 */
public class Main {

	/**
	 * Tests the computation accuracy against specified input files with known frequencies.
	 */
	private static void testAccuracy() {
		System.out.println("Testing accuracy of various classes.");

		FFTTester.testAccuracyFFT();
	}

	/**
	 * Tests if the a frequency can be computed from an musical instrument
	 *
	 * <p>This method tests whether or not the class can compute a frequency from
	 * a given file containing the recording of a musical instrument.</p>
	 */
	private static void testInstruments() {
		System.out.println("Testing compatibility with instruments of various classes");

		FFTTester.testInstrumentsFFT();
	}

	/**
	 * Tests the speed of the various frequency computation methods.
	 *
	 * <p>Tests how fast the computation is done with the various frequency computation
	 * methods on an extremely large array that is randomly generated.</p>
	 */
	private static void testSpeed() {
		final int binSize = /*2097152;*/ 1048576;
		System.out.println("Testing speed of various classes.\nBuffer size: " + binSize);

		FFTTester.testSpeedFFT(binSize);
	}

    /**
     * Runs the program. I think this speaks for itself.
     *
     * @param args Required for the method to compile. Unused.
     */
    public static void main(String[] args) {
		testAccuracy();
		testInstruments();
		testSpeed();
    }
}
