package com.threebrokenerds.metrotuner.metronometuner;

import java.util.List;

/**
 * FrequencyGenerator: An interface to define any class that computes a frequency value from the
 * specified input array or List.  This interface only defines what the methods should be and
 * what they should do rather than defining them.
 *
 * METHODS:
 * setArray(ComplexNumber[]):   Allows for changing the local List with the specified array.
 * setList(List<>):             Allows for changing the local List with the specified List.
 * computeFrequency():          Computes the frequency from the local array of audio data.
 * getFrequency():              Returns the already calculated frequency variable.
 */

public interface FrequencyGenerator {
    // Methods to set the initial data
    public void setArray(ComplexNumber array[]);
    public void setList(List<ComplexNumber> audList);
    // It wouldn't be a frequency generator if it didn't do that...
    public double computeFrequency();
    public double getFrequency();
}
