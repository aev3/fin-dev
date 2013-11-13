/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps3;

import static com.awolart.fin.cu.ps3.PS3Consts.S0;
import static com.awolart.fin.cu.ps3.PS3Consts.u;

import java.text.NumberFormat;

/**
 * <p>
 * Build a 15-period binomial model lattice whose parameters should be
 * calibrated to a Black-Scholes geometric Brownian motion model with:
 * <ol>
 * <li>T = 0.25 years</li>
 * <li>S0 = 100.00</li>
 * <li>r = 0.02</li>
 * <li>d = 0.30</li>
 * <li>c = 0.01</li>
 * <li>u = 1.0395</li>
 * </ol>
 * <p/>
 * All final static variables are accessible from @{link PS3Consts}.
 * </p>
 */
public class Lattice
{

    public double[][] createMatrix()
    {
        double[][] mx = new double[ROWS][COLS];
        for(int i = 0; i <= mx.length; ++i)
        {
            if(i == 0)
            {
                mx[0][0] = S0;
                System.out.println("mx [0][0] \t = \t" + mx[0][0]);
                for(int k = 1; k < mx.length; ++k)
                {
                    mx[i][k] = S0 * Math.pow((1 / u), k);
                    System.out.println("mx [" + i + "][" + k + "] \t = \t"
                            + mx[i][k]);
                }
            }
            else
            {
                int limit =  mx[i-1].length;
                for(int j = i; j < limit; ++j)
                {
                    if(j == i)   {
                        mx[i][j] = S0 * Math.pow((u), j);
                        System.out.println("mx [" + i + "][" + j + "] \t = \t"
                                + mx[i][j]);
                    } else {
                        mx[i][j] = (mx[i-1][j-1]) * u;
                        System.out.println("mx [" + i + "][" + j + "] \t = \t"
                                + mx[i][j]);
                    }
                }
            }
        }

        return mx;
    }

    public static void main(String[] args)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        Lattice lattice = new Lattice();
        double[][] M = lattice.createMatrix();
        for(int i = ROWS-1; i >= 0; i--) {
            for (int j = 0; j < COLS; j++)
                System.out.printf("%9.4f ", M[i][j]);
            System.out.println();
        }
    }

}
