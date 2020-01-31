package co.simplon.gaminlove.repository;

import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Coop;
import org.springframework.stereotype.Repository;

/**
 * Le repository Coop, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
@Repository
public interface CoopRepository extends CrudRepository<Coop, Integer> {

}
