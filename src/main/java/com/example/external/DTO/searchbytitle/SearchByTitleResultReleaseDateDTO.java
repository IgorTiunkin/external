package com.example.external.DTO.searchbytitle;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchByTitleResultReleaseDateDTO {
    private Integer day;
    private Integer month;
    private Integer year;
}
