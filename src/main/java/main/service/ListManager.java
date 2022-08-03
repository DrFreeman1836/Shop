package main.service;

import main.model.List;

public interface ListManager {

  /**
   *
   * @param list
   * @return id списка
   */
  String saveList(List list);

}
