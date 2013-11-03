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
 * What is the discount rate d(0,2)?
 * Submit answer rounded to three decimal places; i.e. 0.1234 would be 0.123.
 * </p>
 */
public class DiscountRateTest {


    public static double discountRate(double spot, double time) {
        return ((1 / (Math.pow((1 + spot), time))));
    }

    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(4);
        formatter.setMinimumFractionDigits(4);
        double time = 2.0;
        double spot1 = 0.063;
        double spot2 = 0.069;
        double discount = DiscountRateTest.discountRate(spot2, time);
        System.out.println("Discount rate = " + formatter.format(discount));

    }

}
