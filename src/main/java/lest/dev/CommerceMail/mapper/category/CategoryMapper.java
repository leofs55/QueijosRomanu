package lest.dev.CommerceMail.mapper.category;

import lest.dev.CommerceMail.dto.response.category.CategoryResponse;
import lest.dev.CommerceMail.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static CategoryResponse map(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category map(Long id) {
        return Category.builder()
                .id(id)
                .build();
    }
}
