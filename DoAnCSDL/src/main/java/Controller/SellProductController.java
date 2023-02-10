/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ConnectDB.AllList;
import ConnectDB.ConnectSQLServer;
import Model.IdSession;
import Model.SellProduct;
import View.LoginForm;
import View.MenuAdminForm;
import View.SellProductForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class SellProductController {

    public void SellProductController(JTable tableDisplaySellProduct, JTextField txttsp, JTextField txtdg, JButton btnLuu,JButton btnXoa, JButton btntv, String user , String pass,int id, SellProductForm sellProductForm) {
        
        AllList al = new AllList();
        String sql = "select MASPB , TENSPB , DONGIA from SANPHAMBAN";
        IdSession idSession = new IdSession();
        List<SellProduct> listSellProduct = al.getListSanPhamBan(sql);
        DefaultTableModel model = (DefaultTableModel) tableDisplaySellProduct.getModel();
        Object[] row = new Object[100];
        for (int i = 0; i < listSellProduct.size(); i++) {
            row[0] = listSellProduct.get(i).getMaspb();
            row[1] = listSellProduct.get(i).getTenspb();
            row[2] = listSellProduct.get(i).getDongia();
            model.addRow(row);
        }
        ListSelectionModel listSelectionModel = tableDisplaySellProduct.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tableDisplaySellProduct.getSelectedRow();
                TableModel tableModel = tableDisplaySellProduct.getModel();
                txttsp.setText(tableModel.getValueAt(i, 1).toString());
                txtdg.setText(tableModel.getValueAt(i, 2).toString());
                idSession.setIdSession(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
            }
        });

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(idSession.getIdSession());
                if (idSession.getIdSession() == 0) {
                    String sqlThemSPB = "insert into SANPHAMBAN(TENSPB,DONGIA) VALUES (? , ?) ";
                    String tenspb = txttsp.getText().trim();
                    int dongia = Integer.parseInt(txtdg.getText().trim());
                    JFrame jFrameAdd = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameAdd,"Bạn chắc chắn muốn thêm sản phẩm mới", "Xác nhận", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlThemSPB);
                            ps.setString(1, tenspb);
                            ps.setInt(2, dongia);
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Thêm sản phẩm mới thành công");
                                sellProductForm.hide();
                                new SellProductForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("lỗi database");
                        }
                    }
                    else {
                        
                    }
                } else {
                    String tenspb = txttsp.getText().trim();
                    int dongia = Integer.parseInt(txtdg.getText().trim());
                    String sqlDoiSPB = "update SANPHAMBAN set TENSPB =? , DONGIA =? WHERE MASPB =?";
                    JFrame jFrameChange = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameChange, "Bạn chắc chắn đổi thông tin sản phẩm?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDoiSPB);
                            ps.setString(1, tenspb);
                            ps.setInt(2, dongia);
                            ps.setInt(3, idSession.getIdSession());
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Đổi thông tin thành công");
                                sellProductForm.hide();
                                new SellProductForm(user,pass,id).setVisible(true);
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
                String sqlDelete = "DELETE FROM SANPHAMBAN WHERE MASPB = ?";
                JFrame jFrameAdd = new JFrame("Xác nhận");
                int result = JOptionPane.showConfirmDialog(jFrameAdd, "Bạn chắc chắn muốn xóa san phâm bán", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (idSession.getIdSession() == 0) {
                        JFrame jFrameNotice = new JFrame("Cảnh báo báo");
                        JOptionPane.showMessageDialog(jFrameNotice, "Bạn cần chọn san ph?m cần xóa");
                    } else {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDelete);
                            ps.setInt(1, idSession.getIdSession());
                            
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Xóa s?n ph?m thành công");
                                sellProductForm.hide();
                                new SellProductForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("lỗi database");
                        }
                    }
                } else {

                }
            }
        });

        btntv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellProductForm.hide();
                MenuAdminForm menuAdminForm = new MenuAdminForm(user, pass,id);
                menuAdminForm.setVisible(true);
            }
        });
    }
}
