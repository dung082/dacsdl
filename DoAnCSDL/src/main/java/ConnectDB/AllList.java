/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;

import Model.Employee;
import Model.Staff;
import Model.SellProduct;
import Model.Account;
import Model.PurchasedProduct;
import Model.Supplier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AllList {

    public List<Account> getListTaikhoan(String sql) {
        List<Account> list = null;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            list = new ArrayList<>();
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Account taikhoan = new Account();
                taikhoan.setUsername(rs.getString(1));
                taikhoan.setPassword(rs.getString(2));
                taikhoan.setRole(rs.getInt(3));
                taikhoan.setId(rs.getInt(4));
                list.add(taikhoan);
            }
            ps.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }
        return list;
    }

    public List<SellProduct> getListSanPhamBan(String sql) {
        List<SellProduct> list = null;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            list = new ArrayList<>();
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                SellProduct sellProduct = new SellProduct();
                sellProduct.setMaspb(rs.getInt(1));
                sellProduct.setTenspb(rs.getString(2));
                sellProduct.setDongia(rs.getInt(3));
                list.add(sellProduct);
            }
            ps.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }
        return list;
    }
    
    public List<PurchasedProduct> getListSanPhamNhap(String sql) {
        List<PurchasedProduct> list = null;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            list = new ArrayList<>();
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                PurchasedProduct purchasedProduct = new PurchasedProduct();
                purchasedProduct.setMasp(rs.getInt(1));
                purchasedProduct.setTensp(rs.getString(2));
                purchasedProduct.setDg(rs.getInt(3));
                purchasedProduct.setNgsx(rs.getDate(4));
                list.add(purchasedProduct);
            }
            System.out.println(list);
            ps.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
            System.out.println("lỗi");
        }
        return list;
    }
    
    

    public List<Employee> getListKhachHang(String sql) {
        List<Employee> list = null;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            list = new ArrayList<>();
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setMkh(rs.getInt(1));
                employee.setHtkh(rs.getString(2));
                employee.setDc(rs.getString(3));
                employee.setSdt(rs.getString(4));
                employee.setDiemtl(rs.getInt(5));
                list.add(employee);
            }
            ps.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }
        return list;
    }

    public List<Staff> getListNhanVien(String sql) {
        List<Staff> list = null;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            list = new ArrayList<>();
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setManv(rs.getInt(1));
                staff.setTennv(rs.getString(2));
                staff.setGioitinh(rs.getInt(3));
                staff.setChucvu(rs.getString(4));
                staff.setNgayvaolam(rs.getString(5));
                staff.setDiachi(rs.getString(6));
                staff.setSdt(rs.getString(7));
                staff.setUsername(rs.getString(8));
                staff.setPassword(rs.getString(9));
                list.add(staff);
            }
            ps.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }
        return list;
    }
    
    public List<Supplier> getListNhaCC(String sql) {
        List<Supplier> list = null;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            list = new ArrayList<>();
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setMancc(rs.getInt(1));
                supplier.setTennnc(rs.getString(2));
                supplier.setDc(rs.getString(3));
                supplier.setSdt(rs.getString(4));
          
                list.add(supplier);
            }
            ps.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }
        return list;
    }
}
