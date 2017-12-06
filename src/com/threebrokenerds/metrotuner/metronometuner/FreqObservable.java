package com.threebrokenerds.metrotuner.metronometuner;

/**
 * FreqObservable: An interface that provided the necessary methods to function with the
 * FreqObserver interface. This method allows for updating the observers with the new frequency
 * data that is computed.
 *
 * METHODS:
 * updateObservers():   Sends the new frequency data to all of the observer classes and allows
 *                      them to manipulate the data according to their update method.
 */

public interface FreqObservable {
    public void updateObservers();
}
