package lest.dev.CommerceMail.dto.response.category;

import lombok.Builder;

@Builder
public record CategoryResponse(

    Long id,
    String name
) {} 