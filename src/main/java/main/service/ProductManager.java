package main.service;

import main.model.Product;

public interface ProductManager {

  /**
   *
   * @param product
   * @return id продукта
   */
  String saveProduct(Product product);

}
