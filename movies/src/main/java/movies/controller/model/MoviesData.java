package movies.controller.model;


import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import movies.entity.Actors;
import movies.entity.Genres;
import movies.entity.Movies;

@Data
@NoArgsConstructor
public class MoviesData {
	private Long movie_id;
	private String title;
	private Long duration;
	private int release_year;
	 Set<ActorsResponse> actors = new HashSet<>();
	 Set<GenresResponse> genres = new HashSet<>();
	
	public MoviesData(Movies movies) {
		movie_id = movies.getMovie_id();
		title = movies.getTitle();
		duration = movies.getDuration();
		release_year = movies.getRelease_year();
		
		for(Genres genre : movies.getGenres()) {
			genres.add(new GenresResponse(genre));
		}
		for(Actors actor : movies.getActors()) {
			actors.add(new ActorsResponse(actor));
		}
	}
}	
		@Data
	@NoArgsConstructor
	
final class GenresResponse {

		private Long genre_id;
		private String Action;
		private String Comedy;
		private String Romance;
		private String Drama;
		private String Family;
		private String Animation;
		
		public GenresResponse(Genres genre) {
			genre_id = genre.getGenre_id();
			Action = genre.getAction();
			Comedy = genre.getComedy();
			Romance = genre.getRomance();
			Drama = genre.getDrama();
			Animation = genre.getAnimation();
			
}
	}
		@Data
	@NoArgsConstructor
	
	final class ActorsResponse{
		private Long actor_id;
		private String actor_firstname;
		private String actor_lastname;
		private Set<Movies> movies = new HashSet<>();
		
	public ActorsResponse(Actors actor) {
			actor_id = actor.getActor_id();
			actor_firstname = actor.getActor_firstname();
			actor_lastname = actor.getActor_lastname();
		
		
	}
		}
	

