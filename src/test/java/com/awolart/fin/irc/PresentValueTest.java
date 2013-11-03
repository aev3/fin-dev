/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * <p/>
 * </p>
 */
public class PresentValueTest {

    /**
     * Problem Set 1/Question 1: Lottery payments
     * A major lottery advertises that it pays the winner $10 million. However
     * this prize money is paid at the rate of $500,000 each year (with the
     * first payment being immediate) for a total of 20 payments. What is the
     * present value of this prize at 10% interest compounded annually? Report
     * your answer in $millions, rounded to two decimal places. So, for example,
     * if you compute the answer to be 5.7124 million dollars then you should
     * submit an answer of 5.71.
     */

    private static final int DEFAULT_INST = 20;
    private static final double DEFAULT_PMT = 500000.00;
    private static final double DEFAULT_RATE = 0.10;

    /**
     * A simple formula for calculating the present value (PV) of any future
     * value (FV) received 't' years from now, assuming that the interest
     * rate is 'r'.
     *
     * @param inst
     * @param pmt
     * @param rate
     * @return
     */
    public static double LotteryNPV(final int inst, final double pmt, final double rate) {
        double PV = 0.00;
        for (int i = 0; i < inst; ++i) {
            double fv = pmt / Math.pow(1 + rate, i);
            PV += fv;
        }

        return PV;
    }


    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        double LPV = PresentValueTest.LotteryNPV(DEFAULT_INST, DEFAULT_PMT, DEFAULT_RATE);
        System.out.println("Present value of lottery = " + formatter.format(LPV));
    }

}
