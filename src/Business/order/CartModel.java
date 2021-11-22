/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.order;

/**
 *
 * @author pradyumnareddy
 */
public class CartModel {
    
    String restaurant;
    String item;

    
    public CartModel(String resto, String item)
    {
        this.restaurant = resto;
        this.item = item;
    }
    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
