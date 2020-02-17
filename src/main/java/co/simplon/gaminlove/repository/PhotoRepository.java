package co.simplon.gaminlove.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.simplon.gaminlove.model.Photo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Le repository Photo, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
    @Query("SELECT p " +
            "FROM Photo p " +
            "WHERE p.geekPhoto.id = ?1 ")
    ArrayList<Photo> findForGeek(int id);
}
