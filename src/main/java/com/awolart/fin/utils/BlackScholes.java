
package com.awolart.fin.utils;

import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


/*

From fOptions:

For PS3, Q1:

> BSAmericanApproxOption(TypeFlag="c", S=100, X=110, Time=0.25, r=0.02, b=0.02, sigma=0.30)

Title:
 BS American Approximated Option

Call:
 BSAmericanApproxOption(TypeFlag = "c", S = 100, X = 110, Time = 0.25,
     r = 0.02, b = 0.02, sigma = 0.3)

Parameters:
          Value:
 TypeFlag c
 S        100
 X        110
 Time     0.25
 r        0.02
 b        0.02
 sigma    0.3

Option Price:
 2.634077

Description:
 Mon Nov 18 07:28:04 2013

Title:
 Roll Geske Whaley Option

Call:
 RollGeskeWhaleyOption(S = 100, X = 110, time1 = 0.25, Time2 = 0.25,
     r = 0.02, D = 0.01, sigma = 0.3)

Parameters:
       Value:
 S     100
 X     110
 time1 0.25
 Time2 0.25
 r     0.02
 D     0.01
 sigma 0.3

Option Price:
 2.633581

Description:
 Mon Nov 18 07:42:21 2013



From RQuantLib:

For PS3, Q1:

> AmericanOption(type="call", underlying=100, strike=110, dividendYield=0.01, riskFreeRate=0.02,  maturity=0.25, volatility=0.30, engine="BaroneAdesiWhaley")
Concise summary of valuation for AmericanOption
 value  delta  gamma   vega  theta    rho divRho
2.5601     NA     NA     NA     NA     NA     NA

> AmericanOption(type="put", underlying=100, strike=110, dividendYield=0.01, riskFreeRate=0.02,  maturity=0.25, volatility=0.30, engine="BaroneAdesiWhaley")
Concise summary of valuation for AmericanOption
  value   delta   gamma    vega   theta     rho  divRho
12.2959      NA      NA      NA      NA      NA      NA


 */

/*
 * Dhruba Bandopadhyay Maximum Zeal ~ Emphatic prose on indulged fascinations
 * The Black Scholes Algorithm: A decomposed implementation in Java Posted on
 * Wed, 22 Aug 2012 18:54:38 +0000 by Dhruba Bandopadhyay • 2 Comments Recently,
 * I had to get to grips with the Black Scholes algorithm and its implementation
 * in a few different languages. I decided to go with Java first as that’s what
 * I’m most proficient at. The objective here was to code the algorithm from
 * scratch rather than use a third party library and remain in ignorance.
 * 
 * A critique of existing Black Scholes implementations in Java
 * 
 * Like any good programmer I first searched to see what was out there. I found
 * numerous such questions on the web as well as only three implementations in
 * Java. The first was Espen Haug’s authoritative webpage implementing Black
 * Scholes in a number of different languages. Espen Haug is known for his two
 * books: The Complete Guide to Option Pricing Formulas and Derivatives: Models
 * on Models; the second – a teaching resource implementation by Robert
 * Sedgewick and Kevin Wayne and third, an implementation by Joshua Davis on
 * koders.com.
 * 
 * To be quite frank I wasn’t happy with any of these implementations. The third
 * implementation was identical to the first one. The second implementation
 * appeared only to support call option prices. And they all exhibited, in my
 * opinion, classic signs of bad code. Let me be more specific by taking Espen
 * Haug’s implementation as an example though many of my reservations would
 * apply to the others too.
 * 
 * Espen Haug’s program, first of all, was written exactly as you would write a
 * C or C++ program; more like C actually. I don’t think it could have been any
 * further from idiomatic Java. First of all it was syntactically incomplete. It
 * was composed only of two functions not residing within a class. The casing of
 * the function and variable naming was arbitrary and not like that of Java at
 * all. The lack of whitespacing and indenting made it all the more hard to read
 * and the constant declarations within the second method invocation was just
 * sheer laziness. I couldn’t help but get the impression that if it had been
 * possible to put the whole thing on one line the author would have.
 * 
 * So far, you could argue, though I would refute it, that my criticisms have
 * been synthetic but if you were to do so you would have spoken too soon. My
 * biggest objection, by far, is still to come and it is I believe a fundamental
 * problem that makes these implementations unacceptable to me. It is the fact
 * that none of the implementations give the reader any idea of how they’ve been
 * composed. To put it another way – although the implementations may be correct
 * and the authors far more intelligent than I – the programs have not been
 * decomposed into their constituent parts for the benefit of the reader’s
 * comprehension. And for this reason there’s insufficient reuse of certain
 * code. For example, if there was a function for the standard normal
 * probability function (which is bundled into the CND() code) then it would be
 * reused by the CND() function as well as the calculation of the Greeks.
 * 
 * The existing implementations may be suitable if I was just looking to copy
 * and paste some code and not care about what it did or its maintainability and
 * readability. However, in this case, I was looking to learn about the
 * algorithm itself from these sources and wanted to know specifically how the
 * implementations had been composed from the formulas and to which areas of
 * code each constituent formula corresponded. In the ideal case I should have
 * been able to look at the formulas and the implementation side by side and be
 * able to relate one to the other by merely identifying blocks of code. I knew
 * I could do better so I did.
 * 
 * A decomposed implementation
 * 
 * Here I present the constituent parts in the implementation of the Black
 * Scholes algorithm and formulas that correspond to each part – an end result I
 * refer to as a decomposed implementation. This is purely the derivation of an
 * implementation from the mathematical formulas. It is NOT a discussion about
 * the mathematical or financial aspects of the algorithm – this I’m simply not
 * qualified to write about. Please see expert authors if you’re looking for a
 * semantic treatment.
 */

