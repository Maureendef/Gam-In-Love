package co.simplon.gaminlove.model;

import java.util.Collection;
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
	@NonNull
	private int age;
	@Column(unique=true)
	@NonNull
	private String pseudo;
	@NonNull
	private String password;
	@NonNull
	private String ville;
	@NonNull
	private String sexe;
	@NonNull
	private String typeCompte;
	@Column(unique=true)
	@NonNull
	private String email;
	private String token;
	private String description;
//	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Collection<Photo> photos;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Collection<Recherche> recherches;
//	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Collection<Coop> coop;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Collection<Action> action;
	@JsonIgnore
	@OneToMany(orphanRemoval=true)
	private Collection<MP> mp;
	@JsonIgnore
	@ManyToMany
	private Collection<Jeu> jeux;
	@JsonIgnore
	@ManyToMany
	private Collection<Event> event;

}
