/*
 *
 */

package com.awolart.fin.irate;

/**
 * <p>
 * </p>
 */
public class SimpleInterest
{
    private static double c10;
    private static double c11;

    public SimpleInterest()
    {
        c10 = 100.00;
        c11 = 104.00;
    }

    public SimpleInterest(double c1)
    {
        c10 = c1;
        c11 = 104.00;
    }

    public SimpleInterest(double c1, double c2)
    {
        c10 = c1;
        c11 = c2;
    }

    public static double realintr(double int_rate)
    {
        return 100 * ( ( c10 * ( 1 + int_rate ) / c11 ) - 1) ;
    }

    public static double rate_squared(double int_rate1, double int_rate2)
    {
        return Math.pow(( 1 + ( int_rate1 / int_rate2)), int_rate2) - 1;
    }

    public static double rate_logged(double int_rate)
    {
        return Math.log( 1 + int_rate );
    }

    public static void main(String[] args)
    {
        SimpleInterest si = new SimpleInterest();
        System.out.println("REAL: " + realintr(0.08));
        System.out.println("LOGGED: " + rate_logged(0.08));
        System.out.println("SQUARED: " + rate_squared(0.08,0.04));
        double rate = ((155 * (1+0.08) / 159) - 1);
        System.out.println("MANUAL: " + 100*rate);
    }

}
