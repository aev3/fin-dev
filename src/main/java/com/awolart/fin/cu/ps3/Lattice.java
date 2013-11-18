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
            int columm = cols-1;
            opt_lat[row][columm] = Math.max(stk_lat[row][columm] - s, 0.0);
            //System.out.println("opt_lat[" + columm + "][" + row + "] = " + opt_lat[row][columm]);
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


    public double[][] createOptionLattice2(int rows, int cols, double q,
                                          double r, double s)
    {

        opt_lat = new double[rows][cols];

        for(int i = cols; i >= 0 ; --i)
        {
            for(int j = rows; j >= 0 ; --j)
            {
                opt_lat[i][j] = Math.max(stk_lat[i][j] - s, 0.0);
                //System.out.println("O: opt_lat[" + i + "][" + j + "] = " + opt_lat[i][j]);
            }
        }

        for(int i = cols; i >= 0 ; --i)
        {
            for(int j = rows; j >= 0 ; --j)
            {

                opt_lat[i][j] = ((0.5570 * opt_lat[i+1][j+1]) + (1.0 - 0.5570) * opt_lat[i+1][j])/1.01;
                //System.out.println("I: opt_lat[" + i + "][" + j + "] = " + opt_lat[i][j]);
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
        Lattice lattice = new Lattice();

        System.out.println("**************************************************");
        System.out.println("\t\t\t\t ECO.createStockLattice");
        System.out.println("**************************************************");

        EuropeanOptions ECO = new EuropeanOptions();
        double[][] ECO_SL = ECO.createStockLattice(PS3Consts.EO_4_ROWS,
                PS3Consts.EO_4_COLS, PS3Consts.EO_4_S0, PS3Consts.EO_4_U);
        /*          */
        System.out.println("Stock Lattice from [0][0] to [3][3]:");
        for(int i = 0; i < PS3Consts.EO_4_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.EO_4_COLS; ++j)
            {
                System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", ECO_SL[i][j]);
            }
            System.out.println();
        }

//        AmericanOptions ao = new AmericanOptions();
//        double[][] rev_lat = ao.reverseLattice(ECOM);
//        System.out.println("Reverse Stock Lattice from [0][0] to [3][3]:");
//        for(int i = 0; i < PS3Consts.ECO_ROWS; ++i)
//        {
//            for(int j = 0; j < PS3Consts.ECO_COLS; ++j)
//            {
//                System.out.print("[" + i + "][" + j + "] =");
//                System.out.printf("%9.4f ", rev_lat[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();


        System.out.println("**************************************************");
        System.out.println("\t\t\t\t ECO.createOptionLattice");
        System.out.println("**************************************************");

        double[][] ECO_OL = ECO.createOptionLattice(PS3Consts.EO_4_ROWS,
                PS3Consts.EO_4_COLS, PS3Consts.EO_4_Q, PS3Consts.EO_4_R, PS3Consts.EO_4_STR);
        System.out.println("Option Lattice from [0][0] to [3][3]:");
        for(int i = 0; i < PS3Consts.EO_4_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.EO_4_COLS; ++j)
            {
                System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", ECO_OL[i][j]);
            }
            System.out.println();
        }

        System.out.println("**************************************************");
        System.out.println("\t\t\t\t ECO.createPricingLattice");
        System.out.println("**************************************************");

        double[][] ECO_PL = ECO.createPricingLattice(PS3Consts.EO_4_ROWS,
                PS3Consts.EO_4_COLS, PS3Consts.EO_4_Q, PS3Consts.EO_4_R, PS3Consts.EO_4_STR);
        System.out.println("Pricing Lattice from [0][0] to [3][3]:");
        for(int i = 0; i < PS3Consts.EO_4_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.EO_4_COLS; ++j)
            {
                System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", ECO_PL[i][j]);
            }
            System.out.println();
        }

        System.out.println("**************************************************");
        System.out.println("\t\t\t\t AO.createStockLattice");
        System.out.println("**************************************************");

        AmericanOptions AO = new AmericanOptions();
        double[][] AO_SL = AO.createStockLattice(PS3Consts.AO_15_ROWS,
                PS3Consts.AO_15_COLS,
                PS3Consts.AO_15_S0,
                PS3Consts.AO_15_U);
        //System.out.println("Option Lattice from [0][0] to [3][3]:");
        for(int i = 0; i < PS3Consts.AO_15_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.AO_15_COLS; ++j)
            {
                //System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", AO_SL[i][j]);
            }
            System.out.println();
        }

        System.out.println("**************************************************");
        System.out.println("\t\t\t\t AO.createOptionLattice");
        System.out.println("**************************************************");

        double[][] AO_OL = AO.createOptionLattice(PS3Consts.AO_15_ROWS,
                PS3Consts.AO_15_COLS,
                PS3Consts.AO_15_Q,
                PS3Consts.AO_15_R,
                PS3Consts.AO_15_K);
        //System.out.println("Option Lattice from [0][0] to [3][3]:");
        for(int i = 0; i < PS3Consts.AO_15_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.AO_15_COLS; ++j)
            {
                //System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", AO_OL[i][j]);
            }
            System.out.println();
        }

        System.out.println("**************************************************");
        System.out.println("\t\t\t\t APO.createPutPricingLattice");
        System.out.println("**************************************************");

        double[][] APO_OL2 = AO.createPutPricingLattice(PS3Consts.AO_15_ROWS,
                PS3Consts.AO_15_COLS,
                PS3Consts.AO_15_Q,
                PS3Consts.AO_15_R,
                PS3Consts.AO_15_K);
        System.out.println("Option Lattice from [0][0] to [15][15]:");
        for(int i = 0; i < PS3Consts.AO_15_ROWS; ++i)
        {
            for(int j = 0; j < PS3Consts.AO_15_COLS; ++j)
            {
                //System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", APO_OL2[i][j]);
            }
            System.out.println();
        }

