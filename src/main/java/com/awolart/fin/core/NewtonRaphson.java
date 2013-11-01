/*
 *
 */

package com.awolart.fin.core;

/**
 * <p>
 * </p>
 */
public abstract class NewtonRaphson extends NewtonsDerivation
{

    public abstract double NewtonRoot(double value);

    public double precision;
    public int iteration;
    int counter;

    public void accuracy(double prec, int iters)
    {
        super.h = prec;
        this.precision = prec;
        this.iteration = iters;
    }


    public double newt_raph(double lower_bound)
    {
        double fx = NewtonRoot(lower_bound);
        double Fx = derivation(lower_bound);
        double x = (lower_bound - (fx/Fx));
//      while(Math.abs(Math.abs(x) - Math.abs(lower_bound)) > precision || counter < iteration)
        while((Math.abs(x - lower_bound) > precision && counter < iteration))
        {
            lower_bound = x;
            newt_raph(lower_bound);
            //fx = newt_raph(lower_bound);
            //Fx = derivation(lower_bound);
            //x = (lower_bound - (fx/Fx));
            counter++;
        }

        return x;

    }

    public double deriveFunction(double input)
    {
        double x1 = NewtonRoot(input);
        return x1;
    }

}
