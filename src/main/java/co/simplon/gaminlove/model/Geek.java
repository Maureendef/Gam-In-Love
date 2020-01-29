package co.simplon.gaminlove.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Geek {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int age;
	@Column(unique=true)
	private String pseudo;
	private String ville;
	private String sexe;
	private String typeCompte;
	@Column(unique=true)
	private String email;
	@JsonIgnore
	@OneToMany
	private Collection<Photo> photos;
	@JsonIgnore
	@OneToMany
	private Collection<Recherche> recherches;
	@JsonIgnore
	@OneToMany
	private Collection<Coop> coop;
	@JsonIgnore
	@OneToMany
	private Collection<Action> action;
	@JsonIgnore
	@OneToMany
	private Collection<MP> mp;	
	@ManyToMany
	private Collection<Jeu> jeux;
	@ManyToMany
	private Collection<Event> event;

}
