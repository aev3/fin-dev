package com.awolart.fin.cu.ps1;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * <p>
 *
 * </p>
 */
public class LotteryPresentValue
{

    /**
     * A major lottery advertises that it pays the winner $10 million. However this prize money is paid at the rate of
     * $500,000 each year (with the first payment being immediate) for a total of 20 payments. What is the present value
     * of this prize at 10% interest compounded annually? Report your answer in $millions, rounded to two decimal places. So, for example,
     * if you compute the answer to be 5.7124 million dollars then you should submit an answer of 5.71. To solve this
     * problem, use the formula for the present value of an annuity, to wit:
     * PV =  PMT * ( ( (1 - Math.pow( 1 + RATE, -INST ) )  / RATE ) );
     * In this example: PMT = 500000, RATE = 0.10, and INSTALLMENTS = 20
     *
     *
     * Joe just won $1,000,000 in a lottery.  He plans to build a house, travel and buy lots of CDs.  But when he goes
     * to collect his prize he's told that he can't have it all at once.  Instead, the lottery officials say they'll
     * pay him $100,000 today, plus $100,000 a year for the next 9 years.  That's okay, Joe thinks, $100,000 times
     * 10 payments is still $1,000,000.  Or is it? Not even close.  Since dollars received in the future aren't worth
     * as much as dollars received today, Joe's prize is worth much less than $1,000,000.  To see how much Joe really
     * won, you need to ask this question:  How much money would Joe need to put in the bank today to be able to collect
     * $100,000 a year over a 10-year period?
     * The answer depends on the rate of interest Joe can get on his savings.  If the interest rate is 7%, then Joe
     * could withdraw $100,000 from his account one year from now by putting only $93,458 in the bank today.   We call
     * this amount the present value of $100,000 received next year, assuming the interest rate is 7%.
     *
     * Here's a simple formula for calculating the present value (PV) of any future value (FV) received t years from
     * now, assuming that the interest rate is r.
     * PV = FV/(1 + r)t
     * For example, the present value of a $100,000 payment that would be received two years from now equals:
     * PV = $100,000/(1.07)2 = $87,344
     *
     * To find out to find out the present value of Joe's lottery winnings before taxes, have your students calculate
     * the rest of the values in the first column, then sum the total.
     */

    private static final int    DEFAULT_INST    = 20;
    private static final double DEFAULT_PMT     = 500000.00;
    private static final double DEFAULT_RATE    = 0.10;

    /**
     * Here's a simple formula for calculating the present value (PV) of any future value (FV) received t years from
     * now, assuming that the interest rate is r.
     * PV = FV/(1 + r)t
     * To find out to find out the present value of Joe's lottery winnings before taxes, have your students calculate
     * the rest of the values in the first column, then sum the total.
     * To find out to find out the present value of Joe's lottery winnings before taxes, have your students calculate
     * the rest of the values in the first column, then sum the total, to wit:
     * PV = $100,000/(1.07)2 = $87,344
     *
     * @param inst
     * @param pmt
     * @param rate
     * @return
     */
    public static double LotteryNPV(final int inst, final double pmt, final double rate)
    {
        double PV =  0.00;
        for(int i = 0; i < inst; ++i)
        {
            double fv = pmt/Math.pow(1 + rate,i);
            PV += fv;
        }
        return PV;
    }


    public static double SimplePV(final int inst, final double pmt, final double rate)
    {
        double PV =  pmt * ( ( (1 - Math.pow( 1 + rate, -inst ) )  / rate ) );
        return PV;
    }

    public static double ForwardRate(final double rt1, final double rt2)
    {
        double numerator = Math.pow(1.0 + rt2, 2);
        double denominator = 1.0 + rt1;
        double FR = ( numerator  / denominator  ) - 1;
        return FR;
    }

    public static double DiscountRate(double rate)
    {
        double DR = 1 / (1 + rate);
        return DR;
    }

    public static double ForwardPrice(double sp, double rate)
    {
        double FP = sp / rate;
        return FP;
    }

    public static void main(String[] args)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        double LPV =  LotteryPresentValue.LotteryNPV(DEFAULT_INST, DEFAULT_PMT, DEFAULT_RATE);
        System.out.println("LPV = " + formatter.format(LPV));
        double SPV =  LotteryPresentValue.SimplePV(DEFAULT_INST, DEFAULT_PMT, DEFAULT_RATE);
        System.out.println("SPV = " + formatter.format(SPV));
        double FR =  LotteryPresentValue.ForwardRate(0.07, 0.12);
        System.out.println("FR = " + FR);

        double DR =  LotteryPresentValue.DiscountRate(0.02);
        System.out.println("DR = " + DR);

        double sp = 50.00;
        double FP=  LotteryPresentValue.ForwardPrice(sp, DR);
        System.out.println("FP = " + formatter.format(FP));

    }

}
