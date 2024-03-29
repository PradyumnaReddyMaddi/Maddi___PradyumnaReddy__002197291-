/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package userinterface.DeliveryManRole;


import Business.Customer.CustomerDirectory;
import Business.DeliveryMan.DeliveryMan;
import Business.DeliveryMan.DeliveryManDirectory;
import Business.EcoSystem;
import Business.Restaurant.Restaurant;
import Business.Restaurant.RestaurantDirectory;
import Business.UserAccount.UserAccount;
import Business.order.FinalOrder;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author pradyumnareddy
 */
public class OrdersDelivery extends javax.swing.JPanel {

    /**
     * Creates new form OrdersDelivery
     */
    JPanel userProcessContainer;

     UserAccount userAccount;
    EcoSystem ecosystem;
    RestaurantDirectory rd;
    CustomerDirectory cd;
    DeliveryManDirectory dd;
    Restaurant resto;
    public OrdersDelivery(JPanel userProcessContainer, UserAccount account,EcoSystem ecosystem) {
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.ecosystem = ecosystem;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        deliveryTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAssignToMe = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();

        deliveryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Restaurant", "Customer", "Item", "Order time", "status", "OrderId"
            }
        ));
        jScrollPane1.setViewportView(deliveryTable);

        jLabel1.setText("Orders to be delivered");

        btnAssignToMe.setText("Assign to me");
        btnAssignToMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignToMeActionPerformed(evt);
            }
        });

        btnProfile.setText("View Profile");
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
                                .addComponent(btnProfile))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(btnAssignToMe)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnProfile))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAssignToMe)
                .addContainerGap(125, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAssignToMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignToMeActionPerformed
        // TODO add your handling code here:
        DefaultTableModel df = (DefaultTableModel) deliveryTable.getModel();
        int selectedRow = deliveryTable.getSelectedRow();
        String orderId = df.getValueAt(selectedRow,5).toString();
        rd = ecosystem.getRestaurantDirectory();
        FinalOrder f = rd.getOrder(orderId);
        f.setStatus("DeliveryMan Assigned");
        f.setDeliveryMan(userAccount.getUsername());
        dd = ecosystem.getDeliveryManDirectory();
        DeliveryMan d = dd.getDeliveryMan(userAccount);
        d.addDeliveredOrder(f);
        populateTable();
    }//GEN-LAST:event_btnAssignToMeActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
        DeliveryProfile dp = new DeliveryProfile(userProcessContainer, userAccount, ecosystem);
        userProcessContainer.add("DeliveryProfile",dp);
        CardLayout crdLyt = (CardLayout) userProcessContainer.getLayout();
        crdLyt.next(userProcessContainer);
    }//GEN-LAST:event_btnProfileActionPerformed

    private void populateTable() {
        DefaultTableModel dm = (DefaultTableModel) deliveryTable.getModel();
        dm.setRowCount(0);
        rd = ecosystem.getRestaurantDirectory();
        ArrayList<FinalOrder> orders;
        ArrayList<Restaurant> restos = rd.getRestaurantList();
        for(Restaurant r: restos)
        {
            if(r.getAcceptedOrders() == null)
                r.setAcceptedOrders(new ArrayList<FinalOrder>());
            orders = r.getAcceptedOrders();
            for(FinalOrder f: orders)
            {
                String[] row = {r.getName(),f.getCustomer(),f.getItem(),f.getDate(),f.getStatus(),f.getOrderId()};
                dm.addRow(row);
            }
        }
        deliveryTable.setModel(dm);
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssignToMe;
    private javax.swing.JButton btnProfile;
    private javax.swing.JTable deliveryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
