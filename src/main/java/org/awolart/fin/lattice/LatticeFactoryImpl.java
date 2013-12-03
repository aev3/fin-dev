/*
 *  Copyright (c) 2013 AWOLart.com
 */

package org.awolart.fin.lattice;

import com.awolart.fin.cu.ps4.*;

/**
 * <p>
 * Simple implementation class for LatticeIF interface that actually
 * instantiates the appropriate implemention of the LatticeIF interface based
 * upon a LatticeType.
 * </p>
 *
 */
public class LatticeFactoryImpl implements LatticeFactoryIF
{
    /**
     * LatticeIF implemenatation class.
     */
    private LatticeIF implementation;

    /**
     * <p>
     * Default no argument constructor.
     * </p>
     */
    public LatticeFactoryImpl()
    {
        /* Default no argument constructor */
    }

    /**
     *
     * <p>
     * Implementation of getImplementation(LatticeType type) method
     * as required by LatticeIF. This method uses a switch statement to
     * determine the actual implementation class.
     * </p>
     *
     * @param type - enum for type of LatticeIF implementation
     *
     * @return LatticeIF - implementation class
     *
     */
    public final LatticeIF getImplementation(final LatticeType type)
    {
        switch(type)
        {
            case SHORT_RATE:
            {
                this.implementation = new ShortRateLatticeImpl();
                break;
            }

            case ZERO_COUPON_BOND:
            {
                this.implementation = new ZeroCouponBondLatticeImpl();
                break;
            }

            case AMER_CALL_ZERO:
            {
                this.implementation = new AmericanCallZeroLatticeImpl();
                break;
            }

            case BOND_FORWARD:
            {
                this.implementation = new BondForwardImpl();
                break;
            }

            case BOND_FUTURES:
            {
                this.implementation = new BondFuturesImpl();
                break;
            }

            case SWAP:
            {
                this.implementation = new SwapImpl();
                break;
            }

            case SWAPTION:
            {
                this.implementation = new SwaptionImpl();
                break;
            }

            default:
            {
                System.err.println("No approriate implementation.");
                break;
            }

        } // switch ( searchType )

        return this.implementation;
    }

}
