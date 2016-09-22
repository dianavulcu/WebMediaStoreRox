package ro.jademy.domain.service;

import java.util.List;

import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;
import ro.jademy.persistance.MediaDAO;

public class MediaService {

	public List<? extends Media> getAllMedia(ProductType productType) {
		return MediaDAO.getInstance().getAllMedia(productType);
	}

	public Media getProductByProductTypeAndCode(ProductType productType, String productCode) {
		List<Media> mediaList = MediaDAO.getInstance().getAllMedia(productType);
		for (Media media : mediaList) {
			if (productCode.equals(media.getCode())) {
				return media;
			}
		}

		return null;

	}

}
