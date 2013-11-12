/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps2;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 2/Question 3: Hedging using futures
 * <p/>
 * Suppose a farmer is expecting that her crop of oranges will be ready for
 * harvest and sale as 150,000 pounds of orange juice in 3 months time.
 * <p/>
 * Suppose each orange juice futures contract is for 15,000 pounds of orange
 * juice, and the current futures price is F0 = 118.65 cents-per-pound.
 * <p/>
 * Assuming that the farmer has enough cash liquidity to fund any margin calls,
 * what is the risk-free price that she can guarantee herself.
 * <p/>
 * Please submit your answer in cents-per-pound rounded to two decimal places.
 * So for example, if your answer is 123.456, then you should submit an answer
 * of 123.47.
 * </p>
 */
public class SimpleFutures {



    /**
     * Convenience main method to facilitate command line/ide testing.
     *
     * @par
     * am args
     */
    public static void main(String[] args) {

        /* Formatting */
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(4);
        formatter.setMinimumFractionDigits(2);

        /* Data setup */
        double F_0 = 118.65; // cents per pound
        double K = F_0 * 150000;
        double T = 0.25;

        /* PS2/Q3 Answer */
        System.out.println("Answer = " + formatter.format(F_0));

    }

}

