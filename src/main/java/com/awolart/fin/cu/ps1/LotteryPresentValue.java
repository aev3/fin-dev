/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps1;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * <p>
 * A major lottery advertises that it pays the winner $10 million. However
 * this prize money is paid at the rate of $500,000 each year (with the
 * first payment being immediate) for a total of 20 payments. What is the
 * present value of this prize at 10% interest compounded annually?
 * <p/>
 * Report your answer in $millions, rounded to two decimal places.
 * So, for example, if you compute the answer to be 5.7124 million dollars,
 * then you should submit an answer of 5.71.
 * <p/>
 */
public class LotteryPresentValue
{

    /**
     * <p>
     * Here's a simple formula for calculating the present value (PV) of any
     * future value (FV) received t years from now, assuming that the interest
     * rate is r. The calculation of the present value, PV, of a future stream
     * of payments, each a p, would be
     * $$ PV = \frac{p}{1 + r} \times t $$
     * <p/>
     * To find out to find out the present value of Joe's lottery winnings
     * before taxes, have your students calculate the rest of the values in the
     * first column, then sum the total. To find out to find out the present
     * value of Joe's lottery winnings before taxes, have your students
     * calculate the rest of the values in the first column, then sum the total,
     * to wit:
     * PV = $100,000/(1.07)2 = $87,344
     *
     * @param C - double representing the payment amount
     * @param r - double representing the interest rate
     * @param n - int representing the number of payments
     * @return
     */
    public static final double pV(final double C, final double r, final int n)
    {
        double PV =  0.00;
        for(int i = 0; i < n; ++i)
        {
            double fv = C / Math.pow(1 + r, i);
            PV += fv;
        }
        return PV;
    }


    public static void main(String[] args)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        double C = 500000.00;
        double r = 0.10;
        int n = 20;

        double pV =  LotteryPresentValue.pV(C, r, n);

        System.out.println("pV = " + formatter.format(pV));

        /*
         * PV =  PMT * ( ( (1 - Math.pow( 1 + RATE, -INST ) )  / RATE ) );
         *
         * C = Cash flow per period
         * i = Interest rate
         * n = Number of payments
         *
         * This calculates the present value of an ordinary annuity.
         * To calculate the present value of an annuity due, multiply
         * the result by ( 1 + i ). (The payments of an annuity due
         * start at time zero instead of one period into the future.)
         */

        double PV =  ( C * ( ( (1 - Math.pow( 1 + r, -n ) )  / r ) ) ) * (1 + r);
        System.out.println("PV = " + formatter.format(PV));

    }

}
