package co.simplon.gaminlove.repository;

import org.springframework.data.repository.CrudRepository;

import co.simplon.gaminlove.model.Event;

/**
 * Le repository Events, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
public interface EventRepository extends CrudRepository<Event, Integer> {

}
