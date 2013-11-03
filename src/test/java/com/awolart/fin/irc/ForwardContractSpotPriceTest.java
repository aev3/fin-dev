/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 1/Question 5: Forward contract on a stock:
 * The current price of a stock is $400 per share and it pays no dividends.
 * Assuming a constant interest rate of 8% per year compounded quarterly,
 * what is the stock's theoretical forward price for delivery in 9 months?
 * Please submit your answer rounded to two decimal places so for example,
 * if your answer is 567.1234 then you should submit an answer of 567.12.
 * <p/>
 * Forward contracts like the above gives the purchaser the right and the
 * obligation to obtain the asset at a specified Time = T for a specified
 * Price = P, which P is seat at time t = 0.
 * </p>
 */
public class ForwardContractSpotPriceTest {

    /**
     * Simple calculation of spot price on a forward contract.
     *
     * @param interest_rate_per_period
     * @param installments
     * @return
     */
    public static double Spot(double interest_rate_per_period, int installments) {
        return (1 / Math.pow((1 + interest_rate_per_period), installments));
    }

    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        int installments = 3;
        double price = 400.00;
        double annual_interest_rate = 0.08;
        double interest_periods_per_annum = 4.0;
        double interest_rate_per_period =
                (annual_interest_rate / interest_periods_per_annum);

        double spot = ForwardContractSpotPriceTest.Spot(interest_rate_per_period, installments);

        System.out.println("Spot = " + formatter.format(spot));
        System.out.println("Spot price = " + formatter.format(price / spot));
    }

}
