package main.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@Builder
public class Product {

  /**
   * id продукта
   */
  @Id
  private String id;

  /**
   * имя продукта
   */
  private String name;

  /**
   * описание продукта
   */
  private String description;

  /**
   * ккал продукта
   */
  private int kcal;

}
