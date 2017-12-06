package com.hawkejo.complexnumberfft;

import java.util.List;

/**
 * Performs an FFT in its own thread.
 *
 * <p>A simple class designed to run the FFT computation in its own thread. It
 * contains member variables designed to store the results of the computation.
 * The Cooley-Tukey algorithm is what's used here.</p>
 */

class FFTThread implements Runnable {
    private List<ComplexNumber> toFFT;
    private List<ComplexNumber> fromFFT;

    /**
     * Initializes internal Lists to null.
     */
    FFTThread() {
        toFFT = null;
        fromFFT = null;
    }

    /**
     * Allows for specifying the input List.
     *
     * @param newList The input List to compute the FFT on
     */
    FFTThread(List<ComplexNumber> newList) {
        toFFT = newList;
        fromFFT = null;
    }

    /**
     * Allows for setting the internal List after computing the FFT.
     *
     * @param newList The new List to compute the FFT on
     */
    public void setList(List<ComplexNumber> newList) {
        toFFT = newList;
    }

    /**
     * Returns the computed FFT in a List object.
     *
     * @return The computed FFT
     */
    public List<ComplexNumber> getFFT() {
        return fromFFT;
    }

    /**
     * Computes a FFT in the background.
     *
     * <p>Computes the FFT of the internal array in the background. The Cooley-Tukey
     * algorithm is used for the computation here as it is easiest to split and use.</p>
     *
     * @throws RuntimeException Thrown if the internal List isn't valid
     */
    public void run() throws RuntimeException {
        if (toFFT == null)
            throw new RuntimeException("FFTThread.java: ERROR - No input list specified");
        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTThread.java: ERROR - The List must be a power of 2\n");
        }

        fromFFT = ComplexNumber.fft(toFFT);
    }
}
