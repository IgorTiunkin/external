package com.example.external.DTO;

import com.example.external.DTO.searchbytitle.SearchByTitleResultMovieDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchByIdResultDTO {
    private SearchByTitleResultMovieDTO results;
}
