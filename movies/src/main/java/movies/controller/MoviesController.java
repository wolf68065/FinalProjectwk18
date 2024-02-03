package movies.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import movies.controller.model.ActorsData;
import movies.controller.model.GenresData;
import movies.controller.model.MoviesData;
import movies.service.MoviesService;

@RestController
@Slf4j
@RequestMapping("/movies")
public class MoviesController {
	@Autowired
	private MoviesService moviesService;
	
	@PostMapping("/movies")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MoviesData insertMovies(
			@RequestBody MoviesData moviesData) {
		log.info("Creating movies {}", moviesData);
		return moviesService.saveMovies(moviesData);
}

	@PutMapping("/movies/{movie_id}")
	public MoviesData updateMovies(@PathVariable Long movie_id, 
			@RequestBody MoviesData moviesData) {
		moviesData.setMovie_id(movie_id);
		log.info("Updating movies {}", moviesData);
		return moviesService.saveMovies(moviesData);
	}

	@GetMapping("/movies")
	public List<MoviesData> retieveAllMovies(){
		log.info("Retrieve all movies called.");
		
		
		return moviesService.retrieveAllMovies();
	}
	@GetMapping("/movies/{movie_id}")
	public MoviesData retrieveMoviesById(@PathVariable Long movie_id) {
		log.info("Retrieving movies with ID={}", movie_id);
		return moviesService.retrieveMoviesById(movie_id);
	
	}
	@DeleteMapping("/movies") 
	public void deleteAllMovies() {
	  log.info("Attempting to delete all movies");
	  throw new UnsupportedOperationException("Deleting all movies is not allowed."); 
	}
	@DeleteMapping("/movies/{movie_id}")
	public Map<String, String> deleteMoviesById(
			@PathVariable Long movie_id){
		log.info("Deleting movies with ID={}", movie_id);
		
		moviesService.deleteMoviesById(movie_id);
		
		return Map.of("message", "Deletion of movies with ID=" + movie_id + " was successful.");
		
	}
	@PostMapping("/movies/{movie_id}/actor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ActorsData insertActors(@PathVariable Long movie_id, 
			@RequestBody ActorsData actorsData) {
		
		log.info("Creating actor {} for movies with ID={}", movie_id, actorsData);
		return moviesService.saveActors(movie_id, actorsData);
	}
	@PostMapping("/movies/{movie_id}/genre")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GenresData addGenresToMovies(@PathVariable Long movie_id, 
			@RequestBody GenresData genresData) {
		log.info("Creating genre {} for movies with ID={}", movie_id, 
				genresData);
		return moviesService.saveGenres(movie_id, genresData);
}}
