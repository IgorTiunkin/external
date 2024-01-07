package com.example.external.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchBlankDTO {

    private String searchWord;

    private Integer page;

}
