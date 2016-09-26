/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.jademy.domain.entities;

public class CartItem {
	private Media media;
	private int quantity;

	public CartItem(Media media, int quantity) {
		this.media = media;
		this.quantity = quantity;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCartItemTotalPrice() {
		return getMedia().getPrice()*getQuantity();
	}
	
	
}
