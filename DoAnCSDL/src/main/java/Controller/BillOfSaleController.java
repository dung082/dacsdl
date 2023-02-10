/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ConnectDB.AllList;
import Model.BillModel;
import Model.IdSession;
import Model.SellProduct;
import View.BillOfSaleForm;
import View.MenuAdminForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class BillOfSaleController {

    public void billofsaleController(JTable tableDisplayCellProduct, JTable tableDisplayBill, JButton btnLuu, JButton btnTv, String user, String pass, int id, BillOfSaleForm billofsaleForm) {
        AllList al = new AllList();
        String sql = "select MASPB , TENSPB , DONGIA from SANPHAMBAN";
        IdSession idSession = new IdSession();
        List<SellProduct> listSellProduct = al.getListSanPhamBan(sql);
        List<BillModel> listBillModel = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tableDisplayCellProduct.getModel();
        Object[] row = new Object[100];
        
        DefaultTableModel model1 = (DefaultTableModel) tableDisplayBill.getModel();
        Object[] row1 = new Object[100];

        for (int i = 0; i < listSellProduct.size(); i++) {
            row[0] = listSellProduct.get(i).getMaspb();
            row[1] = listSellProduct.get(i).getTenspb();
            row[2] = listSellProduct.get(i).getDongia();
            model.addRow(row);
        }

        ListSelectionModel listSelectionModel = tableDisplayCellProduct.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tableDisplayCellProduct.getSelectedRow();
                int count = 0;
                TableModel tableModel = tableDisplayCellProduct.getModel();

                if (!listBillModel.isEmpty()) {
                    for (int j = 0; j < listBillModel.size(); j++) {
                        if (listBillModel.get(j).getMaspb() == Integer.parseInt(tableModel.getValueAt(i, 0).toString())) {
                            listBillModel.get(j).setSl(listBillModel.get(j).getSl() + 1);
                            count++;
                        }
                    }

                    if (count == 0) {
                        BillModel billModel = new BillModel();
                        billModel.setMaspb(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
                        billModel.setTenspb(tableModel.getValueAt(i, 1).toString());
                        billModel.setDongia(Integer.parseInt(tableModel.getValueAt(i, 2).toString()));
                        billModel.setSl(1);
                        listBillModel.add(billModel);
                        System.out.println("check1");
                    }
                } else {
                    BillModel billModel1 = new BillModel();
                    billModel1.setMaspb(Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
                    billModel1.setTenspb(tableModel.getValueAt(i, 1).toString());
                    billModel1.setDongia(Integer.parseInt(tableModel.getValueAt(i, 2).toString()));
                    billModel1.setSl(1);
                    listBillModel.add(billModel1);
                        System.out.println("check2");
                }
                model1.setRowCount(0);
                for (int k = 0; k < listBillModel.size(); k++) {
                    row1[0] = listBillModel.get(k).getMaspb();
                    row1[1] = listBillModel.get(k).getTenspb();
                    row1[2] = listBillModel.get(k).getDongia();
                    row1[3] = listBillModel.get(k).getSl();
                    model1.addRow(row1);
                }

            }
        });
        btnTv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billofsaleForm.hide();
                MenuAdminForm menuAdminForm = new MenuAdminForm(user, pass, id);
                menuAdminForm.setVisible(true);
            }
        });

    }
}
