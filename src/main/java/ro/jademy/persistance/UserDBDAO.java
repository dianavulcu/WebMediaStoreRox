package ro.jademy.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.jademy.domain.entities.CD;
import ro.jademy.domain.entities.DVD;
import ro.jademy.domain.entities.EBOOK;
import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.MediaGenre;
import ro.jademy.domain.entities.ProductType;
import ro.jademy.domain.entities.ShoppingCart;
import ro.jademy.domain.entities.User;

public class UserDBDAO implements UserDAO {

	// private Properties importFile;
	private Connection connection;
	private static UserDBDAO soleInstance = new UserDBDAO();

	private UserDBDAO() {
	}

	public static UserDBDAO getInstance() {
		return soleInstance;
	}

	public void createUser(User user) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERt INTO USERS (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME) VALUES (?,?,?,?,?)");
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmailAddress());
			statement.setString(4, user.getFirst_name());
			statement.setString(5, user.getLast_name());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}

	}

	public User getUserByUsername(String username) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM  USERS WHERE USERNAME = ?");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				return null;
			}
			User user = new User();
			user.setPassword(result.getString("password"));
			user.setEmailAddress(result.getString("email"));
			user.setUuid(result.getString("uuid"));
			user.setUsername(result.getString("username"));
			user.setFirst_name(result.getString("first_name"));
			user.setLast_name(result.getString("last_name"));
			return user;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}
	}

	public void updateUser(User user) {
		try {
			PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
			getStatement.setString(1, user.getUsername());
			ResultSet rs = getStatement.executeQuery();
			if (!rs.next()) {
				return;
			}
			PreparedStatement putStatement = connection.prepareStatement(
					"UPDATE USERS SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PASSWORD=?, UUID=? WHERE USERNAME =?");
			putStatement.setString(1, user.getFirst_name());
			putStatement.setString(2, user.getLast_name());
			putStatement.setString(3, user.getEmailAddress());
			putStatement.setString(4, user.getPassword());
			putStatement.setString(5, user.getUuid());
			putStatement.setString(6, user.getUsername());
			putStatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}
	}

	public User getUserByUuid(String uuid) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM  USERS WHERE uuid = ?");
			statement.setString(1, uuid);
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				return null;
			}
			User user = new User();
			user.setUsername(result.getString("username"));
			user.setPassword(result.getString("password"));
			user.setEmailAddress(result.getString("email"));
			user.setFirst_name(result.getString("first_name"));
			user.setLast_name(result.getString("last_name"));
			user.setUuid(uuid);
			return user;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}
	}

	public List<ShoppingCart> getShoppingCartsByUser(User currentUser) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"select * from users " + "left outer join shopping_carts on users.id = shopping_carts.user_id "
							+ "left outer join cart_items on shopping_carts.id = cart_items.shopping_cart_id "
							+ "inner join media on cart_items.media_id = media.id " + "where users.username = ?");
			statement.setString(1, currentUser.getUsername());
			ResultSet rs = statement.executeQuery();
			List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();

			int previousShoppingCartId = -1;
			ShoppingCart dbShoppingCart = null;

			while (rs.next()) {

				int currentShoppingCartId = rs.getInt("shopping_carts.id");
				if (currentShoppingCartId != previousShoppingCartId) {
					dbShoppingCart = new ShoppingCart();
					shoppingCarts.add(dbShoppingCart);
					previousShoppingCartId = currentShoppingCartId;
				}

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
			return shoppingCarts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("could not connect to database", e);
		}

	}

}
