/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Employee {

    int mkh;
    String htkh;
    String dc;
    String sdt;
    int diemtl;

    public Employee(int mkh, String htkh, String dc, String sdt, int diemtl) {
        this.mkh = mkh;
        this.htkh = htkh;
        this.dc = dc;
        this.sdt = sdt;
        this.diemtl = diemtl;
    }

    public Employee() {
    }

    public int getMkh() {
        return mkh;
    }

    public void setMkh(int mkh) {
        this.mkh = mkh;
    }

    public String getHtkh() {
        return htkh;
    }

    public void setHtkh(String htkh) {
        this.htkh = htkh;
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

    public int getDiemtl() {
        return diemtl;
    }

    public void setDiemtl(int diemtl) {
        this.diemtl = diemtl;
    }

}
