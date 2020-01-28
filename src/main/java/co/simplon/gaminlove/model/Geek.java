package co.simplon.gaminlove.model;

import java.util.Collection;

import javax.persistence.CascadeType;
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
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	private Collection<Photo> photos;
	@JsonIgnore
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	private Collection<Recherche> recherches;
	@JsonIgnore
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	private Collection<Coop> coop;
	@JsonIgnore
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	private Collection<Action> action;
	@JsonIgnore
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	private Collection<MP> mp;	
	@JsonIgnore
	@ManyToMany
	private Collection<Jeu> jeux;
	@JsonIgnore
	@ManyToMany
	private Collection<Event> event;

}
