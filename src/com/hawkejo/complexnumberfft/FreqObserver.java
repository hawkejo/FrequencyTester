package com.hawkejo.complexnumberfft;

/**
 * An observer interface to check for new frequency data.
 *
 * <p>An observer interface that allows for checking for new data from an object that
 * implements the {@link FreqObservable} interface.</p>
 *
 * @author Joseph Hawker
 * @version 1.2
 */

public interface FreqObserver {
    /**
     * Updates the member data with the new frequency.
     *
     * <p>Allows for updating the member implemented data with the new frequency data that is
     * passed as a parameter. Implementation is unique to each class and t is used with the
     * updateObservers() method of {@link FreqObservable}.</p>
     *
     * @param newFreq The new frequency to be used by the child class.
     */
    public void update(double newFreq);
}
