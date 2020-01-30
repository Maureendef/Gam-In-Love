package co.simplon.gaminlove.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Recherche;

/**
 * Le repository Recherche, l'héritage de CRUD donne des méthodes de base :
 * save, findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */

public interface RechercheRepository extends CrudRepository<Recherche, Integer> {
	/*
	 * @Query("select g from Geek g where g.sexe in ('homme',  'M')") List<Geek>
	 * findMale();
	 * 
	 * @Query("select f from Geek f where f.sexe in ('femme',  'F')") List<Geek>
	 * findFemale();
	 * 
	 * @Query("select g from Geek g where g.age between 18 AND 24") List<Geek>
	 * findByYears();
	 */
	@Query("select g from Geek g where g.sexe like %?1% and g.ville like %?2% and g.age between ?3 and ?4")
	List<Geek> findCity(String sexe, String ville, int ageMin, int ageMax);

	/**
	 * Au dessus ça fonctionne Voici les test non concluant !
	 */
	// faire un INNER JOIN avec jeu LEFT OUTER JOIN S
//	  @Query("select g from Geek g join geek_jeux on g.id = geek_jeux.geek_id join Jeu on geek_jeux.jeux_id = Jeu.id where Jeu.nom like %?1%") 
//	  List<Geek> findGame(String nom);
/*
	@Query("select g.pseudo, j.nom from geek g join g.id gj join gj.jeux_id j where j.nom like '%itcher%'")
	List<Geek> findGame(String nom);
*/
}
//  VOICI LE SQL PURE
//select geek.pseudo, j.nom from geek
//join geek_jeux on geek.id = geek_jeux.geek_id
//join jeux j on geek_jeux.jeux_id = j.id
//where j.nom LIKE '%itcher%'