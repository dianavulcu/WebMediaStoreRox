package ro.jademy.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import ro.jademy.domain.entities.CD;
import ro.jademy.domain.entities.DVD;
import ro.jademy.domain.entities.EBOOK;
import ro.jademy.domain.entities.MediaGenre;
import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;

import java.util.Properties;
import java.util.TreeSet;

public class MediaDBDAO {
	private Properties importFile;
	private static MediaDBDAO soleInstance = new MediaDBDAO();

	private MediaDBDAO() {
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
			FileInputStream stream = new FileInputStream("media-store-database.properties");
			importFile.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open user.properties", e);
		}
	}

	public static MediaDBDAO getInstance() {
		return soleInstance;
	}

	public List<Media> getMediaByProductType(ProductType productType) {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM  MEDIA WHERE PRODUCT_TYPE = ?");
			statement.setString(1, productType.name());
			ResultSet resultSet = statement.executeQuery();

			List<Media> aList = new ArrayList<>();

			while (resultSet.next()) {
				// ProductType.valueOf(resultSet.getString("PRODUCT_TYPE"));
				Media media = null;
				String title = resultSet.getString("title");
				String code = resultSet.getString("code");
				MediaGenre genre = MediaGenre.valueOf(resultSet.getString("genre"));
				Double price = resultSet.getDouble("price");

				switch (productType) {
				case CD:
					media = new CD();
					String artist = resultSet.getString("artist");
					media = new CD.Builder().title(title).artist(artist).price(price).code(code).genre(genre).build();
					break;
				case DVD:
					media = new DVD();
					String productionLabel = resultSet.getString("production_label");
					String directors = resultSet.getString("directors");
					media = new DVD.Builder().title(title).productionLabel(productionLabel).directors(directors)
							.price(price).code(code).genre(genre).build();
					break;
				case EBOOK:
					media = new EBOOK();
					String author = resultSet.getString("author");
					media = new EBOOK.Builder().title(title).author(author).price(price).code(code).genre(genre)
							.build();
					break;
				}
				aList.add(media);
			}
			return aList;

		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to db", e);
		}
	}

	public Media getProductbyCode(ProductType productType, String productCode) {
		Media media = null;
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM MEDIA WHERE CODE = ?");
			statement.setString(1, productCode);
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				return null;
			}
			result.beforeFirst();
			while (result.next()) {
				String title = result.getString("title");
				String code = result.getString("code");
				Double price = result.getDouble("price");
				MediaGenre genre = MediaGenre.valueOf(result.getString("genre"));

				// String dbCode =
				// importFile.getProperty(productType.name().toLowerCase() + "["
				// + i + "].code");
				// if (dbCode == null) {
				// return null;
				// }
				// if (dbCode.equals(productCode)) {
				// String dbTitle =
				// importFile.getProperty(productType.name().toLowerCase() + "["
				// + i + "].title");
				// Double dbPrice = Double
				// .valueOf(importFile.getProperty(productType.name().toLowerCase()
				// + "[" + i + "].price"));
				// MediaGenre dbGenre = MediaGenre
				// .valueOf(importFile.getProperty(productType.name().toLowerCase()
				// + "[" + i + "].genre"));
				switch (productType) {
				case CD:
					media = new CD();
					String artist = result.getString("artist");
					media = new CD.Builder().title(title).artist(artist).price(price).code(code).genre(genre).build();
					break;
				case DVD:
					media = new DVD();
					String productionLabel = result.getString("production_label");
					String directors = result.getString("directors");
					media = new DVD.Builder().title(title).productionLabel(productionLabel).directors(directors)
							.price(price).code(code).genre(genre).build();
					break;
				case EBOOK:
					media = new EBOOK();
					String author = result.getString("author");
					media = new EBOOK.Builder().title(title).author(author).price(price).code(code).genre(genre)
							.build();
					break;
				}
			}
			

		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to db", e);
		}
		return media;

	}
}
