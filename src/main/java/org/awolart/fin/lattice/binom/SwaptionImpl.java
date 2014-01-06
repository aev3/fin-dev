/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.lattice.binom;

import java.util.Properties;

import org.awolart.fin.lattice.LatticeFactoryIF;
import org.awolart.fin.lattice.LatticeFactoryImpl;
import org.awolart.fin.lattice.LatticeIF;
import org.awolart.fin.lattice.LatticeType;
import org.awolart.fin.util.PS4Consts;

/**
 * <p>
 * Class that creates a lattice containing the values relating to an
 * American Call Option on a Zero Coupon Bond for different time periods.
 * <p/>
 * This class implements a very sophisticated algorithm
 * $$ r = \frac{1 - d(0,T)}{\sum_{t_0}^{T} d(0,T)} $$.
 */
public class SwaptionImpl implements LatticeIF
{

    LatticeFactoryIF factory = new LatticeFactoryImpl();
    LatticeIF short_rate_impl = factory.getImplementation(LatticeType.SHORT_RATE);
    double[][] short_rate_latt = short_rate_impl.getLattice(PS4Consts.Q1_PROPS);

    LatticeIF swap_impl = factory.getImplementation(LatticeType.SWAP);
    double[][] swap_latt = swap_impl.getLattice(PS4Consts.Q5_PROPS);

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
     * <pre>{@code XL: MAX(0, $B$43*(H37-$B$42)}</pre>
     *  Where B43 is (1/-1) [call/put] and B42 is the strike
     *  H37 is a reference to a price in the zcb lattice
     *
     * {@code XL: MAX(0, $B$43*(H37-$B$42)}
     *
     * <p/>
     * Generate data for all remaining columns where col <= cols-2:
     * <pre>
     *     {@code XL: IF($A52 <=G$45, MAX($B$43*(G37-$B$42),($B$5*H51 + $B$6*H52)/(1+G21 )),"")}
     * </pre>
     *  Where A_n is a reference to a row number and C_n is a reference to
     *  a column number.
     *  <ol>
     *  <li>
     *   If the row number is less than or equal to the column number:
     *  </li>
     *  <li>
     *   If B43 equals 1 or -1 depending on whether the option type is a call[1]
     *   or a put[-1] option
     *  </li>
     *  <li>
     *   Multiply B43 by the option type times the value of 'zcb_latt[row][col]'
     *   minus the 'strike'
     *  </li>
     *  <li>
     *   (i) multiply 'q' times the value of the 'lattice[row+1]{col+1]',
     *   (ii) multiply '1-q' times the value of the 'lattice[row]{col+1]',
     *   (iii) add (i) and (ii) and divide by 1 and add the value of
     *   'short_rate_latt[row][col]'
     *   (iv) return the maximum value
     *  </li>
     *  </ol>
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


        //LatticeIF zcb_impl = factory.getImplementation(LatticeType.ZERO_COUPON_BOND);
        //double[][] zcb_latt = zcb_impl.getLattice(PS4Consts.Q1_PROPS);

        double[][] lattice = new double[rows][cols];

        double q_less = 1 - q;

        /*
         * Generate the last column
         * XL =MAX(G34,0)   G34 is r/c of SwapLattice
         */
        for(int row = rows - 1; row >= 0; --row)
        {
            int col = cols - 1;
            lattice[row][col] =  Math.max(swap_latt[row][col], 0.0);
                    // (short_rate_latt[row][col]-fixed_rate)/(1 + short_rate_latt[row][col]);
            --col;
        }
        /*
         * Generate data for all remaining columns where col <= cols-2:
         * XL =IF($A49 <=F$47,  ($B$5*G48 + $B$6*G49 )/(1+F17 ),"  F17 is R/C short rate
         */
        for(int col = cols - 2; col >= 0; --col)
        {
            for(int row = 0; row <= rows-1; ++row)
            {
                if(row <= col)
                {
                    lattice[row][col] =
                            (q * lattice[row + 1][col + 1] + q_less * lattice[row][col + 1])
                            /(1 + short_rate_latt[row][col]);
                }
            }

        }

        return lattice;

    }
}
