/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps4;

import java.util.Properties;

/**
 * A simple class that holds the constant values that will be used by Questions
 * in Problem Set 4.
 */
public class PS4Consts {

    /*
     * Build an n=10-period binomial model for the short-rate, ri,j.
     * The lattice parameters are:
     * rows = 10,
     * sols = 10
     * r0,0 = 5%,
     * u = 1.1,
     * d = 0.9, and
     * q=1−q=1/2
     */
    public static Properties Q1_PROPS = new Properties();
    static {
        Q1_PROPS.setProperty("rows", "11");
        Q1_PROPS.setProperty("cols", "11");
        Q1_PROPS.setProperty("rate", "0.05");
        Q1_PROPS.setProperty("up", "1.1");
        Q1_PROPS.setProperty("down", "0.9");
        Q1_PROPS.setProperty("q", "0.50");
        Q1_PROPS.setProperty("fv", "100.00");
    }

    public static Properties Q2_PROPS = new Properties();
    static {
        Q2_PROPS.setProperty("rows", "5");
        Q2_PROPS.setProperty("cols", "5");
        Q2_PROPS.setProperty("rate", "0.05");
        Q2_PROPS.setProperty("up", "1.1");
        Q2_PROPS.setProperty("down", "0.9");
        Q2_PROPS.setProperty("q", "0.50");
        Q2_PROPS.setProperty("fv", "100.00");
    }

    public static Properties Q3_PROPS = new Properties();
    static {
        Q3_PROPS.setProperty("rows", "11");
        Q3_PROPS.setProperty("cols", "11");
        Q3_PROPS.setProperty("rate", "0.05");
        Q3_PROPS.setProperty("up", "1.1");
        Q3_PROPS.setProperty("down", "0.9");
        Q3_PROPS.setProperty("q", "0.50");
        Q3_PROPS.setProperty("fv", "100.00");
    }

    public static Properties Q4_PROPS = new Properties();
    static {
        Q4_PROPS.setProperty("rows", "7");
        Q4_PROPS.setProperty("cols", "7");
        Q4_PROPS.setProperty("q", "0.50");
        Q4_PROPS.setProperty("strike", "80.00");
    }

    /**
     * Question 5:
     * A forward-starting swap that
     * begins at t=1
     * matures at t=10
     * fixed rate of 4.5%.
     * First payment takes place at t=2.
     * Final payment takes place at t=11.
     * Assuming, as usual, that payments take place in arrears.
     * You should assume a swap notional of 1 million
     * and assume that you receive floating and pay fixed.
     */
    public static Properties Q5_PROPS = new Properties();
    static {
        Q5_PROPS.setProperty("rows", "11");
        Q5_PROPS.setProperty("cols", "11");
        Q5_PROPS.setProperty("rate", "0.05");
        Q5_PROPS.setProperty("up", "1.1");
        Q5_PROPS.setProperty("down", "0.9");
        Q5_PROPS.setProperty("q", "0.50");
        Q5_PROPS.setProperty("fv", "100.00");
        Q5_PROPS.setProperty("fixed_rate", "0.045");
    }

    /**
     * Question 6:
     * Compute the initial price of a swaption that
     * matures at time t=5 and
     * has a strike of 0.
     * The underlying swap is the same swap as described in Question 5
     * with a notional of 1 million.
     * To be clear, you should assume that if the swaption is exercised
     * at t=5 then the owner of the swaption will receive all cash-flows
     * from the underlying swap from times t=6 to t=11 inclusive.
     * The swaption strike of 0 should also not be confused with the
     * fixed rate of 4.5% on the underlying swap.
     */
    public static Properties Q6_PROPS = new Properties();
    static {
        Q6_PROPS.setProperty("rows", "11");
        Q6_PROPS.setProperty("cols", "11");
        Q6_PROPS.setProperty("rate", "0.05");
        Q6_PROPS.setProperty("up", "1.1");
        Q6_PROPS.setProperty("down", "0.9");
        Q6_PROPS.setProperty("q", "0.50");
        Q6_PROPS.setProperty("fv", "100.00");
        Q6_PROPS.setProperty("fixed_rate", "0.045");
    }


    public static void main(String[] args)
    {
        Integer rows1 = Integer.parseInt(Q1_PROPS.getProperty("rows"));
        System.out.println("ROWS = " + rows1 );
        Integer cols1 = Integer.parseInt(Q1_PROPS.getProperty("cols"));
        System.out.println("COLS = " + cols1);
        Double q1 = Double.parseDouble(Q1_PROPS.getProperty("q"));
        System.out.println("Q = " + q1 );
        Double q_less1 = 1-q1;
        System.out.println("Q_LESS = " + q_less1 );
        Double fv1 = Double.parseDouble(Q1_PROPS.getProperty("fv"));
        System.out.println("FV = " + fv1 );

        Integer rows = Integer.parseInt(Q4_PROPS.getProperty("rows"));
        System.out.println("ROWS = " + rows );
        Integer cols = Integer.parseInt(Q4_PROPS.getProperty("cols"));
        System.out.println("COLS = " + cols);
        Double q = Double.parseDouble(Q4_PROPS.getProperty("q"));
        System.out.println("Q = " + q );
        Double q_less = 1-q;
        System.out.println("Q_LESS = " + q_less );
        Double strike = Double.parseDouble(Q4_PROPS.getProperty("strike"));
        System.out.println("STRIKE = " + strike );

    }
}
