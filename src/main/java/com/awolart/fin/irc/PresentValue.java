/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.irc;

import java.text.NumberFormat;

/**
 * <p>
 * Collection of methods that apply present value calculations.
 * </p>
 */
public class PresentValue {
    /**
     * Create a new instance of the class PresentValue using the default no
     * argument constructor
     */
    public PresentValue() {
    }

    /**
     * The most commonly applied model of present valuation uses compound
     * interest. The standard formula is:
     * {@latex.ilb %resolution{128} \\begin{equation} PV = \\frac{C}{(1+i)^n} \\end{equation}}
     * Where <b>C</b>, is the future amount of money that must be discounted,
     * <i>n</i>, is the number of compounding periods between the present date
     * and the date where the sum is worth <b>C</b>, <i>i</i> is the interest
     * rate for one compounding period. The end of a compounding period is
     * when interest is applied; e.g., annually, semiannually, quarterly,
     * monthly, or daily. The interest rate, <i>i</i>, is given as a percentage,
     * but expressed as a decimal in this formula.
     * <p/>
     * For example if you are to receive $1000 in 5 years, and the effective
     * annual interest rate during this period is 10% (or 0.10), then the
     * present value of this amount is
     * {@latex.inline $PV = \\frac{1000}{(1+0.10)^{5}} = \\$620.92 $}
     * <p/>
     * The interpretation is that for an effective annual interest rate of
     * 10%, an individual would be indifferent to receiving $1000 in 5 years,
     * or $620.92 today.
     *
     * @param C double that represents the amount of money to be paid at some
     *          future time
     * @param i double that represents the interest rate to be used for all
     *          calculations
     * @param n int that represents the number of compounding periods or
     *          installments
     * @return double Discounted present value of the future payments(s)
     */
    public static double discountedPresentValueOfSinglePayment(double C, double i, int n) {
        return (C / (Math.pow((1 + i), n)));
    }

    /**
     * @param r
     * @param cash
     * @param period
     * @return
     */
    public double pV(double r, double cash, int period) {
        double sum = 0.0;
        int index = 1;
        for (int i = 0; i < period; ++i) {
            sum += (cash / (Math.pow((1 + r), (index))));
            index++;
        }

        return sum;

    }


    /**
     * A cash flow is an amount of money that is either paid out or received,
     * differentiated by a negative or positive sign, at the end of a period.
     * Conventionally, cash flows that are received are denoted with a positive
     * sign (total cash has increased) and cash flows that are paid out are
     * denoted with a negative sign (total cash has decreased). The cash flow
     * for a period represents the net change in money of that period.
     * <p/>
     * Calculating the net present value, NPV, of a stream of cash flows consists
     * of discounting each cash flow to the present, using the present value factor
     * and the appropriate number of compounding periods, and combining these values.
     * <p/>
     * For example, if a stream of cash flows consists of +$100 at the end of period one,
     * -$50 at the end of period two, and +$35 at the end of period three, and the interest
     * rate per compounding period is 5% (0.05) then the present value of these three Cash
     * Flows are:
     * {@latex.ilb %resolution{96} \\begin{equation} PV_{1} = \\frac{\\$100}{(1.05)^{1}} = \\$95.24 \\end{equation}}
     * {@latex.ilb %resolution{96} \\begin{equation} PV_{2} = \\frac{\\$100}{(1.05)^{2}} =  -\\$45.35 \\end{equation}}
     * {@latex.ilb %resolution{96} \\begin{equation} PV_{3} = \\frac{\\$100}{(1.05)^{3}} = \\$30.23 \\end{equation}}
     * <p/>
     * Thus the net present value would be
     * {@latex.inline $NPV = PV_{1}+PV_{2}+PV_{3} = 80.12 $}
     * <p/>
     * There are a few considerations to be made. The periods might not be consecutive.
     * If this is case, the exponents will change to reflect the appropriate number of
     * periods.
     * <p/>
     * The interest rates per period might not be the same. The cash flow must
     * be discounted using the interest rate for the appropriate period. If the
     * interest rate changes, the sum must be discounted to the period where
     * the change occurs using the second interest rate, then discounted back
     * to the present using the first interest rate. For example, if the cash
     * flow for period one is $100, and $200 for period two, and the interest
     * rate for the first period is 5%, and 10% for the second.
     * <p/>
     * The interest rate must coincide with the payment period. If not, either
     * the payment period or the interest rate must be modified. For example,
     * if the interest rate given is the effective annual interest rate, but
     * cash flows are received (and/or paid) quarterly, the interest rate per
     * quarter must be computed. This can be done by converting the effective
     * annual interest rate, i, to the nominal annual interest rate compounded
     * quarterly: {@latex.inline $ (1+i) = (1 +\\frac{i^{4}}{4})^4  $}
     * Here,  {@latex.inline $ i^{4} $}  is the nominal annual interest rate,
     * compounded quarterly, and the interest rate per quarter would be
     * {@latex.inline $ \\frac{i^{4}}{4} $}.
     *
     * @param C double that represents the amount of money to be paid at some
     *          future time
     * @param i double that represents the interest rate to be used for all
     *          calculations
     *
     * @return double Discounted present value of the future payments(s)
     *
     * @latex.block %preamble{\\usepackage{amssymb}}
     * \\begin{eqnarray}
     * \\sum_{n=1}^{N} f(n) &=& f(n) = \\frac{C}{(1+i)^n}
     * \\end{eqnarray}
     * Some additional non \\TeX~text
     *
     */
    public static double netPresentValueOfCashFlowStream(double[] C, double i) {
        double NPV = 0.0;
        int idx = 1;
        int installments = C.length;
        for (int j = 0; j < installments; ++j) {
            NPV += discountedPresentValueOfSinglePayment(C[j], i, idx);
            ++idx;
        }

        return NPV;
    }

