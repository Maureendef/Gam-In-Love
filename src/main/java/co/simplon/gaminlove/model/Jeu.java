package co.simplon.gaminlove.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.ManyToMany;

/**
 * Une simple classe pour représenter un jeu.
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
@Table(name = "JEUX") // par défaut, hibernate crée une table appelée le nom de la classe au pluriel,
						// ici ça ne marche pas, car le pluriel de Jeu est Jeux...
public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique=true)
	private String nom;
	private String rang;
	@ManyToMany
	private Collection<Geek> geekJeux;
	
}
