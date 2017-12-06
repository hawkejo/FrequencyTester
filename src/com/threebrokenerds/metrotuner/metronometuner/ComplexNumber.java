package com.threebrokenerds.metrotuner.metronometuner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Class: ComplexNumber
 * A class that defines a complex number and allows for operations to be
 * performed on it.  This class also includes the necessary definitions
 * to perform Fast Fourier Transforms on arrays or Lists of these numbers.
 *
 * CONSTRUCTORS:
 * ComplexNumber(): Creates the object initializing the member variables to zero
 * ComplexNumber(double newR, double newI): Creates the object allowing for member variables
 *                  to be initialized as specified
 *
 * METHODS:
 * setReal(double newReal): Allows for setting this.real
 * setImaginary(double newImag): Allows for setting this.imaginary
 * getReal(): Returns this.real
 * getImaginary(): Returns this.imaginary
 * toString(): Returns a string containing the values of both numbers in the format real + jImag
 *
 * The following will be described as math functions with this being the current object, x being lhs,
 * y being rhs, out being the output for a static function, and j being equal to sqrt(-1).  The
 * following notation is also defined as follows:
 *
 * +  : Addition
 * -  : Subtraction
 * *  : Multiplication
 * /  : Division
 * ** : Exponentiation
 * =  : Assignment
 *
 * Basic Arithmetic Functions
 * static *(ComplexNumber lhs, ComplexNumber rhs)
 * static *(ComplexNumber lhs, double rhsReal)
 * static *(ComplexNumber lhs, double rhsReal, double rhsImag)
 * *(ComplexNumber rhs)
 * *(double rhsReal)
 * *(double rhsReal, double rhsImag)
 *
 * Where * is add, subtract, multiply, or divide
 * add: out = (x.real + y.real) + j(x.imaginary + y.imaginary), or this += y
 * subtract: out = (x.real - y.real) + j(x.imaginary - y.imaginary), or this -= y
 * multiply: out = x * y, or this *= y
 * divide: out = x / y, or this /= y
 *
 * Return: ComplexNumber
 *
 * Trigonometry Functions
 * static *(ComplexNumber arg)
 * *()
 *
 * sin: out = sin(arg), or this = sin(this)
 * cos: out = cos(arg), or this = cos(this)
 * tan: out = tan(arg), or this = tan(this)
 *
 * Return: ComplexNumber
 *
 * Comparison Functions
 * equals(Object rhs)
 * notEquals(Object rhs)
 *
 * out = this == rhs, or out = this != rhs
 *
 * Return: boolean
 *
 * Fourier Series Functions
 * fft(ComplexNumber[])
 * fft(List<ComplexNumber>)
 * fftMT(List<ComplexNumber>)
 *
 * Computes the Fourier series of the following arguments using the Cooley-Tukey algorithm. The
 * number of elements in the array/List must be a power of 2. fftMT() is a multithreaded version
 * of the fft(List) variant
 *
 * Return: ComplexNumber[], or List<ComplexNumber> depending on what was passed in
 *
 * ifft(ComplexNumber[])
 * ifft(List<ComplexNumber>)
 *
 * Computes the inverse Fourier series of the specified arguments also using the Cooley-Tukey
 * algorithm. The number of elements in the array/List must be a power of 2.
 *
 * Return: ComplexNumber[], or List<ComplexNumber> depending on what was passed in
 *
 * Miscellaneous Functions
 * static reciprocal(ComplexNumber arg)
 * reciprocal()
 *
 * out = 1 / arg, or this = 1 / this
 *
 * Return: ComplexNumber
 *
 * static conjugate(ComplexNumber arg)
 * conjugate()
 *
 * out = out.real - out.imaginary, or this = this.real - this.imaginary
 *
 * Return: ComplexNumber
 *
 * static magnitude(ComplexNumber arg)
 * magnitude()
 *
 * out = sqrt((real ** 2) + (imag ** 2))
 *
 * Return: double
 *
 * static phaseRadians(ComplexNumber arg)
 * static phaseDegrees(ComplexNumber arg)
 * phaseRadians()
 * phaseDegrees()
 *
 * out = atan(imaginary / real)
 *
 * Return: double - The output is either in degrees or radians based on the method called
 *
 * static exp(ComplexNumber arg)
 * exp()
 *
 * out = e ** arg, or this = e ** this
 *
 * Return: ComplexNumber
 */

