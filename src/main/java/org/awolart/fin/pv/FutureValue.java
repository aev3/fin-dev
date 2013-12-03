/*
 * Copyright (c) 2013 AWOLart.org
 */

package org.awolart.fin.pv;

/**
 * <p>
 * Interest is the cost of borrowing money.
 * </p>
 */
public class FutureValue
{

    /**
     * <p>
     * The algorithm for the calculation of a future value based upon four
     * variables:
     * <p/>
     * $
     * n = \textrm{number of periods} \\
     * m = \textrm{number of interest calculations per period} \\
     * r = \textrm{interest rate per period} \\
     * P = \textrm{principal amount of investment}
     * $
     * <p/>
     * The equation for this function is as follows:
     * $$ FV = P\left(1 + \frac{r}{m}\right)^{nm} $$
     * <p/>
     *
     * @param n  int that represents the number of periods
     * @param m  int that represents the number of calculations of interest per
     *           period
     * @param r  double that represents the interest rate
     * @param P  double that represents the principal amount invested
     *
     * @return  double representing the future value
     */
    public double FutureValue(int n,
                              int m,
                              double r,
                              double P)
    {
        return P * Math.pow(1 + (r/m), n*m);
    }

    /**
     * <p>
     * The algorithm for the calculation of a future value is based upon four
     * variables:
     * <p/>
     * $
     * n = \textrm{number of periods} \\
     * m = \textrm{number of interest calculations per period} \\
     * r = \textrm{interest rate per period} \\
     * P = \textrm{principal amount of investment}
     * $
     * <p/>
     * The equations for this function are as follows:
     * $$
     * \begin{align}
     * \left(1 + \frac{r_2}{m}\right)^m  &=& e^{r_1} \\[16pt]
     * \text{Therefore,} \\
     * r_1 &=& m \log\left(1 + \frac{r_2}{m}\right) \\
     * r_2 &=& m \left(e^{r_1/m} - 1\right) \\
     * \end{align}
     * $$
     * <p/>
     *
     * @param n  int that represents the number of periods
     * @param m  int that represents the number of calculations of interest per
     *           period
     * @param r  double that represents the interest rate
     * @param P  double that represents the principal amount invested
     *
     * @return  double representing the future value
     */
    public double Conversion(int n,
                             int m,
                             double r,
                             double P)
    {
        return P * Math.pow(1 + (r/m), n*m);
    }

}
