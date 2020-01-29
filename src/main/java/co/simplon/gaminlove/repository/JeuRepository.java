package co.simplon.gaminlove.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Jeu;

/**
 * Le repository Jeu, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */

public interface JeuRepository extends CrudRepository<Jeu, Integer> {
	// on peut générer automatiquement des méthodes de recherche dans le repository
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	public Optional<Jeu> findByNom(String nom);
	public Collection<Jeu> findAllByNom(String nom);
	
}

