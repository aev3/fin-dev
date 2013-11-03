/*
 *
 */

package com.awolart.fin.core.math;

import java.util.*;

/**
 * <p>
 * </p>
 */
public class Interpolate
{
    /**
     * In mathematical optimization, the method of Lagrange multipliers, named
     * after Joseph Louis Lagrange, is a strategy for finding the local maxima
     * and minima of a function subject to equality constraints. For instance,
     * consider the optimization problem
     * maximize f(x, y)  => subject to g(x, y) = c
     * We need both f and g to have continuous first partial derivatives.
     * We introduce a new variable (\\lambda) called a Lagrange multiplier and
     * study the Lagrange function (or Lagrangian) defined by
     * Lambda(x,y,\\lambda) = f(x,y) + \\lambda \cdot \\Big(g(x,y)-c\\Big),
     * where the \\lambda term may be either added or subtracted.
     * If f(x_0, y_0) is a maximum of f(x,y) for the original constrained problem,
     * then there exists \\lambda_0 such that (x_0,y_0,\\lambda_0) is a stationary
     * point for the Lagrange function (stationary points are those points where the
     * partial derivatives of \Lambda are zero, i.e. \\nabla\s\Lambda = 0).
     * However, not all stationary points yield a solution of the original problem.
     * Thus, the method of Lagrange multipliers yields a necessary condition for
     * optimality in constrained problems. Sufficient conditions for a minimum or
     * maximum also exist.
     *
     * @param valpairs
     * @param xval
     * @return
     */
    public double lagrange(double[][] valpairs, double xval)
    {
        int vp_len = valpairs.length;
        double pn = 0.0;
        for(int i = 0; i < vp_len; ++i)
        {
            double px = 1;
            for(int  j = 0; j < vp_len; ++j)
            {
                if(j != i)
                {
                    px *= ((xval - valpairs[j][0]) / (valpairs[i][0]
                            - valpairs[j][0]));
                }
            }

            pn += px * valpairs[i][1];
        }

        return pn;

    }

    /**
     *
     * Neville type interpolation fir the given value - xval.
     * In mathematics, Neville's algorithm is an algorithm used for polynomial
     * interpolation that was derived by the mathematician Eric Harold Neville.
     * Given n + 1 points, there is a unique polynomial of degree ≤ n which goes
     * through the given points. Neville's algorithm evaluates this polynomial.
     * Neville's algorithm is based on the Newton form of the interpolating polynomial
     * and the recursion relation for the divided differences.
     *
     * @param valpairs
     *
     * @param xval
     *
     * @return
     */
    public double neville(double[][] valpairs, double xval)
    {
        // For Positive Values Only //
        double prec = 1e-2;
        int k = 0;
        int ky = 0;
        int vp_len = valpairs.length;
        double[][] kp_vals = new double[vp_len][2];
        double x = 0.0;
        double v = 0.0;
        double nume;
        double denom;
        double newp = 0;
        double compareval;
        int counter = 0;
        int m = 0;
        int indx = 1;

        List<Double> pvalues = new ArrayList<Double>();
        Map<Double, Double> treeMap = new TreeMap<Double, Double>();

        for(int i = 0; i < vp_len; ++i)
        {
            if(xval > valpairs[i][0])
            {
               treeMap.put( new Double( xval - (valpairs[i][0] )),
                new Double( valpairs[i][1] ));
            }
            else
            {
                treeMap.put( new Double( Math.abs(xval - valpairs[i][0] )),
                        new Double( -valpairs[i][1] ));
            }
        }

        Iterator<Double> key = treeMap.keySet().iterator();
        Iterator<Double> val = treeMap.values().iterator();
        while(val.hasNext())
        {
            x = val.next().doubleValue();
            v = (x > 0.0) ? key.next().doubleValue() - xval:xval + key.next().doubleValue();
        }

        x = Math.abs(x);
        v = Math.abs(v);
        kp_vals[k][0] = v;
        kp_vals[k][1] = x;

        if(counter >= 1)
        {
            pvalues.add(ky, new Double( ( ( ( (xval - kp_vals[ky][0])
            * kp_vals[ky+1][1] ) + ( ( kp_vals[ky+1][0]- - xval )
            * kp_vals[ky][1] ) ) / (kp_vals[ky+1][0] - kp_vals[ky][0]) ) ) );

            double res = pvalues.get(ky).doubleValue();
            ky++;
        }

        counter++;
        k++;

        while(!pvalues.isEmpty())
        {
            indx++;
            compareval = pvalues.get(0).doubleValue();

            for(m = 0; m < pvalues.size(); ++m)
            {
                nume = ( ( ( xval-kp_vals[m][0] )
                * ( pvalues.get(m+1).doubleValue() ) ) + ( kp_vals[indx+m][0]-xval )
                * ( pvalues.get(m).doubleValue() ) );

                denom = (kp_vals[indx+m][0]-kp_vals[m][0]);
                newp = nume / denom;
                pvalues.set(m, new Double(newp));
             }

            if((Math.abs(compareval - newp)) < prec)
            {
                return compareval;
            }

            pvalues.remove(m);

        }

        return newp;

    }

}
