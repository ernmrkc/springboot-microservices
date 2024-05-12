package org.ernmrkc.productservice.Modules.Product;

import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.ernmrkc.productservice.Modules.Product.Models.ProductDTO;
import org.ernmrkc.productservice.Modules.Product.Models.ProductStockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT new org.ernmrkc.productservice.Modules.Product.Models.ProductDTO(p.id, p.name, p.price, p.stock) FROM Product p")
    List<ProductDTO> getAllProductDTOs();
}
