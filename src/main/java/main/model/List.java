package main.model;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lists")
@Data
@Builder
public class List {

  /**
   * id списка
   */
  @Id
  private String id;

  /**
   * имя списка
   */
  private String name;

  /**
   * продукты в списке
   */
  private ArrayList<Product> listProducts;

}
