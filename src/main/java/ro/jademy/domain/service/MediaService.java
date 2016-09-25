package ro.jademy.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;
import ro.jademy.persistance.MediaDAO;

@Service
public class MediaService {

	public List<? extends Media> getAllMedia(ProductType productType) {
		return MediaDAO.getInstance().getMediaByProductType(productType);
	}

	public Media getProductByProductTypeAndCode(ProductType productType, String productCode) {
		return MediaDAO.getInstance().getProductbyCode(productType, productCode);
	}

}
