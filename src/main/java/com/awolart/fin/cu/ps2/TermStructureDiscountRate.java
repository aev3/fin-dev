/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps2;

import java.text.NumberFormat;

/**
 * Suppose the current term structure of interest rates, assuming
 * annual compounding, is as follows:
 * s1	    s2	     s3	    s4	    s5	     s6
 * 7.0% 	7.3%	7.7%	8.1%	8.4%	8.8%
 *
 * What is the discount rate d(0,4)?
 *
 * (Recall that interest rates are always quoted on an annual basis
 * unless stated otherwise.)
 *
 * Please submit your answer rounded to three decimal places;
 * i.e., if your answer is 0.4567 then you should submit 0.457.
 */
public class TermStructureDiscountRate {

    /**
     * <p>
     * Method to calculate the discount rate $ d(0,4) $
     * where $ d(0,4) = \frac{1}{(1 + r)^t} $
     * </p>
     *
     * @param s double representing the spot interest rate at time $ t $
     * @param t double representing the time $ t $
     * @return double containing the calculated discount rate
     */
    public static double discountRate(double s, double t) {
        return ((1 / Math.pow(1 + s, t)));
    }


    /**
     * Convenience main method to facilitate command line/ide testing.
     *
     * @param args
     */
    public static void main(String[] args) {

        /* Formatting */
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(4);

        /* Data setup */
        double[] s = { 0.070, 0.073, 0.077, 0.081, 0.084, 0.088 };
        double d_0_4 = TermStructureDiscountRate.discountRate(s[3], 4);

        /* PS2/Q1 Answer */
        System.out.println("Answer d(0,4) = " + formatter.format(d_0_4));

    }

}
