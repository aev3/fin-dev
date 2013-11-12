/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps3;

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
public class PS3Consts {
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
    public static final double r    = 0.02;
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
    public static final double u    = 1.0395;

    /**
     * Default no argument constructor made private to bar instantiation
     */
    private PS3Consts() { /* No instantiation, no calls. */ }
}
