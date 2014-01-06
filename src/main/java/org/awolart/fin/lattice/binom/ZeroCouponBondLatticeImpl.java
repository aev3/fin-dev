/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.lattice.binom;

import java.util.Properties;

import org.awolart.fin.lattice.LatticeIF;


/**
 * <p>
 * Class that creates a lattice containing the values relating to a
 * Zero Coupon Bond for different time periods.
 * </p>
 */
public class ZeroCouponBondLatticeImpl implements LatticeIF
{
    /**
     /**
     * <p>
     * Method that uses the Properties object passed as the sole parameter to
     * obtain the data that is relevant to the construction of the lattice via
     * its call to the createLattice(int, int, double, double, double, double, double)
     * method of this class.
     * </p>
     *
     *
     * @param props A Properties object that contains all the data necessary
     *              to create a lattice.
     *
     * @return double[][] lattice containing data for all row/column pairs
     */
    public double[][] getLattice(Properties props)
    {
        Integer rows = Integer.parseInt(props.getProperty("rows"));
        Integer cols = Integer.parseInt(props.getProperty("cols"));
        double rate = Double.parseDouble(props.getProperty("rate"));
        double up = Double.parseDouble(props.getProperty("up"));
        double down = Double.parseDouble(props.getProperty("down"));
        double q = Double.parseDouble(props.getProperty("q"));
        double fv = Double.parseDouble(props.getProperty("fv"));

        return createLattice(rows, cols, rate, up, down, q, fv);
    }

    /**
     *
     * <p>
     * Excel: @r0,c1 IF($A37<=C$26,($B$5*D36 + $B$6*D37)/(1+C21), ""
     * Excel: @r1,c1 IF($A36 <=C$26,($B$5*D35 + $B$6*D36)/(1+C20), ""
     * Where A_n is a reference to a row number and C_n is a reference to
     * a column number. Thus, if the row number is less than or equal to
     * the column number:
     * <ol>
     *     <li>
     *         multiply the constant 'q' times the value of lattice[row+1][col+1],
     *     </li>
     *     <li>
     *         add the value of the constant (1-q) times the value of lattice[row][col+1],
     *     </li>
     *     <li>
     *         divide the sum of the preceding calculations by 1 plus the value of
     *         short_rate_lattice[n][n].
     *     </li>
     *   </ol>
     * <p/>
     * This method implements a reverse column lattice in which the last column
     * is created with a constant value equal to the face value of the bond,
     * which in this case is 100.00. All other column values are generated in
     * reverse order; i.e., row 9, 8, 7, ..., 0.
     * </p>
     *
     * @param rows
     * @param cols
     * @param rate
     * @param up
     * @param down
     * @param q
     * @param fv
     *
     * @return
     */
    public double[][] createLattice(int rows, int cols, double rate,
            double up, double down, double q, double fv)
    {
        ShortRateLatticeImpl impl = new ShortRateLatticeImpl();
        double[][] short_rate_latt = impl.createLattice(rows, cols, rate, up,
                down, q);

        double[][] lattice = new double[rows][cols];

        double q_less = 1 - q;

        /*
         * Generate the last column with the value of the 'fv' constant
         */
        for(int row = rows - 1; row >= 0; --row)
        {
            int col = cols - 1;
            lattice[row][col] = fv;
            --col;
        }
        /*
         * Generate data for all columns <= cols-2
         */
        for(int col = cols - 2; col >= 0; --col)
        {
            for(int row = rows - 1; row >= 0; --row)
            {
                if(row <= col)
                {
                    lattice[row][col] = (q * lattice[row + 1][col + 1] + q_less
                            * lattice[row][col + 1])
                            / (1 + short_rate_latt[row][col]);
                }
            }

        }

        return lattice;

    }
}
