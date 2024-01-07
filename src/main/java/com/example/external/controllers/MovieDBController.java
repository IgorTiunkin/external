package com.example.external.controllers;

import com.example.external.DTO.SearchBlankDTO;
import com.example.external.DTO.SearchByIdResultDTO;
import com.example.external.DTO.searchbytitle.SearchByTitleResultDTO;
import com.example.external.services.MoviesDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
@SessionAttributes({"blank", "currentPage"})
public class MovieDBController {

    private final MoviesDBService moviesDBService;

    @GetMapping("/find")
    public String findByNameBlank(Model model) {
        model.addAttribute("blank", new SearchBlankDTO());
        model.addAttribute("currentPage", 0);
        return "/findblank";
    }

    @GetMapping("/findResult")
    public String findByName(@ModelAttribute("blank") SearchBlankDTO searchBlankDTO, Model model,
                             @ModelAttribute(value = "currentPage") String page) {
        if(page==null || page.isEmpty()) page="0";
        searchBlankDTO.setPage(Integer.parseInt(page)+1);
        SearchByTitleResultDTO searchByTitleResultDTO = moviesDBService.findByTitle(searchBlankDTO);
        model.addAttribute("results", searchByTitleResultDTO.getResults());
        model.addAttribute("currentPage", searchByTitleResultDTO.getPage());
        model.addAttribute("next", searchByTitleResultDTO.getNext());
        return "/searchresult";
    }

    @GetMapping("/movie/{id}")
    public String movie(@PathVariable ("id") String id, Model model) {
        SearchByIdResultDTO movieById = moviesDBService.findMovieById(id);
        model.addAttribute("result", movieById.getResults());
        return "/movie";
    }

}
