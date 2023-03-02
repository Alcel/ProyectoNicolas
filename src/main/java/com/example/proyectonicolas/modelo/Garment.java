package com.example.proyectonicolas.modelo;

import java.util.Date;

/**
 * Garment model
 */
public class Garment {
    private int clothesNumber;
    private String clothesName;
    private int clothesBrandNumber;
    private Date launchDate;
    private  int avalible;
    private float earnings;
    private String countryManufacture;

    /**Creates a new Garment
     * @param clothesNumber
     * @param clothesName
     * @param clothesBrandNumber
     * @param launchDate
     * @param earnings
     * @param avalible
     * @param countryManufacture
     */
    public Garment(int clothesNumber, String clothesName,int clothesBrandNumber, Date launchDate ,float earnings,
                   int avalible, String countryManufacture) {
        this.clothesNumber = clothesNumber;
        this.clothesName = clothesName;
        this.earnings = earnings;
        this.clothesBrandNumber = clothesBrandNumber;
        this.launchDate = launchDate;
        this.earnings = earnings;
        this.avalible = avalible;
        this.countryManufacture = countryManufacture;
    }

    /**Gets clothes number
     * @return clothesNumber
     */
    public int getClothesNumber() {
        return clothesNumber;
    }

    /**Sets a clothes number
     * @param clothesNumber
     */
    public void setClothesNumber(int clothesNumber) {
        this.clothesNumber = clothesNumber;
    }

    /** Gets the clothes name
     * @return clothesName
     */
    public String getClothesName() {
        return clothesName;
    }

    /**Sets the clothe's name
     * @param clothesName
     */
    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    /** Gets the clothes brand number
     * @return clothesBrandNumber
     */
    public int getClothesBrandNumber() {
        return clothesBrandNumber;
    }

    /** Sets the clothes brand number
     * @param clothesBrandNumber
     */
    public void setClothesBrandNumber(int clothesBrandNumber) {
        this.clothesBrandNumber = clothesBrandNumber;
    }

    /**Gets the launch date
     * @return launchDate
     */
    public Date getLaunchDate() {
        return launchDate;
    }

    /**Sets the launch date
     * @param launchDate
     */
    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    /** Gets the earnings
     * @return earnings
     */
    public float getEarnings() {
        return earnings;
    }

    /**Sets the earning
     * @param earnings
     */
    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    /**Returns the availability of a garment
     * @return avalible
     */
    public int getAvalible() {
        return avalible;
    }

    /** Sets the availability of a garment
     * @param avalible
     */
    public void setAvalible(int avalible) {
        this.avalible = avalible;
    }

    /** Gets the country of manufacture
     * @return countryManufacture
     */
    public String getCountryManufacture() {
        return countryManufacture;
    }

    /** Sets the country of manufacture
     * @param countryManufacture
     */
    public void setCountryManufacture(String countryManufacture) {
        this.countryManufacture = countryManufacture;
    }


}
