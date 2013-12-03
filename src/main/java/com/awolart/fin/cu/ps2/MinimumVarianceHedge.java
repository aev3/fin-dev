/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps2;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 2/Question 4: Minimum variance hedge
 * <p/>
 * Suppose a farmer is expecting that her crop of grapefruit will be ready for
 * harvest and sale as 150,000 pounds of grapefruit juice in 3 months time. She
 * would like to use futures to hedge her risk but unfortunately there are no
 * futures contracts on grapefruit juice. Instead she will use orange juice futures.
 * <p/>
 * Suppose each orange juice futures contract is for 15,000 pounds of orange juice
 * and the current futures price is F0=118.65 cents-per-pound. The volatility,
 * i.e. the standard deviation, of the prices of orange juice and grape fruit
 * juice is 20% and 25%, respectively, and the correlation coefficient is 0.7.
 * <p/>
 * What is the approximate number of contracts she should purchase to minimize
 * the variance of her payoff?
 * <p/>
 * Please submit your answer rounded to the nearest integer.
 * So for example, if your calculations result in 10.78 contracts,
 * you should submit an answer of 11.
 * </p>
 */
public class MinimumVarianceHedge {
    // correlation
    static final double P = 0.7;
    // Standard deviation of the spot
    static final double S = 0.25;
    // Standard deviation of the futures
    static final double F = 0.20;
    // Size of position one wants to hedge
    static final double QA = 150000.00;
    // Size of futures
    static final double QF = 150000.00;
    // per contract amount
    static final double C = 15000.00;


    /**
     * <p/>
     * Using the Hull formula from Hull:
     * ``Option, Futures, and other Derivatives'',
     * for calculating the optimal hedge ratio:
     * $ h = \rho \frac{\delta S}{\delta F} $
     * Where $ \rho $ is the correlation,
     * $ \delta S $  is the
     * standard deviation of the spot, and
     * $\delta F $  is the standard
     * deviation of the futures contract.
     * <p/>
     * The optimal number of contracts is calculated as
     * $N = \frac{h \times QA}{QF} $
     * where $ QA $  is the size of the position
     * one wishes to hedge, and $ QF $ 
     * is the size of the futures contract.
     */
    // double correlation, double sdS, double sdF
    public static double hull() {
        double h = ( P * ( S / F) );
        System.out.println("h = " + h);
        double numerator = ( h * QA );
        double denominator = QF;
        double N = ( numerator / denominator );
        System.out.println("N = " + N);
        return ( N * 10 );
    }

    /**
     *
     * Using the formula in the module slides, the minimum-variance hedge
     * is given by
     * $ h∗ = \frac{150000}{15000} \times \frac{cov(S_t^G,S_t^O)}{var(S_t^G)} $
     * or
     * $ 10 \times \frac{\rho \delta G}{\delta O} = 10 \times \frac{(0.7)(0.25)}{0.20}  = 8.75 $
     * @return
     */
    public static double garud() {
        double h = ( QA / C ) * ( P * ( S / F ) );
        double N = ( (h * QA) / QF );
        return N;
    }

    /**
     * Convenience main method to facilitate command line/ide testing.
     *
     * @param args
     */
    public static void main(String[] args) {

        /* Formatting */
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(0);

        //double N = MinimumVarianceHedge.hull();
        double N = MinimumVarianceHedge.garud();
        /* PS2/Q3 Answer formatter.format("N = " + N) */
        //System.out.println("N * 10 = " + N * 10);
        System.out.println("N = " + formatter.format("N = " + N));

    }

}

