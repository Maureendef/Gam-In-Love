package co.simplon.gaminlove.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Recherche;

/**
 * Le repository Recherche, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
public interface RechercheRepository extends CrudRepository<Recherche, Integer> {
	@Query("select g from Geek g where g.sexe in ('homme',  'M')")
    List<Geek> findMale();
	@Query("select f from Geek f where f.sexe in ('femme',  'F')")
    List<Geek> findFemale();
	@Query("select g from Geek g where g.age between 18 AND 24")
    List<Geek> findKinder();
}
