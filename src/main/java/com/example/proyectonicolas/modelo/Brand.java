package com.example.proyectonicolas.modelo;

import java.util.Date;

/**
 * Brand model
 */
public class Brand {
    private int brandNumber;
    private String brandName;
    private float earnings;
    private Date fundation;
    private String headquarters;
    private String web;
    private int isSporty;
    private String isin;

    /**Creates a new brand
     * @param brandNumber
     * @param brandName
     * @param earnings
     * @param fundation
     * @param headquarters
     * @param web
     * @param isSporty
     * @param isin
     */
    public Brand(int brandNumber, String brandName, float earnings, Date fundation, String headquarters, String web, int isSporty, String isin) {
        this.brandNumber = brandNumber;
        this.brandName = brandName;
        this.earnings = earnings;
        this.fundation = fundation;
        this.headquarters = headquarters;
        this.web = web;
        this.isSporty = isSporty;
        this.isin = isin;
    }
    public Brand(int brandNumber, String brandName, float earnings, Date fundation, String headquarters, int isSporty, String isin) {
        this.brandNumber = brandNumber;
        this.brandName = brandName;
        this.earnings = earnings;
        this.fundation = fundation;
        this.headquarters = headquarters;
        this.web = null;
        this.isSporty = isSporty;
        this.isin = isin;
    }

    /**Returns a string of every value
     * @return string
     */
    @Override
    public String toString() {
        return "Brand{" +
                "brandNumber=" + brandNumber +
                ", brandName='" + brandName + '\'' +
                ", earnings=" + earnings +
                ", fundation=" + fundation +
                ", headquarters='" + headquarters + '\'' +
                ", web='" + web + '\'' +
                ", isSporty=" + isSporty +
                ", isis='" + isin + '\'' +
                '}';
    }

    /** Gets brand number
     * @return brandNumber
     */
    public int getBrandNumber() {
        return brandNumber;
    }

    /**Sets brand number
     * @param brandNumber
     */
    public void setBrandNumber(int brandNumber) {
        this.brandNumber = brandNumber;
    }

    /**Gets brand name
     * @return brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /** Sets brand name
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**Get earnings value
     * @return earnings
     */
    public float getEarnings() {
        return earnings;
    }

    /**Set earnings value
     * @param earnings
     */
    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    /**Gets fundation values
     * @return date of fundation
     */
    public Date getFundation() {
        return fundation;
    }

    /** Sets fundation value
     * @param fundation
     */
    public void setFundation(Date fundation) {
        this.fundation = fundation;
    }

    /** Returns headquarters value
     * @return headquarters value
     */
    public String getHeadquarters() {
        return headquarters;
    }

    /** Sets headquarters value
     * @param headquarters
     */
    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    /**Gets web value
     * @return
     */
    public String getWeb() {
        return web;
    }

    /**Sets web value
     * @param web
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**Returns if the brand is sporty
     * @return
     */
    public int getIsSporty() {
        return isSporty;
    }

    /**Sets if the brand is sporty
     * @param isSporty
     */
    public void setSporty(boolean isSporty) {
        isSporty = isSporty;
    }

    /**Gets isin code
     * @return
     */
    public String getIsin() {
        return isin;
    }

    /**Sets isin code using parameter
     * @param isin
     */
    public void setIsin(String isin) {
        this.isin = isin;
    }
}
