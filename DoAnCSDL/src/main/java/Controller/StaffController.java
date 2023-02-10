/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ConnectDB.AllList;
import ConnectDB.ConnectSQLServer;
import Model.IdSession;
import View.MenuAdminForm;
import View.StaffForm;
import Model.Staff;
import View.EmployeeForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class StaffController {

    public void staffController(JTable tableDisplayStaff, JTextField txthtnv, JComboBox cbgt, JTextField txtcv, JTextField txtdc, JTextField txtsdt, JTextField txtusername, JTextField txtpassword, JButton btnLuu, JButton btnXoa, JButton btnTv, String user, String pass,int id, StaffForm staffForm) {

        String sqlDataTable = "SELECT MANV , TENNV, GIOITINH , CHUCVU , NGAYLAMVIEC , DIACHI , SDT , USERNAME , MATKHAU FROM NHANVIEN";
        AllList al = new AllList();
        IdSession idSession = new IdSession();
        List<Staff> listStaff = al.getListNhanVien(sqlDataTable);
        DefaultTableModel model = (DefaultTableModel) tableDisplayStaff.getModel();
        Object[] row = new Object[100];
        System.out.println(listStaff);
        for (int i = 0; i < listStaff.size(); i++) {
            row[0] = listStaff.get(i).getManv();
            row[1] = listStaff.get(i).getTennv();
            if (listStaff.get(i).getGioitinh() == 0) {
                row[2] = "Nữ";
            } else {
                row[2] = "Nam";
            }
            row[3] = listStaff.get(i).getChucvu();
            row[4] = listStaff.get(i).getNgayvaolam();
            row[5] = listStaff.get(i).getDiachi();
            row[6] = listStaff.get(i).getSdt();
            row[7] = listStaff.get(i).getUsername();
            row[8] = listStaff.get(i).getPassword();
            model.addRow(row);
        }
        ListSelectionModel listSelectionModel = tableDisplayStaff.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tableDisplayStaff.getSelectedRow();
                TableModel tableModel = tableDisplayStaff.getModel();
                txthtnv.setText(tableModel.getValueAt(i, 1).toString());
                if (tableModel.getValueAt(i, 2).toString() == "Nam") {
                    cbgt.setSelectedItem("Nam");
                } else {
                    cbgt.setSelectedItem("Nữ");
                }
                txtcv.setText(tableModel.getValueAt(i, 3).toString());
                txtdc.setText(tableModel.getValueAt(i, 5).toString());
                txtsdt.setText(tableModel.getValueAt(i, 6).toString());
                txtusername.setText(tableModel.getValueAt(i, 7).toString());
                txtpassword.setText(tableModel.getValueAt(i, 8).toString());

                idSession.setIdSession(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
            }
        });
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(idSession.getIdSession());
                if (idSession.getIdSession() == 0) {
                    String sqlAdd = "insert into NHANVIEN(TENNV,USERNAME,GIOITINH,CHUCVU,NGAYLAMVIEC,DIACHI,SDT,PHANQUYEN,MATKHAU) VALUES (?,?,?,?,?,?,?,?,? ) ";
                    String htnv = txthtnv.getText().trim();
                    String username = txtusername.getText().trim();
                    String cv = txtcv.getText().trim();
                    String dc = txtdc.getText().trim();
                    String sdt = txtsdt.getText().trim();
                    String password = txtpassword.getText().trim();

                    JFrame jFrameAdd = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameAdd, "Bạn chắc chắn muốn thêm nhân viên", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Calendar calendar = Calendar.getInstance();
                            java.util.Date sqlDate = calendar.getTime();
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlAdd);

                            ps.setString(1, htnv);
                            ps.setString(2, username);
                            if (cbgt.getSelectedItem() == "Nam") {
                                ps.setInt(3, 1);
                            } else {
                                ps.setInt(3, 0);
                            }
                            ps.setString(4, cv);
                            ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                            ps.setString(6, dc);
                            ps.setString(7, sdt);
                            ps.setInt(8, 0);
                            ps.setString(9, password);
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Thêm nhân viên mới thành công");
                                staffForm.hide();
                                new StaffForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("lỗi database");
                        }
                    } else {

                    }
                } else {
                    String htnv = txthtnv.getText().trim();
                    String username = txtusername.getText().trim();
                    String cv = txtcv.getText().trim();
                    String dc = txtdc.getText().trim();
                    String sdt = txtsdt.getText().trim();
                    String password = txtpassword.getText().trim();
                    String sqlChange = "update NHANVIEN set TENNV = ? , GIOITINH = ? ,CHUCVU =?,DIACHI=? ,SDT = ?  , USERNAME = ? , MATKHAU =?  WHERE MANV =?";
                    JFrame jFrameChange = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameChange, "Bạn chắc chắn đổi thông tin khách hàng ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlChange);
                            ps.setString(1, htnv);
                            if (cbgt.getSelectedItem() == "Nam") {
                                ps.setInt(2, 1);
                            } else {
                                ps.setInt(2, 0);
                            }
                            ps.setString(3, cv);
                            ps.setString(4, dc);
                            ps.setString(5, sdt);
                            ps.setString(6, username);
                            ps.setString(7, password);
                            ps.setInt(8, idSession.getIdSession());
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Đổi thông tin thành công");
                                staffForm.hide();
                                new StaffForm(user, pass,id).setVisible(true);
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
                String sqlDelete = "DELETE FROM NHANVIEN WHERE MANV = ?";
                JFrame jFrameAdd = new JFrame("Xác nhận");
                int result = JOptionPane.showConfirmDialog(jFrameAdd, "Bạn chắc chắn muốn xóa nhân viên", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (idSession.getIdSession() == 0) {
                        JFrame jFrameNotice = new JFrame("Cảnh báo báo");
                        JOptionPane.showMessageDialog(jFrameNotice, "Bạn cần chọn nhân viên cần xóa");
                    } else {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDelete);
                            ps.setInt(1, idSession.getIdSession());
                            
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Xóa nhân viên thành công");
                                staffForm.hide();
                                new StaffForm(user, pass,id).setVisible(true);
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
                staffForm.hide();
                MenuAdminForm menuAdminForm = new MenuAdminForm(user, pass,id);
                menuAdminForm.setVisible(true);
            }
        });
    }
}
