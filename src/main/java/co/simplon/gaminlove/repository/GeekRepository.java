package co.simplon.gaminlove.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Geek;

/**
 * Le repository Geek, l'hÃ©ritage de CRUD donne des mÃ©thodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 * @return 
 *
 */
public interface GeekRepository extends CrudRepository<Geek, Integer> {
	@Query("select g from Geek g where g.sexe = 'homme'")
    List<Geek> findMale();
	@Query("select f from Geek f where f.sexe = 'femme'")
    List<Geek> findFemale();
}
