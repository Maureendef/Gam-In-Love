package co.simplon.gaminlove.repository;

import org.springframework.data.repository.CrudRepository;

import co.simplon.gaminlove.model.Album;

/**
 * Le repository Album, l'héritage de CRUD donne des méthodes de base : save,
 * findById, findAll, etc....
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
public interface AlbumRepository extends CrudRepository<Album, Integer> {

}