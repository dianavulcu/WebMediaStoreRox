/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.jademy.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
	List <CartItem> cartItems = new ArrayList<>();

	public List<CartItem> getCartItems() {
		return Collections.unmodifiableList(cartItems);
	}
	
	public void addToCart (CartItem cartItem){
		for (CartItem cartItemList : cartItems) {
			if(cartItem.getMedia().getCode().equals(cartItemList.getMedia().getCode())){
				cartItemList.setQuantity(cartItemList.getQuantity()+cartItem.getQuantity());
				return;
			}
		}
	cartItems.add(cartItem);
	}
	public double getTotalPrice(){
		double price = 0;
		for (CartItem cartItem : cartItems) {
			price += cartItem.getMedia().getPrice()*cartItem.getQuantity();
		}
		return price;
	}
	
	public void addToCart (Media media, int quantity){
	addToCart(new CartItem (media,quantity));
	}

	public int getTotalItems() {
		int totalItems = 0;
		for (CartItem cartItem : cartItems) {
			totalItems += cartItem.getQuantity();
		}
		return totalItems;
	}
	
	
}
