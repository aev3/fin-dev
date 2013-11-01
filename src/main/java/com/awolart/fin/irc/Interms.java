/*
 *
 */

package com.awolart.fin.irc;

/**
 * <p>
 * </p>
 */
public abstract class Interms {

    public abstract void intermtimes();

    public Interms() {

    }

    public double DiscFromYield(double spotrate, double time)
    {
        return Math.exp(-spotrate * time);
    }

    public double YieldFromDisc(double discount, double time)
    {
          return -Math.log(discount/time);
    }

    public double RateFromSpots(double spot1, double spot2)
    {
        return  (Math.pow((1+spot2),2) / (1 + spot1) -1);
    }

    public double RateFromDisc(double discount1, double discount2,
                               double time1, double time2)
    {
        return (Math.log(discount1/discount2) / (time2 - time1) );
    }

    public double RateFromYield(double r1, double r2, double t1, double t2)
    {
        return (r2 * (t2 / (t2 - t1) ) ) - (r1 * (t1 / (t2 - t1) ) );
    }
}
