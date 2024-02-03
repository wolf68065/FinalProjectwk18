package movies.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import movies.entity.Movies;

public interface MoviesDao  extends JpaRepository<Movies, Long>{

	Optional<Movies> findByTitle(String title);

}
