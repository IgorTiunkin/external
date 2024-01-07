package com.example.external.services;

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

    public SearchByTitleResultDTO findByTitle(String searchWord) {
        return builder.build()
                .get()
                .uri("https://moviesdatabase.p.rapidapi.com/titles/search/akas/" + searchWord)
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .retrieve()
                .bodyToMono(SearchByTitleResultDTO.class)
                .block();
    }
}
