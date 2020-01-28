package co.simplon.gaminlove.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

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

}
