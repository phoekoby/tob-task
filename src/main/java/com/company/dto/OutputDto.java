package com.company.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OutputDto {
    Integer instrumentId;
    Character side;
    Long price;
    Integer amount;
}
