package com.hawkejo.complexnumberfft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Defines a complex number and allows for math operations on it
 *
 * <p>A class that defines a complex number and allows for operations to be
 * performed on it.  This class also includes the necessary definitions
 * to perform Fast Fourier Transforms on arrays or Lists of these numbers.</p>
 */

public class ComplexNumber {
    private double real, imaginary;

    /**
     * Initializes the internal variables to 0.0
     */
    public ComplexNumber() {
        real = 0.0;
        imaginary = 0.0;
    }

    /**
     * Allows for specifying the new internal variables
     *
     * @param newR The specified real part
     * @param newI The specified imaginary part
     */
    public ComplexNumber(double newR, double newI) {
        real = newR;
        imaginary = newI;
    }

    /**
     * Allows for setting the real part
     *
     * @param newNum The new number to use for the real part
     */
    public void setReal(double newNum) {
        real = newNum;
    }

    /**
     * Allows for setting the imaginary part
     *
     * @param newNum The new number to use for the imaginary part
     */
    public void setImaginary(double newNum) {
        imaginary = newNum;
    }

    /**
     * Allows for retrieving the real part
     *
     * @return The stored real part
     */
    public double getReal() {
        return real;
    }

    /**
     * Allows for retrieving the imaginary part.
     *
     * @return The stored imaginary part
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Combines the two numbers into a string for displaying in the format Re + jIm
     *
     * @return The output String
     */
    public String toString() {
        if (imaginary == 0)
            return real + "";
        else if (real == 0)
            return "j" + imaginary;
        else if (imaginary < 0)
            return real + " - j" + (-imaginary);
        else
            return real + " + j" + imaginary;
    }

    /**
     * x = (lhs.real + rhs.real) + j(lhs.imaginary + rhs.imaginary)
     *
     * @param lhs Left side argument
     * @param rhs Right side argument
     * @return The output complex number
     */
    public static ComplexNumber add(ComplexNumber lhs, ComplexNumber rhs) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real + rhs.real;
        newNumber.imaginary += lhs.imaginary + rhs.imaginary;

