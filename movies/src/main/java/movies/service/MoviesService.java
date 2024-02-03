package movies.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.controller.model.ActorsData;
import movies.controller.model.GenresData;
import movies.controller.model.MoviesData;
import movies.dao.MoviesDao;
import movies.dao.ActorsDao;
import movies.dao.GenresDao;
import movies.entity.Actors;
import movies.entity.Genres;
import movies.entity.Movies;

@Service
public class MoviesService {
@Autowired
private MoviesDao moviesDao;
@Autowired
private GenresDao genresDao;
@Autowired
private ActorsDao actorsDao;

@Transactional(readOnly = false)

	public MoviesData saveMovies(MoviesData moviesData) {
		Long movies_id = moviesData.getMovie_id();
		Movies movies = findOrCreateMovies(movies_id, moviesData.getTitle());
		
		setFieldsInMovies(movies, moviesData);
		return new MoviesData(moviesDao.save(movies));
		
	}

private void setFieldsInMovies(Movies movies, MoviesData moviesData) {
	movies.setDuration(moviesData.getDuration());
	movies.setTitle(moviesData.getTitle());
	movies.setRelease_year(moviesData.getRelease_year());
	
}

private Movies findOrCreateMovies(Long movies_id, String title) {
	Movies movies;
	
	if(Objects.isNull(movies_id)) {
		Optional<Movies> opTitle = moviesDao.findByTitle(title);
		
	if(opTitle.isPresent()) {
		throw new DuplicateKeyException("Title with title" +  title  +  " already exists.");
	}
		movies = new Movies();
	}
	else {
		movies = findMoviesById(movies_id);
	}
	return movies;
}

private Movies findMoviesById(Long movies_id) {
	return moviesDao.findById(movies_id)
			.orElseThrow(() -> new NoSuchElementException(
					"Movies with ID=" + movies_id + "was not found"));
}
@Transactional(readOnly = true)
public List<MoviesData> retrieveAllMovies() {
	List<Movies> movies = moviesDao.findAll();
	List<MoviesData> response = new LinkedList<>();
	
	for(Movies addmovies : movies) {
		response.add(new MoviesData(addmovies));
	}
	return response;
}
@Transactional(readOnly = true)
public MoviesData retrieveMoviesById(Long movie_id) {
	Movies movies = findMoviesById(movie_id);
	return new MoviesData(movies);
}
@Transactional(readOnly = false)
public void deleteMoviesById(Long movie_id) {
	Movies movies = findMoviesById(movie_id);
	moviesDao.delete(movies);
	
}
@Transactional(readOnly = false)
public ActorsData saveActors(Long movie_id, 
		ActorsData actorsData) {
	Movies movies = findMoviesById(movie_id);
	Long actor_id = actorsData.getActor_id();
	Actors actor = findOrCreateActors(movie_id , actor_id);
	copyActorFields(actor, actorsData);
	actor.setMovie(movies);
	movies.getActors().add(actor);
	return new ActorsData(actorsDao.save(actor));
}
private Actors findOrCreateActors(Long movie_id, Long actor_id) {
	if(Objects.isNull(actor_id)) {
		return new Actors();
	}
	return findActorById(movie_id, actor_id);
}

private Actors findActorById(Long movie_id, Long actor_id) {
	Actors actors = actorsDao.findById(actor_id).orElseThrow(() -> new NoSuchElementException(" Actor was not found"));
	if(actors.getMovie().getMovie_id() != movie_id) {
		throw new IllegalArgumentException(" This Actor is not in this movie.");
	
		}
	
	
	
	return actors;
}

private void copyActorFields(Actors actor, ActorsData actorsData) {
	actor.setActor_id(actorsData.getActor_id());
	actor.setActor_firstname(actorsData.getActor_firstname());
	actor.setActor_lastname(actorsData.getActor_lastname());
}

@Transactional(readOnly = false)
public GenresData saveGenres(Long movie_id, 
		GenresData genresData) {
	Movies movies = findMoviesById(movie_id); 
	Long genre_id = genresData.getGenre_id();
	Genres genre = findOrCreateGenres(movie_id, genre_id);
	copyGenreFields(genre , genresData);
	genre.getMovies().add(movies);
	movies.getGenres().add(genre);
	return new GenresData(genresDao.save(genre));
	
		
}
private void copyGenreFields(Genres genre, GenresData genresData) {
	genre.setAction(genresData.getAction());
	genre.setComedy(genresData.getComedy());
	genre.setRomance(genresData.getRomance());
	genre.setDrama(genresData.getRomance());
	genre.setFamily(genresData.getFamily());
	genre.setAnimation(genresData.getAnimation());
	
}

private Genres findOrCreateGenres(Long movie_id, Long genre_id) {
	if(Objects.isNull(genre_id)) {
		return new Genres();
	}
	return findGenresById(movie_id , genre_id);
}

private Genres findGenresById(Long movie_id, Long genre_id) {
	Genres genres = genresDao.findById(genre_id).orElseThrow(() -> new NoSuchElementException(" Genre was not found."));
	boolean found = false;
	for(Movies movies : genres.getMovies()) {
		if(movies.getMovie_id()== movie_id) {
			found= true;
			break;
		}
	}
	if(!found) {
		throw new IllegalArgumentException(" Genre not attached to this movie");
	}
	return genres;
	
	
}

private Movies findOrCreateMovies(Long movie_id) {
	Movies movies;
	
	if(Objects.isNull(movie_id)) {
		movies = new Movies();
	}
	else {
		movies = findMoviesById(movie_id);
	}
	return movies;
}

}
