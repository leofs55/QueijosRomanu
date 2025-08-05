package lest.dev.CommerceMail.dto.response.category;

import lombok.Builder;

@Builder
public record CategoryUpdateResponse(

    Long id,
    String name
) {} 