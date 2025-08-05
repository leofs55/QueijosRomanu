package lest.dev.CommerceMail.dto.response.category;

import lombok.Builder;

@Builder
public record CategoryCreateResponse(

    Long id,
    String name
) {} 