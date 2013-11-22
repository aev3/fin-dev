/*
 *
 */

package com.awolart.fin.cu.ps4;

public class CouponBond extends AbstractLattice
{

    protected int n;

    private double face, coupon, q, qq;

    protected ShortRate rates;

    public CouponBond(double face, double coupon, double q, int n,
            ShortRate rates)
    {
        super(n);
        this.face = face;
        this.coupon = coupon;
        this.q = q;
        this.qq = 1 - q;
        createLattice();
        this.rates = rates;
        this.n = n;
        createLattice();
    }

    @Override
    public void createLattice()
    {
        // To change body of implemented methods use File | Settings | File
        // Templates.
        for(int i = 0; i <= n; i++)
        {
            lattice[i][n] = face + face * coupon;
        }
        for(int j = n - 1; j >= 0; j--)
        {
            for(int i = 0; i <= j; i++)
            {
                lattice[i][j] = face
                        * coupon
                        + (lattice[i][j + 1] * q + (lattice[i + 1][j + 1] * qq))
                        / (1 + rates.getCell(i, j));
            }
        }
    }

    public double getQ()
    {
        return q;
    }

    public double getFace()
    {
        return face;
    }

    public double getCoupon()
    {
        return coupon;
    }
}