//        System.out.println("**************************************************");
//        System.out.println("\t\t\t\t EO.createStockLattice");
//        System.out.println("**************************************************");
//
//        AmericanOptions EO = new AmericanOptions();
//        double[][] EO_SL = EO.createStockLattice(PS3Consts.EO_10_ROWS,
//                PS3Consts.EO_10_COLS,
//                PS3Consts.EO_10_S0,
//                PS3Consts.EO_10_U);
//        //System.out.println("Option Lattice from [0][0] to [3][3]:");
//        for(int i = 0; i < PS3Consts.EO_10_ROWS; ++i)
//        {
//            for(int j = 0; j < PS3Consts.EO_10_COLS; ++j)
//            {
//                //System.out.print("[" + i + "][" + j + "] =");
//                System.out.printf("%9.4f ", EO_SL[i][j]);
//            }
//            System.out.println();
//        }

//        System.out.println("**************************************************");
//        System.out.println("\t\t\t\t EO.createOptionLattice");
//        System.out.println("**************************************************");
//
//        double[][] EO_OL = EO.createOptionLattice(PS3Consts.EO_10_ROWS,
//                PS3Consts.EO_10_COLS,
//                PS3Consts.EO_10_Q,
//                PS3Consts.EO_10_R,
//                PS3Consts.EO_10_STR);
//        //System.out.println("Option Lattice from [0][0] to [3][3]:");
//        for(int i = 0; i < PS3Consts.EO_10_ROWS; ++i)
//        {
//            for(int j = 0; j < PS3Consts.EO_10_COLS; ++j)
//            {
//                //System.out.print("[" + i + "][" + j + "] =");
//                System.out.printf("%9.4f ", EO_OL[i][j]);
//            }
//            System.out.println();
//        }

//        System.out.println("**************************************************");
//        System.out.println("\t\t\t\t EO.createPutPricingLattice");
//        System.out.println("**************************************************");
//
//        double[][] EO_10_OL = EO.createPutPricingLattice(PS3Consts.EO_10_ROWS,
//                PS3Consts.EO_10_COLS,
//                PS3Consts.EO_10_Q,
//                PS3Consts.EO_10_R,
//                PS3Consts.EO_10_STR);
//        System.out.println("Option Lattice from [0][0] to [3][3]:");
//        for(int i = 0; i < PS3Consts.EO_10_ROWS; ++i)
//        {
//            for(int j = 0; j < PS3Consts.EO_10_COLS; ++j)
//            {
//                //System.out.print("[" + i + "][" + j + "] =");
//                System.out.printf("%9.4f ", EO_10_OL[i][j]);
//            }
//            System.out.println();
//        }

//        System.out.println("**************************************************");
//        System.out.println("\t\t\t\t APO.createStockLattice");
//        System.out.println("**************************************************");
//
//        AmericanOptions APO = new AmericanOptions();
//        double[][] APO_SL = APO.createStockLattice(PS3Consts.APO_4_ROWS,
//                PS3Consts.APO_4_COLS,
//                PS3Consts.APO_4_S0,
//                PS3Consts.APO_4_U);
//        System.out.println("Option Lattice from [0][0] to [3][3]:");
//        for(int i = 0; i < PS3Consts.APO_4_ROWS; ++i)
//        {
//            for(int j = 0; j < PS3Consts.APO_4_COLS; ++j)
//            {
//                System.out.print("[" + i + "][" + j + "] =");
//                System.out.printf("%9.4f ", APO_SL[i][j]);
//            }
//            System.out.println();
//        }

