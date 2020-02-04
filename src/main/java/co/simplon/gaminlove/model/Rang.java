package co.simplon.gaminlove.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Rang {

    @EmbeddedId
    RangKey id;

    @ManyToOne
    @MapsId("jeux_id")
    @JoinColumn(name = "jeux_id")
    Jeu jeu;

    @ManyToOne
    @MapsId("geek_id")
    @JoinColumn(name = "geek_id")
    Geek geek;

    String rang;
}
