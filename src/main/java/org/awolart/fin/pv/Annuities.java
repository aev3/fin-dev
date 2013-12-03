/*
 * Copyright (c) 2013 AWOLart.org
 */

package org.awolart.fin.pv;

/**
 * <p>
 * An annuity is a contract that pays out <i>C</i> dollars at the end of each
 * year  for <i>n</i> years is called an <b>ordinary annuity</b>.
 * <p/>
 * $$
 * \begin{align}
 * \textrm{With the nominal interest rate of}~\textit{r}~\textrm{, the future
 * value of the}\textbf{ ordinary annuity }\textrm{ at the end of}~\textit{n}^{th}
 * ~\textrm{year is equal to:} \\
 * \displaystyle\sum_{i=0}^{n-1} C(1 + r)^i &=& C\frac{(1 + r)^n - 1}{r} \\
 * \textrm{For the}~\textbf{annuity due},\textrm{ cash flows are received at
 * the beginning of each year, and the future value is equal to:} \\
 * \displaystyle\sum_{i=1}^{n} C(1 + r)^i &=& C\frac{(1 + r)^n - 1}{r}(1 + r) \\
 * \text{If}~\textit{m}~\text{payments are received per year (the so-called}
 * ~\textbf{general annuity}),~\text{ and each payment has the amount}\textit{ C, }
 * ~\text{ then the above equations can be generalized to} \\
 * C \frac{(1 + \frac{r}{m})^{nm} - 1}{\frac{r}{m}}~\textrm{ and }
 * C \frac{(1 + \frac{r}{m})^{nm} - 1}{\frac{r}{m}}
 * \left(1 + \frac{r}{m}\right)
 * \textrm{, respectively.}
 * \end{align}
 * $$
 * <p/>
 * Unless stated otherwise, we assume the payment from an annuity is made at the
 * end of each period.
 * <p/>
 * The <b>present value</b> of an annuity can be derived easily. If the payments
 * are received <i>m</i> times per year, the present value is:
 * $$
 * \begin{align}
 * PV &=& \displaystyle\sum_{i=1}^{nm} C \left(1 + \frac{r}{m}\right)^{-i} \\
 * &=& C \frac{1 - \left(1 + \frac{r}{m}\right)^{-nm}}{\frac{r}{m}}
 * \end{align}
 * $$
 * </p>
 */
public class Annuities
{

    /**
     * <p>
     * The algorithm for the calculation of a future value based upon four
     * variables:
     * <ol>
     * <li>n = number of periods</li>
     * <li>m = number of interest calculations per period</li>
     * <li>r = interest rate per period</li>
     * <li>P = principal amount of investment</li>
     * </ol>
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
     * The algorithm for the calculation of a future value based upon four
     * variables:
     * <ol>
     * <li>n = number of periods</li>
     * <li>m = number of interest calculations per period</li>
     * <li>r = interest rate per period</li>
     * <li>P = principal amount of investment</li>
     * </ol>
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



    /**
     * <p>
     * Algorithm for Present Value of an ordinary annuity with variable
     * installments.
     * <p/>
     * $
     * C_t = \text{cash flow at time t}, \\
     * y = \text{yield or interest rate, and} \\
     * n = \text{number of time periods or installments}
     * $
     * <p/>
     * $$ \text{The present value of the cash flows } C_1, C_2, \dots , C_n  \text{ would be }
     * \frac{C_1}{(1 + y)^1} +  \frac{C_2}{(1 + y)^2} +  \frac{C_3}{(1 + y)^3} + \cdots
     * + \frac{C_n}{(1 + y)^n} $$
     * <p/>
     *
     * @param y  double that represents the yield or interest rate
     * @param C  array of type double that represents the cash flows
     *           where the number of installments is equal to the length
     *           of C
     * @return
     */
    public double OrdinaryAnnuity(final double y,
                                  final double[] C)
    {
        double pv = 0.0;

        double d = 1 + y;
        for(int i = 0; i < C.length; ++i)
        {
            pv = (pv + C[i]) / d;
        }

        return pv;
    }

    /**
     * <p>
     * Algorithm for Present Value of an ordinary annuity with fixed and equal
     * installments.
     * <p/>
     * $ C_t = \text{cash flow at time t}, \\
     * y = \text{yield or interest rate, and} \\
     * n = \text{number of time periods or installments}
     * $
     * <p/>
     * $$
     * \text{The present value of the cash flows } C_1, C_2, \dots , C_n  \text{ would be }
     * \frac{C_1}{(1 + y)^1} +  \frac{C_2}{(1 + y)^2} +  \frac{C_3}{(1 + y)^3} + \cdots + \frac{C_n}{(1 + y)^n}
     * $$
     * <p/>
     *
     * @param y  double that represents the yield or interest rate
     * @param n  int that represents the number of installments
     * @param C  double that represents the cash flows where the number of
     *           installments is equal to n
     * @return
     */
    public double OrdinaryAnnuity(final double y,
                                  final int n,
                                  final double C)
    {
        double pv = 0.0;

        double d = 1 + y;
        for(int i = 0; i < n; ++i)
        {
            pv = (pv + C) / d;
        }

        return pv;
    }

    public double AnnuityDue(final int n,
                             final double r,
                             final double C)
    {
        double fv = 0.00;

        for(int i = 0; i < n; ++i)
        {
            fv += C / Math.pow(1 + r, i);
        }

        return fv;
    }

    public static void main(String[] args)
    {
        Annuities fv = new Annuities();

        System.out.println("Per Annum Future Value = " + fv.FutureValue(10, 1, 0.10, 1000000.00));

        System.out.println("Bond Future Value = " + fv.FutureValue(10, 2, 0.10, 1000000.00));

        System.out.println("Mortgage Future Value = " + fv.FutureValue(10, 12, 0.10, 1000000.00));

        double y = 0.10;
        int n = 10;
        double[] C_ARRAY = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        double PV = fv.OrdinaryAnnuity(y, C_ARRAY);
        System.out.println("PV1 = " + PV);
        double C = 100;
        PV = fv.OrdinaryAnnuity(y, n, C);
        System.out.println("PV2 = " + PV);
        PV = fv.AnnuityDue(20, 0.10, 500000.00);
        System.out.println("Annuity Due = " + PV);
    }

}
