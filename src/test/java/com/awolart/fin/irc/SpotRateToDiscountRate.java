/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import com.awolart.fin.core.math.Interpolate;

/**
 * <p>
 *     Question 3: Relation between spot and discount rates.
 *     Spot rate for year 1=0.063 and for year2=0.069 with annual compounding.
 *     What is the discount rate d(0,2)?
 *     Submit answer rounded to three decimal places.
 *
 *     For forward rate:
 *     Math.pow(1+r2),2) = (1+r1) * (1+f2)
 *     where f2 = (Math.pow(1+r2, 2) / (1+r1)) -1;
 *
 * </p>
 */
public class SpotRateToDiscountRate {

    public static void main(String[] args)
    {
        IntermsStructure structure = new IntermsStructure();
        double[][] current_rate_data =
        {
                {1.0,0.063} ,
                {2.0,0.069}
        };
        double r1 = 0.063;
        double r2 = 0.069;
        //double r1 = 0.07;
        //double r2 = 0.12;
        double f2_numerator     = Math.pow(1 + r2, 2);
        double f2_denominator   = (1 + r1);
        double  forwardRate = (f2_numerator / f2_denominator) - 1;
        System.out.println("Forward Rate = " + forwardRate);
        structure.setCurrentRateData(current_rate_data);
        System.out.println("Discount Rate One = " + structure.getCurrentDiscOne(r1 + 1));
        System.out.println("Discount Rate Two = " + structure.getCurrentDiscOne(r2 + 1));

    }

}
