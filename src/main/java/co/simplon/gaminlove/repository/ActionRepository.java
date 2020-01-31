package co.simplon.gaminlove.repository;

import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Action;
import org.springframework.stereotype.Repository;

/**
 * Le repository Action, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
@Repository
public interface ActionRepository extends CrudRepository<Action, Integer> {

}
