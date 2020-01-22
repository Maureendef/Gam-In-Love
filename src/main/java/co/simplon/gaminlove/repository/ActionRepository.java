package co.simplon.gaminlove.repository;

import org.springframework.data.repository.CrudRepository;

import co.simplon.gaminlove.model.Action;

/**
 * Le repository Action, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
public interface ActionRepository extends CrudRepository<Action, Integer> {

}
