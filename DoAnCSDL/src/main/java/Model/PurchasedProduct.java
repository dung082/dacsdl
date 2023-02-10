/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class PurchasedProduct {
    int masp;
    String tensp;
    int dg;
    Date ngsx;

    public PurchasedProduct() {
    }

    public PurchasedProduct(int masp, String tensp, int dg, Date ngsx) {
        this.masp = masp;
        this.tensp = tensp;
        this.dg = dg;
        this.ngsx = ngsx;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getDg() {
        return dg;
    }

    public void setDg(int dg) {
        this.dg = dg;
    }

    public Date getNgsx() {
        return ngsx;
    }

    public void setNgsx(Date ngsx) {
        this.ngsx = ngsx;
    }
    
    
}
