package movies.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import movies.entity.Genres;

@Data
@NoArgsConstructor
public class GenresData {
	    private Long genre_id;
		private String Action;
		private String Comedy;
		private String Romance;
		private String Drama;
		private String Family;
		private String Animation;
		
		
		
		
		public GenresData(Genres genres) {
		genre_id = genres.getGenre_id();
		Action = genres.getAction();
		Comedy = genres.getComedy();
		Romance =genres.getRomance();
		Drama = genres.getDrama();
		Family = genres.getFamily();
		Animation = genres.getAnimation();
		
		
		
		}		
}
