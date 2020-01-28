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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Une simple classe pour représenter un geek: un id, un age, un pseudo, un lieu, un sexe, un type de compte, un email, un album et un catalogue.
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
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String pseudo;
	@Column(nullable = false)
	private String lieu;
	@Column(nullable = false)
	private String sexe;
	@Column(nullable = false)
	private String compte;
	@Column(nullable = false)
	private String email;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Album album;
//	@OneToOne(cascade = CascadeType.ALL)	
//	private Catalogue catalogue;
	
	@OneToMany
	private Collection<Photo> photos;
	@ManyToMany
	private Collection<Jeu> jeux;
	

}