//        System.out.println("**************************************************");
//        System.out.println("\t\t\t\t APO.createOptionLattice");
//        System.out.println("**************************************************");
//
//        double[][] APO_OL = APO.createOptionLattice(PS3Consts.APO_4_ROWS,
//                PS3Consts.APO_4_COLS,
//                PS3Consts.APO_4_Q,
//                PS3Consts.APO_4_R,
//                PS3Consts.APO_4_STR);
//        System.out.println("Option Lattice from [0][0] to [3][3]:");
//        for(int i = 0; i < PS3Consts.APO_4_ROWS; ++i)
//        {
//            for(int j = 0; j < PS3Consts.APO_4_COLS; ++j)
//            {
//                System.out.print("[" + i + "][" + j + "] =");
//                System.out.printf("%9.4f ", APO_OL[i][j]);
//            }
//            System.out.println();
//        }

//        System.out.println("**************************************************");
//        System.out.println("\t\t\t\t APO.createPutPricingLattice");
//        System.out.println("**************************************************");
//
//        double[][] APO_OL2 = APO.createPutPricingLattice(PS3Consts.APO_4_ROWS,
//                PS3Consts.APO_4_COLS,
//                PS3Consts.APO_4_Q,
//                PS3Consts.APO_4_R,
//                PS3Consts.APO_4_STR);
//        System.out.println("Option Lattice from [0][0] to [3][3]:");
//        for(int i = 0; i < PS3Consts.APO_4_ROWS; ++i)
//        {
//            for(int j = 0; j < PS3Consts.APO_4_COLS; ++j)
//            {
//                System.out.print("[" + i + "][" + j + "] =");
//                System.out.printf("%9.4f ", APO_OL2[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println("**************************************************");
//        System.out.println("\t\t\t\t APO.reverse");
//        System.out.println("**************************************************");
//
//        APO.reverse(APO_OL2);

   }


    public void printCanonicalOptionLattice() {

        opt_lat = new double[PS3Consts.EoC_ROWS][PS3Consts.EoC_COLS];

        double q = PS3Consts.EoC_q;
        double r = PS3Consts.EoC_r;

        opt_lat[3][3] = Math.max(stk_lat[3][3] - 100, 0.0);
        System.out.println("opt_lat[3][3] = " + opt_lat[3][3]);
        opt_lat[3][2] = Math.max(stk_lat[2][3] - 100, 0.0);
        System.out.println("opt_lat[3][2] = " + opt_lat[3][2]);
        opt_lat[3][1] = Math.max(stk_lat[1][3] - 100, 0.0);
        System.out.println("opt_lat[3][1] = " + opt_lat[3][1]);
        opt_lat[3][0] = Math.max(stk_lat[0][3] - 100, 0.0);
        System.out.println("opt_lat[3][0] = " + opt_lat[3][0]);
        System.out.println();

        opt_lat[2][2] = ((q * opt_lat[3][3]) + (1.0 - q) * opt_lat[3][2])/r;
        System.out.println("opt_lat[2][2] = " + opt_lat[2][2]);
        opt_lat[2][1] = ((q * opt_lat[3][2]) + (1.0 - q) * opt_lat[3][1])/r;
        System.out.println("opt_lat[2][1] = " + opt_lat[2][1]);
        opt_lat[2][0] = ((q * opt_lat[3][1]) + (1.0 - q) * opt_lat[3][0])/r;
        System.out.println("opt_lat[2][0] = " + opt_lat[2][0]);
        System.out.println();

        opt_lat[1][2] = ((q * opt_lat[2][3]) + (1.0 - q) * opt_lat[2][2])/r;
        System.out.println("opt_lat[1][2]  = " + opt_lat[1][2] );
        opt_lat[1][1] = ((q * opt_lat[2][2]) + (1.0 - q) * opt_lat[2][1])/r;
        System.out.println("opt_lat[1][1]  = " + opt_lat[1][1] );
        opt_lat[1][0] = ((q * opt_lat[2][1]) + (1.0 - q) * opt_lat[2][0])/r;
        System.out.println("opt_lat[1][0]  = " + opt_lat[1][0] );
        System.out.println();

        opt_lat[0][2] = ((q * opt_lat[1][3]) + (1.0 - q) * opt_lat[1][2])/r;
        System.out.println("opt_lat[0][2]  = " + opt_lat[0][0] );
        opt_lat[0][1] = ((q * opt_lat[1][2]) + (1.0 - q) * opt_lat[1][1])/r;
        System.out.println("opt_lat[0][1]  = " + opt_lat[0][0] );
        opt_lat[0][0] = ((q * opt_lat[1][1]) + (1.0 - q) * opt_lat[1][0])/r;
        System.out.println("opt_lat[0][0]  = " + opt_lat[0][0] );
        System.out.println();
    }

}
