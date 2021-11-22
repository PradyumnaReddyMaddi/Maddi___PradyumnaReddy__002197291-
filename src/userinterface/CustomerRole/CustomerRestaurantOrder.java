/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package userinterface.CustomerRole;

import Business.Customer.Customer;
import Business.Customer.CustomerDirectory;
import Business.EcoSystem;
import Business.Restaurant.Restaurant;
import Business.Restaurant.RestaurantDirectory;
import Business.UserAccount.UserAccount;
import Business.order.CartModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author pradyumnareddy
 */
public class CustomerRestaurantOrder extends javax.swing.JPanel {

    /**
     * Creates new form CustomerRestaurantOrder
     */
     private JPanel userProcessContainer;

     UserAccount userAccount;
    EcoSystem ecosystem;
    RestaurantDirectory rd;
    CustomerDirectory cd;
    public CustomerRestaurantOrder(JPanel userProcessContainer, UserAccount account,EcoSystem ecosystem) {
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.ecosystem = ecosystem;
        if(ecosystem.getCustomerDirectory() == null)
            ecosystem.setCustomerDirectory(new CustomerDirectory());
        initComponents();
        populateTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        restaurantItems = new javax.swing.JTable();
        addToCart = new javax.swing.JButton();
        proceedToCart = new javax.swing.JButton();
        btnViewProfile = new javax.swing.JButton();

        jLabel2.setText("Items Available");

        restaurantItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Restaurant", "Item"
            }
        ));
        jScrollPane1.setViewportView(restaurantItems);

        addToCart.setText("Add to cart");
        addToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartActionPerformed(evt);
            }
        });

        proceedToCart.setText("Proceed to cart");
        proceedToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedToCartActionPerformed(evt);
            }
        });

        btnViewProfile.setText("View Profile");
        btnViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addToCart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(proceedToCart))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(97, 97, 97)
                .addComponent(btnViewProfile)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewProfile))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart)
                    .addComponent(proceedToCart))
                .addContainerGap(111, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartActionPerformed
        // TODO add your handling code here:
        DefaultTableModel df = (DefaultTableModel) restaurantItems.getModel();

        int selectedRow = restaurantItems.getSelectedRow();
        //if(selectedRow == null)
        //  JOptionPane.showMessageDialog(this, "No Item Selected");
        String resto = df.getValueAt(selectedRow,0).toString();
        String item = df.getValueAt(selectedRow, 1).toString();
        CartModel cm = new CartModel(resto, item);

        //   if(ecosystem.getCustomerDirectory() == null)
        //       ecosystem.setCustomerDirectory(new ());

        cd = ecosystem.getCustomerDirectory();

        Customer c = cd.getCustoo(userAccount);
        c.addToCart(cm);
        JOptionPane.showMessageDialog(this, "Added to cart");

    }//GEN-LAST:event_addToCartActionPerformed

    private void proceedToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedToCartActionPerformed
        // TODO add your handling code here:
        cd = ecosystem.getCustomerDirectory();
        Customer c = cd.getCustoo(userAccount);
        Cart cart = new Cart(userProcessContainer,c, ecosystem);
        userProcessContainer.add("cart",cart);
        CardLayout crdLyt = (CardLayout) userProcessContainer.getLayout();
        crdLyt.show(userProcessContainer,"cart");
    }//GEN-LAST:event_proceedToCartActionPerformed

    private void btnViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewProfileActionPerformed
        // TODO add your handling code here:
        cd = ecosystem.getCustomerDirectory();
        Customer c = cd.getCustoo(userAccount);
        CustomerProfile cp = new CustomerProfile(userProcessContainer,c,ecosystem);
        userProcessContainer.add("profile",cp);
        CardLayout crdLyt = (CardLayout) userProcessContainer.getLayout();
        crdLyt.show(userProcessContainer,"profile");

    }//GEN-LAST:event_btnViewProfileActionPerformed
private void populateTable() {
        rd = ecosystem.getRestaurantDirectory();
        ArrayList<Restaurant> restoList = rd.getRestaurantList();
        
        DefaultTableModel dt = (DefaultTableModel) restaurantItems.getModel();
        dt.setRowCount(0);
        for(Restaurant r: restoList)
        {
            if(r.getMenu() == null)
                r.setMenu(new ArrayList<String>());
            ArrayList<String> items = r.getMenu();
            for(String s: items)
            {
                String[] row = {r.getName(),s};
                dt.addRow(row);
            }
        }
        
        restaurantItems.setModel(dt);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCart;
    private javax.swing.JButton btnViewProfile;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton proceedToCart;
    private javax.swing.JTable restaurantItems;
    // End of variables declaration//GEN-END:variables
}
