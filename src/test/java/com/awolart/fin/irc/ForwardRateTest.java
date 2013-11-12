/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 1/Question 4: Relation between spot and forward rates.
 * Suppose the spot rates for 1 and 2 years are s1 = 6.3% and s2 = 6.9% with annual compounding.
 * <p/>
 * What is the forward rate, {@latex.inline $ f(1,2) $} assuming annual compounding?
 * <p/>
 * Submit answer rounded to three decimal places; i.e. 0.1234 would be 0.123.
 * </p>
 */
public class ForwardRateTest {

    /**
     * <p>
     * Method to calculate the forward rate {@latex.inline $ f(1,2) $}
     * where {@latex.inline $ f(1,2) = \\frac{ (1 + s_2)^t }{ 1 + s_1 } - 1 $}
     * </p>
     *
     * @param s_1 double representing the spot interest rate {@latex.inline $ s_1 $} at time {@latex.inline $ t $}
     * @param s_2 double representing the spot interest rate {@latex.inline $ s_2 $} at time {@latex.inline $ t $}
     * @param t  double representing the time {@latex.inline $ t $}
     * @return double containing the calculated forward rate
     */
    public static double forwardRate(double s_1, double s_2, double t) {
        return ((Math.pow(1 + s_2, t) / (1 + s_1)) - 1);
    }

    /**
     * Convenience main method to facilitate command line/ide testing.
     *
     * @param args
     */
    public static void main(String[] args) {

        /* Formatting setup */
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(3);

        /* Data setup */
        double s_1 = 0.063;
        double s_2 = 0.069;
        double t = 2.0;

        /* Calculate and display */
        double f_1_2 = ForwardRateTest.forwardRate(s_1, s_2, t) * 100;
        System.out.println("f(1,2) = " + formatter.format(f_1_2));
    }

}
