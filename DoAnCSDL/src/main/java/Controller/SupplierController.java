/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ConnectDB.AllList;
import ConnectDB.ConnectSQLServer;
import Model.IdSession;
import Model.Supplier;
import View.MenuAdminForm;
import View.PurchasedProductForm;
import View.SupplierForm;
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
public class SupplierController {

    public void supplierControler(JTable tableDisplaySupplier, JTextField txttenncc, JTextField txtdc, JTextField txtsdt, JButton btnLuu, JButton btnXoa, JButton btnTv, JButton btnSpn, String user, String pass , int id, SupplierForm supplierForm) {
        AllList al = new AllList();
        String sql = "select MANCC , TENNCC , DIACHI , SDT from NHACUNGCAP";
        IdSession idSession = new IdSession();
        List<Supplier> listSupplier = al.getListNhaCC(sql);
        DefaultTableModel model = (DefaultTableModel) tableDisplaySupplier.getModel();
        Object[] row = new Object[100];
        for (int i = 0; i < listSupplier.size(); i++) {
            row[0] = listSupplier.get(i).getMancc();
            row[1] = listSupplier.get(i).getTennnc();
            row[2] = listSupplier.get(i).getDc();
            row[3] = listSupplier.get(i).getSdt();

            model.addRow(row);
        }
        ListSelectionModel listSelectionModel = tableDisplaySupplier.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tableDisplaySupplier.getSelectedRow();
                TableModel tableModel = tableDisplaySupplier.getModel();
                txttenncc.setText(tableModel.getValueAt(i, 1).toString());
                txtdc.setText(tableModel.getValueAt(i, 2).toString());
                txtsdt.setText(tableModel.getValueAt(i, 3).toString());

                idSession.setIdSession(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
            }
        });

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(idSession.getIdSession());
                if (idSession.getIdSession() == 0) {
                    String sqlAdd = "insert into NHACUNGCAP(TENNCC,DIACHI,SDT) VALUES (? , ? , ? ) ";
                    String tenncc = txttenncc.getText().trim();
                    String dc = txtdc.getText().trim();
                    String sdt = txtsdt.getText().trim();

                    JFrame jFrameAdd = new JFrame("X??c nh???n");
                    int result = JOptionPane.showConfirmDialog(jFrameAdd, "B???n ch???c ch???n mu???n th??m nh?? cung c???p", "X??c nh???n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlAdd);
                            ps.setString(1, tenncc);
                            ps.setString(2, dc);
                            ps.setString(3, sdt);
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Th??ng b??o");
                                JOptionPane.showMessageDialog(jFrameNotice, "Th??m th??ng tin nh?? cung c???p m???i th??nh c??ng");
                                supplierForm.hide();
                                new SupplierForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("l???i database");
                        }
                    } else {

                    }
                } else {
                    String tenncc = txttenncc.getText().trim();
                    String dc = txtdc.getText().trim();
                    String sdt = txtsdt.getText().trim();
                    String sqlDoiTTKH = "update NHACUNGCAP set TENNCC = ? , DIACHI = ? , SDT = ?  WHERE MANCC =?";
                    JFrame jFrameChange = new JFrame("X??c nh???n");
                    int result = JOptionPane.showConfirmDialog(jFrameChange, "B???n ch???c ch???n ?????i th??ng tin nh?? cung c???p?", "X??c nh???n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDoiTTKH);
                            ps.setString(1, tenncc);
                            ps.setString(2, dc);
                            ps.setString(3, sdt);
                            ps.setInt(4, idSession.getIdSession());
                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Th??ng b??o");
                                JOptionPane.showMessageDialog(jFrameNotice, "?????i th??ng tin th??nh c??ng");
                                supplierForm.hide();
                                new SupplierForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("l???i database");
                        }
                    }
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sqlDelete = "DELETE FROM NHACUNGCAP WHERE MANCC = ?";
                JFrame jFrameAdd = new JFrame("X??c nh???n");
                int result = JOptionPane.showConfirmDialog(jFrameAdd, "B???n ch???c ch???n mu???n x??a nh?? cung c???p", "X??c nh???n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (idSession.getIdSession() == 0) {
                        JFrame jFrameNotice = new JFrame("C???nh b??o b??o");
                        JOptionPane.showMessageDialog(jFrameNotice, "B???n c???n ch???n nh?? cung c???p c???n x??a");
                    } else {
                        try {
                            Connection conn = ConnectSQLServer.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sqlDelete);
                            ps.setInt(1, idSession.getIdSession());

                            if (ps.executeUpdate() == 1) {
                                JFrame jFrameNotice = new JFrame("Th??ng b??o");
                                JOptionPane.showMessageDialog(jFrameNotice, "X??a nh?? cung c???p th??nh c??ng");
                                supplierForm.hide();
                                new SupplierForm(user, pass,id).setVisible(true);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println("l???i database");
                        }
                    }
                } else {

                }

            }
        });

        btnSpn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSession.getIdSession() == 0) {
                    JFrame jFrameNotice = new JFrame("C???nh b??o b??o");
                    JOptionPane.showMessageDialog(jFrameNotice, "B???n c???n ch???n nh?? cung c???p c???n xem s???n ph???m");
                }
                else {
                    supplierForm.hide();
                    new PurchasedProductForm(user,pass,id,idSession.getIdSession()).setVisible(true);
                }
            }
        });

        btnTv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supplierForm.hide();
                MenuAdminForm menuAdminForm = new MenuAdminForm(user, pass,id);
                menuAdminForm.setVisible(true);
            }
        });

    }
}
