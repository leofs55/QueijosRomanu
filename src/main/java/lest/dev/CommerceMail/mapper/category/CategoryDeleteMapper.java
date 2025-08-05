package lest.dev.CommerceMail.mapper.category;

import lest.dev.CommerceMail.dto.response.category.CategoryDeleteResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryDeleteMapper {

    public static CategoryDeleteResponse map(String message) {
        return CategoryDeleteResponse.builder()
                .message(message)
                .build();
    }
}
