package main.service.impl;

import main.model.Product;
import main.repository.ProductRepository;
import main.service.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerService implements ProductManager {

  private ProductRepository productRepository;

  @Autowired
  public ProductManagerService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public String addProduct(String name, String description, int kcal) {
    Product product = Product.builder()
        .name(name)
        .description(description)
        .kcal(kcal).build();
    return saveProduct(product);
  }

  @Override
  public String saveProduct(Product product) {
    return productRepository.save(product).getId();
  }

}
