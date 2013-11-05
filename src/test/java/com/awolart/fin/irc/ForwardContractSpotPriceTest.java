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
     * @param installment
     * @return
     */
    public static double Spot(double interest_rate_per_period, int installment) {
        return (1 / Math.pow( ( 1 + interest_rate_per_period ), installment ) );
    }

    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        /* PS1/Q5 Answer  */
        int installments = 3;
        double price = 400.00;
        double annual_interest_rate = 0.08;
        double interest_periods_per_annum = 4.0;
        double interest_rate_per_period =
                (annual_interest_rate / interest_periods_per_annum);

        double spot = ForwardContractSpotPriceTest.Spot(interest_rate_per_period, installments);

        System.out.println("Spot = " + formatter.format(spot));
        System.out.println("Spot price = " + formatter.format(price / spot));


        /*
         * <p/>
         * Suppose we hold a forward contract on a stock with expiration 6 months
         * from now. We entered into this contract 6 months ago so that when we
         * entered into the contract, the expiration was T=1 year. The stock price
         * 6 months ago was S0=100, the current stock price is 125 and the current
         * interest rate is r=10% compounded semi-annually.
         * (This is the same rate that prevailed 6 months ago.)
         * <p/>
         * What is the current value of our forward contract?
         *
         * ft=(Ft−F0)d(t,T)
         * =(St/d(t,T)−S0/d(0,T))d(t,T)
         * =St−S0∗(d(t,T)/d(0,T))
         * =St−S0/d(0,t)
         */

        double f0 = 100;
        double ft = 125;

        double i_s0 = 0.10;
        double i_st = 0.05;
        double time_t = 0.5;
        int inst_0 = 1;
        int inst_t = 2;
        double st = ForwardContractSpotPriceTest.Spot(i_st, inst_t);
        double s0 = ForwardContractSpotPriceTest.Spot(i_s0, inst_0);
        double s0_price = ( 100 / s0 );
        double st_price = ( 125 / st );
        double ans = ( ( st_price - s0_price ) / ( 1 / (Math.pow(( 1 + st ), time_t ) ) ) );
        System.out.println("s0 price = " + formatter.format(s0_price));
        System.out.println("st price = " + formatter.format(st_price));
        System.out.println("ans = " + formatter.format(ans));
        //Spots spots = new Spots();

        /*
        The forward price F0 at the time we entered into the forward contract is given by
        F0=S0/d(0,T)=S0(1+r/2)2=110.25.

        The forward price Ft at the current time t for a forward contract with expiration 6 months is given by
        Ft=St/d(t,T)=St(1+r/2)=131.25.

        Therefore, the value is ft=(Ft−F0)d(t,T)=(131.25−110.25)/(1+r/2)=20.
        */

    }

}
