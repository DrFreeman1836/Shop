package main.service;

import java.util.List;
import main.model.Product;

public interface ProductManager {

  /**
   *
   * @param product
   * @return id продукта
   */
  String saveProduct(Product product);

  /**
   *
   * @return список продуктов
   */
  List<Product> getListProducts();

}
