/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.order;

import java.util.ArrayList;

/**
 *
 * @author pradyumnareddy
 */
public class acceptedOrders {
    
    public class AcceptedOrders {
    ArrayList<FinalOrder> acceptedOrders = new ArrayList<FinalOrder>();
 
    public ArrayList<FinalOrder> getAcceptedOrders() {
        return acceptedOrders;
    }

    public void setAcceptedOrders(ArrayList<FinalOrder> acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
    }
    
    }
}
