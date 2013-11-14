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
public class Lattice
{

    double[][] stk_latt;

    double[][] opt_latt;

    public double[][] createStockLattice(int rows, int cols, double s0,
            double up)
    {
        stk_latt = new double[rows][cols];
        for(int i = 0; i <= stk_latt.length; ++i)
        {
            if(i == 0)
            {
                stk_latt[0][0] = s0;
                System.out.println("mx [0][0] \t = \t" + stk_latt[0][0]);
                for(int k = 1; k < stk_latt.length; ++k)
                {
                    stk_latt[i][k] = S0 * Math.pow((1 / up), k);
                    System.out.println("mx [" + i + "][" + k + "] \t = \t"
                            + stk_latt[i][k]);
                }
            }
            else
            {
                int limit = stk_latt[i - 1].length;
                for(int j = i; j < limit; ++j)
                {
                    if(j == i)
                    {
                        stk_latt[i][j] = s0 * Math.pow((up), j);
                        System.out.println("mx [" + i + "][" + j + "] \t = \t"
                                + stk_latt[i][j]);
                    }
                    else
                    {
                        stk_latt[i][j] = (stk_latt[i - 1][j - 1]) * up;
                        System.out.println("mx [" + i + "][" + j + "] \t = \t"
                                + stk_latt[i][j]);
                    }
                }
            }
        }

        return stk_latt;
    }

