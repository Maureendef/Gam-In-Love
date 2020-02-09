package co.simplon.gaminlove.model;

import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * Une simple classe pour représenter un Geek.
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Geek {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int age;
	@Column(unique=true)
	private String pseudo;
	private String password;
	private String ville;
	private String sexe;
	private String typeCompte;
	@Column(unique=true)
	private String email;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Set<Photo> photos;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Set<Recherche> recherches;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Set<Coop> coop;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Set<Action> action;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Set<MP> mp;
	@ManyToMany
	private Set<Jeu> jeux;
	@ManyToMany
	private Set<Event> event;

}
