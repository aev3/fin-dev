/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps2;


import java.text.NumberFormat;

/**
 * Suppose a 6-year swap with a notional principal of $10 million is being
 * configured. What is the fixed rate of interest that will make the value
 * of the swap equal to zero.
 * (You should use the term structure of interest rates from Question 1.)
 * <p/>
 * Please submit your answer as a percentage rounded to two decimal places;
 * i.e., if your answer is 4.567% or equivalently 0.04567,
 * then submit answer as 4.57.
 * <p/>
 */
public class FixedRateSwapZero {

    /**
     * <p>
     * Method to calculate the discount rate $ d(0,4) $
     * where $ d(0,4) = \frac{1}{(1 + r)^t} $
     * <p/>
     * More generically, $$ r =  \frac{1 - d(0,T)}{\sum_{t0}^{T} d(0,T)}  $$
     * </p>
     *
     * @param s double representing the spot interest rate at time $ t $
     * @param t double representing the time $ t $
     * @return double containing the calculated discount rate
     */
    public static double discountRate(double s, double t) {
        return ((1 / Math.pow(1 + s, t)));
    }


    public static double getFixedRateDenominator(double time, double num) {
        double X = 0.0;
        int idx = 1;
        for(int i = 0; i < num; ++i) {
            X += discountRate(i, idx);
            ++idx;
        }

        return X;

    }

    private static double discountedFloatingRateCF(double C, double[] i, int n)  {
        double dfr = 0.0;
        // return (C / (Math.pow((1 + i), n)));
        int i_len = i.length;
        for(int j = 0; j < i_len; ++j) {
            double dpv = ( C / ( Math.pow( ( 1 + i[j] ), n) ) );
            System.out.println("discounted pv at " + j + " = " + dpv);
            dfr += dpv;
        }
        System.out.println("DFR = " + dfr);
        return dfr;
    }

    public static double zeroSwap(double[] s, double t) {
        double rate = 0.0;
        int idx = 1;
        int s_len = s.length;
        for(int i = 0; i < s_len; ++i) {
            double dr = ( 1 - discountRate(i, idx) );
            System.out.println(" Discount rate where time is " + i
                    + " and interest rate is " + s[i] + " = " + dr);
            rate += dr;
            ++idx;
        }

        //  * r = 1−d(0,T) / ∑T t=1 d(0,t)

        double Z = ( ( 1 - discountRate(0, s_len+1) ) / rate );
        return rate;
    }

    public static double discountRateIterater(double[] s, double t) {
        int s_len = s.length;
        double total = 0.0;
        for(int i = 1; i <= s_len; ++i)
        {
            double idr = discountRate(s[i], i+1);
            System.out.println(" Discount rate where time is " + i
                    + " and interest rate is " + s[i] + " = " + idr);
            total += idr;
        }
        return total;
    }


        /*
         * 1. sum(all discount factors from 1:6)
         * 2. find the discount factor of T = 6
         * 3. 1 - (discount factor of T=6)
         * 4 divide  step 3/step 1
         */
    public static double sumAllDiscountFactors(double[] s, double t)  {
        double sum = 0.0;
        int s_len = s.length;
        double idx = 1.0;
        for(int i = 1; i < s_len; ++i) {
            double dr = discountRate(s[i], idx);
            sum += dr;
            ++idx;
        }
        return sum;
    }



    /**
     * Convenience main method to facilitate command line/ide testing.
     *
     * @param args
     */
    public static void main(String[] args) {

        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(4);

        double[] s = { 0.070, 0.073, 0.077, 0.081, 0.084, 0.088 };

        /*
        double[] sx = { 0.070, 0.073, 0.077, 0.081, 0.084, 0.088 };

        double C = 10000000.00;
        double d_0_T = ( 1 - (discountRate(s[5], 6) ) );
        System.out.println("d(0,T) = " + formatter.format(d_0_T));
        double sum_fpmts = C * ( d_0_T );
        System.out.println("Discount FR Pmts = " + formatter.format(sum_fpmts));
        */


        // C = notional; i = interest rate; and n = number of payments
        ////discountedFloatingRateCF(C, s, s.length);

        /*
        double Z = zeroSwap(s, s.length);
        System.out.println("zeroSwap = " + formatter.format(Z));
        */

        /*
        double sum =  sumAllDiscountFactors(s, s.length);
        System.out.println("sumAllDiscountFactors = " + formatter.format(sum));
        */

        /*
         * 1. sum(all discount factors from 1:6)
         * 2. find the discount factor of T = 6
         */
        double d1 = discountRate(s[0], 1);
        System.out.println("d1 = " + formatter.format(d1));
        double d2 = discountRate(s[1], 2);
        System.out.println("d2 = " + formatter.format(d2));
        double d3 = discountRate(s[2], 3);
        System.out.println("d3 = " + formatter.format(d3));
        double d4 = discountRate(s[3], 4);
        System.out.println("d4 = " + formatter.format(d4));
        double d5 = discountRate(s[4], 5);
        System.out.println("d5 = " + formatter.format(d5));
        double d6 = discountRate(s[5], 6);
        System.out.println("d6 = " + formatter.format(d6));
        double sum1 =  d1 + d2 + d3 + d4 + d5 + d6;
        System.out.println("sum d(0,1) to d(5,6) = " + formatter.format(sum1));

       /*
         * 3. 1 - (discount factor of T=6)
         * 4 divide  step 3/step 1
         */
        double discounted = 1 - d6;
        System.out.println("1 - d(1,6) = " + formatter.format(discounted));
        double answer = discounted / sum1;
        System.out.println("Answer = " + formatter.format(answer * 100));  //
    }

}
