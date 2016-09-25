package ro.jademy.persistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import ro.jademy.domain.entities.CartItem;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

@Repository
public class ShoppingCartDAO {

	private Properties importFile;
	private static ShoppingCartDAO soleInstance = new ShoppingCartDAO();

	private ShoppingCartDAO() {
		importFile = new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public synchronized Enumeration<Object> keys() {
				return Collections.enumeration(new TreeSet<Object>(keySet()));
			}
		};
		try {
			FileInputStream stream = new FileInputStream("shopping-carts.properties");
			importFile.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open shopping-carts.properties", e);
		}
	}

	public static ShoppingCartDAO getInstance() {
		return soleInstance;
	}

	public void createCart(ShoppingCart shoppingCart, User user) {
		int cartIndex = getNextCartIndex();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
		importFile.setProperty("cart[" + cartIndex + "].username", user.getUsername());
		importFile.setProperty("cart[" + cartIndex + "].date", format.format(new Date()));
		int cartItemIndex = 1;
		for (CartItem cartItem : shoppingCart.getCartItems()) {
			importFile.setProperty("cart[" + cartIndex + "].cartItem[" + cartItemIndex + "].productCode",
					cartItem.getMedia().getCode());
			importFile.setProperty("cart[" + cartIndex + "].cartItem[" + cartItemIndex + "].quantity",
					String.valueOf(cartItem.getQuantity()));
			cartItemIndex++;
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("shopping-carts.properties");
			importFile.store(fos, new Date().toString());
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Cannot save shopping-carts.properties", e);
		}
	}

	private int getNextCartIndex() {
		int i = 1;
		while (true) {
			String dbShoppingCart = importFile.getProperty("cart[" + i + "].username");
			if (dbShoppingCart == null) {
				return i;
			}
			i++;
		}
	}

}
