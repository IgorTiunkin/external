package com.example.external.services;

import com.example.external.DTO.SearchBlankDTO;
import com.example.external.DTO.SearchByIdResultDTO;
import com.example.external.DTO.searchbyseries.SearchBySeriesResultDTO;
import com.example.external.DTO.searchbytitle.SearchByTitleResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MoviesDBService {

    private final WebClient.Builder builder;

    @Value("${apiKey}")
    private String apiKey;

    public SearchByTitleResultDTO findByTitle(SearchBlankDTO searchBlankDTO) {
        String searchWord = searchBlankDTO.getSearchWord();
        Integer page = searchBlankDTO.getPage();
        return builder.build()
                .get()
                .uri("https://moviesdatabase.p.rapidapi.com/titles/search/title/" + searchWord,
                        uriBuilder -> uriBuilder.queryParam("page", page).build())
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .retrieve()
                .bodyToMono(SearchByTitleResultDTO.class)
                .block();
    }

    public SearchByIdResultDTO findMovieById(String id) {
        return builder.build()
                .get()
                .uri("https://moviesdatabase.p.rapidapi.com/titles/" + id)
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .retrieve()
                .bodyToMono(SearchByIdResultDTO.class)
                .block();
    }

    public SearchBySeriesResultDTO findSeriesById(String id) {
        return builder.build()
                .get()
                .uri("https://moviesdatabase.p.rapidapi.com/titles/series/"+id)
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .retrieve()
                .bodyToMono(SearchBySeriesResultDTO.class)
                .block();
    }
}
