package co.simplon.gaminlove.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Event;
import co.simplon.gaminlove.model.Geek;

import org.springframework.stereotype.Repository;

/**
 * Le repository Events, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

	Set<Event> findAllByNom(String nom);
}
