package com.example.proyectonicolas.modelo;

import java.util.Date;

public class Garment {
    private int clothesNumber;
    private String clothesName;
    private int clothesBrandNumber;
    private Date launchDate;
    private  int avalible;
    private float earnings;
    private String countryManufacture;

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

    public int getClothesNumber() {
        return clothesNumber;
    }

    public void setClothesNumber(int clothesNumber) {
        this.clothesNumber = clothesNumber;
    }

    public String getClothesName() {
        return clothesName;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public int getClothesBrandNumber() {
        return clothesBrandNumber;
    }

    public void setClothesBrandNumber(int clothesBrandNumber) {
        this.clothesBrandNumber = clothesBrandNumber;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public float getEarnings() {
        return earnings;
    }

    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    public int getAvalible() {
        return avalible;
    }

    public void setAvalible(int avalible) {
        this.avalible = avalible;
    }

    public String getCountryManufacture() {
        return countryManufacture;
    }

    public void setCountryManufacture(String countryManufacture) {
        this.countryManufacture = countryManufacture;
    }


}
