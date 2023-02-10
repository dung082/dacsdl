/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ConnectDB.AllList;
import View.LoginForm;
import View.MenuStaffForm;
import Model.Account;
import View.MenuAdminForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginController {

    public void setLogin(JTextField txtUsername, JPasswordField txtPassword, JButton btnLogin, JButton btnHuy, LoginForm viewLogin) {
        AllList al = new AllList();
        String sql = "Select USERNAME , MATKHAU ,PHANQUYEN , MANV from NHANVIEN";
        List<Account> listtk = al.getListTaikhoan(sql);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = false;
                int id = 0;
                int role = 0;
                String user = null;
                String pass1 = null;
                String tk = txtUsername.getText().trim();
                String mk = txtPassword.getText().trim();
                JFrame frame = new JFrame("Xác Nhận");
                int result = JOptionPane.showConfirmDialog(frame, "Bạn muốn đăng nhập chứ", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    for (Account items : listtk) {
                            if (tk.equals(items.getUsername()) && mk.equals(items.getPassword())) {
                            check = true;
                            user = items.getUsername();
                            pass1 = items.getPassword();
                            role = items.getRole();
                            id = items.getId();
                        }
                    }

                    if (check == true) {
                        if (role == 1) {
                            MenuAdminForm ma = new MenuAdminForm(user, pass1,id);
                            ma.setVisible(true);
                            viewLogin.hide();
                        } else {
                            MenuStaffForm me = new MenuStaffForm(user, pass1, role,id);
                            me.setVisible(true);
                            viewLogin.hide();
                        }

                    } else {
                        JFrame frame1 = new JFrame("Lỗi");
                        JOptionPane.showMessageDialog(frame1,
                                "Tài khoản hoặc mật khẩu không chính xác vui lòng thử lại",
                                "Lỗi nhập",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (result == JOptionPane.NO_OPTION) {

                }

            }

        });

        btnHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int hoi = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?",
                        "Hủy Đăng Nhập", JOptionPane.YES_NO_OPTION);
                if (hoi == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

}
