package lest.dev.CommerceMail.mapper.category;

import lest.dev.CommerceMail.dto.request.category.CategoryCreateRequest;
import lest.dev.CommerceMail.dto.response.category.CategoryCreateResponse;
import lest.dev.CommerceMail.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryCreateMapper {

    public static Category map(CategoryCreateRequest categoryCreateRequest) {
        return Category.builder()
                .name(categoryCreateRequest.name())
                .build();
    }

    public static CategoryCreateResponse map(Category category) {
        return CategoryCreateResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
