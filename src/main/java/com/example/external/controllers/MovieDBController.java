package com.example.external.controllers;

import com.example.external.DTO.SearchBlankDTO;
import com.example.external.DTO.searchbytitle.SearchByTitleResultDTO;
import com.example.external.services.MoviesDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieDBController {

    private final MoviesDBService moviesDBService;

    @GetMapping("/find")
    public String findByNameBlank(Model model) {
        model.addAttribute("blank", new SearchBlankDTO());
        return "/findblank";
    }

    @PostMapping("/findResult")
    public String findByName(@ModelAttribute SearchBlankDTO searchBlankDTO, Model model) {
        String searchWord = searchBlankDTO.getSearchWord();
        SearchByTitleResultDTO searchByTitleResultDTO = moviesDBService.findByTitle(searchWord);
        model.addAttribute("results", searchByTitleResultDTO.getResults());
        return "/searchresult";
    }

}
