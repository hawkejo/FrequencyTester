package com.hawkejo.complexnumberfft;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Performs the merge of a FFT algorithm in its own thread.
 *
 * <p>A simple class designed to perform the merge portion of the Cooley-Tukey FFT
 * algorithm in its own thread.  The class was created to allow for a multi-threaded
 * design to help improve performance. </p>
 */

class FFTMerge implements Runnable {
    private List<ComplexNumber> evens, odds, out;

    /**
     * Initializes the internal values to null.
     */
    FFTMerge() {
        evens = null;
        odds = null;
        out = null;
    }

    /**
     * Allows for specifying the two internal Lists that are to be merged.
     * @param newEvens The even number items
     * @param newOdds The odd number items
     */
    FFTMerge(List<ComplexNumber> newEvens, List<ComplexNumber> newOdds) {
        evens = newEvens;
        odds = newOdds;
    }

    /**
     * Allows for setting the even item List manually
     *
     * @param newEvens The new even number List
     */
    public void setEvens(List<ComplexNumber> newEvens) {
        evens = newEvens;
    }

    /**
     * Allows for setting the odd item List manually
     *
     * @param newOdds The new odd number List
     */
    public void setOdds(List<ComplexNumber> newOdds) {
        odds = newOdds;
    }

    /**
     * Returns the merged result of the two internal arrays
     *
     * @return The merged result, or null if there is nothing
     */
    List<ComplexNumber> getMerge() {
        return out;
    }

    /**
     * Allows for merging the even item List and the odd item list in the background.
     *
     * <p>Runs the merge operation on the two different Lists. As this is implemented with the
     * runnable interface, it allows for the operation to be run in the background.</p>
     *
     * @throws RuntimeException Thrown if either List is null, or if internal sizes are incorrect
     */
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
