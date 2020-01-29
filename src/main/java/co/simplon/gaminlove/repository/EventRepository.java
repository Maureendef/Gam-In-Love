package co.simplon.gaminlove.repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
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

	public Optional<Event> findByNom(String nom);
	public Collection<Event> findAllByDate(Date date);
	public Collection<Event> findAllByNom(String nom);
	
}

