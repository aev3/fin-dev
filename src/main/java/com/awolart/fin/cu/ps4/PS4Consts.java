/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps4;

import java.util.Properties;

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
    public static Properties Q1_props = new Properties();
    static {
        Q1_props.setProperty("rows", "10");
        Q1_props.setProperty("cols", "10");
        Q1_props.setProperty("rate", "0.05");
        Q1_props.setProperty("up", "1.1");
        Q1_props.setProperty("down", "0.9");
        Q1_props.setProperty("q", "0.50");
    }

    public static void main(String[] args)
    {
        Integer rows = Integer.parseInt(Q1_props.getProperty("rows"));
        System.out.println("ROWS = " + rows );
        Integer cols = Integer.parseInt(Q1_props.getProperty("cols"));
        System.out.println("COLS = " + cols);
        Double q = Double.parseDouble(Q1_props.getProperty("q"));
        System.out.println("Q = " + q );
        Double q_less = 1-q;
        System.out.println("Q_LESS = " + q_less );

    }
}
