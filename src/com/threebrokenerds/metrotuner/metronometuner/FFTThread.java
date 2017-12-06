package com.threebrokenerds.metrotuner.metronometuner;

import java.util.List;

/**
 * FFTThread: A simple class designed to run the FFT computation in its own thread.
 * It contains member variables designed to store the results of the computation.
 */

public class FFTThread implements Runnable {
    private List<ComplexNumber> toFFT;
    private List<ComplexNumber> fromFFT;

    FFTThread() {
        toFFT = null;
        fromFFT = null;
    }

    FFTThread(List<ComplexNumber> newList) {
        toFFT = newList;
        fromFFT = null;
    }

    public void setList(List<ComplexNumber> newList) {
        toFFT = newList;
    }

    public List<ComplexNumber> getFFT() {
        return fromFFT;
    }

    public void run() throws RuntimeException {
        if (toFFT == null)
            throw new RuntimeException("FFTThread.java: ERROR - No input list specified");
        if ((toFFT.size() & (toFFT.size() - 1)) != 0) {
            throw new RuntimeException("FFTThread.java: ERROR - The List must be a power of 2\n");
        }

        fromFFT = ComplexNumber.fft(toFFT);
    }
}
