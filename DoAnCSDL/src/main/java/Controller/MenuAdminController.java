/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.BillOfSaleForm;
import View.EmployeeForm;
import View.LoginForm;
import View.StaffForm;
import View.MenuAdminForm;
import View.RevenueForm;
import View.SellProductForm;
import View.SupplierForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class MenuAdminController {

    public void MenuAdminController(JButton btnhdb, JButton btnhdn, JButton btnnv, JButton btnkh, JButton btnspb, JButton btnncc, JButton btndt, JButton btndx, String user, String pass,int id, MenuAdminForm viewMenu) {
        btnnv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                StaffForm staffForm = new StaffForm(user, pass,id);
                staffForm.setVisible(true);
                viewMenu.hide();
            }
        });

        btnkh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                EmployeeForm employeeForm = new EmployeeForm(user, pass,id);
                employeeForm.setVisible(true);
                viewMenu.hide();
            }
        });

        btnspb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SellProductForm sellProductForm = new SellProductForm(user, pass,id);
                sellProductForm.setVisible(true);
                viewMenu.hide();
            }
        });

        btnncc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SupplierForm supplierForm = new SupplierForm(user, pass,id);
                supplierForm.setVisible(true);
                viewMenu.hide();
            }
        });

        btndt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RevenueForm revenueForm = new RevenueForm(user, pass,id);
                revenueForm.setVisible(true);
                viewMenu.hide();
            }
        });
        
        btnhdb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BillOfSaleForm billofsaleForm = new BillOfSaleForm(user, pass,id);
                billofsaleForm.setVisible(true);
                viewMenu.hide();
            }
        });


        btndx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int cancel = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?",
                        "Hủy Đăng Nhập", JOptionPane.YES_NO_OPTION);
                if (cancel == JOptionPane.YES_OPTION) {
                    viewMenu.setVisible(false);
                    new LoginForm().setVisible(true);
                };

            }
        });
        
    }
}