public class BlackScholes
{

    public BlackScholes()
    {

    }

    /**
     * Black Scholes Formula
     * 
     * The Black Scholes formula prices European call or put options and
     * consists, at the top level, of two formulas - one that calculates the
     * price of a call option (c) and another that calculates the price of a put
     * option (p).
     *
     * @latex.block %preamble{\\usepackage[reqno]{amsmath}[2000/07/18]}
     * \\begin{align}
     * c = s \\Phi (d_1) - xe^{rt} \\Phi (d_2)  \\\\
     * p = xe^{-rt} \\Phi (-d_2) - \\Phi (-d_1)
     * \\end{align}
     *
     * where:
     *
     * @latex.block %preamble{\\usepackage[reqno]{amsmath}[2000/07/18]}
     * \\begin{align}
     * d_1 = \\frac{log(s/x) + (r + \\sigma^2/2)t}{\\sigma \\sqrt{t}}  \\\\
     * d_2 = d_1 - \\sigma \\sqrt{t}
     * \\end{align}
     * 
     * The meanings of the variables used above are as below.
     * 
     * s = price of stock x = strike price r = risk free interest rate t = time
     * in years until option expiration σ (sigma) – implied volatility of stock
     * Φ (phi) – standard normal cumulative distribution function
     * 
     * In order to implement these formulas let’s take our first step in
     * decomposing it. So far the formula above consists almost entirely of
     * scalar primitive variables with the exception of one: Φ (phi). This is
     * the standard normal cumulative distribution function (CDF). As it is a
     * prerequisite to the formulas it must be implemented first. Let’s look at
     * the CDF formula.
     * 
     * 
     * 
     * The CDF is not used directly in Black Scholes being an infinite integral.
     * Instead a very accurate numerical approximation method is used. There are
     * a number of different ways CDF can be approximated depending on the level
     * of accuracy desired. In Black Scholes generally the Abramowitz & Stegun
     * (1964) numerical approximation is used which is given below.
     * 
     * 
     * 
     * The Abramowitz & Stegun numerical approximation above uses six constant
     * values in its formula. However it also relies on another function in turn
     * – the standard normal probability density function (PDF) denoted above by
     * Z(x). We will return to implement the CDF later but we must drop down
     * another step in our decomposition to implement the PDF which is our
     * lowest level prerequisite.
     * 
     * Standard normal probability density function
     * 
     * The standard normal probability density function (PDF) is as below.
     * 
     * 
     * 
     * As you can see the PDF is defined purely in terms of variables and there
     * are no further functions involved so let’s begin our Java implementation
     * with this formula.
     */
    public static double standardNormalDistribution(double x)
    {
        double top = exp(-0.5 * pow(x, 2));
        double bottom = sqrt(2 * PI);
        return top / bottom;
    }

