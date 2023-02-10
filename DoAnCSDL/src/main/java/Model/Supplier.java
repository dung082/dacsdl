/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Supplier {
    int mancc;
    String tennnc;
    String dc;
    String sdt;

    public Supplier(int mancc, String tennnc, String dc, String sdt) {
        this.mancc = mancc;
        this.tennnc = tennnc;
        this.dc = dc;
        this.sdt = sdt;
    }

    public Supplier() {
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTennnc() {
        return tennnc;
    }

    public void setTennnc(String tennnc) {
        this.tennnc = tennnc;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    
}
