package com.cybersoft.uniclub06.service.imp;

import com.cybersoft.uniclub06.dto.ProductDTO;
import com.cybersoft.uniclub06.entity.*;
import com.cybersoft.uniclub06.repository.ProductRepository;
import com.cybersoft.uniclub06.repository.VariantRepository;
import com.cybersoft.uniclub06.request.AddProductRequest;
import com.cybersoft.uniclub06.service.FileService;
import com.cybersoft.uniclub06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private FileService fileService;

    @Transactional // roll back data khi gặp lỗi để tránh thêm data rác vô database

    @Override
    public void addProduct(AddProductRequest request) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(request.name());
        productEntity.setDesc(request.desc());
        productEntity.setInfo(request.information());
        productEntity.setPrice(request.price());

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(request.idBrand());

        productEntity.setBrand(brandEntity);

        ProductEntity productInserted = productRepository.save(productEntity);

        VariantEntity variantEntity = new VariantEntity();
        variantEntity.setProduct(productInserted);

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(request.idColor());
        variantEntity.setColor(colorEntity);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(request.idSize());

        variantEntity.setSize(sizeEntity);
        variantEntity.setPrice(request.price());
        variantEntity.setQuantity(request.quantity());
        variantEntity.setImages(request.files().getOriginalFilename());

        variantRepository.save(variantEntity);

        fileService.saveFile(request.files());
    }

    @Override
    public List<ProductDTO> getProduct() {
//        List<ProductEntity> listProductEntity = productRepository.findAll();
        return productRepository.findAll().stream().map(item -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(item.getName());
            productDTO.setPrice(item.getPrice());
            if (item.getVariants().size() > 0) {
                productDTO.setLink("http://localhost:8080/file/" + item.getVariants().getFirst().getImages());
            } else {
                productDTO.setLink("");
            }
            return productDTO;
        }).toList();

    }
}
