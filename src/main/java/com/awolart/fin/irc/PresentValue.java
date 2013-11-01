/*
 *
 */

package com.awolart.fin.irc;

/**
 * <p>
 * </p>
 */
public class PresentValue
{
    /**
     * Create a new instance of the class PresentValue using the default no
     * argument constructor
     */
    public PresentValue()  {
    }

    /**
     *
     * @param discounts
     * @param cashflows
     * @return
     */
    public double pV(double[] discounts, double[] cashflows)
    {
        int cf_len = cashflows.length;
        double present_value = 0;
        for(int i = 0; i < cf_len; ++i)
        {
            /*
             * Aggregates the sum of the discounted values for each
             * matching cashflow period.
             */
            present_value += discounts[i] * cashflows[i];
        }

        return present_value;
    }

    /**
     *
     * @param r
     * @param cashflows
     * @return
     */
    public double pV(double r, double[] cashflows)
    {
        int index = 1;
        double sum = 0.0;
        int cf_len = cashflows.length;
        for(int i =0; i < cf_len; ++i)
        {
            sum += ( cashflows[i] / (Math.pow((1 + r),(index))) );
            index++;
        }

        return sum;
    }

    /**
     *
     * @param r
     * @param cash
     * @param period
     * @return
     */
    public double pV(double r, double cash, int period)
    {
        double sum = 0.0;
        int index = 1;
        for(int i = 0; i < period; ++i)
        {
            sum += ( cash / (Math.pow((1 + r),(index))) );
            index++;
        }

        return sum;

    }

}
