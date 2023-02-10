/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ConnectDB.AllList;
import ConnectDB.ConnectSQLServer;
import Model.IdSession;
import Model.PurchasedProduct;
import Model.SellProduct;
import View.MenuAdminForm;
import View.PurchasedProductForm;
import View.SellProductForm;
import View.SupplierForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
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
public class PurchasedProductController {

    public void purchasedProductController(JTable tableDisplayPurchasedProduct, JTextField txttsp, JTextField txtdg, JTextField txtngsx, JButton btnLuu, JButton btnXoa, JButton btntv, String user, String pass,int id, int mancc, PurchasedProductForm purchasedProductForm) {
        AllList al = new AllList();
        String sql = "SELECT MASPN , TENSPN , DONGIA,NGAYSX from SANPHAMNHAP WHERE MANCC = " + mancc + " ";
        IdSession idSession = new IdSession();
        List<PurchasedProduct> listSellProduct = al.getListSanPhamNhap(sql);
        DefaultTableModel model = (DefaultTableModel) tableDisplayPurchasedProduct.getModel();
        Object[] row = new Object[100];
        for (int i = 0; i < listSellProduct.size(); i++) {
            row[0] = listSellProduct.get(i).getMasp();
            row[1] = listSellProduct.get(i).getTensp();
            row[2] = listSellProduct.get(i).getDg();
            row[3] = listSellProduct.get(i).getNgsx();
            model.addRow(row);
        }
        ListSelectionModel listSelectionModel = tableDisplayPurchasedProduct.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tableDisplayPurchasedProduct.getSelectedRow();
                TableModel tableModel = tableDisplayPurchasedProduct.getModel();
                txttsp.setText(tableModel.getValueAt(i, 1).toString());
                txtdg.setText(tableModel.getValueAt(i, 2).toString());
                txtngsx.setText(tableModel.getValueAt(i, 3).toString());
                idSession.setIdSession(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
            }
        });

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(idSession.getIdSession());
                if (idSession.getIdSession() == 0) {
                    String sqlThemSPB = "insert into SANPHAMNHAP(TENSPN,DONGIA,MANCC,NGAYSX) VALUES (?,? , ? , ?) ";
                    String tenspb = txttsp.getText().trim();
                    int dongia = Integer.parseInt(txtdg.getText().trim());
                    String ngaysx = txtngsx.getText().trim();
                    JFrame jFrameAdd = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameAdd, "Bạn chắc chắn muốn thêm sản phẩm mới", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlThemSPB);
                            ps.setString(1, tenspb);
                            ps.setInt(2, dongia);
                            ps.setInt(3, mancc);
                            ps.setString(4, ngaysx);
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Thêm sản phẩm mới thành công");
                                purchasedProductForm.hide();
                                new PurchasedProductForm(user, pass ,id, mancc).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("lỗi database");
                        }
                    } else {

                    }
                } else {
                    String tenspb = txttsp.getText().trim();
                    int dongia = Integer.parseInt(txtdg.getText().trim());
                    String ngaysx = txtngsx.getText().trim();
                    String sqlChange = "update SANPHAMNHAP set TENSPN =? , DONGIA =? , NGAYSX =? WHERE MASPN =?";
                    JFrame jFrameChange = new JFrame("Xác nhận");
                    int result = JOptionPane.showConfirmDialog(jFrameChange, "Bạn chắc chắn đổi thông tin sản phẩm?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlChange);
                            ps.setString(1, tenspb);
                            ps.setInt(2, dongia);
                            ps.setString(3, ngaysx);
                            ps.setInt(4, idSession.getIdSession());
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Đổi thông tin thành công");
                                purchasedProductForm.hide();
                                new PurchasedProductForm(user, pass,id, mancc).setVisible(true);
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
                String sqlDelete = "DELETE FROM SANPHAMNHAP WHERE MASPN = ?";
                JFrame jFrameAdd = new JFrame("Xác nhận");
                int result = JOptionPane.showConfirmDialog(jFrameAdd, "Bạn chắc chắn muốn xóa sản phẩm nhập", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (idSession.getIdSession() == 0) {
                        JFrame jFrameNotice = new JFrame("Cảnh báo báo");
                        JOptionPane.showMessageDialog(jFrameNotice, "Bạn cần chọn sản phẩm cần xóa");
                    } else {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDelete);
                            ps.setInt(1, idSession.getIdSession());

                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Thông báo");
                                JOptionPane.showMessageDialog(jFrameNotice, "Xóa sản phẩm thành công");
                                purchasedProductForm.hide();
                                new PurchasedProductForm(user, pass,id,mancc).setVisible(true);
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
                purchasedProductForm.hide();
                new SupplierForm(user, pass,id).setVisible(true);
            }
        });
    }
}
