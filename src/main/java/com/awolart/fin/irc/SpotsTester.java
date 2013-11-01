/*
 *
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * </p>
 */
public class SpotsTester
{

    public SpotsTester() {
    }

    public static void main(String[] args)
    {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        Spots spots = new Spots();
        double[][] data =
        {
                {98.736,0.0},
                {99.908,1.78},
                {99.735,2.26},
                {99.908,3.16},
                {99.822,3.67},
                {99.675,4.14},
                {99.998,0.0},
                {98.759,4.92}
        };
        double[] answer = spots.spotFcoupon(data);
        for(double rate : answer)
        {
            System.out.println("The spot rate is = " + rate);
        }
    }

}
