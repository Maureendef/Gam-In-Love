package co.simplon.gaminlove.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Une simple classe pour représenter un MP: un id, l'id du geek émetteur,
 * l'id du geek récepteur et le message.
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MP") // par défaut, hibernate crée une table appelée le nom de la classe au pluriel,
					// ici ça ne marche pas, car le pluriel de MP est MP...
public class MP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.ALL)
	private Geek geekEmetteur;
	@ManyToOne(cascade = CascadeType.ALL)
	private Geek geekRecepteur;
	private String message;

}
