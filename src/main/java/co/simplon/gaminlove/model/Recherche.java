package co.simplon.gaminlove.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

import javax.persistence.ManyToOne;

/**
 * Une simple classe pour représenter une recherche personnalisée.
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
public class Recherche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int ageMin;
	private int ageMax;
	private String nom;
	private String sexe;
	private String ville;
	private String jeu;
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Geek geekRecherche;

}
