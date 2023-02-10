/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ConnectDB.AllList;
import ConnectDB.ConnectSQLServer;
import Model.Employee;
import Model.IdSession;
import View.EmployeeForm;
import View.MenuAdminForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class EmployeeController {

    public void employeeController(JTable tableEmployee, JTextField txthtkh, JTextField txtdc, JTextField txtsdt, JTextField txtdtl, JButton btnLuu,JButton btnXoa, JButton btnTv, String user, String pass, int id, EmployeeForm employeeForm) {

        String sqlDataTable = "Select MAKH , HOTENKH , DIACHI , SDT , DIEMTL from KHACHANG";
        AllList al = new AllList();
        IdSession idSession = new IdSession();
        List<Employee> listEmployee = al.getListKhachHang(sqlDataTable);
        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
        Object[] row = new Object[100];
        System.out.println(listEmployee);
        for (int i = 0; i < listEmployee.size(); i++) {
            row[0] = listEmployee.get(i).getMkh();
            row[1] = listEmployee.get(i).getHtkh();
            row[2] = listEmployee.get(i).getDc();
            row[3] = listEmployee.get(i).getSdt();
            row[4] = listEmployee.get(i).getDiemtl();
            model.addRow(row);
        }
        ListSelectionModel listSelectionModel = tableEmployee.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tableEmployee.getSelectedRow();
                TableModel tableModel = tableEmployee.getModel();
                txthtkh.setText(tableModel.getValueAt(i, 1).toString());
                txtdc.setText(tableModel.getValueAt(i, 2).toString());
                txtsdt.setText(tableModel.getValueAt(i, 3).toString());
                txtdtl.setText(tableModel.getValueAt(i, 4).toString());
                idSession.setIdSession(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
            }
        });

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(idSession.getIdSession());
                if (idSession.getIdSession() == 0) {
                    String sqlThemSPB = "insert into KHACHANG(HOTENKH,DIACHI,SDT,DIEMTL,LEVEL) VALUES (? , ? , ? , ?,?) ";
                    String htkh = txthtkh.getText().trim();
                    String dc = txtdc.getText().trim();
                    String sdt = txtsdt.getText().trim();
                    int diemtl = Integer.parseInt(txtdtl.getText().trim());
                    JFrame jFrameAdd = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameAdd, "Bạn chắc chắn muốn thêm khách hàng", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlThemSPB);
                            ps.setString(1, htkh);
                            ps.setString(2, dc);
                            ps.setString(3, sdt);
                            ps.setInt(4, diemtl);
                            ps.setString(5, "DEFAULT");
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Thêm sản phẩm mới thành công");
                                employeeForm.hide();
                                new EmployeeForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("lỗi database");
                        }
                    } else {

                    }
                } else {
                    String htkh = txthtkh.getText().trim();
                    String dc = txtdc.getText().trim();
                    String sdt = txtsdt.getText().trim();
                    int diemtl = Integer.parseInt(txtdtl.getText().trim());
                    String sqlDoiTTKH = "update KHACHANG set HOTENKH = ? , DIACHI = ? , SDT = ?  , DIEMTL = ?  WHERE MAKH =?";
                    JFrame jFrameChange = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameChange, "Bạn chắc chắn đổi thông tin khách hàng ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDoiTTKH);
                            ps.setString(1, htkh);
                            ps.setString(2, dc);
                            ps.setString(3, sdt);
                            ps.setInt(4, diemtl);
                            ps.setInt(5, idSession.getIdSession());
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Đổi thông tin thành công");
                                employeeForm.hide();
                                new EmployeeForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("lỗi database");
                        }
                    }
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sqlDelete = "DELETE FROM KHACHANG WHERE MAKH = ?";
                JFrame jFrameAdd = new JFrame("Xác nhận");
                int result = JOptionPane.showConfirmDialog(jFrameAdd, "Bạn chắc chắn muốn xóa khách hàng", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (idSession.getIdSession() == 0) {
                        JFrame jFrameNotice = new JFrame("Cảnh báo báo");
                        JOptionPane.showMessageDialog(jFrameNotice, "Bạn cần chọn khách hàng cần xóa");
                    } else {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDelete);
                            ps.setInt(1, idSession.getIdSession());
                            
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Xóa khách hàng thành công");
                                employeeForm.hide();
                                new EmployeeForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("lỗi database");
                        }
                    }
                } else {

                }
            }
        });

        btnTv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.hide();
                MenuAdminForm menuAdminForm = new MenuAdminForm(user, pass,id);
                menuAdminForm.setVisible(true);
            }
        });
    }
}
