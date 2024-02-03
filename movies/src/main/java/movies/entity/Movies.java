package movies.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Movies {
	@Column(insertable=false, updatable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long movie_id;
	
	private String title;
	private Long duration;
	private int release_year;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	//@OneToMany(targetEntity = Actors.class)
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	
	private Set<Actors> actors = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "movie_genre",
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id")
	)
	private Set<Genres> genres = new HashSet<>();

	
		
	}
	

