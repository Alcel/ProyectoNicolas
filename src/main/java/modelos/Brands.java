package modelos;
import java.util.Date;
public class Brands {
    private int brandNumber;
    private  String brandName;
    private  float earnings;
    private  Date fundation;
    private  String headquarters;
    private  String web;
    private  boolean isSporty;
    private  String isin;

    public Brands(int brandNumber, String brandName, float earnings, Date fundation, String headquarters, String web, boolean isSporty, String isin) {
        this.brandNumber = brandNumber;
        this.brandName = brandName;
        this.earnings = earnings;
        this.fundation = fundation;
        this.headquarters = headquarters;
        this.web = web;
        this.isSporty = isSporty;
        this.isin = isin;
    }

    //Faltan setters

    public int getBrandNumber() {
        return brandNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public float getEarnings() {
        return earnings;
    }

    public Date getFundation() {
        return fundation;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public String getWeb() {
        return web;
    }

    public boolean isSporty() {
        return isSporty;
    }

    public String getIsin() {
        return isin;
    }

    public void setBrandNumber(int brandNumber) {
        this.brandNumber = brandNumber;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    public void setFundation(Date fundation) {
        this.fundation = fundation;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setSporty(boolean sporty) {
        isSporty = sporty;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "brandNumber=" + brandNumber +
                ", brandName='" + brandName + '\'' +
                ", earnings=" + earnings +
                ", fundation=" + fundation +
                ", headquarters='" + headquarters + '\'' +
                ", web='" + web + '\'' +
                ", isSporty=" + isSporty +
                ", isin='" + isin + '\'' +
                '}';
    }
}
