/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.lattice;

import java.util.Properties;

/**
 * <p>
 *     A simple interface for lattice implementations.
 * </p>
 */
public interface LatticeIF {

    /**
     * <p>
     *     The sole method required to be implemented by all LatticeUtility objects.
     *     This method creates and returns a lattice populated with the data
     *     required for its intended use.
     * </p>
     *
     * @param props A Properties object that contains all the data necessary
     *              to create a lattice.
     *
     * @return  double[][] matrix containing the row and column data for the
     *          lattice.
     *
     */
    public double[][] getLattice(Properties props);

}
