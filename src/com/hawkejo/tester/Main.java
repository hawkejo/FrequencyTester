package com.hawkejo.tester;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hawkejo.complexnumberfft.*;
import phonecs.WavFile; /* Used for reading in the *.wav files correctly as that
						   is out of the scope of this demonstration program */

/**
 * The driver program for testing all of the frequency generation classes
 */
public class Main {

	/**
	 * Tests the computation accuracy against specified input files with known frequencies.
	 */
	private static void testAccuracy() {
		FFTTester.testAccuracyFFT();
	}

	/**
	 * Tests if the a frequency can be computed from an musical instrument
	 *
	 * <p>This method tests whether or not the class can compute a frequency from
	 * a given file containing the recording of a musical instrument.</p>
	 */
	private static void testInstruments() {
		FFTTester.testInstrumentsFFT();
	}

	/**
	 * Tests the speed of the various frequency computation methods.
	 *
	 * <p>Tests how fast the computation is done with the various frequency computation
	 * methods on an extremely large array that is randomly generated.</p>
	 */
	private static void testSpeed() {
		FFTTester.testSpeedFFT();
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
