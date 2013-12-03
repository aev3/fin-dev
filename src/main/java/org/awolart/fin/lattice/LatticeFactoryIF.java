/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.lattice;

/**
 /**
 * <p>
 * A simple class that implements the abstract factory design pattern for the
 * classes that implement the LatticeIF interface.
 * </p>
 */
public interface LatticeFactoryIF {

    /**
     * <p>
     * Default getImplementation method that takes a LatticeType and
     * instantiates the appropriate LatticeIF implementation.
     * </p>
     *
     * @param type
     *            A LatticeType that is used to determine which LatticeIF
     *            implementation to instantiate.
     *
     * @return LatticeIF - implementation class
     *
     */
    public LatticeIF getImplementation(LatticeType type);

}
