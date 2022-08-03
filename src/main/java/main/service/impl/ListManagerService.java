package main.service.impl;

import java.util.Optional;
import main.model.List;
import main.model.Product;
import main.repository.ListRepository;
import main.repository.ProductRepository;
import main.service.ListManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ListManagerService implements ListManager {

  private ListRepository listRepository;

  private ProductRepository productRepository;

  private MongoTemplate mongoTemplate;

  @Value("${application.communication}")
  private int maxCount;

  @Autowired
  public ListManagerService(ListRepository listRepository,
      ProductRepository productRepository, MongoTemplate mongoTemplate) {
    this.listRepository = listRepository;
    this.productRepository = productRepository;
    this.mongoTemplate = mongoTemplate;
  }

  public String addList(String name) {
    List list = List.builder()
        .name(name)
        .build();
    return saveList(list);
  }

  public String addProductInList(String nameList, String nameProduct) {
    Optional<List> listOptional = listRepository.findByName(nameList);
    Optional<Product> productOptional = productRepository.findByName(nameProduct);
    if(listOptional.isEmpty() || productOptional.isEmpty()){
      return "invalid name specified";
    }
    if(listRepository.findAll().size() == maxCount){
      return "list size is exceeded";
    }
    return addProduct(listOptional.get(), productOptional.get());
  }

  @Override
  public String saveList(List list) {
    return listRepository.save(list).getId();
  }

  @Override
  public String addProduct(List list, Product product) {
    Query query = new Query(Criteria.where("name").is(list.getName()));
    Update update = new Update().addToSet("listProducts", product);
    return mongoTemplate.findAndModify(query, update, List.class).getId();
  }

  @Override
  public List getList(String name) {
    return listRepository.findByName(name).get();
  }
}
