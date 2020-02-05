package co.simplon.gaminlove.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Recherche;
import org.springframework.stereotype.Repository;

/**
 * Le repository Recherche, l'héritage de CRUD donne des méthodes de base :
 * save, findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
@Repository
public interface RechercheRepository extends CrudRepository<Recherche, Integer> {

	@Query("SELECT g.pseudo " +
			"FROM Geek g " +
			"LEFT JOIN g.jeux j " +
			"WHERE g.sexe LIKE %?1% " +
			"AND g.ville LIKE %?2% " +
			"AND g.age BETWEEN ?3 AND ?4 " +
			"AND j.nom LIKE %?5%")
	List<String> findCity(String sexe, String ville, int ageMin, int ageMax, String nom); // TODO on peut aussi générer des requetes avec Like sans les écrire
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
}