package main.repository;

import java.util.Optional;
import main.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

  Optional<Product> findByName(String name);

}
