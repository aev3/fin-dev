/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.lattice.binom;

import java.util.Properties;

import com.awolart.fin.cu.LatticeFactoryIF;
import com.awolart.fin.cu.LatticeFactoryImpl;
import com.awolart.fin.cu.LatticeIF;
import com.awolart.fin.cu.LatticeType;
import com.awolart.fin.cu.ps4.PS4Consts;

/**
 * <p>
 * Class that creates a lattice containing the values relating to an
 * American Call Option on a Zero Coupon Bond for different time periods.
 * </p>
 */
public class SwapImpl implements LatticeIF
{
    /**
     * <p>
     * Method that uses the Properties object passed as the sole parameter to
     * obtain the data that is relevant to the construction of the lattice via
     * its call to the createLattice(int, int, double, double) method of this class.
     * </p>
     *
     * @param props A Properties object that contains all the data necessary
     *              to create a lattice.
     *
     * @return double[][] lattice containing data for all row/column pairs
     */
    public double[][] getLattice(Properties props)
    {
        int rows = Integer.parseInt(props.getProperty("rows"));
        int cols = Integer.parseInt(props.getProperty("cols"));
        double rate = Double.parseDouble(props.getProperty("rate"));
        double q = Double.parseDouble(props.getProperty("q"));
        double fixed_rate = Double.parseDouble(props.getProperty("fixed_rate"));

        return createLattice(rows, cols, q, rate, fixed_rate);
    }

    /**
     *
     * <p>
     * Generate the last column with the value of the 'zcb_latt[n][n] - strike'
     * Last column:
     * Excel: MAX(0, $B$43*(H37-$B$42)
     *  Where B43 is (1/-1) [call/put] and B42 is the strike
     *  H37 is a reference to a price in the zcb lattice
     * <p/>
     * Generate data for all remaining columns where col <= cols-2:
     * Excel: @r0,c5 IF($A52 <=G$45, MAX($B$43*(G37-$B$42),($B$5*H51 + $B$6*H52)/(1+G21 )),""
     *  Where A_n is a reference to a row number and C_n is a reference to
     *  a column number.
     *  (0) If the row number is less than or equal to the column number:
     *  (1) B43 equals 1 or -1 depending on whether the option type is a call[1] or a put[-1] option
     *  (2) Multiply B43 by the option type times the value of 'zcb_latt[row][col]' minus the 'strike'
     *  (3) (a) multiply 'q' times the value of the 'lattice[row+1]{col+1]'
     *      (b) multiply '1-q' times the value of the 'lattice[row]{col+1]'
     *      (c) add (a) and (b) and divide by 1 + the value of 'short_rate_latt[row][col]'
     *  (4) return the greater of (1) and (4)
     *  <p/>
     * This method implements a reverse column lattice in which the last column
     * is created with a constant value equal to the face value of the bond,
     * which in this case is 100.00. All other column values are generated in
     * reverse order; i.e., row 9, 8, 7, ..., 0.
     * </p>
     *
     * @param rows
     * @param cols
     * @param q
     *
     * @return
     */
    public double[][] createLattice(int rows, int cols, double q, double rate, double fixed_rate)
    {
        LatticeFactoryIF factory = new LatticeFactoryImpl();

        LatticeIF short_rate_impl = factory.getImplementation(LatticeType.SHORT_RATE);
        double[][] short_rate_latt = short_rate_impl.getLattice(PS4Consts.Q1_PROPS);

        double[][] lattice = new double[rows][cols];

        double q_less = 1 - q;

        /*
         * Generate the last column
         */
        for(int row = rows - 1; row >= 0; --row)
        {
            //XL = (G16-$C$20)/(1+G16)
            int col = cols - 1;
            lattice[row][col] = (short_rate_latt[row][col]-fixed_rate)/(1 + short_rate_latt[row][col]);
            --col;
        }
        /*
         * Generate data for all remaining columns where col <= cols-2:
         */
        for(int col = cols - 2; col >= 0; --col)
        {
            for(int row = 0; row <= rows-1; ++row)
            {
                if(row <= col)
                {
                    /*
                     * Compute the initial value of a forward-starting swap that begins at t=1,
                     * with maturity t=10 and a fixed rate of 4.5%.
                     * The first payment then takes place at t=2 and the
                     * final payment takes place at t=11 as we are assuming, as usual,
                     * that payments take place in arrears.
                     * You should assume a swap notional of 1 million and assume that
                     * you receive floating and pay fixed.
                     */
                    if(col == 0)
                    {
                        // XL = IF($A39 <=B$28,  ($B$5*C38 + $B$6*C39 )/(1+B21 ),"
                        lattice[row][col] =  (q * lattice[row + 1][col + 1] + q_less * lattice[row][col + 1])
                                /(1 + short_rate_latt[row][col]);
                    }

                    else
                    {
                        // XL = IF($A39 <=C$28,  ((C21-$C$25)+$B$5*D38 + $B$6*D39 )/(1+C21 ),""
                        double sr =  short_rate_latt[row][col];
                        lattice[row][col] = ( (sr - fixed_rate)
                                + q * lattice[row + 1][col + 1] + q_less * lattice[row][col + 1])
                                /(1 + sr);
                    }

                }
            }

        }

        return lattice;

    }
}
