package lest.dev.CommerceMail.mapper.product;

import lest.dev.CommerceMail.dto.request.product.ProductCreateRequest;
import lest.dev.CommerceMail.dto.response.product.ProductCreateResponse;
import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.mapper.category.CategoryMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductCreateMapper {

    public static Product map(ProductCreateRequest productCreateRequest) {
        return Product.builder()
                .name(productCreateRequest.name())
                .description(productCreateRequest.description())
                .price(productCreateRequest.price())
                .grams(productCreateRequest.grams())
                .quantity(productCreateRequest.quantity())
                .imgUrl(productCreateRequest.imgUrl())
                .category(CategoryMapper.map(productCreateRequest.categoryId()))
                .build();
    }

    public static ProductCreateResponse map(Product product) {
        return ProductCreateResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .grams(product.getGrams())
                .imgUrl(product.getImgUrl())
                .categoryResponse(CategoryMapper.map(product.getCategory()))
                .build();
    }
}
