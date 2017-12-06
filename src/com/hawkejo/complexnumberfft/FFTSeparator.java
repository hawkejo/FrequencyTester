package com.hawkejo.complexnumberfft;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Performs the initial FFT separation in the background
 *
 * <p>A self-contained class which performs the initial separation phase for
 * the Cooley-Tukey FFT algorithm. The only reason this class exists is so
 * the FFT method can be multithreaded to improve performance.</p>
 */

class FFTSeparator implements Runnable{
    private List<ComplexNumber> toSeparate, evens, odds;

    /**
     * Initializes everything to null
     */
    FFTSeparator() {
        toSeparate = null;
        evens = null;
        odds = null;
    }

    /**
     * Sets the internal List and initializes the output Lists.
     *
     * @param newList The List to split into even and odd elements
     */
    FFTSeparator(List<ComplexNumber> newList) {
        toSeparate = newList;
        evens = new ArrayList<>(newList.size()/2);
        odds = new ArrayList<>(newList.size()/2);
    }

    /**
     * Sets the internal List to split and initializes the output Lists.
     *
     * @param newVar The new List object to use in the split operation
     */
    public void setList(List<ComplexNumber> newVar) {
        toSeparate = newVar;
        evens = new ArrayList<>(newVar.size() / 2);
        odds = new ArrayList<>(newVar.size() / 2);
    }

    /**
     * Allows for retrieving the even number elements of the input List
     *
     * @return The List of even number elements of the input List
     */
    public List<ComplexNumber> getEvens() {
        return evens;
    }

    /**
     * Allows for retrieving the odd number elements of the input list
     *
     * @return The List of odd number elements of the input List
     */
    public List<ComplexNumber> getOdds() {
        return odds;
    }

    /**
     * Performs the separation operation of the internal List
     *
     * <p>Performs the separation operation of the internal List according to the
     * Cooley-Tukey algorithm. The separate method is implemented using run() as to
     * be compatible with the {@link Runnable} interface.</p>
     *
     * @throws RuntimeException Thrown if the internal List is null.
     */
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
