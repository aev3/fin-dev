/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.util;

/**
 * <p>
 * Each of the questions in this Problem Set should be answered by building
 * a 15-period binomial model whose parameters should be calibrated to a
 * Black-Scholes geometric Brownian motion model with:
 *  <ol>
 *     <li>T = 0.25 years</li>
 *     <li>S0 = 100.00</li>
 *     <li>r = 0.02</li>
 *     <li>d = 0.30</li>
 *     <li>c = 0.01</li>
 *     <li>u = 1.0395</li>
 *  </ol>
 * </p>
 */
public class PS3Consts
{
    /**
     * Constant value for number of years.
     */
    public static final double T    = 0.25;
    /**
     * Constant value for S0
     */
    public static final double S0   = 100.00;
    /**
     * Constant value for rate
     */
    public static final double r    = 0.1194; // 0.02;
    /**
     * Constant value for delta
     */
    public static final double d    = 0.30;
    /**
     * Constant value for cash dividend yield
     */
    public static final double c    = 0.01;
    /**
     * Constant value for u
     */
    public static final double u    = 1.03775; //= 1.0395;

    /**
     * Default no argument constructor made private to bar instantiation
     */
    private PS3Consts() { /* No instantiation, no calls. */ }

    public static final int EoC_ROWS    = 4;
    public static final int EoC_COLS    = 4;
    public static final double EoC_T    = 0.25;
    public static final double EoC_S0   = 100.00;
    public static final double EoC_r    = 1.01;
    public static final double EoC_c    = 0.01;
    public static final double EoC_UP   = 1.07;
    public static final double EoC_d    = 0.93458;
    public static final double EoC_q    = 0.5570;
    public static final double EoC_STR  = 100.00;
    public static final double EoC_YRS  = 0.25;


    /*
     *  A 15-period binomial model whose parameters should be calibrated
     *  to a Black-Scholes geometric Brownian motion model with:
     *  T=.25 years,
     *  S0=100,
     *  r=2%,
     *  σ=30%, and
     *  dividend yield c=0.01
     *  u=1.0395
     */
    public static final int APO_15_ROWS     = 16;
    public static final int APO_15_COLS     = 16;
    public static final double APO_15_S0    = 100.00;
    public static final double APO_15_R     = 0.02;
    public static final double APO_15_U     = 1.03950034625931;
    public static final double APO_15_D     = 1/APO_15_U;
    public static final double APO_15_Q     = 0.5570;
    public static final double APO_15_K     = 110.00;
    public static final double APO_15_C     = 0.01;
    public static final double APO_15_sigma = 0.30;
    public static final double APO_15_T     = 0.25;
    /*
    public static final double APO_15_sigma = 0.30;
    public static final double APO_15_U     = Math.exp(APO_15_sigma * Math.sqrt(T/(APO_15_R-1)));
    public static final double APO_15_D     = 1/APO_15_U;
    */

    public static final int APO_4_ROWS      = 4;
    public static final int APO_4_COLS      = 4;
    public static final double APO_4_S0     = 100.00;
    public static final double APO_4_R      = 1.01;
    public static final double APO_4_U      = 1.0700;
    public static final double APO_4_D      = 1/APO_4_U;
    public static final double APO_4_Q      = 0.5570;
    public static final double APO_4_STR    = 100.00;
    public static final double APO_4_C      = 0.00;
    public static final double APO_4_sigma  = 0.25;
    public static final double APO_4_T      = 0.25;


    public static final int ECO_4_ROWS      = 4;
    public static final int ECO_4_COLS      = 4;
    public static final double ECO_4_S0     = 100.00;
    public static final double ECO_4_R      = 1.01;
    public static final double ECO_4_U      = 1.0700;
    public static final double ECO_4_D      = 1/ECO_4_U;
    public static final double ECO_4_Q      = 0.5570;
    public static final double ECO_4_STR    = 100.00;
    public static final double ECO_4_C      = 0.00;
    public static final double ECO_4_sigma  = 0.25;
    public static final double ECO_4_T      = 0.25;

    public static final int ECO_10_ROWS      = 11;
    public static final int ECO_10_COLS      = 11;
    public static final double ECO_10_S0     = 100.00;
    public static final double ECO_10_R      = 1.1194;
    public static final double ECO_10_U      = 1.03775396831015;
    public static final double ECO_10_D      = 1/ECO_10_U;
    public static final double ECO_10_Q      = 0.53106460663028;
    public static final double ECO_10_STR    = 100.00;
    public static final double ECO_10_C      = 0.00;
    public static final double ECO_10_sigma  = 0.25;
    public static final double ECO_10_T      = 0.25;

}