        return newNumber;
    }

    /**
     * x = (lhs.real + rhsReal) + j(lhs.imaginary)
     *
     * @param lhs Left side argument
     * @param rhsReal Real part of right side argument
     * @return The output complex number
     */
    public static ComplexNumber add(ComplexNumber lhs, double rhsReal) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real + rhsReal;
        newNumber.imaginary = lhs.imaginary;

        return newNumber;
    }

    /**
     * x = (lhs.real + rhsReal) + j(lhs.imaginary + rhsImag)
     *
     * @param lhs The left side argument
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber add(ComplexNumber lhs, double rhsReal, double rhsImag) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real + rhsReal;
        newNumber.imaginary = lhs.imaginary + rhsImag;

        return newNumber;
    }

    /**
     * this = (this.real + rhs.real) + j(this.imaginary + rhs.imaginary)
     *
     * @param rhs The argument to add onto the current object
     * @return The current object after the operation
     */
    public ComplexNumber add(ComplexNumber rhs) {
        this.real += rhs.real;
        this.imaginary += rhs.imaginary;

        return this;
    }

    /**
     * this = (this.real + rhsReal) + j(this.imaginary)
     *
     * @param rhsReal The real part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber add(double rhsReal) {
        this.real += rhsReal;

        return this;
    }

    /**
     * this = (this.real + rhsReal) + j(this.imaginary + rhsImag)
     *
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber add(double rhsReal, double rhsImag) {
        this.real += rhsReal;
        this.imaginary += rhsImag;

        return this;
    }

    /**
     * x = (lhs.real - rhs.real) + j(lhs.imaginary - rhs.imaginary)
     *
     * @param lhs The left side argument
     * @param rhs The right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber subtract(ComplexNumber lhs, ComplexNumber rhs) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real - rhs.real;
        newNumber.imaginary = lhs.imaginary - rhs.imaginary;

        return newNumber;
    }

    /**
     * x = (lhs.real - rhsReal) + j(lhs.imaginary)
     *
     * @param lhs The left side argument
     * @param rhsReal The real part of the right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber subtract(ComplexNumber lhs, double rhsReal) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real - rhsReal;
        newNumber.imaginary = lhs.imaginary;

        return newNumber;
    }

    /**
     * x = (lhs.real - rhsReal) + j(lhs.imaginary - rhsImag)
     *
     * @param lhs The left side argument
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber subtract(ComplexNumber lhs, double rhsReal, double rhsImag) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real - rhsReal;
        newNumber.imaginary = lhs.imaginary - rhsImag;

        return newNumber;
    }

    /**
     * this = (this.real - rhs.real) + j(this.imaginary - rhs.imaginary)
     *
     * @param rhs The right side argument
     * @return The current object after the operation
     */
    public ComplexNumber subtract(ComplexNumber rhs) {
        this.real -= rhs.real;
        this.imaginary -= rhs.imaginary;

        return this;
    }

    /**
     * this = (this.real - rhsReal) + j(this.imaginary)
     *
     * @param rhsReal The real part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber subtract(double rhsReal) {
        this.real -= rhsReal;

        return this;
    }

    /**
     * this = (this.real - rhsReal) + j(this.imaginary - rhsImag)
     *
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber subtract(double rhsReal, double rhsImag) {
        this.real -= rhsReal;
        this.imaginary -= rhsImag;

        return this;
    }

    /**
     * x = ((lhs.r * rhs.r) - (lhs.i * rhs.i)) + j((lhs.r * rhs.i) + (lhs.i * rhs.r))
     *
     * @param lhs The left side argument
     * @param rhs The right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber multiply(ComplexNumber lhs, ComplexNumber rhs) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = (lhs.real * rhs.real) - (lhs.imaginary * rhs.imaginary);
        newNumber.imaginary = (lhs.real * rhs.imaginary) + (lhs.imaginary * rhs.real);

        return newNumber;
    }

    /**
     * x = (rhsReal * lhs.real) + j(rhsReal * lhs.imaginary)
     *
     * @param lhs The left side argument
     * @param rhsReal The real part of the right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber multiply(ComplexNumber lhs, double rhsReal) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real * rhsReal;
        newNumber.imaginary = lhs.imaginary * rhsReal;

        return newNumber;
    }

    /**
     * x = ((lhs.r * rhsReal) - (lhs.i * rhsImag)) + j((lhs.r * rhsImag) + (lhs.i * rhsReal))
     *
     * @param lhs The left side argument
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber multiply(ComplexNumber lhs, double rhsReal, double rhsImag) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = (lhs.real * rhsReal) - (lhs.imaginary * rhsImag);
        newNumber.imaginary = (lhs.real * rhsImag) + (lhs.imaginary * rhsReal);

        return newNumber;
    }

    /**
     * this = ((this.r * rhs.r) - (this.i * rhs.i)) + j((this.r * rhs.i) + (this.i * rhs.r))
     *
     * @param rhs The right side argument
     * @return The current object after the operation
     */
    public ComplexNumber multiply(ComplexNumber rhs) {
        double tempReal = this.real, tempImag = this.imaginary;
        this.real = (tempReal * rhs.real) - (tempImag * rhs.imaginary);
        this.imaginary = (tempReal * rhs.imaginary) + (tempImag * rhs.real);

        return this;
    }

    /**
     * this = (rhsReal * this.r) + j(rhsReal * this.i)
     *
     * @param rhsReal The real part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber multiply(double rhsReal) {
        this.real = this.real * rhsReal;
        this.imaginary = this.imaginary * rhsReal;

        return this;
    }

    /**
     * this = ((this.r * rhsReal) - (this.i * rhsImag)) + j((this.r * rhsImag) + (this.i * rhsReal))
     *
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber multiply(double rhsReal, double rhsImag) {
        double tempReal = this.real, tempImag = this.imaginary;
        this.real = (tempReal * rhsReal) - (tempImag * rhsImag);
        this.imaginary = (tempReal * rhsImag) + (tempImag * rhsReal);

        return this;
    }

    /**
     * x = lhs * (1 / rhs)
     *
     * @param lhs The left side argument
     * @param rhs The right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber divide(ComplexNumber lhs, ComplexNumber rhs) {
        double scale = rhs.real*rhs.real + rhs.imaginary*rhs.imaginary;
        ComplexNumber rhsRec = new ComplexNumber(rhs.real / scale, -rhs.imaginary / scale);

        return ComplexNumber.multiply(lhs, rhsRec);
    }

    /**
     * x = lhs * (1 / rhsReal)
     *
     * @param lhs The left side argument
     * @param rhsReal The real part of the right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber divide(ComplexNumber lhs, double rhsReal) {
        double scale = rhsReal*rhsReal;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, 0);

        return ComplexNumber.multiply(lhs, rhsRec);
    }

    /**
     * x = lhs * (1 / (rhsReal + j*rhsImag))
     *
     * @param lhs The left side argument
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The resulting complex number
     */
    public static ComplexNumber divide(ComplexNumber lhs, double rhsReal, double rhsImag) {
        double scale = rhsReal*rhsReal + rhsImag*rhsImag;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, -rhsImag / scale);

        return ComplexNumber.multiply(lhs, rhsRec);
    }

    /**
     * this = this * (1 / rhs)
     *
     * @param rhs The right side argument
     * @return The current object after the operation
     */
    public ComplexNumber divide(ComplexNumber rhs) {
        double scale = rhs.real*rhs.real + rhs.imaginary*rhs.imaginary;
        ComplexNumber rhsRec = new ComplexNumber(rhs.real / scale, -rhs.imaginary / scale);

        return this.multiply(rhsRec);
    }

    /**
     * this = this * (1 / rhsReal)
     *
     * @param rhsReal The real part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber divide(double rhsReal) {
        double scale = rhsReal*rhsReal;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, 0);

        return this.multiply(rhsRec);
    }

    /**
     * this = this * (1 / (rhsReal + j*rhsImag))
     *
     * @param rhsReal The real part of the right side argument
     * @param rhsImag The imaginary part of the right side argument
     * @return The current object after the operation
     */
    public ComplexNumber divide(double rhsReal, double rhsImag) {
        double scale = rhsReal*rhsReal + rhsImag*rhsImag;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, -rhsImag / scale);

        return this.multiply(rhsRec);
    }

    /**
     * x = 1 / arg
     *
     * @param arg The input complex number
     * @return The resulting complex number
     */
    public static ComplexNumber reciprocal(ComplexNumber arg) {
        double scale = arg.real*arg.real + arg.imaginary*arg.imaginary;

        return new ComplexNumber(arg.real / scale, -arg.imaginary / scale);
    }

    /**
     * x = 1 / this
     * @return The current object after the operation
     */
    public ComplexNumber reciprocal() {
        double scale = this.real*this.real + this.imaginary*this.imaginary;

        this.real = this.real / scale;
        this.imaginary = -this.imaginary / scale;

        return this;
    }

    /**
     * arg.real + j*arg.imaginary -> x.real - j*x.imaginary
     *
     * @param arg The number to take the conjugate of
     * @return The resulting complex number
     */
    public static ComplexNumber conjugate(ComplexNumber arg) {
        return new ComplexNumber(arg.real, -arg.imaginary);
    }

    /**
     * this.real + j*this.imaginary -> this.real - j*this.imaginary
     *
     * @return The current object after the operation
     */
    public ComplexNumber conjugate() {
        this.imaginary = -this.imaginary;

        return this;
    }

    /**
     * x = sqrt((arg.real * arg.real) + (arg.imaginary * arg.imaginary))
     *
     * @param arg The number to compute the magnitude of
     * @return The resulting magnitude
     */
    public static double magnitude(ComplexNumber arg) {
        return Math.hypot(arg.real, arg.imaginary);
    }

    /**
     * x = sqrt((this.real * this.real) + (this.imaginary * this.imaginary))
     *
     * @return The resulting magnitude
     */
    public double magnitude() {
        return Math.hypot(this.real, this.imaginary);
    }

    /**
     * x = atan(arg.imaginary/arg.real)
     *
     * @param arg The argument to compute the phase angle of
     * @return The resulting phase angle in radians
     */
    public static double phaseRadians(ComplexNumber arg) {
        return Math.atan2(arg.imaginary, arg.real);
    }

    /**
     * x = atan(this.imaginary/this.real)
     *
     * @return The resulting phase angle in radians
     */
    public double phaseRadians() {
        return Math.atan2(this.imaginary, this.real);
    }

    /**
     * x = atan(arg.imaginary/arg.real)
     *
     * @param arg The argument to compute the phase angle of
     * @return The resulting phase angle in degrees
     */
    public static double phaseDegrees(ComplexNumber arg) {
        return Math.toDegrees(Math.atan2(arg.imaginary, arg.real));
    }

    /**
     * x = atan(this.imaginary/this.real)
     *
     * @return The resulting phase angle in degrees
     */
    public double phaseDegrees() {
        return Math.toDegrees(Math.atan2(this.imaginary, this.real));
    }

    /**
     * x = e^arg
     *
     * @param arg The number to raise to e
     * @return The resulting complex number
     */
    public static ComplexNumber exp(ComplexNumber arg) {
        return new ComplexNumber(Math.exp(arg.real) * Math.cos(arg.imaginary),
                Math.exp(arg.real) * Math.sin(arg.imaginary));
    }

    /**
     * this = e^this
     *
     * @return The current object after the operation
     */
    public ComplexNumber exp() {
        double tempReal = this.real;
        double tempImag = this.imaginary;

        this.real = Math.exp(tempReal) * Math.cos(tempImag);
        this.imaginary = Math.exp(tempReal) * Math.sin(tempImag);

        return this;
    }

    /**
     * x = sin(arg)
     *
     * @param arg The number to perform the operation on
     * @return The resulting complex number
     */
    public static ComplexNumber sin(ComplexNumber arg) {
        return new ComplexNumber(Math.sin(arg.real) * Math.cosh(arg.imaginary),
                Math.cos(arg.real) * Math.sinh(arg.imaginary));
    }

    /**
     * this = sin(this)
     *
     * @return The current object after the operation
     */
    public ComplexNumber sin() {
        double tempReal = this.real, tempImag = this.imaginary;

        this.real = Math.sin(tempReal) * Math.cosh(tempImag);
        this.imaginary = Math.cos(tempReal) * Math.sinh(tempImag);

        return this;
    }

    /**
     * x = cos(arg)
     *
     * @param arg The argument for the operation
     * @return The resulting complex number
     */
    public static ComplexNumber cos(ComplexNumber arg) {
        return new ComplexNumber(Math.cos(arg.real) * Math.cosh(arg.imaginary),
                -(Math.sin(arg.real) * Math.sinh(arg.imaginary)));
    }

    /**
     * this = cos(this)
     *
     * @return The current object after the operation
     */
    public ComplexNumber cos() {
        double tempReal = this.real, tempImag = this.imaginary;

        this.real = Math.cos(tempReal) * Math.cosh(tempImag);
        this.imaginary = -(Math.sin(tempReal) * Math.sinh(tempImag));

        return this;
    }

    /**
     * x = tan(arg) = sin(arg)/cos(arg)
     *
     * @param arg The argument for the operation
     * @return The resulting complex number
     */
    public static ComplexNumber tan(ComplexNumber arg) {
        return sin(arg).divide(cos(arg));
    }

    /**
     * this = tan(this) = sin(this)/cos(this)
     *
     * @return The current object after the operation
     */
    public ComplexNumber tan() {
        ComplexNumber tempSin = sin(this);
        ComplexNumber tempCos = cos(this);

        ComplexNumber out = divide(tempSin, tempCos);

        this.real = out.real;
        this.imaginary = out.imaginary;

        return this;
    }


    /**
     * x = this == obj
     *
     * @param obj The argument object to compare against
     * @return Whether or not the objects are equal to each other
     */
    public boolean equals(Object obj) {
        double delta = 0.00000000001;

        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        else {
            ComplexNumber that = (ComplexNumber) obj;
            return (Math.abs(this.real - that.real) < delta) &&
                    (Math.abs(this.imaginary - that.imaginary) < delta);
        }
    }

    /**
     * x = this != obj
     *
     * @param obj The argument object to compare against
     * @return Whether or not the objects are not equal to each other
     */
    public boolean notEqual(Object obj) {
        if (obj == null)
            return true;
        if (this.getClass() != obj.getClass())
            return true;
            // DeMorgan's theorem of the equals function
        else {
            ComplexNumber that = (ComplexNumber) obj;
            return !this.equals(that);
        }
    }

    /**
     * Compute an FFT on an array of ComplexNumbers
     *
     * <p>This method computes the Fourier series of an input array by performing the
     * Cooley-Tukey FFT algorithm. This specific method is single threaded. The array
     * size must be a power of 2.</p>
     *
     * @param arg The input array of ComplexNumbers
     * @return The Fourier series of the input array
     * @throws RuntimeException Thrown when the size of the array isn't a power of 2
     */
    public static ComplexNumber[] fft(ComplexNumber[] arg) throws RuntimeException{
        int size = arg.length;

        // Base case
        if (size == 1) {
            return new ComplexNumber[] {arg[0]};
        }

        // Check if the array size is a power of 2
        if ((size & (size - 1)) != 0) {
            throw new RuntimeException("ComplexNumber.fft: ERROR - The array must be a power of 2\n");
        }

        // Separate the array into separate arrays of even and odd elements
        ComplexNumber[] evens = new ComplexNumber[size/2];
        ComplexNumber[] odds = new ComplexNumber[size/2];
        for (int i = 0; i < size/2; i++) {
            evens[i] = arg[2*i];
            odds[i] = arg[2*i + 1];
        }

        // Perform an FFT on the even number variable
        ComplexNumber[] evensFFT = fft(evens);
        // Perform an FFT on the odd number variable
        ComplexNumber[] oddsFFT = fft(odds);

        // Combine the arrays containing the FFT data
        ComplexNumber[] out = new ComplexNumber[size];
        for (int k = 0; k < size/2; k++) {
            double kth = -2 * k * Math.PI / size;
            ComplexNumber wk = new ComplexNumber(Math.cos(kth), Math.sin(kth));
            out[k]       = add(evensFFT[k], multiply(wk, oddsFFT[k]));
            out[k + size/2] = subtract(evensFFT[k], multiply(wk, oddsFFT[k]));
        }

        return out;
    }

    /**
     * Compute the Fourier series of a List object
     *
     * <p>This method computes the Fourier series of an input List by performing the
     * Cooley-Tukey FFT algorithm. This specific method is single threaded. This method
     * also uses the ArrayList class for the internal Lists. The array size must also be
     * a power of 2.</p>
     *
     * @param arg The input List of ComplexNumbers
     * @return The Fourier series List of the input
     * @throws RuntimeException Thrown when the List size isn't a power of 2
     */
    public static List<ComplexNumber> fft(List<ComplexNumber> arg) throws RuntimeException {
        int size = arg.size();
        if (size == 1) {
            return arg;
        }

        if ((size & (size - 1)) != 0) {
            throw new RuntimeException("ComplexNumber.fft: ERROR - The List<> size must be a power of 2\n");
        }

        // Separate the list into chunks based on either the even or odd address
        List<ComplexNumber> evens = new ArrayList<ComplexNumber>(size/2);
        List<ComplexNumber> odds = new ArrayList<ComplexNumber>(size/2);

        ListIterator<ComplexNumber> it = arg.listIterator();
        while (it.hasNext()) {
            evens.add(it.next());
            odds.add(it.next());
        }
        it = null;

        // Compute the FFT of the separate lists
        List<ComplexNumber> evensFFT = fft(evens);
        List<ComplexNumber> oddsFFT = fft(odds);

        // Combine the lists into the final list
        List<ComplexNumber> out = new ArrayList<ComplexNumber>(size);
        ListIterator<ComplexNumber> itEven = evensFFT.listIterator();
        ListIterator<ComplexNumber> itOdd = oddsFFT.listIterator();
        int i = 0, k = 0;

        while(itEven.hasNext() && itOdd.hasNext()) {
            double kth = -2 * k * Math.PI / size;
            ComplexNumber wk = new ComplexNumber(Math.cos(kth), Math.sin(kth));
            ComplexNumber evenArg = itEven.next();
            ComplexNumber oddArg = itOdd.next();

            out.add(i, add(evenArg, multiply(wk, oddArg)));
            i++;
            out.add(subtract(evenArg, multiply(wk, oddArg)));
            k++;
        }

        return out;
    }

    /**
     * Compute the Fourier series of a List
     *
     * <p>This method computes the Fourier series of an input List by performing the
     * Cooley-Tukey FFT algorithm. This specific method is multi-threaded for extremely
     * large arrays. This method also uses the ArrayList class for the internal Lists.
     * The array size must also be a power of 2.</p>
     *
     * @param toFFT The input List of ComplexNumbers
     * @return The Fourier series of the input List
     * @throws RuntimeException Thrown when the input List size isn't a power of 2
     */
    public static List<ComplexNumber> fftMT(List<ComplexNumber> toFFT) throws RuntimeException {
        int size = toFFT.size();
        if ((size & (size - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }

        // Start by performing the first separation of the fft components
        FFTSeparator a = new FFTSeparator(toFFT);
        a.run();

        // Perform the second separation of the fft components
        FFTSeparator b0 = new FFTSeparator(a.getEvens()), b1 = new FFTSeparator(a.getOdds());

        Thread tb0 = new Thread(b0), tb1 = new Thread(b1);

        tb0.start();
        tb1.start();

        // Perform the third separation of the fft components
        try {
            tb0.join();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(120);
        }

        FFTSeparator c0 = new FFTSeparator(b0.getEvens()), c1 = new FFTSeparator(b0.getOdds());
        Thread tc0 = new Thread(c0), tc1 = new Thread(c1);
        tc0.start();
        tc1.start();

        try {
            tb1.join();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(121);
        }

        FFTSeparator c2 = new FFTSeparator(b1.getEvens()), c3 = new FFTSeparator(b1.getOdds());
        Thread tc2 = new Thread(c2), tc3 = new Thread(c3);
        tc2.start();
        tc3.start();

        // Perform a fourth separation of the fft components. This will give us 16 items
        // to compute in their own threads.
        try {
            tc0.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(130);
        }

        FFTSeparator d0 = new FFTSeparator(c0.getEvens()), d1 = new FFTSeparator(c0.getOdds());
        Thread td0 = new Thread(d0), td1 = new Thread(d1);
        td0.start();
        td1.start();

        try {
            tc1.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(131);
        }

        FFTSeparator d2 = new FFTSeparator(c1.getEvens()), d3 = new FFTSeparator(c1.getOdds());
        Thread td2 = new Thread(d2), td3 = new Thread(d3);
        td2.start();
        td3.start();

        try {
            tc2.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(132);
        }

        FFTSeparator d4 = new FFTSeparator(c2.getEvens()), d5 = new FFTSeparator(c2.getOdds());
        Thread td4 = new Thread(d4), td5 = new Thread(d5);
        td4.start();
        td5.start();

        try {
            tc3.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(133);
        }

        FFTSeparator d6 = new FFTSeparator(c3.getEvens()), d7 = new FFTSeparator(c3.getOdds());
        Thread td6 = new Thread(d6), td7 = new Thread(d7);
        td6.start();
        td7.start();

        // Now perform FFTs on the separated components created by the separator
        try {
            td0.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(140);
        }

        FFTThread e0 = new FFTThread(d0.getEvens()), e1 = new FFTThread(d0.getOdds());
        Thread te0 = new Thread(e0), te1 = new Thread(e1);
        te0.start();
        te1.start();

        try {
            td1.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(141);
        }

        FFTThread e2 = new FFTThread(d1.getEvens()), e3 = new FFTThread(d1.getOdds());
        Thread te2 = new Thread(e2), te3 = new Thread(e3);
        te2.start();
        te3.start();

        try {
            td2.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(142);
        }

        FFTThread e4 = new FFTThread(d2.getEvens()), e5 = new FFTThread(d2.getOdds());
        Thread te4 = new Thread(e4), te5 = new Thread(e5);
        te4.start();
        te5.start();

        try {
            td3.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(143);
        }

        FFTThread e6 = new FFTThread(d3.getEvens()), e7 = new FFTThread(d3.getOdds());
        Thread te6 = new Thread(e6), te7 = new Thread(e7);
        te6.start();
        te7.start();

        try {
            td4.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(144);
        }

        FFTThread e8 = new FFTThread(d4.getEvens()), e9 = new FFTThread(d4.getOdds());
        Thread te8 = new Thread(e8), te9 = new Thread(e9);
        te8.start();
        te9.start();

        try {
            td5.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(145);
        }

        FFTThread e10 = new FFTThread(d5.getEvens()), e11 = new FFTThread(d5.getOdds());
        Thread te10 = new Thread(e10), te11 = new Thread(e11);
        te10.start();
        te11.start();

        try {
            td6.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(146);
        }

        FFTThread e12 = new FFTThread(d6.getEvens()), e13 = new FFTThread(d6.getOdds());
        Thread te12 = new Thread(e12), te13 = new Thread(e13);
        te12.start();
        te13.start();

        try {
            td7.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(147);
        }

        FFTThread e14 = new FFTThread(d7.getEvens()), e15 = new FFTThread(d7.getOdds());
        Thread te14 = new Thread(e14), te15 = new Thread(e15);
        te14.start();
        te15.start();

        // Now start merging everything back into the final FFT array
        // Do the first merge operations
        try {
            te0.join();
            te1.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(200);
        }
        FFTMerge f0 = new FFTMerge(e0.getFFT(), e1.getFFT());
        Thread tf0 = new Thread(f0);
        tf0.start();

        try {
            te2.join();
            te3.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(202);
        }
        FFTMerge f1 = new FFTMerge(e2.getFFT(), e3.getFFT());
        Thread tf1 = new Thread(f1);
        tf1.start();

        try {
            te4.join();
            te5.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(204);
        }
        FFTMerge f2 = new FFTMerge(e4.getFFT(), e5.getFFT());
        Thread tf2 = new Thread(f2);
        tf2.start();

        try {
            te6.join();
            te7.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(206);
        }
        FFTMerge f3 = new FFTMerge(e6.getFFT(), e7.getFFT());
        Thread tf3 = new Thread(f3);
        tf3.start();

        try {
            te8.join();
            te9.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(208);
        }
        FFTMerge f4 = new FFTMerge(e8.getFFT(), e9.getFFT());
        Thread tf4 = new Thread(f4);
        tf4.start();

        try {
            te10.join();
            te11.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(210);
        }
        FFTMerge f5 = new FFTMerge(e10.getFFT(), e11.getFFT());
        Thread tf5 = new Thread(f5);
        tf5.start();

        try {
            te12.join();
            te13.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(212);
        }
        FFTMerge f6 = new FFTMerge(e12.getFFT(), e13.getFFT());
        Thread tf6 = new Thread(f6);
        tf6.start();

        try {
            te14.join();
            te15.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(214);
        }
        FFTMerge f7 = new FFTMerge(e14.getFFT(), e15.getFFT());
        Thread tf7 = new Thread(f7);
        tf7.start();

        // Do the second stage merge operation
        try {
            tf0.join();
            tf1.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(300);
        }
        FFTMerge g0 = new FFTMerge(f0.getMerge(), f1.getMerge());
        Thread tg0 = new Thread(g0);
        tg0.start();

        try {
            tf2.join();
            tf3.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(302);
        }
        FFTMerge g1 = new FFTMerge(f2.getMerge(), f3.getMerge());
        Thread tg1 = new Thread(g1);
        tg1.start();

        try {
            tf4.join();
            tf5.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(304);
        }
        FFTMerge g2 = new FFTMerge(f4.getMerge(), f5.getMerge());
        Thread tg2 = new Thread(g2);
        tg2.start();

        try {
            tf6.join();
            tf7.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(306);
        }
        FFTMerge g3 = new FFTMerge(f6.getMerge(), f7.getMerge());
        Thread tg3 = new Thread(g3);
        tg3.start();

        // Perform the third merge operations
        try {
            tg0.join();
            tg1.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(310);
        }
        FFTMerge h0 = new FFTMerge(g0.getMerge(), g1.getMerge());
        Thread th0 = new Thread(h0);
        th0.start();

        try {
            tg2.join();
            tg3.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(312);
        }
        FFTMerge h1 = new FFTMerge(g2.getMerge(), g3.getMerge());
        Thread th1 = new Thread(h1);
        th1.start();

        // Finally, do the final merge and return
        try {
            th0.join();
            th1.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(320);
        }
        FFTMerge out = new FFTMerge(h0.getMerge(), h1.getMerge());
        out.run();

        return out.getMerge();
    }

    /**
     * Compute the inverse Fourier series of an array
     *
     * <p>Compute the inverse Fourier series of an input array using the Cooley-Tukey
     * FFT algorithm. This method is single threaded. The input array must have the size
     * of a power of 2</p>
     *
     * @param arg The input array of ComplexNumbers
     * @return The inverse FFT of the input array
     * @throws RuntimeException Thrown when the input size isn't a power of 2
     */
    public static ComplexNumber[] ifft(ComplexNumber[] arg) throws RuntimeException {
        int size = arg.length;

        // Check if the array size is a power of 2
        if ((size & (size - 1)) != 0) {
            throw new RuntimeException("ComplexNumber.ifft: ERROR - The array must be a power of 2\n");
        }

        ComplexNumber[] out = new ComplexNumber[size];

        // Take conjugate of each element
        for (int i = 0; i < size; i++) {
            out[i] = conjugate(arg[i]);
        }

        // Compute FFT of conjugate array
        out = fft(out);

        // Take another conjugate
        for (int i = 0; i < size; i++) {
            out[i] = conjugate(out[i]);
        }

        // Divide the array elements by the array size
        for (int i = 0; i < size; i++) {
            out[i] = multiply(out[i], 1.0 / size);
        }

        return out;
    }

    /**
     * Compute the inverse Fourier series of a List
     *
     * <p>Compute the inverse Fourier series of an input list using the Cooley-Tukey
     * FFT algorithm. This method is single threaded. The input list must have the size
     * of a power of 2</p>
     *
     * @param arg The input List of ComplexNumbers
     * @return The inverse Fourier series of the numbers
     * @throws RuntimeException Thrown when the size of the input List isn't a power of 2
     */
    public static List<ComplexNumber> ifft(List<ComplexNumber> arg) throws RuntimeException {
        int size = arg.size();

        if ((size & (size - 1)) != 0) {
            throw new RuntimeException("ComplexNumber.ifft: ERROR - The List<> size must be a power of 2\n");
        }

        List<ComplexNumber> a = new LinkedList<ComplexNumber>();
        List<ComplexNumber> b = new LinkedList<ComplexNumber>();
        List<ComplexNumber> out = new LinkedList<ComplexNumber>();

        // Take conjugate of List elements
        ListIterator<ComplexNumber> it = arg.listIterator();

        while (it.hasNext()) {
            a.add(conjugate(it.next()));
        }

        it = null;

        // Compute FFT of List
        a = fft(a);

        // Take conjugate after FFT
        it = a.listIterator();

        while (it.hasNext()) {
            b.add(conjugate(it.next()));
        }

        it = null;

        // Divide the elements by the size of the List
        it = b.listIterator();

        while(it.hasNext()) {
            out.add(multiply(it.next(), 1.0 / size));
        }

        return out;
    }

    /**
     * Compute the inverse Fourier series of a List
     *
     * <p>Compute the inverse Fourier series of an input list using the Cooley-Tukey
     * FFT algorithm. This method is multi-threaded. The input list must have the size
     * of a power of 2</p>
     *
     * @param arg The input List of ComplexNumbers
     * @return The inverse Fourier series of the input
     * @throws RuntimeException Thrown when the input List size isn't a power of 2
     */
    public static List<ComplexNumber> ifftMT(List<ComplexNumber> arg) throws RuntimeException {
        int size = arg.size();

        if ((size & (size - 1)) != 0) {
            throw new RuntimeException("ComplexNumber.ifft: ERROR - The List<> size must be a power of 2\n");
        }

        List<ComplexNumber> a = new LinkedList<ComplexNumber>();
        List<ComplexNumber> b = new LinkedList<ComplexNumber>();
        List<ComplexNumber> out = new LinkedList<ComplexNumber>();

        // Take conjugate of List elements
        ListIterator<ComplexNumber> it = arg.listIterator();

        while (it.hasNext()) {
            a.add(conjugate(it.next()));
        }

        it = null;

        // Compute FFT of List
        a = fftMT(a);

        // Take conjugate after FFT
        it = a.listIterator();

        while (it.hasNext()) {
            b.add(conjugate(it.next()));
        }

        it = null;

        // Divide the elements by the size of the List
        it = b.listIterator();

        while(it.hasNext()) {
            out.add(multiply(it.next(), 1.0 / size));
        }

        return out;
    }
}
