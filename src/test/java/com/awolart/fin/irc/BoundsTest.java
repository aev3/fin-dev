/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 1/Question 6: BoundsTest using different lending and borrowing rate
 * Suppose the borrowing rate BR = 10% compounded annually.
 * However, the lending rate (or equivalently, the interest rate on deposits)
 * is only 8% compounded annually.
 * Compute the difference between the upper and lower bounds on the price of
 * a perpetuity that pays A = 10,000$ per year.
 * Submit your answer rounded to the nearest dollar; i.e., if your answer is
 * 23,456.789 then you should submit an answer of 23457.
 * </p>
 */
public class BoundsTest {

    /**
     * Simplified analysis of bounded investments = A/r.
     *
     * @param A - The annuity payment amount
     * @param r - The interest rate
     * @return double containing the limits applicable under the formula.
     */
    public static double Bound(double A, double r) {
        return (A / r);
    }


    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        double borrow_rate = 0.10;
        double lend_rate = 0.08;
        double annual_pmt = 10000.00;

        double Lower = BoundsTest.Bound(annual_pmt, borrow_rate);
        double Upper = BoundsTest.Bound(annual_pmt, lend_rate);
        System.out.println("Delta = " + formatter.format(Upper - Lower));

    }

}
