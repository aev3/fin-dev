/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 1/Question 4: Relation between spot and discount rates.
 * Spot rate for year 1=0.063 and for year2=0.069 with annual compounding.
 * What is the discount rate d(0,2)?
 * Submit answer rounded to three decimal places.
 * <p/>
 * For forward rate:
 * Math.pow(1+r2),2) = (1+r1) * (1+f2)
 * where f2 = (Math.pow(1+r2, 2) / (1+r1)) -1;
 * <p/>
 * </p>
 */
public class ForwardRateTest {

    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        double r1 = 0.063;
        double r2 = 0.069;
        double f2_numerator = Math.pow(1 + r2, 2);
        double f2_denominator = (1 + r1);
        double forwardRate = (f2_numerator / f2_denominator) - 1;
        System.out.println("Forward Rate = " + formatter.format(forwardRate * 100));
    }

}
