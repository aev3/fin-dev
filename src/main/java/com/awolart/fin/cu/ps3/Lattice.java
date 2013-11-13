/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps3;

import static com.awolart.fin.cu.ps3.PS3Consts.S0;
import static com.awolart.fin.cu.ps3.PS3Consts.u;

import java.text.NumberFormat;
import java.util.*;

/**
 * <p>
 * Build a 15-period binomial model lattice whose parameters should be
 * calibrated to a Black-Scholes geometric Brownian motion model with:
 * <ol>
 * <li>T = 0.25 years</li>
 * <li>S0 = 100.00</li>
 * <li>r = 0.02</li>
 * <li>d = 0.30</li>
 * <li>c = 0.01</li>
 * <li>u = 1.0395</li>
 * </ol>
 * <p/>
 * All final static variables are accessible from @{link PS3Consts}.
 * </p>
 */
public class Lattice
{

    static final double N = 10;

    static final int ROWS = 11;

    static final int COLS = ROWS;

    static class LatDat
    {

        double s0;

        int t;

        double up;

        double down;
    }

    private List<LatDat> list = new ArrayList<LatDat>();

    private List<List<LatDat>> listList = new ArrayList<List<LatDat>>();

    private Map<Integer, LatDat> map = new HashMap<Integer, LatDat>();

    public Lattice()
    {
        // Default no argument constructor
    }

    public void createLattice()
    {
        for(int i = 0; i <= N; ++i)
        {
            LatDat LD = new LatDat();
            if(i == 0)
            {
                LD.s0 = S0;
                LD.t = i;
                list.add(LD);
            }
            else
            {
                /* For up */
                LD.up = S0 * Math.pow((u), i);
                /* For down */
                LD.down = S0 * Math.pow((1 / u), i);
                LD.t = i;
                list.add(LD);
                map.put(i, LD);
            }
        }
    }

    public double[][] createMatrix()
    {
        double[][] mx = new double[ROWS][COLS];
        for(int i = 0; i <= mx.length; ++i)
        {
            if(i == 0)
            {
                mx[0][0] = S0;
                System.out.println("S mx[0][0] = " + mx[0][0]);
                for(int k = 1; k < mx.length; ++k)
                {
                    mx[i][k] = S0 * Math.pow((1 / u), k);
                    System.out.println("F mx [" + i + "][" + k + "] = "
                            + mx[i][k]);
                }
            }
            else
            {
                for(int j = i; j < mx[i].length; ++j)
                {
                    if(j == i)   {
                        mx[i][j] = S0 * Math.pow((u), j);
                        System.out.println("S mx [" + i + "][" + j + "] = "
                                + mx[i][j]);
                    } else {
                        mx[i][j] = S0 * Math.pow((1 / u), j-i);
                        System.out.println("F mx [" + i + "][" + j + "] = "
                                + mx[i][j]);
                    }
                }
            }
        }

        return mx;
    }

    public static void main(String[] args)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        Lattice lattice = new Lattice();
        lattice.createMatrix();
    }

}
