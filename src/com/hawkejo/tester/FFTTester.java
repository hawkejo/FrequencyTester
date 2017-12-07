package com.hawkejo.tester;

import com.hawkejo.complexnumberfft.ComplexNumber;
import com.hawkejo.complexnumberfft.FFTFreqGen;
import com.hawkejo.complexnumberfft.FrequencyGenerator;
import phonecs.WavFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Allows for testing the the FFTFreqGen class specifically
 */
class FFTTester {

	/**
	 * Tests the accuracy of the FFTFreqGen class
	 */
	static void testAccuracyFFT() {
		int binSize = 32768 * 4;

		System.out.println("Testing Accuracy of FFTFreqGen.\n");

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
	 * Tests the FFTFreqGen class against files containing instrument audio data
	 */
	static void testInstrumentsFFT() {
		int binSize = 32768 * 4;

		System.out.println("Testing Instruments of FFTFreqGen.\n");

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
	 * Tests the speed of the FFTFreqGen class
	 */
	static void testSpeedFFT(int binSize) {
		long startTime;
		long endTime;
		long totalTime;
		double out;

		System.out.println("Testing Speed of FFTFreqGen.\n");

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
}
