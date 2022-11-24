package com.example.proyectonicolas.modelo;

import java.util.Date;

public class Brand {
    private int brandNumber;
    private String brandName;
    private float earnings;
    private Date fundation;
    private String headquarters;
    private String web;
    private int isSporty;
    private String isin;

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

    public int getBrandNumber() {
        return brandNumber;
    }

    public void setBrandNumber(int brandNumber) {
        this.brandNumber = brandNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public float getEarnings() {
        return earnings;
    }

    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    public Date getFundation() {
        return fundation;
    }

    public void setFundation(Date fundation) {
        this.fundation = fundation;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getIsSporty() {
        return isSporty;
    }

    public void setSporty(boolean isSporty) {
        isSporty = isSporty;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }
}
