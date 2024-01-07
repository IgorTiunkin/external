package com.example.external.DTO.searchbyseries;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchBySeriesResultDTO {

    private SearchBySeriesEpisodeDTO [] results;
}
