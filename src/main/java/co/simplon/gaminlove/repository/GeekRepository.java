package co.simplon.gaminlove.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.simplon.gaminlove.model.Geek;

/**
 * Le repository Geek, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 * @return 
 *
 */
@Repository
public interface GeekRepository extends CrudRepository<Geek, Integer> {

	Optional<Geek> findByPseudo(String pseudo);
	Iterable<Geek> findByToken(String token);
}
