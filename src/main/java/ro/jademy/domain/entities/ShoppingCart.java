/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ra.jademy.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mihai
 */
public class ShoppingCart {
	List <CartItem> cartItems = new ArrayList<>();

	public List<CartItem> getCartItems() {
		return Collections.unmodifiableList(cartItems);
	}
	
	public void addToCart (CartItem cartItem){
	cartItems.add(cartItem);
	}
	public void addToCart (Media media, int quantity){
	addToCart(new CartItem (media,quantity));
	}
	
	
}
