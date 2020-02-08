package co.simplon.gaminlove.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

/**
 * Une simple classe pour représenter un MP (message privé).
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
@Table(name = "MP") // par défaut, hibernate crée une table appelée le nom de la classe au pluriel,
					// ici ça ne marche pas, car le pluriel de MP est MP...
public class MP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Geek geekCible;
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Geek geekMP;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDate date;
	private String message;
	
}
