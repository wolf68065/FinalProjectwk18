package movies.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.controller.model.ActorsData;
import movies.controller.model.MoviesData;
import movies.dao.ActorsDao;
import movies.entity.Actors;
import movies.entity.Movies;

@Service
public class ActorsService {
	@Autowired
	
	private ActorsDao actorsDao;
	

	@Transactional(readOnly = false)
	public ActorsData saveActors(ActorsData actorsData) {
		Long actor_id = actorsData.getActor_id();
		Actors actors =findOrCreateActors(actor_id);
		
		setFieldsInActors(actors, actorsData);
		return new ActorsData(actorsDao.save(actors));
		
	}

	private void setFieldsInActors(Actors actors, ActorsData actorsData) {
		actors.setActor_firstname(actorsData.getActor_firstname());
		actors.setActor_lastname(actorsData.getActor_lastname());
		
	}

	private Actors findOrCreateActors(Long actor_id) {
		Actors actors;
		
		if(Objects.isNull(actor_id)) {
			actors = new Actors();
		}
		else {
			actors = findActorsById(actor_id);
		}
		return actors;
	}

	private Actors findActorsById(Long actor_id) {
		
		return actorsDao.findById(actor_id).
				orElseThrow(() -> new NoSuchElementException(
						"Actors with ID=" + actor_id + " was not found."));
	}

	@Transactional(readOnly = true)
	public ActorsData retrieveActorsById(Long actor_id) {
		Actors actors = findActorsById(actor_id);
		return new ActorsData(actors);
	}

}
