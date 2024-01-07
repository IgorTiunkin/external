package com.example.external.DTO.searchbytitle;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchByTitleResultMovieDTO {

    private String id;

    private SearchByTitleResultMovieImageDTO primaryImage;

    private SearchByTitleResultMovieTitle titleText;

    private SearchByTitleResultReleaseYearDTO releaseYear;

    private SearchByTitleResultReleaseDateDTO releaseDate;

    private SearchByTitleResultTitleTypeDTO titleType;

}
