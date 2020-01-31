package co.simplon.gaminlove.repository;

import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Geek;
import org.springframework.stereotype.Repository;

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

}
