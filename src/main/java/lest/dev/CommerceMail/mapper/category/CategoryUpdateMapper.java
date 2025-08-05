package lest.dev.CommerceMail.mapper.category;

import lest.dev.CommerceMail.dto.request.category.CategoryUpdateRequest;
import lest.dev.CommerceMail.dto.response.category.CategoryUpdateResponse;
import lest.dev.CommerceMail.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryUpdateMapper {

    public static Category map(CategoryUpdateRequest categoryUpdateRequest) {
        return Category.builder()
                .name(categoryUpdateRequest.name())
                .build();
    }

    public static CategoryUpdateResponse map(Category category) {
        return CategoryUpdateResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
