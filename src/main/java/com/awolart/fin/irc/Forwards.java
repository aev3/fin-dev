/*
 *
 */

package com.awolart.fin.irc;

/**
 * <p>
 * </p>
 */
public class Forwards {

    public Forwards() {

    }


    public static double dollarIntr(double term, double reporate)
    {
        return reporate * (term / 360);
    }

    public static double delPriceNoInc(double spotPrice, double maturity, double currenttime,
                                       double deliveryprice, double reporate)
    {
        return (spotPrice * Intr.effectintp(reporate,maturity));
    }
}
