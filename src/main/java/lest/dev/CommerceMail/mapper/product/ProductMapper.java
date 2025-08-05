package lest.dev.CommerceMail.mapper.product;

import lest.dev.CommerceMail.dto.response.product.ProductResponse;
import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.mapper.category.CategoryMapper;
import lombok.experimental.UtilityClass;    

@UtilityClass
public class ProductMapper {

    public static Product map(Product product, Integer quantity) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .grams(product.getGrams())
                .quantity(quantity)
                .imgUrl(product.getImgUrl())
                .category(product.getCategory())
                .build();
    }

    public static ProductResponse map(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .grams(product.getGrams())
                .imgUrl(product.getImgUrl())
                .categoryResponse(CategoryMapper.map(product.getCategory()))
                .build();
    }
}
