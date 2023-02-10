/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Staff {
    int manv;
    String tennv;
    int gioitinh;
    String chucvu;
    String ngayvaolam;
    String diachi;
    String sdt;
    String username;
    String password;

    public Staff() {
    }

    public Staff(int manv, String tennv, int gioitinh, String chucvu, String ngayvaolam, String diachi, String sdt, String username, String password) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.chucvu = chucvu;
        this.ngayvaolam = ngayvaolam;
        this.diachi = diachi;
        this.sdt = sdt;
        this.username = username;
        this.password = password;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getNgayvaolam() {
        return ngayvaolam;
    }

    public void setNgayvaolam(String ngayvaolam) {
        this.ngayvaolam = ngayvaolam;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
