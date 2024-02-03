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
public class ActorsData {
		private Long actor_id;
		private String actor_firstname;
		private String actor_lastname;
		
		
		public ActorsData(Actors actors) {
			actor_id = actors.getActor_id();
			actor_firstname = actors.getActor_firstname();
			actor_lastname = actors.getActor_lastname();
			
			
			
	}	
	@Data
	@NoArgsConstructor
		public static class MoviesData{
			
			private Long movie_id;
			private String title;
			private Long duration;
			private int release_year;
	Set<ActorsData> actors = new HashSet<>();
	Set<GenresData> genres = new HashSet<>();
	public MoviesData(Movies movies) {
		movie_id = movies.getMovie_id();
		title = movies.getTitle();
		duration = movies.getDuration();
		release_year = movies.getRelease_year();
		for(Actors actor : movies.getActors()) {
				actors.add(new ActorsData(actor));
		}
		for(Genres genre : movies.getGenres()) {
			genres.add(new GenresData(genre));
		}
		}			
	}


		
	}	
		