    /**
     * @param r
     * @param cashflows
     * @return
     * @deprecated As of release 1.0.1, replaced by {@link #netPresentValueOfCashFlowStream}
     */
    @Deprecated
    public double pV(double r, double[] cashflows) {
        int idx = 1;
        double sum = 0.0;
        int cf_len = cashflows.length;
        for (int i = 0; i < cf_len; ++i) {
            sum += (cashflows[i] / (Math.pow((1 + r), (idx))));
            idx++;
        }

        return sum;
    }



    /**
     * @param discounts
     * @param cashflows
     * @return
     */
    public double pV(double[] discounts, double[] cashflows) {
        int cf_len = cashflows.length;
        double present_value = 0;
        for (int i = 0; i < cf_len; ++i) {
            /*
             * Aggregates the sum of the discounted values for each
             * matching cashflow period.
             */
            present_value += discounts[i] * cashflows[i];
        }

        return present_value;
    }

    /**
     * Convenience Command Line method for testing purposes.
     *
     * @param args
     */
    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        double PV =
                PresentValue.discountedPresentValueOfSinglePayment(125.00, 0.05, 1);
        System.out.println("Lump Sum Discounted Present Value = " + formatter.format(PV));

        double F = ( 100 * ( Math.pow(1+0.05,1) ) );
        System.out.println("Bespoke Discounted Present Value = " + formatter.format(F));

        double[] cash_flows = {100.00, 50.00, 35.00};
        double NPV =
                PresentValue.netPresentValueOfCashFlowStream(cash_flows, 0.05);
        System.out.println("Cash Flows Discounted Present Value = " + formatter.format(NPV));

        PresentValue prVal = new PresentValue();
        // pV(double r, double[] cashflows)
        double pV = prVal.pV(0.05, cash_flows);
        System.out.println("pV Cash Flows Discounted = " + formatter.format(pV));

        //NumberFormat iformatter = NumberFormat.getInstance();
        //iformatter.setMaximumFractionDigits(2);
        //iformatter.setMinimumFractionDigits(4);
        /*
        Suppose we hold a forward contract on a stock with expiration 6 months from now.
        We entered into this contract 6 months ago so that when we entered into the
        contract, the expiration was T=1 year. The stock price 6 months ago was S0=100,
        the current stock price is 125 and the current interest rate is r=10% compounded semi-annually.
        What is the current value of our forward contract?
         */
        double[] cash_flows2 = {0.00, 0.00, 100.00};
        double NPV2 = PresentValue.netPresentValueOfCashFlowStream(cash_flows2, 0.05);
        System.out.println("Cash Flows2 Discounted Present Value = " + formatter.format(NPV2));

        NumberFormat iformatter = NumberFormat.getInstance();
        iformatter.setMaximumFractionDigits(2);
        iformatter.setMinimumFractionDigits(6);
        double val = (1 / ( 1 + ( 0.1 / 2 ) ) );
        System.out.println("Value = " + iformatter.format(100 / val));

        // Then calculate the difference between the current asset price and val.

    }

}
