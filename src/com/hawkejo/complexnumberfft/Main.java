package com.hawkejo.complexnumberfft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import phonecs.WavFile; /* Used for reading in the *.wav files correctly as that
						   is out of the scope of this demonstration program */

/**
 * The driver program for testing all of the frequency generation classes
 */
public class Main {

	/**
	 * Tests the accuracy of the FFTFreqGen class
	 */
	private static void testAccuracyFFT() {
		int binSize = 32768 * 4;

		System.out.println("Testing Accuracy.");

		// Test the FFTFreqGen class
		// Test 440 Hz
		try
		{
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File("res/440sr44100.wav"));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Create a buffer of 100 frames
			double[] buffer = new double[binSize * numChannels];

			int framesRead = wavFile.readFrames(buffer, binSize);
			System.out.format("\nItems read: %d\n", framesRead);

			// Close the wavFile
			wavFile.close();

			List<ComplexNumber> toFFT = new ArrayList<>(binSize);
			for(int i = 0; i < binSize; i++) {
				toFFT.add(new ComplexNumber(buffer[i], 0));
			}

			FFTFreqGen freqGen = new FFTFreqGen(toFFT, 44100);

			freqGen.computeFrequencyMT();

			System.out.format("\nFrequency: %f\n", freqGen.getFrequency());
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		System.out.println("Done: 440 Hz file\n");

		// Test 1 kHz
		try
		{
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File("res/1ksr44100.wav"));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Create a buffer of 100 frames
			double[] buffer = new double[binSize * numChannels];

			int framesRead = wavFile.readFrames(buffer, binSize);
			System.out.format("Items read: %d\n", framesRead);

			// Close the wavFile
			wavFile.close();

			List<ComplexNumber> toFFT = new ArrayList<>(binSize);
			for(int i = 0; i < binSize; i++) {
				toFFT.add(new ComplexNumber(buffer[i], 0));
			}

			FrequencyGenerator freqGen = new FFTFreqGen(toFFT, 44100);

			freqGen.computeFrequency();

			System.out.format("\nFrequency: %f\n", freqGen.getFrequency());
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		System.out.println("Done: 1000 Hz file\n");

		// Test 10 kHz.
		try
		{
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File("res/10ksr44100.wav"));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Create a buffer of 100 frames
			double[] buffer = new double[binSize * numChannels];

			int framesRead = wavFile.readFrames(buffer, binSize);
			System.out.format("\nItems read: %d\n", framesRead);

			// Close the wavFile
			wavFile.close();

			List<ComplexNumber> toFFT = new ArrayList<>(binSize);
			for(int i = 0; i < binSize; i++) {
				toFFT.add(new ComplexNumber(buffer[i], 0));
			}

			FrequencyGenerator freqGen = new FFTFreqGen(toFFT, 44100);

			freqGen.computeFrequency();

			System.out.format("\nFrequency: %f\n", freqGen.getFrequency());
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		System.out.println("Done: 10k Hz file\n");
	}

	/**
	 * Tests the computation accuracy against specified input files with known frequencies.
	 */
	private static void testAccuracy() {
		testAccuracyFFT();
	}

	/**
	 * Tests the FFTFreqGen class against files containing instrument audio data
	 */
	private static void testInstrumentsFFT() {
		int binSize = 32768 * 4;

		System.out.println("\nTesting Instruments.");

		// Test the FFTFreqGen class
		// Test against 6-string acoustic guitar ~ 326.538 Hz
		try
		{
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File("res/Guitarsr16000.wav"));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Create a buffer of 100 frames
			double[] buffer = new double[binSize * numChannels];

			int framesRead = wavFile.readFrames(buffer, binSize);
			System.out.format("Items read: %d\n", framesRead);

			// Close the wavFile
			wavFile.close();

			List<ComplexNumber> toFFT = new ArrayList<>(binSize);
			for(int i = 0; i < binSize; i++) {
				toFFT.add(new ComplexNumber(buffer[i], 0));
			}

			FrequencyGenerator freqGen = new FFTFreqGen(toFFT, 16000);

			freqGen.computeFrequency();

			System.out.format("\nFrequency: %f\n", freqGen.getFrequency());
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		System.out.println("Done: Guitar file\n");
	}

	/**
	 * Tests if the a frequency can be computed from an musical instrument
	 *
	 * <p>This method tests whether or not the class can compute a frequency from
	 * a given file containing the recording of a musical instrument.</p>
	 */
	private static void testInstruments() {
		testInstrumentsFFT();
	}

	/**
	 * Tests the speed of the FFTFreqGen class
	 */
	private static void testSpeedFFT() {
		int binSize = /*2097152;*/ 1048576;
		long startTime;
		long endTime;
		long totalTime;
		double out;

		System.out.println("\nTesting Speed - Array size: " + binSize + "\n");

		// Generate the array
		ComplexNumber toFFTArr[] = new ComplexNumber[binSize];
		Random rand = new Random();
		for (int i = 0; i < binSize; i++) {
			double n = rand.nextDouble() * 2 - 1;
			toFFTArr[i] = new ComplexNumber(n, 0);
		}

		// Test FFTFreqGen
		FFTFreqGen toFreqST = new FFTFreqGen();
		FFTFreqGen toFreqMT = new FFTFreqGen();

		// Set the arrays
		toFreqST.setArray(toFFTArr);
		toFreqMT.setArray(toFFTArr);

		// Test the Single-threaded version
		startTime = System.nanoTime();
		out = toFreqST.computeFrequencyST();
		endTime = System.nanoTime();
		totalTime = (endTime-startTime) / 1000000;

		System.out.println("Single Threaded Performance:");
		System.out.format("Frequency: %.3f Hz\nTime: %d ms\n\n", out, totalTime);

		// Test the Multi-threaded version
		startTime = System.nanoTime();
		out = toFreqMT.computeFrequencyMT();
		endTime = System.nanoTime();
		totalTime = (endTime-startTime) / 1000000;

		System.out.println("Multi Threaded Performance:");
		System.out.format("Frequency: %.3f Hz\nTime: %d ms", out, totalTime);
	}

	/**
	 * Tests the speed of the various frequency computation methods.
	 *
	 * <p>Tests how fast the computation is done with the various frequency computation
	 * methods on an extremely large array that is randomly generated.</p>
	 */
	private static void testSpeed() {
		testSpeedFFT();
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
