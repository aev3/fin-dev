/*
 *
 */

package com.awolart.fin.bonds;

import com.awolart.fin.irc.Intr;

/**
 * <p>
 * </p>
 */
public class Volatility {

    private double mktprice;
    private double mktpricelow;
    private double mktpricenew;
    private double couponvalue;
    private double facevalue;
    private double frequency;
    private double pv;
    private double par;
    private double relativeprice;
    private double relativepricelow;
    private double upyield;
    private double downyield;
    private double newpriceup;
    private double newpricedown;
    private double currentyield;
    private double currentpvb;

    public Volatility()
    {
        this.facevalue = 100.0;
        this.frequency = 2.0;
    }


    public Volatility(double parvalue, double coupontimes)
    {
        this.facevalue = parvalue;
        this.frequency = coupontimes;
    }

    public double getMktprice() {
        return mktprice;
    }

    public void setMktprice(double mktprice) {
        this.mktprice = mktprice;
    }

    public double getMktpricelow() {
        return mktpricelow;
    }

    public void setMktpricelow(double mktpricelow) {
        this.mktpricelow = mktpricelow;
    }

    public double getMktpricenew() {
        return mktpricenew;
    }

    public void setMktpricenew(double mktpricenew) {
        this.mktpricenew = mktpricenew;
    }

    public double getCouponvalue() {
        return couponvalue;
    }

    public void setCouponvalue(double couponvalue) {
        this.couponvalue = couponvalue;
    }

    public double getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(double facevalue) {
        this.facevalue = facevalue;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public double getPar() {
        return par;
    }

    public void setPar(double par) {
        this.par = par;
    }

    public double getRelativeprice() {
        return relativeprice;
    }

    public void setRelativeprice(double relativeprice) {
        this.relativeprice = relativeprice;
    }

    public double getRelativepricelow() {
        return relativepricelow;
    }

    public void setRelativepricelow(double relativepricelow) {
        this.relativepricelow = relativepricelow;
    }

    public double getUpyield() {
        return upyield;
    }

    public void setUpyield(double upyield) {
        this.upyield = upyield;
    }

    public double getDownyield() {
        return downyield;
    }

    public void setDownyield(double downyield) {
        this.downyield = downyield;
    }

    public double getNewpriceup() {
        return newpriceup;
    }

    public void setNewpriceup(double newpriceup) {
        this.newpriceup = newpriceup;
    }

    public double getNewpricedown() {
        return newpricedown;
    }

    public void setNewpricedown(double newpricedown) {
        this.newpricedown = newpricedown;
    }

    public double getCurrentyield() {
        return currentyield;
    }

    public void setCurrentyield(double currentyield) {
        this.currentyield = currentyield;
    }

    public double getCurrentpvb() {
        return currentpvb;
    }

    public void setCurrentpvb(double currentpvb) {
        this.currentpvb = currentpvb;
    }

    /* Price value of a basis point */
    public void pVbPoints(double yield, double yearterm, double coupon, double pointchange)
    {
        double yieldval;
        mktprice = Bpricing(yield, yearterm, coupon);
    }

    public double Bpricing(double yield, double yearterm, double coupon)
    {
        couponvalue = ( ( facevalue * coupon / 100) / frequency );
        pv = ( couponvalue * Intr.pvancert( ( yield/100.0 ) / frequency,
                ( frequency * yearterm ) ) );
        par = ( facevalue * ( 1.0  / Math.pow( 1.0 + ( yield / 100.0) / frequency,
                ( frequency * yearterm ) ) ) );
        return pv + par;
    }
}
