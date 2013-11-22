/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.ps4;

public class ShortRate extends AbstractLattice
{

    private double r, u, d;

    public ShortRate(int n, double r, double u, double d)
    {
        super(n + 1);
        this.r = r;
        this.u = u;
        this.d = d;
        createLattice();

    }

    public void createLattice()
    {
        lattice[0][0] = r;
        for(int i = 1; i < n + 1; i++)
        {
            lattice[0][i] = lattice[0][i - 1] * this.u;
            for(int j = 1; j < n; j++)
            {
                lattice[j][i] = lattice[j - 1][i - 1] * this.d;
            }
        }
        System.out.println("Short Rate Lattice:");
        for(int i = 0; i < super.n; ++i)
        {
            for(int j = 0; j < super.n; ++j)
            {
                //System.out.print("[" + i + "][" + j + "] =");
                System.out.printf("%9.4f ", lattice[i][j]);
            }
            System.out.println();
        }
    }

}
