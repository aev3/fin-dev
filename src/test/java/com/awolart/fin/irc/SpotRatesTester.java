package com.awolart.fin.irc;

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
         Spots spots = new Spots();
         //Volatility vol = new Volatility(100.0,2.0);
         double[][] data =
         {
                 {0.5,1.03},
                 {1.0,1.28},
                 {2.0,1.83},
                 {3.0,2.36}
         };

         int j = 0;
         double mat = 0.5;
         //double firstpoint = structure.
     }
}
