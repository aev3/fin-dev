/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps3;

import static com.awolart.fin.cu.ps3.PS3Consts.c;
import static com.awolart.fin.cu.ps3.PS3Consts.d;
import static com.awolart.fin.cu.ps3.PS3Consts.r;
import static com.awolart.fin.cu.ps3.PS3Consts.S0;
import static com.awolart.fin.cu.ps3.PS3Consts.T;
import static com.awolart.fin.cu.ps3.PS3Consts.u;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Build a 15-period binomial model lattice whose parameters should be
 * calibrated to a Black-Scholes geometric Brownian motion model with:
 *  <ol>
 *     <li>T = 0.25 years</li>
 *     <li>S0 = 100.00</li>
 *     <li>r = 0.02</li>
 *     <li>d = 0.30</li>
 *     <li>c = 0.01</li>
 *     <li>u = 1.0395</li>
 *  </ol>
 * <p/>
 * All final static variables are accessible from @{link PS3Consts}.
 * </p>
 */
public class Lattice {

    static final int N = 4;

    static class LatDat {
        double t;
        double s;
    }

    private List<LatDat> list = new ArrayList<LatDat>();

    public Lattice() {
        // Default no argument constructor
    }

    public void createLattice() {
        for(int i = 0; i <= N; ++i) {
            LatDat LD = new LatDat();
            if(i == 0) {
               LD.s = S0;
               LD.t = i;
               list.add(LD);
            }
            else {
                LD.s = S0 + i;
                LD.t = i;
                list.add(LD);
            }
        }
    }

    public static void main(String[] args)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        Lattice l = new Lattice();
        l.createLattice();
        for(LatDat idx : l.list)  {
            System.out.println("Data: s = " + idx.s + " and t = " + idx.t);
        }
    }

}
