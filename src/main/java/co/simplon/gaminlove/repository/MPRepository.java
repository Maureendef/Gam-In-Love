package co.simplon.gaminlove.repository;

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
	@Query("select m from MP m where m.geekMP.id like ?1 and m.geekCible.id like ?2 or m.geekMP.id like ?2 and m.geekCible.id like ?1")
    Collection<MP> ListMp(int emetteur, int recepteur);
}
