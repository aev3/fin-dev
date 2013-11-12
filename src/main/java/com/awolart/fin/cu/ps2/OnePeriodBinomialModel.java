/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps2;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 2/Question 5: Call Options
 * Consider a 1-period binomial model with:
 * <ol>
 * <li>R=1.02</li>
 * <li>S0=100</li>
 * <li>u=1</li>
 * <li>d=1.05</li>
 * <li>K=102</li>
 * </ol>
 * <p/>
 * Compute the value of a European call option on the stock with strike K=102.
 * The stock does not pay dividends.
 * <p/>
 * Please submit your answer rounded to two decimal places.
 * So for example, if your answer is 3.4567 then you should submit an answer of 3.46.
 * </p>
 */
public class OnePeriodBinomialModel {

    private static final double R   = 1.02;
    private static final double S0  = 100.00;
    private static final double D   = 1.05;
    private static final double U   = 1.00;
    private static final double K   = 102.00;


    /**
     *
     S0 = 100
     d = .75
     u = 1.5
     X = 120
     r = 5%

     Is d=1.05 < R=1.02 < u=1.00? NO. Ergo

     1. uS0 = 150, dS0 = 75
        Cu = uS0 – X = 30, Cd = 0
     2. H = ( Cu – Cd ) / ( uS0 – dS0) = 0.4
     3. HdS0 = 30
     4. PV( HdS0 ) = HdS0 / (1 + r ) = 28.57
     5. HS0 = 0.4 ⋅ 100 = 40
        C = HS0 – PV( HdS0 ) = 11.43


     S1=uS_0 with probability p and S1=dS_0 with probability 1−p
     so the answer is (c) ->  E[S_1] = puS_0+(1−p)dS_0

     From answer:
     The arbitrage-free value is C0=1REQ0[C1]=1R[qCu+(1−q)Cd] where q=R−du−d, 1−q=u−Ru−d, Cu=3 and Cd=0.
     Therefore C0=qCu/R=2.0373.

     * @return
     */
    private static double computeValue() {
        //double value = 0.0;
        double uS0 = U * S0;
        double dS0 = D * S0;
        double Cu = uS0 - K;
        double Cd = 0;
        double H = ( ( Cu - Cd ) / ( uS0 - dS0 ) );
        double HdS0 = H * dS0;
        double PV = ( ( HdS0 / ( 1 + R ) ) );
        double HS0 = H * S0;
        double C = HS0 - PV;
        return C;
    }

    public static void main(String[] args) {

        /* Formatting */
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(0);

        double value = computeValue();
        // formatter.format("Value = " + value)
        System.out.println("Value = " + value);

    }
}
