package main.rest;

import lombok.RequiredArgsConstructor;
import main.repository.ListRepository;
import main.repository.ProductRepository;
import main.service.impl.ListManagerService;
import main.service.impl.ProductManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shop")
@RequiredArgsConstructor
public class Controller {

  private final ProductManagerService productService;

  private final ListManagerService listService;

  private final ListRepository listRepository;

  private final ProductRepository productRepository;

  @PostMapping("product")
  public ResponseEntity<?> addProduct(
      @RequestParam(name = "name") String name,
      @RequestParam(name = "description") String description,
      @RequestParam(name = "kcal") int kcal) {
    String id = productService.addProduct(name, description, kcal);
    if (id != null) {
      return ResponseEntity.ok().body(id);
    }
    return ResponseEntity.status(500).build();
  }

  @PostMapping("list")
  public ResponseEntity<?> addList(@RequestParam(name = "name") String name) {
    String id = listService.addList(name);
    if (id != null) {
      return ResponseEntity.ok().body(id);
    }
    return ResponseEntity.status(500).build();
  }

  @PutMapping()
  public ResponseEntity<?> addProductInList() {
    return null;
  }

  @GetMapping("product")
  public ResponseEntity<?> getProducts() {
    return null;
  }

  @GetMapping("list")
  public ResponseEntity<?> getLists() {
    return null;
  }

  @GetMapping("test")
  public void test() {
    listRepository.findAll().forEach(System.out::println);
    productRepository.findAll().forEach(System.out::println);
  }

}
