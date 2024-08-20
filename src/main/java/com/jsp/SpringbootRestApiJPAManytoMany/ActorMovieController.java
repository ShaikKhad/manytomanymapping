package com.jsp.SpringbootRestApiJPAManytoMany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actor-movie")
public class ActorMovieController {

    private final ActorService actorService;
    private final MovieService movieService;

    public ActorMovieController(ActorService actorService, MovieService movieService) {
        this.actorService = actorService;
        this.movieService = movieService;
    }

    @PostMapping("/actor")
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
        return new ResponseEntity<>(actorService.saveActor(actor), HttpStatus.CREATED);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor actorDetails) {
        return actorService.getActorById(id).map(actor -> {
            actor.setName(actorDetails.getName());
            actor.setMovies(actorDetails.getMovies());
            Actor updatedActor = actorService.saveActor(actor);
            return new ResponseEntity<>(updatedActor, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        return movieService.getMovieById(id).map(movie -> {
            movie.setTitle(movieDetails.getTitle());
            movie.setRating(movieDetails.getRating());
            movie.setActors(movieDetails.getActors());
            Movie updatedMovie = movieService.saveMovie(movie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/actors")
    public ResponseEntity<Page<Actor>> getAllActors(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {

        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortBy);
        return new ResponseEntity<>(actorService.getActors(pageable), HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<Page<Movie>> getAllMovies(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {

        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortBy);
        return new ResponseEntity<>(movieService.getMovies(pageable), HttpStatus.OK);
    }

    @GetMapping("/actors/search")
    public ResponseEntity<Page<Actor>> findActorsByName(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {

        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortBy);
        return new ResponseEntity<>(actorService.getActorsByName(name, pageable), HttpStatus.OK);
    }


    @GetMapping("/movies/search")
    public ResponseEntity<Page<Movie>> findMoviesByRating(
            @RequestParam double rating,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {

        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortBy);
        return new ResponseEntity(movieService.getMoviesByRating(rating, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
