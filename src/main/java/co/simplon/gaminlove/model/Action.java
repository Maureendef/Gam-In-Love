package co.simplon.gaminlove.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Une simple classe pour représenter une Action: un id, l'id du geek émetteur,
 * l'id du geek récepteur et un type d'action (like/superlike/dislike).
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
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String action;
	@ManyToOne(cascade = CascadeType.ALL)
	private Geek emetteur;
	@ManyToOne(cascade = CascadeType.ALL)
	private Geek recepteur;

}
