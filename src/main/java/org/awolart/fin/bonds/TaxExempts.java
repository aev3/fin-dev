package org.awolart.fin.bonds;

/**
 * Date: 31-12-2013
 * Time: 12:18 PM
 * <p>
 * An Investor choosing between taxable and tax-exempt bonds must compare
 * after-tax rates of returns on each bond. An exact comparison requires the
 * computation of after-tax rates of return that explicitly accounts for taxes
 * on income and realized capital gains. In practice, there is a simpler rule
 * of thumb. If we let $T$ denote the investors combined federal and local
 * marginal tax rates and let $R$ represent the total before-tax rate of return
 * available on taxable bonds, then $R(1-T)$ is the after-tax rate available on
 * those securities. If this value exceeds the rate on municipal bonds, the
 * investor does better holding the Taxable bonds. Otherwise, the tax-exempt
 * municipals provide higher after-tax returns.
 * <p/>
 * One way is to compare bonds to determine the interest rate on taxable bonds
 * that would be necessary to provide an after-tax return equal to that of the
 * municipals. To derive this value, set after-tax yields equal, and solve
 * for the equivalent taxable yields of the tax-exempt bond. This is the rate a
 * taxable bond must offer to match the after-tax yield on the tax-free
 * municipal. Thus, the equivalent taxable yield is simply the tax-free rate
 * divided by $1-T$.
 *</p>
 */
public class TaxExempts
{
    /**
     * <p>
     * $r_m = R(1 - T)$
     * </p>
     * @param taxable_yield
     * @param marginal_tax_rate
     *
     *
     * @return double
     */
    public static double exemptRate(final double taxable_yield, final double marginal_tax_rate)
    {
        return (taxable_yield * (1 - marginal_tax_rate));
    }

    /**
     * <p>
     * Method to calculate the <b>equivalent taxable yield</b> of the tax
     * exempt bond. This is the rate that the taxable bond must offer to
     * match the after-tax yield on the tax-free bond.
     * <p/>
     * To solve for $R$, where $R$ is the rate of the taxable bond, use:
     * $r_m \textrm{ where } r_m \textrm{ is the interest rate of the tax-exempt
     * bond and }
     * T \textrm{ is the marginal tax rate of the bond holder.}
     * <p/>
     * $R = r_m/(1-T)$
     * </p>
     *
     * @param tax_exempt_rate
     * @param marginal_tax_rate
     *
     * @return
     */
    public static double equivalentTaxableYield(final double tax_exempt_rate, final double marginal_tax_rate)
    {
        return (tax_exempt_rate/(1 - marginal_tax_rate));
    }

    /**
     * <p>
     * Method to calculate the tax bracket at which investors are indifferent
     * between taxable and tax-exempt bonds. The <b>cutoff tax bracket</b> is
     * given by $T = 1 - \frac{r_m}{R}$. Thus, the yield ratio $\frac{r_m}{R}$ is
     * a key determinant of the attractiveness of tax-exempt bonds. The higher
     * the yield ratio, the lower the cutoff bracket, and the more investors will
     * prefer to hold tax-exempt debt.
     * </p>
     *
     * @param tax_exempt_rate
     * @param taxable_yield
     *
     * @return
     */
    public static double yieldRatio(final double tax_exempt_rate, final double taxable_yield)
    {
        return (1 - (tax_exempt_rate/taxable_yield) );
    }

    /**
     * <p>
     * $
     * \textrm{Algorithm for discounted present value of future cash flows where: } \\
     * C \textrm{ represents the cash flow at time }  t \textrm{,} \\
     * r \textrm{ represents the interest rate, }  \\
     * n \textrm{ represents the number of periods, and } \\
     * m \textrm{ represents the number of times interest is compounded for the
     * period. }
     * $
     * <p/>
     * $ PV = \displaystyle\sum_{i=1}^{n} \frac{C_t}{(1+y)^{n}} $
     * </p>
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Exempt Rate = "
            + TaxExempts.exemptRate(0.04, 0.30));
        System.out.println("Equivalent Taxable Yield Rate = "
            + TaxExempts.equivalentTaxableYield(0.04, 0.30));
        System.out.println("Equivalent Taxable Yield Bracket = "
            + TaxExempts.yieldRatio(0.04, 0.06));
    }
}
