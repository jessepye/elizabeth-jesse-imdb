package com.example.elizabethjesseimdb;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    DataService dataService;

    public MovieController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return dataService.getMovies();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie newMovie) {
        dataService.addMovie(newMovie);
        return newMovie;
    }
}