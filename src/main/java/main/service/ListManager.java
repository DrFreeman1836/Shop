package main.service;

import main.model.List;
import main.model.Product;

public interface ListManager {

  /**
   *
   * @param list
   * @return id списка
   */
  String saveList(List list);

  /**
   *
   * @param list
   * @param product
   * @return id списка
   */
  String addProduct(List list, Product product);

  /**
   *
   * @param name
   * @return список
   */
  List getList(String name);

}
