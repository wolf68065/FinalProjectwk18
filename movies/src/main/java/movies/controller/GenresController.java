package movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import movies.controller.model.ActorsData;
import movies.controller.model.GenresData;
import movies.service.GenresService;

@RestController
@RequestMapping("/genres")
@Slf4j
public class GenresController {
	@Autowired
	private GenresService genresService;
	
	@PostMapping("/genres")
	public GenresData insertGenres(@RequestBody GenresData genresData) {
		log.info("Creating genres {}", genresData);
		return genresService.saveGenres(genresData);
	}
	@GetMapping("/actors/{actor_id}")
	public GenresData retrieveGenresById(@PathVariable Long genre_id) {
		log.info("Retrieving movies with ID={}", genre_id);
		return genresService.retrieveGenresById(genre_id);

}}
