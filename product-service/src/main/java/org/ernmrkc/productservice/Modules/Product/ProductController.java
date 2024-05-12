package org.ernmrkc.productservice.Modules.Product;

import jakarta.validation.Valid;
import org.ernmrkc.productservice.Modules.Product.CommandHandlers.CreateProductCommandHandler;
import org.ernmrkc.productservice.Modules.Product.CommandHandlers.DeleteProductCommandHandler;
import org.ernmrkc.productservice.Modules.Product.CommandHandlers.UpdateProductCommandHandler;
import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.ernmrkc.productservice.Modules.Product.Models.ProductDTO;
import org.ernmrkc.productservice.Modules.Product.Models.ProductStockDTO;
import org.ernmrkc.productservice.Modules.Product.Models.UpdateProductCommand;
import org.ernmrkc.productservice.Modules.Product.QueryHandlers.GetAllProductDTOsQueryHandler;
import org.ernmrkc.productservice.Modules.Product.QueryHandlers.GetAllProductsQueryHandler;
import org.ernmrkc.productservice.Modules.Product.QueryHandlers.GetProductStockValueQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final CreateProductCommandHandler createProductCommandHandler;
    private final DeleteProductCommandHandler deleteProductCommandHandler;
    private final UpdateProductCommandHandler updateProductCommandHandler;
    private final GetAllProductDTOsQueryHandler getAllProductDTOsQueryHandler;
    private final GetAllProductsQueryHandler getAllProductsQueryHandler;
    private final GetProductStockValueQueryHandler getProductStockValueQueryHandler;

    public ProductController(CreateProductCommandHandler createProductCommandHandler,
                             DeleteProductCommandHandler deleteProductCommandHandler,
                             UpdateProductCommandHandler updateProductCommandHandler,
                             GetAllProductDTOsQueryHandler getAllProductDTOsQueryHandler,
                             GetAllProductsQueryHandler getAllProductsQueryHandler,
                             GetProductStockValueQueryHandler getProductStockValueQueryHandler) {
        this.createProductCommandHandler = createProductCommandHandler;
        this.deleteProductCommandHandler = deleteProductCommandHandler;
        this.updateProductCommandHandler = updateProductCommandHandler;
        this.getAllProductDTOsQueryHandler = getAllProductDTOsQueryHandler;
        this.getAllProductsQueryHandler = getAllProductsQueryHandler;
        this.getProductStockValueQueryHandler = getProductStockValueQueryHandler;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/DTOs")
    public ResponseEntity<List<ProductDTO>> getAllProductDTOs() {
        return getAllProductDTOsQueryHandler.execute(null);
    }

    @GetMapping("/product-stock")
    public ResponseEntity<ProductStockDTO> getProductStockDTO(@RequestParam(value = "id") UUID id){
        return getProductStockValueQueryHandler.execute(id);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        return createProductCommandHandler.execute(product, bindingResult);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam(value = "id") UUID id) {
        return deleteProductCommandHandler.execute(id, null);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestParam(value = "id") UUID id, @Valid @RequestBody Product product, BindingResult bindingResult){
        UpdateProductCommand updateProductCommand = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(updateProductCommand, bindingResult);
    }
}