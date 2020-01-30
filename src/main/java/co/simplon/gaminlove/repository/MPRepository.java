package co.simplon.gaminlove.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.MP;

/**
 * Le repository MP, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */

public interface MPRepository extends CrudRepository<MP, Integer> {
	@Query("select m.date, g.pseudo, m.message from MP m left join m.geekMP g where m.geekMP.id like ?1 and m.geekCible.id like ?2 or m.geekMP.id like ?2 and m.geekCible.id like ?1")
	ArrayList<?> ListMp(int emetteur, int recepteur);
}

//select g.pseudo from Geek g left join g.jeux j where g.sexe like %?1% and g.ville like %?2% and g.age between ?3 and ?4 and j.nom like %?5%"
