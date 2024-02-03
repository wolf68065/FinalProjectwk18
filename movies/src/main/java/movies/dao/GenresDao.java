package movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movies.entity.Genres;

public interface GenresDao extends JpaRepository<Genres, Long> {
	
	

}