    /**
     * Note that even the implementation of this function is decomposed into its
     * top half and its bottom half allowing it be easily related to the formula
     * above visually by the reader. This is how I prefer formulas to be coded.
     * Now that we’ve implemented the PDF let us return to the next function up
     * which required the PDF: the CDF.
     * 
     * Standard normal cumulative distribution function
     * 
     * As we discussed previously the CDF implementation uses the Abramowitz &
     * Stegun (1964) numerical approximation formula consisting of six constant
     * values as below.
     * 
     * 
     * 
     * Since we now have a PDF implementation above we can now use this in our
     * CDF Java implementation below. This implements the CDF with one little
     * difference: on line 9 and 17 it handles negative values appropriately. On
     * line 16 we use the PDF function defined earlier on.
     */
    private static final double P = 0.2316419;

    private static final double B1 = 0.319381530;

    private static final double B2 = -0.356563782;

    private static final double B3 = 1.781477937;

    private static final double B4 = -1.821255978;

    private static final double B5 = 1.330274429;

    public static double cumulativeDistribution(double x) { 
         double t = 1 / (1 + P * abs(x)); 
         double t1 = B1 * pow(t, 1); 
         double t2 = B2 * pow(t,2); 
         double t3 = B3 * pow(t, 3); 
         double t4 = B4 * pow(t, 4); 
         double t5 = B5 * pow(t, 5); 
         double b = t1 + t2 + t3 + t4 + t5; 
         double cd = 1 - standardNormalDistribution(x) * b; 
         return x < 0 ? 1 - cd : cd;
     }

     /**
     * Once again, note the nature of the code above. The function and variables
     * are named appropriately with the variable naming being the same as in the
     * formula, each step of the formula is broken down into a separate line and
     * constants are declared separately and not inline. And the code is again
     * easy to relate to the formula. Let us now move to the next function
     * implementation required by the Black Scholes formula. This time, it is
     * not one, but two: d1 and d2. Let’s look at d1 first. Now, bear in mind,
     * although d1 and d2 do not require the CDF directly the Black Scholes
     * formula does so that’s why we looked at CDF first. Though – we could also
     * have looked at d1 and d2 first. It would have been an equally valid
     * decomposition.
     * 
     * d1 – A sub-formula of Black Scholes
     * 
     * The d1 formula is as follows which I reproduce here again for
     * convenience.
     * 
     * 
     * 
     * Before I present the d1 Java implementation here is the legend of the
     * variable names used which are mostly substitutions of the greek letters
     * used in the maths formula.
     * 
     * s = Spot price of underlying stock/asset
     * k = Strike price
     * r = Risk free annual interest rate continuously compounded
     * t = Time in years until option expiration (maturity)
     * v = Implied volatility of returns of underlying stock/asset
     * Here is the Java implementation of d1 using the
     * names above. Once again note the formula is broken down into regions
     * which makes it easy to relate to the formula.
     *
     */
    private static double d1(double s, double k, double r, double t, double v) {
        double top = log(s / k) + (r + pow(v, 2) / 2) * t;
        double bottom = v * sqrt(t);
        return top / bottom;
    }

    /**
     * Now let’s move onto d2 which is
     * significantly simpler.
     * 
     * d2 – A sub-formula of Black Scholes
     * 
     * The d2 formula is as below.
     * 
     * 
     * 
     * Its implementation using the same variable names is as below.
     *
     */
     private static double d2(double s, double k, double r, double t, double v) {
         return d1(s, k, r, t, v) - v * sqrt(t);
     }

    /**
     * Once you have
     * prerequisite function implementations it’s so easy to compose higher
     * level functions. Here d2 uses d1 from earlier on. Now let us return to
     * the parent functions – the call and put formulas themselves.
     * 
     * Black Scholes formula
     * 
     * Now that we have prerequisite functions it should be simple to code up
     * the top level formulas – the call and put price calculations.
     * 
     * 
     * 
     * 
     * 
     * The Java implementation is as below using the same variable naming as in
     * the legend given above. There is an additional boolean variable which is
     * set to true if the input is a call option and false otherwise.
     *
     */

     public static double calculate(boolean callOption, double s, double k, double r, double t, double v) {
         if (callOption) {
             double cd1 = cumulativeDistribution(d1(s, k, r, t, v));
             double cd2 = cumulativeDistribution(d2(s, k, r, t, v));
             return s * cd1 - k * exp(-r * t) * cd2;
         } else {
             double cd1 = cumulativeDistribution(-d1(s, k, r, t, v));
             double cd2 = cumulativeDistribution(-d2(s, k, r, t, v));
             return k * exp(-r * t) * cd2 - s * cd1;
         }
     }

