package lest.dev.CommerceMail.mapper.product;

import lest.dev.CommerceMail.dto.request.product.ProductUpdateRequest;
import lest.dev.CommerceMail.dto.response.product.ProductUpdateResponse;
import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.mapper.category.CategoryMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductUpdateMapper {

    public static Product map(ProductUpdateRequest productUpdateRequest) {
        return Product.builder()
                .name(productUpdateRequest.name())
                .description(productUpdateRequest.description())
                .price(productUpdateRequest.price())
                .grams(productUpdateRequest.grams())
                .quantity(productUpdateRequest.quantity())
                .imgUrl(productUpdateRequest.imgUrl())
                .category(CategoryMapper.map(productUpdateRequest.categoryId()))
                .build();
    }

    public static ProductUpdateResponse map(Product product) {
        return ProductUpdateResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .grams(product.getGrams())
                .quantity(product.getQuantity())
                .imgUrl(product.getImgUrl())
                .categoryResponse(CategoryMapper.map(product.getCategory()))
                .build();
    }
}
