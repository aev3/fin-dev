/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 1/Question 7: Value of a Forward contract at an intermediate
 * time.
 * <p/>
 * Suppose we hold a forward contract on a stock with expiration 6 months
 * from now. We entered into this contract 6 months ago so that when we
 * entered into the contract, the expiration was T=1 year. The stock price
 * 6 months ago was S0=100, the current stock price is 125 and the current
 * interest rate is r=10% compounded semi-annually.
 * (This is the same rate that prevailed 6 months ago.)
 * <p/>
 * What is the current value of our forward contract?
 * <p/>
 * Please submit your answer in dollars rounded to one decimal place; i.e.,
 * if your answer is 42.678 then you should submit 42.7.
 * </p>
 */
public class Intermediation {



    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(3);

        double spot_price_0 = ((1 / (1 + .10)));
        System.out.println("Spot price at t0 = " + formatter.format((spot_price_0 * 100)));
        double spot_price_t = ((1 / (1 + .05)));
        System.out.println("Spot price at t1 = " + formatter.format((spot_price_t * 100)));
        double spot_price_T = ((1 /1));
        System.out.println("Spot price at T = " + formatter.format((spot_price_T * 100)));

        double time = 0.5;
        double spot1 = 0.10;
        double spot2 = 0.10;
        double discount = DiscountRateTest.discountRate(spot2, time);
        System.out.println("Discount rate = " + formatter.format(discount));
        System.out.println("Discounted value = " + formatter.format((spot_price_0 * 100) / discount));

        int installments = 2;
        double price = 100.00;
        double annual_interest_rate = 0.10;
        double interest_periods_per_annum = 2.0;
        double interest_rate_per_period =
                (annual_interest_rate / interest_periods_per_annum);

        double spot = ForwardContractSpotPriceTest.Spot(interest_rate_per_period, installments);

        System.out.println("Spot forward = " + formatter.format(spot));
        System.out.println("Spot forward price = " + formatter.format(price / spot));

        double r = interest_rate_per_period;
        // Vt = ( spot rate at t [St] ) - exp to -r(T - t) * F
        double Vt = ( ( spot_price_t * 125 ) - Math.exp(r * 0.5) * ( spot_price_0 * 100 ) );
        System.out.println("Vt = " + formatter.format(Vt));

        // F = spot price of asset * e to (T-t)*r
        double F = ( ( spot_price_t * 100 ) - Math.exp(0.5*0.1) );
        System.out.println("F = " + formatter.format(F));

        // f = spot price of asset - k * e to (T-t)*r
        double f = ( ( spot_price_0 * 100 ) - Math.exp(0.5*0.1) );
        System.out.println("F = " + formatter.format(f));

    }

}
