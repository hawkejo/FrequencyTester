package com.threebrokenerds.metrotuner.metronometuner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * FFTSeparator: A self-contained class which performs the initial separation
 * phase for the Cooley-Tukey FFT algorithm. The only reason this class exists
 * is so the FFT method can be multithreaded to improve performance.
 */

public class FFTSeparator implements Runnable{
    private List<ComplexNumber> toSeparate, evens, odds;

    FFTSeparator() {
        toSeparate = null;
        evens = null;
        odds = null;
    }

    FFTSeparator(List<ComplexNumber> newList) {
        toSeparate = newList;
        evens = new ArrayList<>(newList.size()/2);
        odds = new ArrayList<>(newList.size()/2);
    }

    public void setVariable(List<ComplexNumber> newVar) {
        toSeparate = newVar;
    }

    public List<ComplexNumber> getEvens() {
        return evens;
    }

    public List<ComplexNumber> getOdds() {
        return odds;
    }

    public void separate() throws RuntimeException {
        if (toSeparate == null)
            throw new RuntimeException("FFTSeparator.java: toSeparate cannot be null.");

        ListIterator<ComplexNumber> it = toSeparate.listIterator();
        while(it.hasNext()) {
            evens.add(it.next());
            odds.add(it.next());
        }
    }

    public void run() throws RuntimeException {
        if (toSeparate == null)
            throw new RuntimeException("FFTSeparator.java: toSeparate cannot be null.");

        ListIterator<ComplexNumber> it = toSeparate.listIterator();
        while(it.hasNext()) {
            evens.add(it.next());
            odds.add(it.next());
        }
    }
}
