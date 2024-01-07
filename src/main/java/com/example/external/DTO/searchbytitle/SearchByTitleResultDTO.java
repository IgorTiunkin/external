package com.example.external.DTO.searchbytitle;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchByTitleResultDTO {

    private Integer entries;

    private SearchByTitleResultMovieDTO [] results;

}
