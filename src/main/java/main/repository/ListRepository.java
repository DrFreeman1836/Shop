package main.repository;

import java.util.Optional;
import main.model.List;
import main.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ListRepository extends MongoRepository<List, String> {

  Optional<List> findByName(String name);

}
