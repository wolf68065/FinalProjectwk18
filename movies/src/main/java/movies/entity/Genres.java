package movies.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Genres {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long genre_id;
	private String Action;
	private String Comedy;
	private String Romance;
	private String Drama;
	private String Family;
	private String Animation;
	
	
	@ManyToMany(mappedBy = "genres")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Movies> movies =  new HashSet<>();

}
