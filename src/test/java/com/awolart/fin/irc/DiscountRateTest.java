/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 1/Question 3: Relation between spot and discount rates
 * Suppose the spot rates for 1 and 2 years are s1=6.3% and s2=6.9%
 * with annual compounding.
 * <p/>
 * What is the discount rate {@latex.inline $ d(0,2) $}?
 * <p/>
 * Submit answer rounded to three decimal places; i.e. 0.1234 would be 0.123.
 * </p>
 */
public class DiscountRateTest {

    /**
     * <p>
     * Method to calculate the discount rate {@latex.inline $ d(0,2) $}
     * where {@latex.inline $ d(0,2) = \\frac{1}{(1 + r)^t} $}
     * </p>
     *
     * @param s double representing the spot interest rate at time {@latex.inline $ t $}
     * @param t double representing the time {@latex.inline $ t $}
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

        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(3);

        double t = 2.0;
        double s1 = 0.063;
        double s2 = 0.069;
        double d_0_2 = DiscountRateTest.discountRate(s2, t);
        System.out.println("d(0,2) = " + formatter.format(d_0_2));

    }

}
