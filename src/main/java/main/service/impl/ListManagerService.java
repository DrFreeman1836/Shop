package main.service.impl;

import main.model.List;
import main.repository.ListRepository;
import main.service.ListManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListManagerService implements ListManager {

  private ListRepository listRepository;

  @Autowired
  public ListManagerService(ListRepository listRepository) {
    this.listRepository = listRepository;
  }

  public String addList(String name) {
    List list = List.builder()
        .name(name)
        .build();
    return saveList(list);
  }

  @Override
  public String saveList(List list) {
    return listRepository.save(list).getId();
  }
}
