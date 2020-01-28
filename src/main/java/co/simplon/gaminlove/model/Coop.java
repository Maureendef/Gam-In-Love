package co.simplon.gaminlove.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Une simple classe pour représenter une Coop: un id, l'id du geek émetteur et
 * de l'id du geek récepteur.
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
public class Coop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Geek emetteur;
	@ManyToOne
	private Geek recepteur;

	
}
