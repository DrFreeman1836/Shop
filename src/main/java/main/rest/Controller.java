package main.rest;

import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import main.model.List;
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

  //docker run --rm --name skill-mongo -p 127.0.0.1:27017:27017/tcp -d scalar4eg/skill-mongo-with-hacker
  //http://localhost:8080/swagger-ui/index.html#/

  private final ProductManagerService productService;

  private final ListManagerService listService;

  @Operation(summary = "Добавление продукта")
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

  @Operation(summary = "Добавление списка")
  @PostMapping("/list")
  public ResponseEntity<?> addList(@RequestParam(name = "name") String name) {
    String id = listService.addList(name);
    if (id != null) {
      return ResponseEntity.ok().body(id);
    }
    return ResponseEntity.status(500).build();
  }

  @Operation(summary = "Добавление продукта в список")
  @PutMapping("/list")
  public ResponseEntity<?> addProductInList(
      @RequestParam(name = "nameProduct") String nameProduct,
      @RequestParam(name = "nameList") String nameList) {

    try {
      String id = listService.addProductInList(nameList, nameProduct);
      return ResponseEntity.ok().body(id);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.toString());
    }

  }

  @Operation(summary = "Получение всего списка продуктов")
  @GetMapping("/product")
  public ResponseEntity<?> getProducts() {
    return ResponseEntity.ok().body(Map.of("products", productService.getListProducts()));
  }

  @Operation(summary = "Получение списка по имени")
  @GetMapping("/list")
  public ResponseEntity<?> getList(@RequestParam(name = "name") String name) {
    List list = listService.getList(name);
    if(list == null){
      return ResponseEntity.status(400).body("There is no list with the specified name");
    }

    int sumKcal = listService.countSumKcal(list);
    return ResponseEntity.ok().body(Map.of("list", listService.getList(name), "kcal", sumKcal));
  }

}
