package com.hawkejo.complexnumberfft;

/**
 * An observable interface for frequencies.
 *
 * <p>An interface that provided the necessary methods to function with the {@link FreqObserver}
 * interface. This follows the observer pattern This interface allows for updating the observers
 * with the new frequency data that is computed.</p>
 *
 * @author Joseph Hawker
 * @version 1.25
 */

public interface FreqObservable {
    /**
     * Add an observer to update.
     *
     * <p>This method allows for adding an observer to whatever is being used to store
     * observers. As this is an interface, the implementation is class dependent. A List
     * of observers is a good way to implement this method.</p>
     *
     * @param newObs The new observer to add to the object.
     */
    public void addObserver(FreqObserver newObs);

    /**
     * Update all observers that we are aware of.
     *
     * <p>Personally, I think this method speaks for itself, but something needs to be said
     * about the implementation in the child class. It's easiest to do this with a loop iterating
     * through each observer and then calling the update method for each.</p>
     */
    public void updateObservers();
}
