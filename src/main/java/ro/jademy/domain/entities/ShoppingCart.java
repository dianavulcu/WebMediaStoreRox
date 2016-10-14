/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.jademy.domain.entities;

import static ro.jademy.domain.entities.AgeCategory.CHILDREN;
import static ro.jademy.domain.entities.PriceCategory.INFREQUENT_SALE;
import static ro.jademy.domain.entities.PriceCategory.NEW_RELEASE;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ShoppingCart {
	List<CartItem> cartItems = new ArrayList<>();

	public List<CartItem> getCartItems() {
		return Collections.unmodifiableList(cartItems);
	}

	public void addToCart(CartItem cartItem) {
		for (CartItem cartItemList : cartItems) {
			if (cartItem.getMedia().getCode().equals(cartItemList.getMedia().getCode())) {
				cartItemList.setQuantity(cartItemList.getQuantity() + cartItem.getQuantity());
				return;
			}
		}
		cartItems.add(cartItem);
	}

	public double getTotalPrice() {
		double price = 0;
		MathContext mc = new MathContext(8);
		for (CartItem cartItem : cartItems) {
			price += cartItem.getTotalPrice();
		}
		return price;
	}

	public void addToCart(Media media, int quantity) {
		addToCart(new CartItem(media, quantity));
	}

	public String getTotalItems() {
		int totalItems = 0;
		for (CartItem cartItem : cartItems) {
			totalItems += cartItem.getQuantity();
		}
		return NumberFormat.getNumberInstance(Locale.US).format(totalItems);
	}

	public double getAmountForPriceCategory(PriceCategory priceCategory) {
		double suma = 0;
		for (CartItem cartItem : cartItems) {
			if (cartItem.getMedia().getPriceCategory() == priceCategory) {
				suma+=cartItem.getTotalPrice();
			}
		}
		return suma;	
	}

	public int getCountForAgeCategory(AgeCategory ageCategory) {
		int suma = 0;
		for (CartItem cartItem : cartItems) {
			if (cartItem.getMedia().getAgeCategory() == ageCategory) {
				suma+=cartItem.getQuantity();
			}
		}
		return suma;
	}

	public int getLoyaltyPointsForMediaCategories() {
		double amount = getAmountForPriceCategory(NEW_RELEASE);
		if (amount>5) {
			return 2;
		}
		amount = getAmountForPriceCategory(INFREQUENT_SALE);
		if (amount>0) {
			return 3;
		}
		int count = getCountForAgeCategory(CHILDREN);
		if (count>1) {
			return 3;
		}
		return 1;
	}
	
}
