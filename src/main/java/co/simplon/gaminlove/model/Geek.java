package co.simplon.gaminlove.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	@OneToOne(cascade = CascadeType.ALL)
	
	private Album album;
	@OneToOne(cascade = CascadeType.ALL)
	
	private Catalogue catalogue;

}
