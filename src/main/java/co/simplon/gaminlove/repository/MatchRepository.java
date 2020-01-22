package co.simplon.gaminlove.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Match;

/**
 * Le repository Match, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
public interface MatchRepository extends CrudRepository<Match, Integer> {

	public Optional<Match> findByGeekEmetteur(Geek geekEmetteur);
}
