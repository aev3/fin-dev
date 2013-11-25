/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu.utils;

import com.awolart.fin.cu.LatticeFactoryIF;
import com.awolart.fin.cu.LatticeFactoryImpl;
import com.awolart.fin.cu.LatticeIF;
import com.awolart.fin.cu.LatticeType;
import com.awolart.fin.cu.ps3.PS3Consts;
import com.awolart.fin.cu.ps4.PS4Consts;

import static com.awolart.fin.cu.ps4.PS4Consts.Q1_PROPS;
import static com.awolart.fin.cu.ps4.PS4Consts.Q2_PROPS;
import static com.awolart.fin.cu.ps4.PS4Consts.Q4_PROPS;
import static com.awolart.fin.cu.ps4.PS4Consts.Q5_PROPS;
import static com.awolart.fin.cu.ps4.PS4Consts.Q6_PROPS;


/**
 * <p>
 *     Utility class for testing LatticeIF implementations.
 * <p/>
 * All final static variables are accessible from @{link PS3Consts}.
 * </p>
 */
public class LatticeUtility
{
    static void printLattice(double[][] lattice, String reference, boolean verbose)
    {
        System.out.println(reference);
        for(int i = 0; i < lattice[0].length; ++i)
        {
            for(int j = 0; j < lattice[1].length; ++j)
            {
                if(verbose)
                {
                    System.out.print("[" + i + "][" + j + "] =");
                }

                System.out.printf("%9.8f ", lattice[i][j]);

            }

            System.out.println();

        }
    }

    public static void main(String[] args)
    {
        /*
        Each of the following questions should be answered by building an n=10-period
        binomial model for the short-rate, ri,j.
        The lattice parameters are:
        r0,0=5%,
        u=1.1,
        d=0.9 and
        q=1−q=1/2.
        */
        LatticeFactoryIF factory = new LatticeFactoryImpl();

        //LatticeIF shortRateImpl = factory.getImplementation(LatticeType.SHORT_RATE);
        //double[][] sr_latt  = shortRateImpl.getLattice(Q1_PROPS);
        //LatticeUtility.printLattice(sr_latt, "Short Rate Lattice:", false);

        //LatticeIF zeroCouponBondImpl = factory.getImplementation(LatticeType.ZERO_COUPON_BOND);
        //double[][] zcb_latt  = zeroCouponBondImpl.getLattice(Q1_PROPS);
        //LatticeUtility.printLattice(zcb_latt, "Zero Coupon Bond Lattice:", false);

        //LatticeIF zeroCouponBondImpl = factory.getImplementation(LatticeType.ZERO_COUPON_BOND);
        //double[][] zcb_latt  = zeroCouponBondImpl.getLattice(Q2_PROPS);
        //LatticeUtility.printLattice(zcb_latt, "Zero Coupon Bond Lattice:", false);

        //LatticeIF bondForwardImpl = factory.getImplementation(LatticeType.BOND_FORWARD);
        //double[][] bond_fwd_latt  = bondForwardImpl.getLattice(Q2_PROPS);
        //LatticeUtility.printLattice(bond_fwd_latt, "Bond Forward ZCB Lattice:", false);

        //LatticeIF bondFuturesImpl = factory.getImplementation(LatticeType.BOND_FUTURES);
        //double[][] bond_fut_latt  = bondFuturesImpl.getLattice(Q3_PROPS);
        //LatticeUtility.printLattice(bond_fut_latt, "Bond Futures ZCB Lattice:", false);

        //LatticeIF americanCallZcbImpl = factory.getImplementation(LatticeType.AMER_CALL_ZERO);
        //double[][] amer_call_zcb_latt  = americanCallZcbImpl.getLattice(Q4_PROPS);
        //LatticeUtility.printLattice(amer_call_zcb_latt, "American Call ZCB Lattice:", false);

        LatticeIF swapImpl = factory.getImplementation(LatticeType.SWAP);
        double[][] swap_latt  = swapImpl.getLattice(Q5_PROPS);
        LatticeUtility.printLattice(swap_latt, "Swap Lattice:", false);

        //LatticeIF swaptionImpl = factory.getImplementation(LatticeType.SWAPTION);
        //double[][] swaption_latt  = swaptionImpl.getLattice(Q6_PROPS);
        //LatticeUtility.printLattice(swaption_latt, "Swaption Lattice:", false);

    }

}
