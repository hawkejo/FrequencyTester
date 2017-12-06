package com.threebrokenerds.metrotuner.metronometuner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * FFTMerge: A simple class designed to perform the merge portion of the Cooley-Tukey
 * FFT algorithm in its own thread.  The class was created to allow for a multi-threaded
 * design to help improve performance.
 */

public class FFTMerge implements Runnable {
    private List<ComplexNumber> evens, odds, out;

    FFTMerge() {
        evens = null;
        odds = null;
        out = null;
    }

    FFTMerge(List<ComplexNumber> newEvens, List<ComplexNumber> newOdds) {
        evens = newEvens;
        odds = newOdds;
    }

    public void setEvens(List<ComplexNumber> newEvens) {
        evens = newEvens;
    }

    public void setOdds(List<ComplexNumber> newOdds) {
        odds = newOdds;
    }

    public List<ComplexNumber> getMerge() {
        return out;
    }

    public void run() throws RuntimeException {
        if (evens == null)
            throw new RuntimeException("FFTThread.java: ERROR - No even number input list specified");
        if (odds == null)
            throw new RuntimeException("FFTThread.java: ERROR - No odd number input list specified");
        if ((evens.size() & (evens.size() - 1)) != 0)
            throw new RuntimeException("FFTThread.java: ERROR - The even number List must be a power of 2\n");
        if ((odds.size() & (odds.size() - 1)) != 0)
            throw new RuntimeException("FFTThread.java: ERROR - The even number List must be a power of 2\n");

        out = new ArrayList<>(evens.size() + odds.size());
        ListIterator<ComplexNumber> itEven = evens.listIterator();
        ListIterator<ComplexNumber> itOdd = odds.listIterator();
        int i = 0, k = 0, size = evens.size() + odds.size();

        while(itEven.hasNext() && itOdd.hasNext()) {
            double kth = -2 * k * Math.PI / size;
            ComplexNumber wk = new ComplexNumber(Math.cos(kth), Math.sin(kth));
            ComplexNumber evenArg = itEven.next();
            ComplexNumber oddArg = itOdd.next();

            out.add(i, ComplexNumber.add(evenArg, ComplexNumber.multiply(wk, oddArg)));
            i++;
            out.add(ComplexNumber.subtract(evenArg, ComplexNumber.multiply(wk, oddArg)));
            k++;
        }
    }
}
