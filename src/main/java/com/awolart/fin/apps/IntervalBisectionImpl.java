/*
 *
 */

package com.awolart.fin.apps;

import com.awolart.fin.core.IntervalBisection;

/**
 * <p>
 * </p>
 */
public class IntervalBisectionImpl extends IntervalBisection
{

    protected double high;
    protected double low;
    protected double nominal_price;
    protected double market_price;
    protected double maturity_period;
    protected double term_period;
    protected double coupon_rate;
    protected double rate_per_term;
    protected double rate_index;


    public IntervalBisectionImpl()
    { /* Default no argument constructor */ }

    public IntervalBisectionImpl(int iters, double prec, double hi, double lo)
    {
        super(iters, prec);
        high = hi;
        low = lo;
    }

    public double computeFunction(double rootInput)
    {
        double positive_cash_flow = rate_per_term;
        double solution = (positive_cash_flow/rootInput*(1.0-
                1.0/(Math.pow(1.0 + rootInput, rate_index))))+(nominal_price/
                (Math.pow(1.0 + rootInput, rate_index)))-market_price;
        return solution;
    }

    public double yield(double nom_price, double term, double coupon, double mkt_price, double period)
    {
        this.nominal_price = nom_price;
        this.term_period = term;
        this.coupon_rate = coupon;
        this.market_price = mkt_price;
        this.rate_per_term = ((coupon/term));
        this.maturity_period = period;
        this.rate_index = (maturity_period*term);
        return evaluateRoot(low, high);
    }

    public static void main(String[] args)
    {
        int iterations = 16;         // number of iterations
        double precision = 1e-6;     // the optimal precision
        double yld_high = 0.07;      // 7% yield
        double yld_low = 0.03;       // 3% yield

        IntervalBisectionImpl calc =
                new IntervalBisectionImpl(iterations, precision, yld_high, yld_low);

        double nom_prc = 100.0;      // nominal price of investment
        double term = 2.0;           // investment term
        double coupon_rate = 10.0;   // 10% coupon rate per annum
        double mkt_prc = 104.5;      // market price
        double maturity = 3.0;       // maturity in years

        double yield = calc.yield(nom_prc, term, coupon_rate, mkt_prc, maturity);

        System.out.println("The required yield is = " + yield);
    }
}
