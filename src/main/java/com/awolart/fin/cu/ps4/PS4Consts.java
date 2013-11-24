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

    public static Properties Q4_PROPS = new Properties();
    static {
        Q4_PROPS.setProperty("rows", "7");
        Q4_PROPS.setProperty("cols", "7");
        Q4_PROPS.setProperty("q", "0.50");
        Q4_PROPS.setProperty("strike", "80.00");
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
