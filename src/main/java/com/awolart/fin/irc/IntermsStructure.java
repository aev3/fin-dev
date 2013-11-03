/*
 *
 */

package com.awolart.fin.irc;

import com.awolart.fin.core.math.Interpolate;

/**
 * <p>
 * </p>
 */
public class IntermsStructure extends Interms
{
    Interpolate It = new Interpolate();

    private int current_flag = 0;

    private double[][] current_data;

    /**
     * Some text {@latex.inline $x=y^2 + x_n,\\quad n \\in \\mathcal{L}$} and some more text
     *
     */
    public IntermsStructure() {

    }

    public double DiscpOne(double iterate, double time_1)
    {
        return DiscFromYield(iterate, time_1);
    }

    public double SpotpOne(double iterate, double time_1)
    {
        return YieldFromDisc(iterate, time_1);
    }

    public double Forwdisc(double iterate_1, double iterate_2,
                           double time_1, double time_2)
    {
        return  RateFromDisc(iterate_1, iterate_2, time_1, time_2);
    }


    public double Forwyld(double iterate_1, double iterate_2,
                           double time_1, double time_2)
    {
        return  RateFromYield(iterate_1, iterate_2, time_1, time_2);
    }

    public void setCurrentRateData(double[][] data)
    {
        current_data = data;
        current_flag = 1;
    }

    public double getCurrentDiscOne(double timepoint)
    {
        return ErrorCheck(timepoint) == 1 ? DiscFromYield(
                It.lagrange(current_data, timepoint), timepoint):0.0;
    }

    public double getCurrentSpotOne(double timepoint)
    {
        return ErrorCheck(timepoint) == 1 ? It.lagrange(current_data, timepoint):0.0;
    }

    public double getCurrentForwardRateYields(double timepoint1, double timepoint2)
    {
        return (ErrorCheck(timepoint1) == 1 & ErrorCheck(timepoint2) == 1) ?
        (RateFromYield(getCurrentSpotOne(timepoint1), getCurrentSpotOne(timepoint2),
                timepoint1, timepoint2)):0.0;
    }



    public void intermtimes() {
        // Implementation? of abstract method from Interms
    }

    public int ErrorCheck(double timepoint)
    {
        if(current_flag == 0)
        {
            return 0;
        }

        int n = current_data.length;

        if((timepoint < current_data[0][0]) || (timepoint > current_data[n-1][0]))
        {
            return 0;
        }

        return 1;
    }


}
