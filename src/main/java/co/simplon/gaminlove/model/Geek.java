package co.simplon.gaminlove.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Une simple classe pour représenter un jeux: un id, un nom et le rang du geek
 * dans ce jeu.
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
	private String pseudo;
	private String lieu;
	private String sexe;
	private String compte;
	private String email;
	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Photo> photo;
	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Jeu> jeu;

}
