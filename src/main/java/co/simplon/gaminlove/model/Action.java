package co.simplon.gaminlove.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.*;

/**
 * Une simple classe pour représenter une Action (like/superlike/dislike).
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
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String typeAction;
	@ManyToOne
	private Geek geekCible;
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Geek geekAction;

}
