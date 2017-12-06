package com.hawkejo.complexnumberfft;

import java.util.List;

/**
 * An interface for an arbitrary class that computes a frequency from an array of doubles
 *
 * <p>An interface to define any class that computes a frequency value from the specified
 * input array or List.  This interface only defines what the methods should be and what
 * they should do rather than defining them. This class requires the {@link ComplexNumber}
 * class as a dependency.</p>
 *
 *  @author Joseph Hawker
 *  @version 1.2
 */

public interface FrequencyGenerator extends Runnable, FreqObservable {
    /**
     * Allows for changing the local List with the specified array.
     *
     * @param array The new array of ComplexNumbers
     */
    public void setArray(ComplexNumber array[]);

    /**
     * Allows for changing the local List with the specified List.
     *
     * @param audList The new List of ComplexNumbers
     */
    public void setList(List<ComplexNumber> audList);

    /**
     * Computes the frequency from the local array of audio data.
     *
     * @return The computed frequency value based on the internal array.
     */
    public double computeFrequency();

    /**
     * Returns the already calculated frequency variable.
     *
     * @return The already computed frequency value.
     */
    public double getFrequency();

    /**
     * When used with a {@link Thread}, allows for doing the frequency computation completely
     * in the background, storing the result in a local variable.
     */
    public void run();
}
