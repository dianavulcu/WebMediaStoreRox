package ro.jademy.persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import ro.jademy.domain.entities.CartItem;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

@Repository
public class ShoppingCartDBDAO {

	Connection connection = getConnection();
	private static ShoppingCartDBDAO soleInstance = new ShoppingCartDBDAO();

	public static ShoppingCartDBDAO getInstance() {
		return soleInstance;
	}

	public void createCart(ShoppingCart shoppingCart, User user) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT id FROM USERS WHERE username = ?");
			statement.setString(1, user.getUsername());
			ResultSet rs = statement.executeQuery();
			rs.next();
			Integer userId = rs.getInt(1);

			statement = connection
					.prepareStatement("INSERT INTO shopping_carts (user_id, shopping_cart_date) VALUES (?, ?)");
			statement.setInt(1, userId);
			Date x = new Date(System.currentTimeMillis());
			statement.setDate(2, x);
			statement.executeUpdate();

			statement = connection.prepareStatement("SELECT id FROM shopping_carts ORDER BY id DESC");
			rs = statement.executeQuery();
			rs.next();
			int shoppingCartId = rs.getInt(1);

			for (CartItem cartItem : shoppingCart.getCartItems()) {
				statement = connection.prepareStatement(
						"INSERT INTO cart_items (shopping_cart_id, media_id, quantity) VALUES (?, ?, ?)");
				statement.setInt(1, shoppingCartId);

				PreparedStatement statement2 = connection.prepareStatement("SELECT id FROM media WHERE code = ?");
				statement2.setString(1, cartItem.getMedia().getCode());
				rs = statement2.executeQuery();
				rs.next();
				int mediaId = rs.getInt(1);
				statement.setInt(2, mediaId);
	
				statement.setInt(3, cartItem.getQuantity());
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}

	}
	// int cartIndex = getNextCartIndex();
	// SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
	// importFile.setProperty("cart[" + cartIndex + "].username",
	// user.getUsername());
	// importFile.setProperty("cart[" + cartIndex + "].date", format.format(new
	// Date()));
	// int cartItemIndex = 1;
	// for (CartItem cartItem : shoppingCart.getCartItems()) {
	// importFile.setProperty("cart[" + cartIndex + "].cartItem[" +
	// cartItemIndex + "].productCode",
	// cartItem.getMedia().getCode());
	// importFile.setProperty("cart[" + cartIndex + "].cartItem[" +
	// cartItemIndex + "].quantity",
	// String.valueOf(cartItem.getQuantity()));
	// cartItemIndex++;
	// }
	// FileOutputStream fos;
	// try {
	// fos = new FileOutputStream("shopping-carts.properties");
	// importFile.store(fos, new Date().toString());
	// fos.close();
	// } catch (IOException e) {
	// throw new RuntimeException("Cannot save shopping-carts.properties", e);
	// }
	// }

	Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media_store", "root",
					"root");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
