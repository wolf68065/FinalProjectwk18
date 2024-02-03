package movies.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Actors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long actor_id;
	private String actor_firstname;
	private String actor_lastname;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	
	@ManyToOne(cascade = CascadeType.ALL) 
	@JoinColumn( name = "movie_id", nullable = false)
	private Movies movie;
}
