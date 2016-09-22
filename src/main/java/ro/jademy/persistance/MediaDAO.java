package ro.jademy.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;

import ro.jademy.domain.entities.CD;
import ro.jademy.domain.entities.DVD;
import ro.jademy.domain.entities.EBOOK;
import ro.jademy.domain.entities.MediaGenre;
import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;

import java.util.Properties;
import java.util.TreeSet;

<<<<<<< HEAD:src/main/java/ro/jademy/persistance/MediaDAO.java
=======
import ra.jademy.domain.entities.CD;
import ra.jademy.domain.entities.DVD;
import ra.jademy.domain.entities.EBOOK;
import ra.jademy.domain.entities.Genre;
import ra.jademy.domain.entities.Media;
import ra.jademy.domain.entities.ProductType;
import ra.jademy.domain.entities.User;

>>>>>>> origin/master:src/main/java/ra/jademy/persistance/MediaDAO.java
public class MediaDAO {
	private Properties importFile;
	private static MediaDAO soleInstance = new MediaDAO();

	private MediaDAO() {
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

	public static MediaDAO getInstance() {
		return soleInstance;
	}

	public List<Media> getAllMedia(ProductType productType) {
		List<Media> aList = new ArrayList<>();
		int i = 0;
		while (true) {
			i++;
			String dbCode = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].code");
			if (dbCode == null) {
				break;
			}
			String dbTitle = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].title");
			Double dbPrice = Double
					.valueOf(importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].price"));
			MediaGenre dbGenre = MediaGenre
					.valueOf(importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].genre"));
			Media media = null;
			switch (productType) {
			case CD:
				String dbArtist = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].artist");
				media = new CD.Builder().title(dbTitle).artist(dbArtist).price(dbPrice).code(dbCode).genre(dbGenre)
						.build();
				break;
			case DVD:
				String dbDirectors = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].directors");
				String dbProductionLabels = importFile
						.getProperty(productType.name().toLowerCase() + "[" + i + "].productionLabels");
				media = new DVD.Builder().title(dbTitle).directors(dbDirectors).productionLabel(dbProductionLabels)
						.price(dbPrice).code(dbCode).genre(dbGenre).build();
				break;
			case EBOOK:
				String dbAuthor = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].author");
				media = new EBOOK.Builder().title(dbTitle).author(dbAuthor).price(dbPrice).code(dbCode).genre(dbGenre)
						.build();
			}
			aList.add(media);
		}
		return aList;
	}

<<<<<<< HEAD:src/main/java/ro/jademy/persistance/MediaDAO.java
=======
	public Media getProductbyCode(ProductType productType, String productCode) {
		// int i=0;
		// for (Entry pEntry:importFile.entrySet()){
		// i++;
		// if (pEntry.getValue().equals(productCode) &&
		// ((String)pEntry.getKey()).endsWith(".code")){
		//
		// }
		// }
		// return null;
		Media media = null;
		int i = 0;
		while (true) {
			i++;
			String dbCode = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].code");
			if (dbCode == null) {
				return null;
			}
			if (dbCode.equals(productCode)) {
				String dbTitle = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].title");
				Double dbPrice = Double
						.valueOf(importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].price"));
				Genre dbGenre = Genre
						.valueOf(importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].genre"));
				switch (productType) {
				case CD:
					String dbArtist = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].artist");
					media = new CD.Builder().title(dbTitle).artist(dbArtist).price(dbPrice).code(dbCode).genre(dbGenre)
							.build();
					break;
				case DVD:
					String dbDirectors = importFile
							.getProperty(productType.name().toLowerCase() + "[" + i + "].directors");
					String dbProductionLabels = importFile
							.getProperty(productType.name().toLowerCase() + "[" + i + "].productionLabel");
					media = new DVD.Builder().title(dbTitle).directors(dbDirectors).productionLabel(dbProductionLabels)
							.price(dbPrice).code(dbCode).genre(dbGenre).build();
					break;
				case EBOOK:
					String dbAuthor = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].author");
					media = new EBOOK.Builder().title(dbTitle).author(dbAuthor).price(dbPrice).code(dbCode)
							.genre(dbGenre).build();
					return media;
				}
			}
		}
	}
>>>>>>> origin/master:src/main/java/ra/jademy/persistance/MediaDAO.java
}