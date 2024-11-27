/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Macken
 */
public class Order extends javax.swing.JFrame {

 
    private static final String DB_URL = "jdbc:mysql://localhost:3306/order";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    
    public Order() {
        initComponents();
       
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productNameField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        priceField = new javax.swing.JTextField();
        customerNameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        view = new javax.swing.JButton();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(productNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 125, 35));
        getContentPane().add(quantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 125, 35));
        getContentPane().add(priceField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 125, 35));

        customerNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(customerNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 125, 35));

        jLabel1.setText("Customer Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 96, -1, -1));

        jLabel2.setText("Product Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 149, -1, -1));

        jLabel3.setText("Qty");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 211, -1, -1));

        jLabel4.setText("Price");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 264, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Product", "Qty", "Price", "Order Date", "Updated At", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 540, 210));

        view.setText("View Orders");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        getContentPane().add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, -1, -1));

        add.setText("Add Order");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, -1));

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, -1));

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void load() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    String query = "SELECT customer_name, product_name, quantity, total_price, order_date,updated_at,status FROM orders";

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String customerName = rs.getString("customer_name");
            String productName = rs.getString("product_name");
            int quantity = rs.getInt("quantity");
            double totalPrice = rs.getDouble("total_price");
            String orderDate = rs.getString("order_date");  
            String updateDate= rs.getString("updated_at");
            String status= rs.getString("status");

            model.addRow(new Object[]{customerName, productName, quantity, totalPrice, orderDate,updateDate,status});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading orders into the table.");
    }
}

    private void productNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameFieldActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
       
    double productPrice =Double.parseDouble(priceField.getText());
    String customerName = customerNameField.getText();   
    String productName = productNameField.getText();    
    int quantity = Integer.parseInt(quantityField.getText());  
 
    double totalPrice = quantity * productPrice;

   
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        String query = "INSERT INTO orders (customer_name, product_name, quantity, total_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customerName);
            stmt.setString(2, productName);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, totalPrice);
            stmt.executeUpdate();
            System.out.println("Order added successfully!");
        } 
        load();
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding the order to the database.");
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    
    private void delete(){
    
    
    int selectedRow = jTable1.getSelectedRow();

if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select a row to delete.");
    return;
}

String customerName = (String) jTable1.getValueAt(selectedRow, 0);
String productName = (String) jTable1.getValueAt(selectedRow, 1);

int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this order?", 
                                             "Delete Order", JOptionPane.YES_NO_OPTION);
if (confirm == JOptionPane.YES_OPTION) {
    String query = "DELETE FROM orders WHERE customer_name = ? AND product_name = ?";

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = connection.prepareStatement(query)) {

        stmt.setString(1, customerName);
        stmt.setString(2, productName);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Order deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete the order.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting the order from the database.");
    }
}} 
    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
delete();
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
int selectedRow = jTable1.getSelectedRow();

if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select a row to update.");
    return;
}

String newCustomerName = customerNameField.getText();
String newProductName = productNameField.getText();
int newQuantity = Integer.parseInt(quantityField.getText());
double newPrice = Double.parseDouble(priceField.getText());
double newTotalPrice = newQuantity * newPrice;

String query = "UPDATE orders SET customer_name = ?, product_name = ?, quantity = ?, total_price = ? WHERE customer_name = ? AND product_name = ?";

try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
     PreparedStatement stmt = connection.prepareStatement(query)) {

    stmt.setString(1, newCustomerName);
    stmt.setString(2, newProductName);
    stmt.setInt(3, newQuantity);
    stmt.setDouble(4, newTotalPrice);
    stmt.setString(5, jTable1.getValueAt(selectedRow, 0).toString());
    stmt.setString(6, jTable1.getValueAt(selectedRow, 1).toString());
    int rowsAffected = stmt.executeUpdate();

    if (rowsAffected > 0) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setValueAt(newCustomerName, selectedRow, 0);
        model.setValueAt(newProductName, selectedRow, 1);
        model.setValueAt(newQuantity, selectedRow, 2);
        model.setValueAt(newTotalPrice, selectedRow, 3);
        JOptionPane.showMessageDialog(this, "Order updated successfully.");
    } else {
        JOptionPane.showMessageDialog(this, "Failed to update the order.");
    }
} catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error updating the order in the database.");
}
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
    load();        // TODO add your handling code here:
    }//GEN-LAST:event_viewActionPerformed

    private void customerNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField customerNameField;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField priceField;
    private javax.swing.JTextField productNameField;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton update;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
