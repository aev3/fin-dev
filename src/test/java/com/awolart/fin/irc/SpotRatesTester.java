/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import com.awolart.fin.bonds.Volatility;
import com.awolart.fin.core.math.Interpolate;

import java.text.NumberFormat;

/**
 *
 */
public class SpotRatesTester {

    public SpotRatesTester() {

    }
     public static void main(String[] args) {
         NumberFormat formatter = NumberFormat.getNumberInstance();
         formatter.setMaximumFractionDigits(3);
         formatter.setMinimumFractionDigits(2);

         IntermsStructure structure = new IntermsStructure();
         Interpolate interpolate = new Interpolate();

         Spots spots = new Spots();
         Volatility vol = new Volatility(100.0, 2.0);
         double[][] data =
         {
                 {0.5,1.03},
                 {1.0,1.28},
                 {2.0,1.83},
                 {3.0,2.36}
         };

         int j = 0;
         double mat = 0.5;
         double firstpoint = interpolate.lagrange(data, 1.5);
         double secondpoint = interpolate.lagrange(data, 2.5);
         double[] yields = {1.03, 1.28, firstpoint, 1.83, secondpoint, 2.36};
         double[] coupons = {0.0, 0.0, (firstpoint - 0.4), 1.53,
                 (secondpoint - 0.3), 2.0};
         double[][] pcdata = new double[6][2];
         for(double yld : yields)
         {
             pcdata[j][0] = vol.Bpricing(yld, mat, coupons[j]);
             pcdata[j][1] = coupons[j];
             mat += 0.5;
             j++;
         }

         int n = 0;
         double[] ansx = spots.spotFcoupon(pcdata, 2);
         for( double x : ansx)
         {
             System.out.println(
                     "Spot Rate: " + formatter.format((200.0*x))
                     + " for price = " + formatter.format(pcdata[n][0])
                     + " and Coupon = " + formatter.format(pcdata[n][1])
                     + " and Yield = " + formatter.format(yields[n]));
             n++;
         }
     }
}
