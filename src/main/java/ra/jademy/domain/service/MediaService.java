package ra.jademy.domain.service;

import ra.jademy.domain.entities.ProductType;
import ra.jademy.domain.entities.Media;
import java.util.List;

import ra.jademy.persistance.MediaDAO;

public class MediaService {

	public List<? extends Media> getAllMedia(ProductType productType) {
		return MediaDAO.getInstance().getAllMedia(productType);
	}

	public Media getProductByCode(String productCode) {
		for (ProductType productType : ProductType.values()) {

			List<Media> mediaList = MediaDAO.getInstance().getAllMedia(productType);
			for (Media media : mediaList) {
				if (media.getCode().equals(productCode)) {
					return media;
				}
			}
		}
		return null;

	}
}
