/*
 *
 */

package com.awolart.fin.irc;


/**
 * <p>
 * </p>
 */
public class Spots
{

    public Spots() {

    }

    /**
     *
     * @param data
     * @return
     */
    public double[] spotFcoupon(double[][] data)
    {
        int n = data.length;
        double[] spots = new double[n];
        double price;
        int s = 0;
        double index = 1.0;
        spots[0] = ((100.0/data[0][0]) - 1);
        price = (data[0][0]/100.0);
        for(s = 1; s < n; ++s)
        {
            index++;
            spots[s] = (Math.exp(1/index * Math.log((data[s][1] + 100.0)
                /(data[s][0] - (data[s][1] * price)))) - 1);
            price += (Math.exp(-index * Math.log(1 + spots[s])));
        }

        return spots;

    }

    /**
     * For period frequency of annual coupons.
     *
     * @param data
     * @param periods
     * @return
     */
    public double[] spotFcoupon(double[][] data, int periods)
    {
        int n = data.length;
        double[] spots = new double[n];
        double price;
        double temp = 0;
        int s = 0;
        double index = 1.0;
        /* First entry */
        spots[0] = ((100.0/data[0][0]) - 1);
        price = (data[0][0]/100.0);

        for(s = 1; s < n; ++s)
        {
            index++;
            spots[s] = (Math.exp(1/index * Math.log(((data[s][1]/periods) + 100.0)
                    /(data[s][0] - ((data[s][1]/
                    periods) * price)))) - 1);
            price += (Math.exp(-index * Math.log(1 + spots[s])));
        }

        return spots;

    }

    /**
     * Returns the nth period coupon for par price given the spot rate.
     *
     * @param spots
     * @param period
     * @return
     */
    public double parCoupon(double[] spots, int period)
    {
        int i = spots.length;
        int j = 0;
        int counter = 0;
        double flow_disc = 0.0;
        double final_disc = 0.0;
        if(period > 1)
        {
            return -1.0;
        }

        final_disc = (1.0 - (Math.exp(-period * Math.log(1.0 + spots[(period - 1)]))));
        for(double d:spots)
        {
           if(j < period)
           {
               j++;
               flow_disc += ((Math.exp(-j * Math.log(1.0 + d))));
           }
        }

        return (final_disc/flow_disc);
    }

}
