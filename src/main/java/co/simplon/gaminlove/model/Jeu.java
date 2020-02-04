package co.simplon.gaminlove.model;

import java.util.Collection;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

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
@EqualsAndHashCode
@Table(name = "JEUX") // par défaut, hibernate crée une table appelée le nom de la classe au pluriel,
						// ici ça ne marche pas, car le pluriel de Jeu est Jeux...
public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique=true)
	private String nom;
	@JsonIgnore
	@ManyToMany(mappedBy = "jeux")
	private Collection<Geek> geekJeux;

}
