package co.simplon.gaminlove.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.MP;
import org.springframework.stereotype.Repository;

/**
 * Le repository MP, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
@Repository
public interface MPRepository extends CrudRepository<MP, Integer> {
	@Query("SELECT m.date, g.pseudo, m.message " +
			"FROM MP m " +
			"LEFT JOIN m.geekMP g " +
			"WHERE m.geekMP.id LIKE ?1 " +
			"AND m.geekCible.id LIKE ?2 " +
			"OR m.geekMP.id LIKE ?2 " +
			"AND m.geekCible.id LIKE ?1")
	ArrayList<?> ListMp(int emetteur, int recepteur);
}

//select g.pseudo from Geek g left join g.jeux j where g.sexe like %?1% and g.ville like %?2% and g.age between ?3 and ?4 and j.nom like %?5%"
