/*
 *
 */

package com.awolart.fin.stats;

/**
 * <p>
 * Collection of static methods use to provide standard deviation calculation.
 * See: Listing 1.7 and Listing 1.8
 * </p>
 */
public class DataDispersion
{

    /*
     * Following methods are from Listing 1.7 and provide basic functionality
     * for the methods in Listing 21.8, infra.
     */

    /**
     * Arithmetic mean for a single array.
     * 
     * @param x
     * 
     * @return
     */
    public static double mean(double[] x)
    {

        double total = 0.0;

        for(int i = 0; i < x.length; ++i)
        {
            total += x[i];
        }

        return total / x.length;
    }

    /**
     * Calculates expected value for variable times probability.
     * 
     * @param x
     * 
     * @return
     */
    public static double mean(double[][] x)
    {

        double total = 0.0;
        double probability = 0.0;

        for(int i = 0; i < x.length; ++i)
        {
            total += (x[i][0] * x[i][1]);
            probability += x[i][1];
        }

        if(probability != 1.0)
        {
            System.out.println("WARNING: The probabilities do not sum to 1.0");
        }

        return total;

    }

    /**
     * Variance of a single variable with equal likelihood.
     * 
     * @param v
     * 
     * @return
     */
    public static double variance(double[] v)
    {
        double variance = 0.0;

        double sumd = 0.0;
        double total = 0.0;

        for(int i = 0; i < v.length; ++i)
        {
            total += v[i];
            sumd += Math.pow(v[i], 2);
        }

        /*
         * variance as the true value of the convergence as length is large
         */
        variance = (sumd - total) / ((v.length) - 1);

        return variance;
    }

    /**
     * Variance of a variable with different probabilities of outcome.
     * 
     * @param v
     * 
     * @return
     */
    public static double variance(double[][] v)
    {
        double variance = 0.0;

        double sumd = 0.0;
        double total = 0.0;
        double total_pow = 0.0;
        double probability = 0.0;

        for(int i = 0; i < v.length; ++i)
        {
            /* mean or expected value */
            total += (v[i][0] * v[i][1]);
            /* e[x^2] */
            total_pow = (Math.pow(v[i][0], 2) * v[i][1]);
            probability += v[i][1];
        }

        if(probability != 1.0)
        {
            System.out.println("WARNING: The probabilities do not sum to 1.0");
        }

        /*
         * variance as the true value of the convergence as length is large
         */
        variance = (total_pow - Math.pow(total, 2));

        return variance;
    }

    /**
     * Computes standard deviation for variance s.
     * 
     * @param s
     * 
     * @return
     */
    public static double standardDeviation(final double s)
    {
        double std_dev = Math.sqrt(s);
        return std_dev;
    }

    /*
     * Following methods are from Listing 1.8
     */

    /**
     * Arithmetic mean for a double list
     * 
     * @param x
     * 
     * @return
     */
    public static double[] dumean(double[][] x)
    {
        double[] total = new double[2];

        double x1 = 0.0;
        double y = 0.0;
        for(int i = 0; i < x.length; ++i)
        {
            x1 += x[i][0];
            y += x[i][1];
        }
        
        total[0] = x1/x.length;
        total[1] = y/x.length;

        return total;
    }

    /**
     * Mean convergence for large length.
     *
     * @param x
     *
     * @return
     */
    public static double convmean(double[] x)
    {
        double total = 0.0;

        for(int i =0; i < x.length; ++i)
        {
            total += x[i];
        }

        total = total/(x.length-1) ;

        return total;
    }

    /**
     * Variance of a single variable with equal likelihood.
     *
     * @param v
     *
     * @return
     */
    public static double[] variances(double[][] v)
    {
        double[] output = new double[2];
        double sumd = 0.0;
        double sumd1 = 0.0;
        double total = 0.0;
        double total1 = 0.0;

        for(int i = 0; i < v.length; ++i)
        {
            total += v[i][0];
            total1 += v[i][1];

            sumd = Math.pow(v[i][0],2);
            sumd1 = Math.pow(v[i][1],2);
        }

        total = (Math.pow(total,2)/v.length);
        total1 = (Math.pow(total1,2)/v.length);
        output[0] = ((sumd-total)/((v.length)-1));
        output[1] = ((sumd1-total1)/((v.length)-1));

        return output;
    }

    /**
     * Variance with equally likely outcomes.
     *
     * @param outcomes
     *
     * @return
     */
    public static double covariance(double[][] outcomes)
    {
        double covariance = 0.0;

        double sa = 0.0;
        double sb = 0.0;
        double product = 0.0;

        int size = outcomes.length;

        for(int i = 0; i < size; ++i)
        {
            sa += outcomes[i][0];
            sb += outcomes[i][1];
        }

        double samn = sa/size;
        double sbmn = sb/size;

        for(int i = 0; i < size; ++i)
        {
            /* sum of products of deviation */
            product += ((outcomes[i][0]-samn) * (outcomes[i][1]-sbmn));
        }

        covariance = product/size;

        return covariance;
    }

    /**
     * Inputs of non-equal joint outcomes.
     *
     * @param outcomes
     *
     * @return
     */
    public static double covariance2(double[][] outcomes)
    {
        double covariance = 0.0;

        double productx = 0.0;
        double producty = 0.0;

        int size = outcomes.length;

        for(int i = 0; i < size; ++i)
        {
            productx += outcomes[i][0] * outcomes[i][2];
            producty += outcomes[i][1] * outcomes[i][2];
        }

        for(int j = 0; j < size; ++j)
        {
            double xdevs = outcomes[j][0] - productx;
            double ydevs = outcomes[j][1] - producty;
            double devproduct = xdevs * ydevs;
            double covprobs = devproduct * outcomes[j][2];
            covariance += covprobs;
        }

        return covariance;
    }

    public static double correlation(double covariance, double sd1, double sd2)
    {
        double correlation = covariance/(sd1*sd2);
        return correlation;
    }

}
