package com.hawkejo.complexnumberfft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Computes frequencies from {@link ComplexNumber} objects
 *
 * <p>A class that uses the ComplexNumber class to compute frequencies from arrays of
 * data. This class takes in an array or a List<> of ComplexNumbers which is assumed to be
 * audio data and then manipulates it to generate a frequency.</p>
 *
 */

public class FFTFreqGen implements Runnable, FreqObservable, FrequencyGenerator {
    private List<ComplexNumber> toFFT;
    private double frequency;
    private int sampleRate;
    private final String CLASS_TAG = "FFTFreqGen";
    private List<FreqObserver> observers;

    /**
     * Defaults the sampleRate variable to 8000 Hz
     */
    FFTFreqGen() {
        toFFT = null;
        sampleRate = 8000;
        frequency = 0.0;
        observers = new LinkedList<>();
    }

    /**
     * Allows for specifying the internal List object.
     *
     * <p>This value allows for specifying the internal List for computing the FFT and
     * defaults the other parameters to the specified values. The sampleRate variable
     * defaults to 8000 Hz. An exception is thrown if the List size is not a power of 2.</p>
     *
     * @param newList The new list to be specified
     */
    FFTFreqGen(List<ComplexNumber> newList) {
        toFFT = newList;
        sampleRate = 8000;
        frequency = 0.0;
        observers = new LinkedList<>();

        if (toFFT != null && (toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - The List<> size must be a power of 2\n");
        }
    }

    /**
     * Allows for specifying the sample rate.
     *
     * <p>Allows for specifying the internal sample rate variable. The internal List is
     * initialized to null, and everything else is initialized to the default values.</p>
     *
     * @param newRate The specified sample rate.
     */
    FFTFreqGen(int newRate) {
        toFFT = null;
        sampleRate = newRate;
        frequency = 0.0;
        observers = new LinkedList<>();
    }

    /**
     * Allows for specifying the internal List and the sample rate.
     *
     * <p>Allows for specifying the internal sample rate variable as well as the List
     * object to have the frequency computed from. An exception is thrown if the List
     * size is not a power of 2.</p>
     *
     * @param newList The new List to compute the frequency from.
     * @param newRate The specified sample rate.
     */
    FFTFreqGen(List<ComplexNumber> newList, int newRate) {
        toFFT = newList;
        sampleRate = newRate;
        frequency = 0.0;
        observers = new LinkedList<>();

        if (toFFT != null && (toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - The List<> size must be a power of 2\n");
        }
    }

    @Override
    public void setArray(ComplexNumber array[]) {
        toFFT = new ArrayList<>(Arrays.asList(array));

        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - The List<> size must be a power of 2\n");
        }
    }

    @Override
    public void setList(List<ComplexNumber> audList) {
        toFFT = audList;

        if (toFFT != null && (toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - The List<> size must be a power of 2\n");
        }
    }

    /**
     * Allows for changing the internal sampleRate variable
     *
     * @param newRate The new sample rate to use in computations.
     */
    public void setSampleRate(int newRate) {
        sampleRate = newRate;
    }

    @Override
    public double computeFrequency() throws RuntimeException {
        if (toFFT == null) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - toFFT array is null");
        }

        if (toFFT.size() >= 524288) {
            return computeFrequencyMT();
        }
        else
            return computeFrequencyST();
    }

    /**
     * A multi threaded method for computing a frequency from an FFT
     *
     * <p>This method computes the FFT of the specified array and then uses that
     * FFT to find and compute the frequency of the array. This method is single threaded
     * and works best on arrays that are larger than 2^19.</p>
     * @return The computed frequency
     * @throws RuntimeException Thrown when the array is null, or not sized as a power of 2.
     */
    double computeFrequencyMT() throws RuntimeException {
        if (toFFT == null) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - toFFT array is null");
        }

        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - The List<> size must be a power of 2\n");
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

    /**
     * A single threaded method for computing a frequency from an FFT
     *
     * <p>This method computes the FFT of the specified array and then uses that
     * FFT to find and compute the frequency of the array. This method is single threaded
     * and works best on arrays that are smaller than 2^19.</p>
     * @return The computed frequency
     * @throws RuntimeException Thrown when the array is null, or not sized as a power of 2.
     */
    double computeFrequencyST() throws RuntimeException {
        if (toFFT == null) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - toFFT array is null");
        }

        // We should never go here as we already do this check when setting the array.
        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException(CLASS_TAG + ": ERROR - The List<> size must be a power of 2\n");
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

    @Override
    public void run() {
        this.computeFrequency();
    }

    @Override
    public void addObserver(FreqObserver newObs) {
        if (newObs != null)
            observers.add(newObs);
    }

    @Override
    public void updateObservers() {
        ListIterator<FreqObserver> it = observers.listIterator();

        while(it.hasNext()) {
            it.next().update(frequency);
        }
    }

    @Override
    public double getFrequency() {
        return frequency;
    }
}
