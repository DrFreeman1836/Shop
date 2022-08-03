package main.repository;

import main.model.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListRepository extends MongoRepository<List, String> {

}
