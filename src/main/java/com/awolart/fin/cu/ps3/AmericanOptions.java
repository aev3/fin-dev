/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps3;

import static com.awolart.fin.cu.ps3.PS3Consts.EoC_ROWS;
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
public class AmericanOptions
{

    double[][] stk_lat;

    double[][] opt_lat;

    public double[][] createStockLattice(int rows, int cols, double s0,
                                         double up)
    {
        stk_lat = new double[rows][cols];
        for(int i = 0; i <= stk_lat.length; ++i)
        {
            if(i == 0)
            {
                stk_lat[0][0] = s0;

                for(int k = 1; k < stk_lat.length; ++k)
                {
                    stk_lat[i][k] = S0 * Math.pow((1 / up), k);
                }
            }
            else
            {
                int limit = stk_lat[i - 1].length;
                for(int j = i; j < limit; ++j)
                {
                    if(j == i)
                    {
                        stk_lat[i][j] = s0 * Math.pow((up), j);
                    }
                    else
                    {
                        stk_lat[i][j] = (stk_lat[i - 1][j - 1]) * up;
                    }
                }
            }
        }

        return stk_lat;
    }

    public double[][] createOptionLattice(int rows, int cols, double q,
                                          double r, double s)
    {
        /*
         * Initialize the opt_lat two dimensional matrix.
         */
        opt_lat = new double[rows][cols];

        for(int row = 0; row < opt_lat.length; ++row)
        {
            /* Define the last column data rows using the data from the stk_lat lattice */
            int column = cols-1;
            opt_lat[row][column] = Math.max(stk_lat[row][column] - s, 0.0);
            //System.out.println("opt_lat[" + column + "][" + row + "] = " + opt_lat[row][column]);
        }


        /**
         * Build remaining portion of opt_lat by iterating from [rows-1][cols-1] to [0][0]
         * because data required for defining [row][col] is in [row+1]
         */
        for(int row = rows-2; row >= 0 ; --row)
        {
            for(int col = cols-2; col >= 0 ; --col)
            {
                opt_lat[row][col] = ((q * opt_lat[row+1][col+1]) + (1.0 - q) * opt_lat[row][col+1])/r;
                //System.out.println("D: opt_lat[" + row + "][" + col + "] = " + opt_lat[col][row]);
            }
        }

        return opt_lat;
    }


    /**
     * Calculating the fair value of an European Option (Eo) in a 1 period
     * binomial model: {@latex.inline $ X = max[N_0, \\frac 1}{R}(q \\times N_d
     * + (1 - q) \\times N_u) $}
     */
    public double calculateFairValueEo()
    {
        double fv = 0.0;
        return fv;
    }


    public static void main(String[] args)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        AmericanOptions lattice = new AmericanOptions();

        double[][] EoCM = lattice.createStockLattice(PS3Consts.EoC_ROWS,
                PS3Consts.EoC_COLS, PS3Consts.EoC_S0, PS3Consts.EoC_UP);
        /*          */
        System.out.println("Stock Lattice from [0][0] to [3][3]:");
        for(int i = 0; i < PS3Consts.EoC_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.EoC_COLS; ++j)
            {
                System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", EoCM[i][j]);
            }
            System.out.println();
        }
        System.out.println();


        /*         */
        double[][] EoC_OL = lattice.createOptionLattice(PS3Consts.EoC_ROWS,
                PS3Consts.EoC_COLS, PS3Consts.EoC_q, PS3Consts.EoC_r, PS3Consts.EoC_STR);
        System.out.println("Option Lattice from [0][0] to [3][3]:");
        for(int i = 0; i < PS3Consts.EoC_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.EoC_COLS; ++j)
            {
                System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", EoC_OL[i][j]);
            }
            System.out.println();
        }

    }

}
