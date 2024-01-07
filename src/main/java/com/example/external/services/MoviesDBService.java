package com.example.external.services;

import com.example.external.DTO.SearchBlankDTO;
import com.example.external.DTO.SearchByIdResultDTO;
import com.example.external.DTO.searchbyseries.SearchBySeriesResultDTO;
import com.example.external.DTO.searchbytitle.SearchByTitleResultDTO;
import com.example.external.exceptions.resilience4j.MovieDBServiceCircuitException;
import com.example.external.exceptions.resilience4j.MovieDBServiceException;
import com.example.external.exceptions.resilience4j.MovieDBServiceTimeoutException;
import com.example.external.exceptions.resilience4j.MovieDBServiceTooManyRequestsException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoviesDBService {

    private final WebClient.Builder builder;

    @Value("${apiKey}")
    private String apiKey;

    @Retry(name = "movieDB", fallbackMethod = "movieDBFail")
    @CircuitBreaker(name = "movieDB", fallbackMethod = "movieDBCircuit")
    @TimeLimiter(name = "movieDB", fallbackMethod = "movieDBTimeout")
    @RateLimiter(name = "movieDB", fallbackMethod = "movieDBTooManyRequests")
    public CompletableFuture<SearchByTitleResultDTO> findByTitle(SearchBlankDTO searchBlankDTO) {
        String searchWord = searchBlankDTO.getSearchWord();
        Integer page = searchBlankDTO.getPage();
        return CompletableFuture.supplyAsync(() -> builder.build()
                .get()
                .uri("https://moviesdatabase.p.rapidapi.com/titles/search/title/" + searchWord,
                        uriBuilder -> uriBuilder.queryParam("page", page).build())
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .retrieve()
                .bodyToMono(SearchByTitleResultDTO.class)
                .block()
        );
    }

    @Retry(name = "movieDB", fallbackMethod = "movieDBFail")
    @CircuitBreaker(name = "movieDB", fallbackMethod = "movieDBCircuit")
    @TimeLimiter(name = "movieDB", fallbackMethod = "movieDBTimeout")
    @RateLimiter(name = "movieDB", fallbackMethod = "movieDBTooManyRequests")
    public CompletableFuture<SearchByIdResultDTO> findMovieById(String id) {
        return CompletableFuture.supplyAsync(()-> builder.build()
                .get()
                .uri("https://moviesdatabase.p.rapidapi.com/titles/" + id)
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .retrieve()
                .bodyToMono(SearchByIdResultDTO.class)
                .block()
        );
    }

    @Retry(name = "movieDB", fallbackMethod = "movieDBFail")
    @CircuitBreaker(name = "movieDB", fallbackMethod = "movieDBCircuit")
    @TimeLimiter(name = "movieDB", fallbackMethod = "movieDBTimeout")
    @RateLimiter(name = "movieDB", fallbackMethod = "movieDBTooManyRequests")
    public CompletableFuture<SearchBySeriesResultDTO> findSeriesById(String id) {
        return CompletableFuture.supplyAsync( ()-> builder.build()
                .get()
                .uri("https://moviesdatabase.p.rapidapi.com/titles/series/"+id)
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .retrieve()
                .bodyToMono(SearchBySeriesResultDTO.class)
                .block()
        );
    }

    public CompletableFuture <SearchByTitleResultDTO> movieDBFail (Exception exception) {
        log.info("Fallback method  movieDBFail, {}", exception.getMessage());
        if (exception instanceof WebClientResponseException) {
            throw new MovieDBServiceException("movieDB is unavailable. Please try later.");
        }
        throw new MovieDBServiceException(exception.getMessage());
    }

    public CompletableFuture <SearchByTitleResultDTO> movieDBCircuit (CallNotPermittedException exception) {
        log.info("Fallback method movieDBCircuit activated, {}", exception.getMessage());
        throw new MovieDBServiceCircuitException("MovieDB service is unavailable. Please try later.");
    }

    public CompletableFuture <SearchByTitleResultDTO> movieDBTimeout (TimeoutException exception) {
        log.info("Fallback method movieDBTimeout activated, {}", exception.getMessage());
        throw new MovieDBServiceTimeoutException("MovieDB service is unavailable. Please try later.");
    }

    public CompletableFuture <SearchByTitleResultDTO> movieDBTooManyRequests (RequestNotPermitted exception) {
        log.info("Fallback method movieDBTooManyRequests activated, {}", exception.getMessage());
        throw new MovieDBServiceTooManyRequestsException("You have done too many requests to inventory. Please try later.");
    }
}
