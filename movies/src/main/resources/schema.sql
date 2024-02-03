DROP TABLE IF EXISTS movie_genre;
DROP TABLE IF EXISTS genres;

DROP TABLE IF EXISTS actors;
DROP TABLE IF EXISTS movies;

CREATE TABLE movies(
  movie_id int AUTO_INCREMENT NOT NULL,
  title varchar(60),
  duration varchar(25),
  release_year int,
  PRIMARY KEY(movie_id)
);

CREATE TABLE actors(
  actor_id int AUTO_INCREMENT NOT NULL,
  movie_id int NOT NULL,
  actor_firstname varchar(60),
  actor_lastname varchar (60),
  PRIMARY KEY(actor_id),
  FOREIGN KEY(movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE
  );
  
CREATE TABLE genres(
  genre_id int AUTO_INCREMENT NOT NULL,
  action varchar(10),
  comedy varchar(10),
  romance varchar(10),
  drama varchar(10),
  family varchar(10),
  PRIMARY KEY(genre_id)
);
  
CREATE TABLE movie_genre(
  movie_id int NOT NULL,
  genre_id int NOT NULL,
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE,
  FOREIGN KEY (genre_id) REFERENCES genres(genre_id) ON DELETE CASCADE,
  UNIQUE KEY (movie_id, genre_id)
);  


