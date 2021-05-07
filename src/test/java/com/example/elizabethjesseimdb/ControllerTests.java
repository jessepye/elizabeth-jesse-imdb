package com.example.elizabethjesseimdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataService dataService;

    ArrayList<Movie> movieList;

    @BeforeEach
    void setup() {
        movieList = new ArrayList<Movie>();
        Movie movie1 = new Movie("The Royal Tenenbaums", "2001", "notNominated", "comedy");
        movieList.add(movie1);
    }

    @Test
    void getMovies() throws Exception {
        when(dataService.getMovies()).thenReturn(movieList);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void addMovie() throws Exception {
        Movie postMovie = new Movie("Citizen Kane", "1941", "oscarWinner", "drama");
        when(dataService.addMovie(postMovie)).thenReturn(movieList);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Citizen Kane\",\"year\":\"1941\",\"oscarStatus\":\"oscarWinner\",\"genre\":\"drama\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }
}