package co.simplon.gaminlove.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RangKey implements Serializable {

    @Column(name = "jeux_id")
    Integer jeuxId;

    @Column(name = "geek_id")
    Integer geekId;

}
