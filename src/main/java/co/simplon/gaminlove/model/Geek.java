package co.simplon.gaminlove.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private int age;
	@Column(unique=true)
	@NotNull
	private String pseudo;
	@NotNull
	private String password;
	@NotNull
	private String ville;
	@NotNull
	private String sexe;
	@NotNull
	private String typeCompte;
	@Column(unique=true)
	@NotNull
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
