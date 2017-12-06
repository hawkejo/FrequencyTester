package com.threebrokenerds.metrotuner.metronometuner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int binSize = 32768 * 4;

        try
        {
            // Open the wav file specified as the first argument
            WavFile wavFile = WavFile.openWavFile(new File("C:\\Users\\\\hawkejo\\OneDrive\\IdeaProjects\\FrequencyTester\\src\\com\\threebrokenerds\\metrotuner\\metronometuner\\Stuffsr16000.wav"));

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

            FFTFreqGen freqGen = new FFTFreqGen(toFFT, 16000);

            freqGen.computeFrequencyMT();

            System.out.format("Frequency: %f\n", freqGen.getFrequency());
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        System.out.println("Done.");
    }
}
