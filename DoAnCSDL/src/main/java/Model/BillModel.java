/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class BillModel {
    int maspb;

    String tenspb;

    int dongia;
    
    int sl;

    public BillModel(int maspb, String tenspb, int dongia, int sl) {
        this.maspb = maspb;
        this.tenspb = tenspb;
        this.dongia = dongia;
        this.sl = sl;
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

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public BillModel() {
    }
    
    
}
