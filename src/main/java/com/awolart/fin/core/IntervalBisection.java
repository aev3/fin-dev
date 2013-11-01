/*
 * $Id: $
 */

package com.awolart.fin.core;

/**
 * <p>
 * Evaluation of successive root estimates
 * </p>
 */
public abstract class IntervalBisection
{

    public abstract double computeFunction(double root);

    protected double precision;

    protected int iterations;

    protected double lower;

    protected double upper;

    /* Default no argument constructor */
    protected IntervalBisection()
    {
        iterations = 20;
        precision = 1e-3;
    }

    /* Constructor with caller defined iterations and precision */
    protected IntervalBisection(int iterations, double precision)
    {
        this.iterations = iterations;
        this.precision = precision;
    }

    public double getPrecision()
    {
        return precision;
    }

    public int getIterations()
    {
        return iterations;
    }

    public double evaluateRoot(double lower, double higher) 
    {

        double fa;
        double fb;
        double fc;
        double mid_val = 0;
        double prec_val = 0;
        fa = computeFunction(lower);
        System.out.println("fa = " + fa);
        fb = computeFunction(higher);
        System.out.println("fb = " + fb);

        /* Check to see if the root is within the range bounds */
        if (fa * fb > 0) 
        {
            mid_val = 0; // terminate
        } 
        else
        {
            do {
                /* Use prec_val for testing the relative precision */
                prec_val = mid_val;
                mid_val = lower + 0.5 * (higher - lower);
                fc = computeFunction(mid_val);
                if (fa * fc < 0)
                {
                    higher = mid_val;
                }
                else if (fa * fc > 0)
                {
                    lower = mid_val;
                }

                System.out.println("mid_val = " + mid_val);

            } while((Math.abs(fc) > prec_val && Math.abs(fc)  < iterations));

        }

        return mid_val;
        
    }  // public double evaluateRoot(double lower, double higher)

} // public abstract class IntervalBisection
