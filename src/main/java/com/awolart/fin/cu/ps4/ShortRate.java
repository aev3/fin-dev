/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps4;

import java.util.Properties;

import static com.awolart.fin.cu.ps3.PS3Consts.S0;

public class ShortRate // extends AbstractLattice
{

    public Double[][] getLattice(Properties props)
    {
        Integer rows = Integer.parseInt(props.getProperty("rows"));
        Integer cols = Integer.parseInt(props.getProperty("cols"));
        Double rate = Double.parseDouble(props.getProperty("rate"));
        Double up = Double.parseDouble(props.getProperty("up"));
        Double down = Double.parseDouble(props.getProperty("down"));
        Double q = Double.parseDouble(props.getProperty("q"));
        Double[][] lattice = new Double[rows][cols];
        lattice = createLattice(rows, cols, rate, up, down, q);
        return lattice;
    }

    public Double[][] createStockLattice(int rows, int cols, double s0,
                                         double up)
    {
        Double[][] lattice = new Double[rows][cols];
        return lattice;
    }

    public Double[][] createLattice(Integer rows, Integer cols, Double rate,
                                    Double up, Double down, Double q)
    {
       Double[][] lattice = new Double[rows][cols];
       return lattice;
    }
}
