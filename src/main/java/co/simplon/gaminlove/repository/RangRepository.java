package co.simplon.gaminlove.repository;

import co.simplon.gaminlove.model.Rang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RangRepository extends CrudRepository<Rang, Integer> {

}
