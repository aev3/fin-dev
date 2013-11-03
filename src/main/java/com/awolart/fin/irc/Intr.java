/*
 *
 */

package com.awolart.fin.irc;

/**
 * <p>
 * </p>
 */
public class Intr {

    private static double c10;
    private static double c11;

    public Intr()
    {
        c10 = 100.00;
        c11 = 104.00;
    }

    public Intr(double c1)
    {
        c10 = c1;
        c11 = 104.00;
    }

    public Intr(double c1, double c2)
    {
        c10 = c1;
        c11 = c2;
    }

    public static double realintr(double int_rate)
    {
        return 100 * ( ( c10 * ( 1 + int_rate ) / c11 ) - 1) ;
    }

    public static double erate(double int_rate1, double int_rate2)
    {
        return Math.pow(( 1 + ( int_rate1 / int_rate2)), int_rate2) - 1;
    }

    public static double fint(double int_rate)
    {
        return Math.log( 1 + int_rate );
    }

    public static double ancertain(double intr, double n)
    {
        return ((Math.pow((1 + intr), n) - 1) / intr);
    }

    public static double ancertainAd(double intr, double n)
    {
        return (((Math.pow(( 1 + intr ), n) - 1) / intr) * ( 1 + intr ));
    }

    public static double pvancert(double intr, double n)
    {
        return ( 1.0 - ( 1 / Math.pow( ( 1 + intr ), n ) ) ) / intr;
    }

    public static double pvancertAd(double intr, double n)
    {
        return ( ( 1 + intr ) * ( 1.0 - ( 1 / Math.pow( ( 1 + intr ), n ) ) ) / intr );
    }

    public static double pvainfprog(double intr, double growth, double value)
    {
        return value/intr-growth;
    }

    public static double pvanmult(double intr, double n)
    {
       double value = 1 / (1 + intr);
        return ( ( pvancertAd( intr, n ) ) - ( n * Math.pow( value, n ) ) ) / intr;
    }

    /**
     * Given the effective annual interest rate , this method returns the
     * nominal rate.
     *
     * @param annualintr
     *
     * @param p
     *
     * @return
     */
    public static double effectintp(double annualintr, double p)
    {
        return Math.pow( ( 1 + annualintr ), ( 1 / p ) ) - 1;
    }

    /**
     * Given a nominal annual rate, this method returns the effective
     * rate.
     *
     * @param nomnualintr
     *
     * @param p
     *
     * @return
     */
    public static double effectann(double nomnualintr, double p)
    {
        return ( Math.pow( ( 1 + nomnualintr / p ), p ) - 1 );
    }

    public static void main(String[] args)
    {
        Intr intr = new Intr();
        System.out.println("REAL: " + realintr(0.08));
        System.out.println("LOGGED: " + fint(0.08));
        System.out.println("SQUARED: " + erate(0.08,0.04));
        double rate = ((155 * (1+0.08) / 159) - 1);
        System.out.println("MANUAL: " + 100*rate);
    }

}