public class ComplexNumber {
    // Variable declarations
    private double real, imaginary;

    // Default constructor
    public ComplexNumber() {
        real = 0.0;
        imaginary = 0.0;
    }

    // Non-default constructor: allows for initializing member variables
    public ComplexNumber(double newR, double newI) {
        real = newR;
        imaginary = newI;
    }

    // Mutators
    public void setReal(double newNum) {
        real = newNum;
    }

    public void setImaginary(double newNum) {
        imaginary = newNum;
    }

    // Accessors
    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    // A function that converts a complex number to a string
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

    // Basic arithmetic operations, addition, subtraction, multiplication, division
    // Addition: Adds two numbers and returns a new object
    // Later methods takes separate arguments for the rhs and adds them on to a complex number
    // Creating an object based on the sum of the numbers. If you need separate variables
    // for both sides, use the non-default constructor.
    public static ComplexNumber add(ComplexNumber lhs, ComplexNumber rhs) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real + rhs.real;
        newNumber.imaginary += lhs.imaginary + rhs.imaginary;

        return newNumber;
    }

    public static ComplexNumber add(ComplexNumber lhs, double rhsReal) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real + rhsReal;
        newNumber.imaginary = lhs.imaginary;

        return newNumber;
    }

    public static ComplexNumber add(ComplexNumber lhs, double rhsReal, double rhsImag) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real + rhsReal;
        newNumber.imaginary = lhs.imaginary + rhsImag;

        return newNumber;
    }

    // Add onto: Takes a rhs argument or arguments and adds them onto the current object
    // and returns the current object
    public ComplexNumber add(ComplexNumber rhs) {
        this.real += rhs.real;
        this.imaginary += rhs.imaginary;

        return this;
    }

    public ComplexNumber add(double rhsReal) {
        this.real += rhsReal;

        return this;
    }

    public ComplexNumber add(double rhsReal, double rhsImag) {
        this.real += rhsReal;
        this.imaginary += rhsImag;

        return this;
    }

    // Subtraction: Subtracts two numbers and returns a new object
    // Later methods takes separate arguments for the rhs and subtracts them on to a complex
    // number creating an object based on the difference of the numbers. If you need separate
    // variables for both sides, use the non-default constructor.
    public static ComplexNumber subtract(ComplexNumber lhs, ComplexNumber rhs) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real - rhs.real;
        newNumber.imaginary = lhs.imaginary - rhs.imaginary;

        return newNumber;
    }

    public static ComplexNumber subtract(ComplexNumber lhs, double rhsReal) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real - rhsReal;
        newNumber.imaginary = lhs.imaginary;

        return newNumber;
    }

    public static ComplexNumber subtract(ComplexNumber lhs, double rhsReal, double rhsImag) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real - rhsReal;
        newNumber.imaginary = lhs.imaginary - rhsImag;

        return newNumber;
    }

    // Subtract onto: Takes a rhs argument or arguments and subtracts them onto the current object
    // and returns the current object
    public ComplexNumber subtract(ComplexNumber rhs) {
        this.real -= rhs.real;
        this.imaginary -= rhs.imaginary;

        return this;
    }

    public ComplexNumber subtract(double rhsReal) {
        this.real -= rhsReal;

        return this;
    }

    public ComplexNumber subtract(double rhsReal, double rhsImag) {
        this.real -= rhsReal;
        this.imaginary -= rhsImag;

        return this;
    }

    // Multiply: Multiplies two numbers and returns a new object
    // Later methods takes separate arguments for the rhs and multiplies them on to a complex
    // number creating an object based on the product of the numbers. If you need separate
    // variables for both sides, use the non-default constructor.
    public static ComplexNumber multiply(ComplexNumber lhs, ComplexNumber rhs) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = (lhs.real * rhs.real) - (lhs.imaginary * rhs.imaginary);
        newNumber.imaginary = (lhs.real * rhs.imaginary) + (lhs.imaginary * rhs.real);

        return newNumber;
    }

    public static ComplexNumber multiply(ComplexNumber lhs, double rhsReal) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = lhs.real * rhsReal;
        newNumber.imaginary = lhs.imaginary * rhsReal;

        return newNumber;
    }

    public static ComplexNumber multiply(ComplexNumber lhs, double rhsReal, double rhsImag) {
        ComplexNumber newNumber = new ComplexNumber();

        newNumber.real = (lhs.real * rhsReal) - (lhs.imaginary * rhsImag);
        newNumber.imaginary = (lhs.real * rhsImag) + (lhs.imaginary * rhsReal);

        return newNumber;
    }

    // Multiply onto: Takes a rhs argument or arguments and multiplies them onto the current object
    // and returns the current object
    public ComplexNumber multiply(ComplexNumber rhs) {
        double tempReal = this.real, tempImag = this.imaginary;
        this.real = (tempReal * rhs.real) - (tempImag * rhs.imaginary);
        this.imaginary = (tempReal * rhs.imaginary) + (tempImag * rhs.real);

        return this;
    }

    public ComplexNumber multiply(double rhsReal) {
        this.real = this.real * rhsReal;
        this.imaginary = this.imaginary * rhsReal;

        return this;
    }

    public ComplexNumber multiply(double rhsReal, double rhsImag) {
        double tempReal = this.real, tempImag = this.imaginary;
        this.real = (tempReal * rhsReal) - (tempImag * rhsImag);
        this.imaginary = (tempReal * rhsImag) + (tempImag * rhsReal);

        return this;
    }

    // Divide: Divides two numbers and returns a new object
    // Later methods takes separate arguments for the rhs and divides them on to a complex
    // number creating an object based on the quotient of the numbers. If you need separate
    // variables for both sides, use the non-default constructor.
    public static ComplexNumber divide(ComplexNumber lhs, ComplexNumber rhs) {
        double scale = rhs.real*rhs.real + rhs.imaginary*rhs.imaginary;
        ComplexNumber rhsRec = new ComplexNumber(rhs.real / scale, -rhs.imaginary / scale);

        return ComplexNumber.multiply(lhs, rhsRec);
    }

    public static ComplexNumber divide(ComplexNumber lhs, double rhsReal) {
        double scale = rhsReal*rhsReal;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, 0);

        return ComplexNumber.multiply(lhs, rhsRec);
    }

    public static ComplexNumber divide(ComplexNumber lhs, double rhsReal, double rhsImag) {
        double scale = rhsReal*rhsReal + rhsImag*rhsImag;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, -rhsImag / scale);

        return ComplexNumber.multiply(lhs, rhsRec);
    }

    // Divide onto: Takes a rhs argument or arguments and divides them onto the current object
    // and returns the current object
    public ComplexNumber divide(ComplexNumber rhs) {
        double scale = rhs.real*rhs.real + rhs.imaginary*rhs.imaginary;
        ComplexNumber rhsRec = new ComplexNumber(rhs.real / scale, -rhs.imaginary / scale);

        return this.multiply(rhsRec);
    }

    public ComplexNumber divide(double rhsReal) {
        double scale = rhsReal*rhsReal;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, 0);

        return this.multiply(rhsRec);
    }

    public ComplexNumber divide(double rhsReal, double rhsImag) {
        double scale = rhsReal*rhsReal + rhsImag*rhsImag;
        ComplexNumber rhsRec = new ComplexNumber(rhsReal / scale, -rhsImag / scale);

        return this.multiply(rhsRec);
    }

    // Reciprocal: 1 / (a + jb)
    public ComplexNumber reciprocal() {
        double scale = this.real*this.real + this.imaginary*this.imaginary;

        this.real = this.real / scale;
        this.imaginary = -this.imaginary / scale;

        return this;
    }

    public static ComplexNumber reciprocal(ComplexNumber arg) {
        double scale = arg.real*arg.real + arg.imaginary*arg.imaginary;

        return new ComplexNumber(arg.real / scale, -arg.imaginary / scale);
    }

    // Conjugate: Return the complex conjugate of the number
    // a + jb -> a - jb
    public ComplexNumber conjugate() {
        this.imaginary = -this.imaginary;

        return this;
    }

    public static ComplexNumber conjugate(ComplexNumber arg) {
        return new ComplexNumber(arg.real, -arg.imaginary);
    }

    // Magnitude: Return the distance from origin to the point
    public double magnitude() {
        return Math.hypot(this.real, this.imaginary);
    }

    public static double magnitude(ComplexNumber arg) {
        return Math.hypot(arg.real, arg.imaginary);
    }

    // Phase*: Return the phase angle from origin to point
    public double phaseRadians() {
        return Math.atan2(this.imaginary, this.real);
    }

    public static double phaseRadians(ComplexNumber arg) {
        return Math.atan2(arg.imaginary, arg.real);
    }

    public double phaseDegrees() {
        return Math.toDegrees(Math.atan2(this.imaginary, this.real));
    }

    public static double phaseDegrees(ComplexNumber arg) {
        return Math.toDegrees(Math.atan2(arg.imaginary, arg.real));
    }

    // exp(): e^x.  I think this speaks for itself.
    public ComplexNumber exp() {
        double tempReal = this.real;
        double tempImag = this.imaginary;

        this.real = Math.exp(tempReal) * Math.cos(tempImag);
        this.imaginary = Math.exp(tempReal) * Math.sin(tempImag);

        return this;
    }

    public static ComplexNumber exp(ComplexNumber arg) {
        return new ComplexNumber(Math.exp(arg.real) * Math.cos(arg.imaginary),
                Math.exp(arg.real) * Math.sin(arg.imaginary));
    }

    // Trig functions.  Inverse trig functions are not provided here.
    public ComplexNumber sin() {
        double tempReal = this.real, tempImag = this.imaginary;

        this.real = Math.sin(tempReal) * Math.cosh(tempImag);
        this.imaginary = Math.cos(tempReal) * Math.sinh(tempImag);

        return this;
    }

    public static ComplexNumber sin(ComplexNumber arg) {
        return new ComplexNumber(Math.sin(arg.real) * Math.cosh(arg.imaginary),
                Math.cos(arg.real) * Math.sinh(arg.imaginary));
    }

    public ComplexNumber cos() {
        double tempReal = this.real, tempImag = this.imaginary;

        this.real = Math.cos(tempReal) * Math.cosh(tempImag);
        this.imaginary = -(Math.sin(tempReal) * Math.sinh(tempImag));

        return this;
    }

    public static ComplexNumber cos(ComplexNumber arg) {
        return new ComplexNumber(Math.cos(arg.real) * Math.cosh(arg.imaginary),
                -(Math.sin(arg.real) * Math.sinh(arg.imaginary)));
    }

    public ComplexNumber tan() {
        ComplexNumber tempSin = sin(this);
        ComplexNumber tempCos = cos(this);

        ComplexNumber out = divide(tempSin, tempCos);

        this.real = out.real;
        this.imaginary = out.imaginary;

        return this;
    }

    public static ComplexNumber tan(ComplexNumber arg) {
        return sin(arg).divide(cos(arg));
    }

    // Basic comparison operations: Check if the object is equal to or not equal
    // to another object
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        else {
            ComplexNumber that = (ComplexNumber) obj;
            return (this.real == that.real) && (this.imaginary == that.imaginary);
        }
    }

    public boolean notEqual(Object obj) {
        if (obj == null)
            return true;
        if (this.getClass() != obj.getClass())
            return true;
            // DeMorgan's theorem of the equals function
        else {
            ComplexNumber that = (ComplexNumber) obj;
            return (this.real != that.real) || (this.imaginary != that.imaginary);
        }
    }

    // Compute the FFT on an array of values.
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

    // Perform an FFT computation on a List object.  This implementation uses a linked list
    // specifically, although it can be changed to use ArrayLists
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

    public static List<ComplexNumber> fftMT(List<ComplexNumber> toFFT) {
        int size = toFFT.size();
        if ((size & (size - 1)) != 0) {
            throw new RuntimeException("FFTFreqGen: ERROR - The List<> size must be a power of 2\n");
        }

        // Start by performing the first separation of the fft components
        FFTSeparator a = new FFTSeparator(toFFT);
        a.separate();

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

    // Compute the inverse FFT of the input array.  The size must be a power of 2
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
