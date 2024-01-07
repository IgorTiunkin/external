package com.example.external.DTO.searchbytitle;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchByTitleResultMovieImageDTO {

    private String id;

    private Integer width;

    private Integer height;

    private String url;
}
