/*
 *  Copyright (c) 2013 AWOLart.com
 */

package com.awolart.fin.cu;

/**
 * <p>
 *     A simple enum class that provides types for LatticeIF implementations.
 * </p>
 */
public enum LatticeType {
    SHORT_RATE,
    ZERO_COUPON_BOND,
    AMER_CALL_ZERO,
    BOND_FORWARD,
    BOND_FUTURES,
    SWAP,
    SWAPTION,
    AO_SL,
    AO_OL;
}
