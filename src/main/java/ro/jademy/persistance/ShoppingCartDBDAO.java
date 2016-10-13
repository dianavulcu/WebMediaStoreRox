package ro.jademy.persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import ro.jademy.domain.entities.CD;
import ro.jademy.domain.entities.CartItem;
import ro.jademy.domain.entities.DVD;
import ro.jademy.domain.entities.EBOOK;
import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.MediaGenre;
import ro.jademy.domain.entities.ProductType;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;
import ro.jademy.domain.entities.UserType;

@Repository
public class ShoppingCartDBDAO {

	Connection connection = ConnectionManager.getConnection();
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

			statement = connection.prepareStatement(
					"INSERT INTO shopping_carts (user_id, shopping_cart_date) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, userId);
			Date x = new Date(System.currentTimeMillis());
			statement.setDate(2, x);
			statement.executeUpdate();
			ResultSet keysRS = statement.getGeneratedKeys();
			keysRS.next();

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

	public ShoppingCart getShoppingCart(long savedCartId) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from shopping_carts "
					+ "left outer join cart_items on shopping_carts.id = cart_items.shopping_cart_id "
					+ "inner join media on cart_items.media_id = media.id " + "where shopping_carts.id = ?");
			statement.setLong(1, savedCartId);
			ResultSet rs = statement.executeQuery();
			ShoppingCart dbShoppingCart = new ShoppingCart();

			while (rs.next()) {
				Media dbMedia = null;
				ProductType dbProductType = ProductType.valueOf(rs.getString("media.product_type"));
				switch (dbProductType) {
				case CD:
					dbMedia = new CD(rs.getString("media.title"), rs.getDouble("media.price"),
							rs.getString("media.code"), MediaGenre.valueOf(rs.getString("media.genre")),
							rs.getString("media.artist"));
					break;
				case DVD:
					dbMedia = new DVD(rs.getString("media.title"), rs.getDouble("media.price"),
							rs.getString("media.code"), MediaGenre.valueOf(rs.getString("media.genre")),
							rs.getString("media.directors"), rs.getString("media.production_label"));
					break;
				case EBOOK:
					dbMedia = new EBOOK(rs.getString("media.title"), rs.getDouble("media.price"),
							rs.getString("media.code"), MediaGenre.valueOf(rs.getString("media.genre")),
							rs.getString("media.author"));
					break;
				default:
					break;
				}
				int quantity = rs.getInt("cart_items.quantity");
				dbShoppingCart.addToCart(dbMedia, quantity);
			}
			return dbShoppingCart;
		} catch (SQLException e) {
			throw new RuntimeException("could not connect to database", e);
		}
	}

}
