package com.threebrokenerds.metrotuner.metronometuner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * FFTFreqGen: A class that uses the ComplexNumber class to compute frequencies from arrays
 * of data. This class takes in an array or a List<> of ComplexNumbers which is assumed to be
 * audio data and then manipulates it to generate a frequency.
 *
 * CONSTRUCTORS:
 * FFTFreqGen():                Defaults the sampleRate variable to 8000 Hz
 * FFTFreqGen(List):            Allows for specifying the List object and defaults the sampleRate to
 *                              8000 Hz.
 * FFTFreqGen(List, int):       Allows for specifying the List object and the default sampleRate
 *
 * METHODS:
 * setArray(ComplexNumber[]):   Allows for changing the local List with the specified array. The
 *                              method creates the List object and checks for correct sizing
 * setList(List<>):             Allows for changing the local List with the specified List. The
 *                              method check for correct sizing.
 * setSampleRate(int):          Allows for changing the sampleRate variable on the fly
 * computeFrequency():          Computes the frequency from the local array of audio data. This is
 *                              not multithreaded so the overhead for large arrays is very high.
 * computeFrequencyMT():        Computes the frequency from the local array of audio data. This is
 *                              multithreaded somewhat so the performance gain is around 10-20 %
 *                              for large arrays (> 2^19).
 * getFrequency():              Returns the already calculated frequency variable.
 */

public class FFTFreqGen implements FrequencyGenerator {
    private List<ComplexNumber> toFFT;
    private double frequency;
    private int sampleRate;

    FFTFreqGen() {
        toFFT = null;
        sampleRate = 8000;
        frequency = 0.0;
    }

    FFTFreqGen(List<ComplexNumber> newList) {
        toFFT = newList;
        sampleRate = 8000;
        frequency = 0.0;

        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }
    }

    FFTFreqGen(List<ComplexNumber> newList, int newRate) {
        toFFT = newList;
        sampleRate = newRate;
        frequency = 0.0;

        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }
    }

    // Create the list of data to compute the transform on
    public void setArray(ComplexNumber array[]) {
        toFFT = new ArrayList<>(Arrays.asList(array));

        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }
    }
    // Change the list object that we're working with
    public void setList(List<ComplexNumber> audList) {
        toFFT = audList;

        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }
    }

    // Allows for changing the sample rate
    public void setSampleRate(int newRate) {
        sampleRate = newRate;
    }

    public double computeFrequencyMT() throws RuntimeException {
        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }

        // Create the FFT of the array of numbers
        List<ComplexNumber> fromFFT = ComplexNumber.fftMT(toFFT);

        // Finally, compute the frequency from the newly created array. Unfortunately, this has
        // to be a linear search as the location in the list determines what the frequency
        // actually is.
        int size = fromFFT.size();
        int maxBin = -1, argX = 0, argY = size/2 - 1;
        double maxMagnitude = -1.0;

        while(argX < argY) {
            ComplexNumber tempX = fromFFT.get(argX);
            ComplexNumber tempY = fromFFT.get(argY);

            double tempMagnitudeX = tempX.magnitude();
            double tempMagnitudeY = tempY.magnitude();

            if (maxMagnitude < tempMagnitudeX) {
                maxBin = argX;
                maxMagnitude = tempMagnitudeX;
            }
            if (maxMagnitude < tempMagnitudeY) {
                maxBin = argY;
                maxMagnitude = tempMagnitudeY;
            }

            argX++;
            argY--;
        }

        frequency = maxBin * ((double) sampleRate/fromFFT.size());

        return frequency;
    }

    public double computeFrequency() throws RuntimeException {
        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }

        // Create the FFT of the array of numbers
        List<ComplexNumber> fromFFT = ComplexNumber.fft(toFFT);

        // Finally, compute the frequency from the newly created array. Unfortunately, this has
        // to be a linear search as the location in the list determines what the frequency
        // actually is.
        int maxBin = -1, argX = 0, argY = fromFFT.size()/2 - 1;
        double maxMagnitude = -1.0;
        while(argX < argY) {
            ComplexNumber tempX = fromFFT.get(argX);
            ComplexNumber tempY = fromFFT.get(argY);
            double tempMagnitudeX = tempX.magnitude();
            double tempMagnitudeY = tempY.magnitude();
            if (maxMagnitude < tempMagnitudeX) {
                maxBin = argX;
                maxMagnitude = tempMagnitudeX;
            }
            if (maxMagnitude < tempMagnitudeY) {
                maxBin = argY;
                maxMagnitude = tempMagnitudeY;
            }
            argX++;
            argY--;
        }

        frequency = maxBin * ((double) sampleRate/fromFFT.size());

        return frequency;
    }

    // Returns the already computed frequency
    public double getFrequency() {
        return frequency;
    }
}
