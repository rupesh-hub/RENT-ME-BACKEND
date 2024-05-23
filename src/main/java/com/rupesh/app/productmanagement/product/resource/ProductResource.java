package com.rupesh.app.productmanagement.product.resource;

import com.rupesh.app.productmanagement.product.model.ProductRequest;
import com.rupesh.app.productmanagement.product.model.ProductResponse;
import com.rupesh.app.productmanagement.product.service.IProductService;
import com.rupesh.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "products")
public class ProductResource {


    private final IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> save(@RequestBody ProductRequest request) {

        return ResponseEntity
                .ok(productService.save(request)
                );
    }

    @GetMapping("/by.id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<ProductResponse>> getById(@PathVariable Long id) {

        return ResponseEntity
                .ok(productService.findById(id)
                );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<List<ProductResponse>>> getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {

        return ResponseEntity
                .ok(productService.findAll(page, size)
                );
    }


    @PutMapping
    public ResponseEntity<GlobalResponse<Void>> update(@RequestBody ProductRequest request,
                                                       @RequestParam(name = "id") Long id) {

        return ResponseEntity
                .ok(productService.update(request, id)
                );
    }

    @DeleteMapping
    public ResponseEntity<GlobalResponse<Void>> delete(@RequestParam(name = "id") Long id) {

        return ResponseEntity
                .ok(productService.delete(id)
                );
    }


}
