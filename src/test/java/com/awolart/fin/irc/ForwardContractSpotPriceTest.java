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
 * <p/>
 * Please submit your answer rounded to two decimal places so for example,
 * if your answer is 567.1234 then you should submit an answer of 567.12.
 * <p/>
 * Forward contracts like the above give the purchaser the right and the
 * obligation to obtain the asset at a specified Time = T for a specified
 * Price = P, which P is set at time t = 0.
 * </p>
 */
public class ForwardContractSpotPriceTest {

    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        /*
         * The interest rate throughout the periods covered, which is equal
         * to a per annum rate of 0.08 (8%) payable in quarterly installments
         * of 0.02 (2%).
         */
        double r = (0.08 / 4);

        double s = 400.0;
        int i = 3;
        double s_3 = ForwardContractValueAtIntermediateTime.forwardPrice(s, r, i);
        System.out.println("s3 forwardPrice = " + formatter.format(s_3));

    }

}
