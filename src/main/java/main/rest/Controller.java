package main.rest;

import java.util.Map;
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

  private final ListRepository listRepository;//delete

  private final ProductRepository productRepository;//delete

  @PostMapping("/product")
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

  @PostMapping("/list")
  public ResponseEntity<?> addList(@RequestParam(name = "name") String name) {
    String id = listService.addList(name);
    if (id != null) {
      return ResponseEntity.ok().body(id);
    }
    return ResponseEntity.status(500).build();
  }

  @PutMapping("/list")
  public ResponseEntity<?> addProductInList(
      @RequestParam(name = "nameProduct") String nameProduct,
      @RequestParam(name = "nameList") String nameList) {

    String result = listService.addProductInList(nameList, nameProduct);
    if(result.equals("invalid name specified")){
      return ResponseEntity.status(500).body(result);
    }
    if(result.equals("list size is exceeded")){
      return ResponseEntity.status(500).body(result);
    }
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/product")
  public ResponseEntity<?> getProducts() {
    return ResponseEntity.ok().body(Map.of("products", productService.getListProducts()));
  }

  @GetMapping("/list")
  public ResponseEntity<?> getLists(@RequestParam(name = "name") String name) {
    return ResponseEntity.ok().body(Map.of("list", listService.getList(name)));
  }

  @GetMapping("/test")
  public void test() {
    //productRepository.deleteAll();
    //listRepository.deleteAll();
    listRepository.findAll().forEach(System.out::println);
    productRepository.findAll().forEach(System.out::println);
  }

}
