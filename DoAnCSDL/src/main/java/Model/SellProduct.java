/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class SellProduct {

    int maspb;

    String tenspb;

    int dongia;

    public SellProduct(int maspb, String tenspb, int dongia) {
        this.maspb = maspb;
        this.tenspb = tenspb;
        this.dongia = dongia;
    }

    public int getMaspb() {
        return maspb;
    }

    public void setMaspb(int maspb) {
        this.maspb = maspb;
    }

    public String getTenspb() {
        return tenspb;
    }

    public void setTenspb(String tenspb) {
        this.tenspb = tenspb;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public SellProduct() {
    }

}
