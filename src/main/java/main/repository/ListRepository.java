package main.repository;

import java.util.Optional;
import main.model.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListRepository extends MongoRepository<List, String> {

  Optional<List> findByName(String name);

}
