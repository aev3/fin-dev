/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.lattice.binom;

import java.util.Properties;

import com.awolart.fin.cu.LatticeIF;

/**
 * <p>
 * Class that creates a lattice containing the interest rate referred to
 * as the "short rate" at different time periods.
 * </p>
 */
public class ShortRateLatticeImpl implements LatticeIF
{
    /**
     * <p>
     * Method that uses the Properties object passed as the sole parameter to
     * obtain the data that is relevant to the construction of the lattice via
     * its call to the createLattice(int, int, double, double, double, double)
     * method of this class.
     * </p>
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

        return createLattice(rows, cols, rate, up, down, q);
    }

    /*
     * Excel: @r0,c0 aka C10
     *  IF($A21<C$10,
     *      $B$4*OFFSET(C21,0,-1),
     *      IF($A21=C$10,
     *          $B$3*OFFSET(C21,1,-1),
     *  "")
     */

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
     *
     * @return
     */
    public double[][] createLattice(int rows, int cols, double rate,
            double up, double down, double q)
    {
        double[][] lattice = new double[rows][cols];

        for(int row = 0; row < rows; ++row)
        {
            for(int col = 0; col < cols; ++col)
            {
                /*
                 * Generate the first column and first row with the value
                 * of the 'rate' constant
                 */
                if(row == 0 && col == 0)
                {
                    lattice[0][0] = rate;
                }
                /*
                 * Generate data for all columns > 0 in row 0
                 */
                else if(row == 0 && col != 0)
                {
                    lattice[row][col] = down * lattice[row][col - 1];

                }
                /*
                 * Where the row number is equal to the column number, multiply
                 * 'up' constant times the value in lattice[row-1][column-1].
                 * All other values in this row will be dealt with in the next
                 * conditional.
                 */
                else if(row == col)
                {
                    lattice[row][col] = up * lattice[row - 1][col - 1];
                }
                /*
                 * Generate data for all columns greater than the row number.
                 * This will only work where the values have already been
                 * generated where row number equal column number as set forth
                 * in the preceding conditional.
                 */
                else if(row < col)
                {
                    lattice[row][col] = up * lattice[row - 1][col - 1];
                }

            }

        }

        return lattice;

    }
}
