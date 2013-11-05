/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * This class answers Problem Set 1/Question 7:
 * "Value of a Forward contract at an intermediate time"
 * </p>
 */
public class ForwardContractValueAtIntermediateTime {

    /**
     * The forward price {@latex.inline $ F_0 $}  at the time we entered into the forward contract
     * is given by {@latex.inline $ F_0 = \\frac{S_0}{d(0,T)} = S_0(1+r)^i $}
     * <p/>
     * The forward price {@latex.inline $ F_t $} at the current time {@latex.inline $ t $} for a forward contract with
     * expiration 6 months is given by {@latex.inline $ F_t = \\frac{S_t}{d(t,T)} = S_t(1+r)^i $}
     * <p/>
     *
     *
     * @param s double representing the price of the asset at time X
     * @param r double representing the effective interest rate at time X
     * @param i int representing the installment relating to time X
     * @return double representing the forward price of the asset at time X
     */
    public static double forwardPrice(double s, double r, int i) {
        return (s * (Math.pow((1 + r), i)));
    }

    /**
     * Convenience Command Line method for testing purposes.
     *
     * @param args
     */
    public static void main(String[] args) {

        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        /*
         * The interest rate throughout the periods covered, which is equal
         * to a per annum rate of 0.10 (10%) payable in bi-annual installments
         * of 0.05 (5%).
         */
        double r = (0.10 / 2);

        double s = 100.0;
        int i = 2;
        double s_0 = forwardPrice(s, r, i);
        System.out.println("s0 price = " + formatter.format(s_0));

        s = 125.00;
        i = 1;
        double s_t = forwardPrice(s, r, i);
        System.out.println("st price = " + formatter.format(s_t));

        /*
         * Calculate the difference between the asset price at s_t and the
         * asset price at s_0 and divide by 1 plus the interest rate per
         * installment.
         */
        System.out.println("value of contract at s_t = "
                + formatter.format((s_t - s_0) / (1 + r)));

    }


}
