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
	@OneToMany(mappedBy = "geekPhoto")
	private Collection<Photo> photos;
	@JsonIgnore
	@OneToMany(mappedBy = "geekRecherche")
	private Collection<Recherche> recherches;
	@JsonIgnore
	@OneToMany(mappedBy = "geekCoop")
	private Collection<Coop> coop;
	@JsonIgnore
	@OneToMany(mappedBy = "geekAction")
	private Collection<Action> action;
	@JsonIgnore
	@OneToMany(mappedBy = "geekMP", cascade = CascadeType.REMOVE)
	private Collection<MP> mp;	
	@ManyToMany(mappedBy = "geekJeux")
	private Collection<Jeu> jeux;
	@ManyToMany(mappedBy = "geekParticipant")
	private Collection<Event> event;

}
