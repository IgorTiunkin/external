package com.example.external.DTO.searchbyseries;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchBySeriesEpisodeDTO {

    private String tconst;

    private Integer seasonNumber;

    private Integer episodeNumber;
}
