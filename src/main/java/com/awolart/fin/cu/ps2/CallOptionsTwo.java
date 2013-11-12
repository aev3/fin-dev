/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps2;

import java.text.NumberFormat;

/**
 * <p>
 * Problem Set 2/Question 6: Call Options II
 * When you construct the replicating portfolio for the option in the
 * previous question how many dollars do you need to invest in the cash
 * account?
 * <p/>
 * Please submit your answer rounded to three decimal places.
 * So for example, if your answer is −43.4567,
 * then you should submit an answer of −43.457.
 * </p>
 */
public class CallOptionsTwo {

    /*
        Using R:
        > A<-matrix(c(105,100/1.05,1.02,1.02),2,2)
        > b<-c(3,0)
        > solve(A,b)
            [1]   0.3073171 -28.6944046

        Using slides:
        We need to solve two equations in two unknowns as described in the lecture slides:
            uS0x+RydS0x+Ry==CuCd
        which yields a solution of x=.3073 and y=−28.694.
        The answer is therefore y=−28.694.

     */
    public static void main(String[] args) {

        /* Formatting */
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(0);

        // double value = computeValue();
        // formatter.format("Value = " + value)
        System.out.println("Value = " + (-28.694));

    }
}
