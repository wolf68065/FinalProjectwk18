package movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movies.entity.Actors;

public interface ActorsDao extends JpaRepository<Actors , Long> {

}
