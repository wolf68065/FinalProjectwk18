package movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import movies.controller.model.ActorsData;
import movies.service.ActorsService;

@RestController
@RequestMapping("/actors")
@Slf4j
public class ActorsController {
@Autowired	
	private ActorsService actorsService;
	
@PostMapping("/actors")	
@ResponseStatus(code = HttpStatus.CREATED)
	public ActorsData insertActors(
			@RequestBody ActorsData actorsData) {
		log.info("Creating actors {}", actorsData);
		return actorsService.saveActors(actorsData);
	}

@GetMapping("/actors/{actor_id}")
public ActorsData retrieveActorsById(@PathVariable Long actor_id) {
	log.info("Retrieving movies with ID={}", actor_id);
	return actorsService.retrieveActorsById(actor_id);

}

}