    /*
     * Black Scholes in Java:
     * The complete implementation
     * 
     * The complete Black Scholes Java implementation is given below to see at a
     * glance.
     * 
     * package name.dhruba.black.scholes.formula; 02
     * 
     * import static java.lang.Math.PI;
     * import static java.lang.Math.abs;
     * import static java.lang.Math.exp;
     * import static java.lang.Math.log;
     * import static java.lang.Math.pow;
     * import static java.lang.Math.sqrt;
     *
     *
     public enum BlackScholesFormula { 11
     *
     * 12 _; 13
     * 
     * 14 private static final double P = 0.2316419; 15 private static final
     * double B1 = 0.319381530; 16 private static final double B2 =
     * -0.356563782; 17 private static final double B3 = 1.781477937; 18 private
     * static final double B4 = -1.821255978; 19 private static final double B5
     * = 1.330274429; 20
     * 
     * 21 public static double calculate(boolean callOption, 22 double s, double
     * k, double r, double t, double v) { 23 if (callOption) { 24 double cd1 =
     * cumulativeDistribution(d1(s, k, r, t, v)); 25 double cd2 =
     * cumulativeDistribution(d2(s, k, r, t, v)); 26 return s * cd1 - k * exp(-r
     * * t) * cd2; 27 } else { 28 double cd1 = cumulativeDistribution(-d1(s, k,
     * r, t, v)); 29 double cd2 = cumulativeDistribution(-d2(s, k, r, t, v)); 30
     * return k * exp(-r * t) * cd2 - s * cd1; 31 } 32 } 33
     * 
     * 34 private static double d1(double s, double k, double r, double t,
     * double v) { 35 double top = log(s / k) + (r + pow(v, 2) / 2) * t; 36
     * double bottom = v * sqrt(t); 37 return top / bottom; 38 } 39
     * 
     * 40 private static double d2(double s, double k, double r, double t,
     * double v) { 41 return d1(s, k, r, t, v) - v * sqrt(t); 42 } 43
     * 
     * 44 public static double cumulativeDistribution(double x) { 45 double t =
     * 1 / (1 + P * abs(x)); 46 double t1 = B1 * pow(t, 1); 47 double t2 = B2 *
     * pow(t, 2); 48 double t3 = B3 * pow(t, 3); 49 double t4 = B4 * pow(t, 4);
     * 50 double t5 = B5 * pow(t, 5); 51 double b = t1 + t2 + t3 + t4 + t5; 52
     * double cd = 1 - standardNormalDistribution(x) * b; 53 return x < 0 ? 1 -
     * cd : cd; 54 } 55
     * 
     * 56 public static double standardNormalDistribution(double x) { 57 double
     * top = exp(-0.5 * pow(x, 2)); 58 double bottom = sqrt(2 * PI); 59 return
     * top / bottom; 60 } 61
     * 
     * 62 } Compare this implementation to the alternative implementations
     * linked to at the beginning of the article. Do you see the differences?
     * Financial algorithms are sufficiently complex without programmers
     * obfuscating their implementations even further. The function by function
     * decomposition technique coupled with region based decomposition of
     * individual functions makes such complex code maintainable and readable
     * which should be our primary objectives when developing such algorithms.
     * This is the guide I wished I had when I started looking into Black
     * Scholes. Bear in mind I have omitted the javadoc on these methods for
     * brevity which would normally contain online links to formulas etc.
     * 
     * Example outputs
     * 
     * A couple of examples follow of the Black Scholes calculation – one call
     * and one put.
     * 
     * 01 public static void main(String[] args) { 02
     * 
     * 03 boolean call; 04 double s, k, r, t, v, price; 05
     * 
     * 06 // call option example 07 call = true; 08 s = 56.25; 09 k = 55; 10 r =
     * 0.0285; 11 t = 0.34; 12 v = 0.28; 13
     * 
     * 14 price = BlackScholesFormula.calculate(call, s, k, r, t, v); 15
     * System.out.println(price); // 4.56 16
     * 
     * 17 // put option example 18 call = false; 19 s = 49; 20 k = 50; 21 r =
     * 0.001; 22 t = 0.25; 23 v = 0.20; 24
     * 
     * 25 price = BlackScholesFormula.calculate(call, s, k, r, t, v); 26
     * System.out.println(price); // 2.51 27
     * 
     * 28 }
     */

}