    public double[][] createOptionLattice(int rows, int cols, double q,
            double r, double s)
    {

        opt_latt = new double[rows][cols];

        /*
        Call stack:
        t3,r3 = MAX(stk_latt[3][3] - Strike Price, 0)
        t3,r2 = MAX(stk_latt[3][2] - Strike Price, 0)
        t3,r1 = MAX(stk_latt[3][1] - Strike Price, 0)
        t3,r0 = MAX(stk_latt[3][0] - Strike Price, 0)
        */
        // First, calculate the values for column N
//        for(int i = EoC_ROWS; i > 0; --i)
//        {
//            opt_latt[i-1][i-1] = Math.max(stk_latt[i][i] - s, 0.0);
//            System.out.println("opt_latt[cols][i] =" + opt_latt[i-1][i-1]);
//        }

        /*
        t2,r3 = 0.000 //(q * opt_latt[3][3]) + (1-q) * opt_latt[3][2]) / R
        t2,r2 = (q * opt_latt[3][3]) + (1-q) * opt_latt[3][2]) / R
        t2,r1 = (q * opt_latt[3][2]) + (1-q) * opt_latt[3][1]) / R
        t2,r0 = (q * opt_latt[3][1]) + (1-q) * opt_latt[3][0]) / R

        t1,r3 = 0.000 //(q * opt_latt[2][2] + (1-q) * opt_latt[2][1]) / R
        t1,r2 = 0.000 //(q * opt_latt[2][2] + (1-q) * opt_latt[2][1]) / R
        t1,r1 = (q * opt_latt[2][2] + (1-q) * opt_latt[2][1]) / R
        t1,r0 = (q * opt_latt[2][1] + (1-q) * opt_latt[2][0]) / R

        t0,r3 =  0.000 //(q * opt_latt[1][1] + (1-q) * opt_latt[1][0]) / R
        t0,r2 =  0.000 //(q * opt_latt[1][1] + (1-q) * opt_latt[1][0]) / R
        t0,r1 =  0.000 //(q * opt_latt[1][1] + (1-q) * opt_latt[1][0]) / R
        t0,r0 = (q * opt_latt[1][1] + (1-q) * opt_latt[1][0]) / R
        */
        for(int i = rows-1; i > 0 ; --i)
        {
            for(int j = cols-1; j > 0 ; --j)
            {
                if(j == cols-1)
                {
                    opt_latt[i][j] = Math.max(stk_latt[i][j] - s, 0.0);
                    System.out.println("opt_latt[i][j] = " + opt_latt[i][j]);
                }
                else
                {
                    /// ((0.5570*22.504) + (1-0.5570) * 7.00)/1.01
                    System.out.println("stk_latt[3][3] = " + stk_latt[3][3]);
                    System.out.println("stk_latt[2][3] = " + stk_latt[2][3]);
                    opt_latt[2][2] = ((0.5570 * opt_latt[3][3]) + (1.0 - 0.5570) * opt_latt[3][2]) / r;
                    opt_latt[2][1] = ((0.5570 * opt_latt[3][2]) + (1.0 - 0.5570) * opt_latt[3][1]) / r;
                    opt_latt[2][0] = ((0.5570 * opt_latt[3][1]) + (1.0 - 0.5570) * opt_latt[3][0]) / r;
                    opt_latt[1][1] = ((0.5570 * opt_latt[2][2]) + (1.0 - 0.5570) * opt_latt[2][1]) / r;
                    opt_latt[1][0] = ((0.5570 * opt_latt[2][1]) + (1.0 - 0.5570) * opt_latt[2][0]) / r;
                    opt_latt[0][0] = ((0.5570 * opt_latt[1][1]) + (1.0 - 0.5570) * opt_latt[1][0]) / r;
                }
//                else
//                {
//                    //for(int j = cols-1; j > 0;--j)
//                    while(j != 0)
//                    {
//                        int ROW = j; int COL = j;
//                        double R3C3 = opt_latt[COL+1][ROW+1]; double R3C2 = opt_latt[COL+1][ROW];
//                        double R2C1 = opt_latt[COL][ROW-1];
//                        opt_latt[i][j+1] = (q * opt_latt[j+1][j+1]) + (1.0-q) * (opt_latt[j+1][j] / r);
//                        --j;
//                    }
//                }
            }
        }

        return opt_latt;
    }

//    public double[][] createStockLattice()
//    {
//        double[][] mx = new double[ROWS][COLS];
//        for(int i = 0; i <= mx.length; ++i)
//        {
//            if(i == 0)
//            {
//                mx[0][0] = S0;
//                System.out.println("mx [0][0] \t = \t" + mx[0][0]);
//                for(int k = 1; k < mx.length; ++k)
//                {
//                    mx[i][k] = S0 * Math.pow((1 / u), k);
//                    System.out.println("mx [" + i + "][" + k + "] \t = \t"
//                            + mx[i][k]);
//                }
//            }
//            else
//            {
//                int limit = mx[i - 1].length;
//                for(int j = i; j < limit; ++j)
//                {
//                    if(j == i)
//                    {
//                        mx[i][j] = S0 * Math.pow((u), j);
//                        System.out.println("mx [" + i + "][" + j + "] \t = \t"
//                                + mx[i][j]);
//                    }
//                    else
//                    {
//                        mx[i][j] = (mx[i - 1][j - 1]) * u;
//                        System.out.println("mx [" + i + "][" + j + "] \t = \t"
//                                + mx[i][j]);
//                    }
//                }
//            }
//        }
//
//        return mx;
//    }

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
        Lattice lattice = new Lattice();
        // double[][] M = lattice.createStockLattice();
        // for(int i = ROWS-1; i >= 0; i--) {
        // for (int j = 0; j < COLS; j++)
        // System.out.printf("%9.4f ", M[i][j]);
        // System.out.println();
        // }
        double[][] EoCM = lattice.createStockLattice(PS3Consts.EoC_ROWS,
                PS3Consts.EoC_COLS, PS3Consts.EoC_S0, PS3Consts.EoC_UP);
        for(int i = PS3Consts.EoC_ROWS - 1; i >= 0; i--)
        {
            for(int j = 0; j < PS3Consts.EoC_COLS; j++)
                System.out.printf("S %9.4f ", EoCM[i][j]);
            System.out.println();
        }
        /*
        (int rows, int cols, double q,
            double r, double s)
         */
        double[][] EoC_OL = lattice.createOptionLattice(PS3Consts.EoC_ROWS,
                PS3Consts.EoC_COLS, PS3Consts.EoC_q, PS3Consts.EoC_r, PS3Consts.EoC_STR);
        for(int i = PS3Consts.EoC_ROWS - 1; i >= 0; i--)
        {
            for(int j = 0; j < PS3Consts.EoC_COLS; j++)
                System.out.printf("O %9.4f ", EoC_OL[i][j]);
            System.out.println();
        }
    }

}
