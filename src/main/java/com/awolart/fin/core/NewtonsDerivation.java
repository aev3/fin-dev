/*
 *
 */

package com.awolart.fin.core;

/**
 * <p>
 * </p>
 */
public abstract class NewtonsDerivation {

     public abstract double deriveFunction(final double fx);

     /* Degree of accuracy in the calculation */
     public double h;

    public double derivation(final double input) {
        double value;
        double X2 = deriveFunction(input - h);
        double X1 = deriveFunction(input + h);
        return ((X1-X2)/(2*h));
    }

}
