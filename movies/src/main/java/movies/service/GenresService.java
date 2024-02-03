package movies.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.controller.model.ActorsData;
import movies.controller.model.GenresData;
import movies.dao.GenresDao;
import movies.entity.Actors;
import movies.entity.Genres;

@Service
public class GenresService {
	@Autowired
	private GenresDao genresDao;
	
@Transactional(readOnly = false)
	public GenresData saveGenres(GenresData genresData) {
		Long genre_id = genresData.getGenre_id();
		Genres genres =findOrCreateGenres(genre_id);
		
		setFieldsInGenres(genres, genresData);
		return new GenresData(genresDao.save(genres));
	}

private void setFieldsInGenres(Genres genres, 
		GenresData genresData) {
	genres.setAction(genresData.getAction());
	genres.setComedy(genresData.getComedy());
	genres.setRomance(genresData.getRomance());
	genres.setDrama(genresData.getDrama());
	genres.setFamily(genresData.getFamily());
	genres.setAnimation(genresData.getAnimation());
	
	
}

public Genres findOrCreateGenres(Long genre_id) {
	Genres genres;
	
	if(Objects.isNull(genre_id)) {
		genres = new Genres();
	}
	else {
		genres = findGenresById(genre_id);
	}
	return genres;
}

private Genres findGenresById(Long genre_id) {
	
	return genresDao.findById(genre_id)
			.orElseThrow(() -> new NoSuchElementException(
			"Genres with ID=" + genre_id + "was not found."));
}
@Transactional(readOnly = true)
public GenresData retrieveGenresById(Long genre_id) {
	Genres genres = findGenresById(genre_id);
	return new GenresData(genres);
}
}
