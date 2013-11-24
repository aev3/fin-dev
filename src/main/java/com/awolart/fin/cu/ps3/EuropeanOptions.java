/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps3;

import static com.awolart.fin.cu.ps3.PS3Consts.S0;

/**
 * TODO: This class is primarily for testing lattice construction.
 * TODO: It should be considered unreliable without further testing.
 *
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
public class EuropeanOptions
{

    double[][] stk_lat;

    double[][] opt_lat;

    public double[][] createStockLattice(int rows, int cols, double s0,
                                         double up)
    {
        stk_lat = new double[rows][cols];
        //rev_lat = new double[rows][cols];

        for(int i = 0; i <= stk_lat.length; ++i)
        {
            if(i > 0)
            {
                int limit = stk_lat[i-1].length;
                for(int j = i; j < limit; ++j)
                {
                    if(j == i)
                    {
                        stk_lat[i][j] = s0 * Math.pow((up), j);
                    }
                    else
                    {
                        stk_lat[i][j] = (stk_lat[i-1][j-1]) * up;
                    }
                }
            }
            else if(i == 0)
            {
                stk_lat[0][0] = s0;

                for(int k = 1; k < stk_lat.length; ++k)
                {
                    stk_lat[i][k] = S0 * Math.pow((1/up), k);
                }
            }
        }

        return stk_lat;
    }

    public double[][] createCallOptionLattice(int rows, int cols, double q,
            double r, double s)
    {
        /*
         * Initialize the opt_lat two dimensional matrix.
         */
        opt_lat = new double[rows][cols];

        for(int row = 0; row < opt_lat.length; ++row)
        {
            /*
             * Define the last column data rows using the data from the stk_lat
             * lattice
             */
            int column = cols - 1;
            opt_lat[row][column] = Math.max(stk_lat[row][column] - s, 0.0);
            // System.out.println("opt_lat[" + column + "][" + row + "] = " +
            // opt_lat[row][column]);
        }

        /**
         * Build remaining portion of opt_lat by iterating from [rows-1][cols-1]
         * to [0][0] because data required for defining [row][col] is in [row+1]
         */
        for(int row = rows - 2; row >= 0; --row)
        {
            for(int col = cols - 2; col >= 0; --col)
            {
                opt_lat[row][col] = ((q * opt_lat[row + 1][col + 1]) + (1.0 - q)
                        * opt_lat[row][col + 1])
                        / r;
                // System.out.println("D: opt_lat[" + row + "][" + col + "] = "
                // + opt_lat[col][row]);
            }
        }

        return opt_lat;
    }

    public double[][] createPutOptionLattice(int rows, int cols, double q,
            double r, double s)
    {
        /*
         * Initialize the opt_lat two dimensional matrix.
         */
        opt_lat = new double[rows][cols];

        /*
         * For this first column use max((strike - stk_lat[row][col]), 0.0)
         */
        for(int row = 0; row < rows; ++row)
        {
            /*
             * Define the last column data rows using the data from the stk_lat
             * lattice
             */
            int col = cols - 1;
            opt_lat[row][col] = Math.max(s - stk_lat[row][col], 0.0);
        }

        /*
         * Build remaining portion of opt_lat by iterating from [rows-1][cols-1]
         * to [0][0] because data required for defining [row][col] is in [row+1]
         * At row[0], col[2] use (Math.max(Math.max((s - stk_lat[0][2]),0.0),
         * (q*opt_lat[0][0] + (1-q)* opt_lat[0][0])/r
         */
        for(int row = rows - 2; row >= 0; --row)
        {
            for(int col = cols - 2; col >= 0; --col)
            {
                double MM;

                if(stk_lat[row][col] == 0.0 || stk_lat[row][col] < 0)
                {
                    MM = Math.max((q * opt_lat[row + 1][col] + (1 - q)
                            * opt_lat[row][col])
                            * (1 / r), 0.0);
                }
                else
                {
                    MM = Math.max(Math.max((s - stk_lat[row][col]), 0.0), (q
                            * opt_lat[row + 1][col + 1] + (1 - q)
                            * opt_lat[row][col + 1])
                            * (1 / r));
                }
                opt_lat[row][col] = MM;
            }
        }

        return opt_lat;
    }

    public double[][] createPutPayoffLattice(int rows, int cols, double q, double r,
            double s, double T)
    {
        double[][] pay_lat = new double[rows][cols];

        /*
         * For this first column use max((strike - stk_lat[row][col]), 0.0
         * XL=MAX($G$2*(L16-$G$3),0))
         * G2=1 (call/put)
         * G3=strike=100
         * L16=stk_lat[R_n, C_n]
         */
        for(int row = 0; row < rows; ++row)
        {
            /*
             * Define the last column data rows using the data from the stk_lat
             * lattice
             */
            int col = cols-1;
            //pay_lat[row][col] = Math.max(s - stk_lat[row][col], 0.0);
            pay_lat[row][col] = Math.max((stk_lat[row][col])-s, 0.0);
        }

        /*
         * For Row 5[XL=36] and Col 9[XL=30]:
         * =IF($A36 <= K$30, ($B$10*L35+$B$11*L36)/EXP($B$6 * $B$3/$B$5),"")
         * A36=R_n
         * K30=C_n
         *
         */
        //for(int row = 0; row <= rows-2; ++row)
        for(int row = rows-2; row >= 0; --row)
        {
            for(int col = cols-2; col >= 0; --col)
            {
                if(row <= col)
                {
                    /* constant */
                    int periods =  cols-1;
                    double q_less = 1-q;
                    double denom = Math.exp(r * T / periods );
                    //System.out.println("EXP = " + denom + ", where r = " + r
                    //        + ", T = " + T + ", and periods = " + periods);
                    /* variable */
                    double num_left = q * pay_lat[row+1][col+1];
                    double num_right = q_less * pay_lat[row][col+1];
                    //double num_left = q * pay_lat[row-1][col-1];
                    //double num_right = q_less * pay_lat[row][col-1];
                    pay_lat[row][col] = (num_left + num_right)/denom;
                    System.out.println("[" + row + "][" + col + "]:"
                            + " num_right = " + num_right
                            + ", num_left = " + num_left
                            + ", denom = " + denom
                            + ", and pay_lat[row][col] = " + pay_lat[row][col]
                            );
                }
                else
                {
                    pay_lat[row][col] = 0.0;
                }
            }

        }

        return pay_lat;

    }

}
