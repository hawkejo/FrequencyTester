package com.threebrokenerds.metrotuner.metronometuner;

/**
 * FreqObserver: An observer interface for functions that rely on frequency data that is
 * calculated elsewhere.
 *
 * METHODS:
 * update(double):  Takes the new frequency data and allows for the child class to manipulate
 *                  the new data.
 */

public interface FreqObserver {
    public void update(double newFreq);
}
