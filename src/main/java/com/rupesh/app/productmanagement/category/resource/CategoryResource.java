package com.rupesh.app.productmanagement.category.resource;

import com.rupesh.app.productmanagement.category.model.CategoryRequest;
import com.rupesh.app.productmanagement.category.model.CategoryResponse;
import com.rupesh.app.productmanagement.category.service.ICategoryService;
import com.rupesh.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "product_category")
public class CategoryResource {

    private final ICategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> save(@RequestBody CategoryRequest category) {
        categoryService.save(category);
        return ResponseEntity.ok(
                categoryService.save(category)
        );
    }

    @GetMapping("/by.id/{id}")
    public ResponseEntity<GlobalResponse<CategoryResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                categoryService.findById(id)
        );
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<CategoryResponse>>> getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                categoryService.findAll(page, size)
        );
    }

    @GetMapping("/by.name/{name}")
    public ResponseEntity<GlobalResponse<CategoryResponse>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(
                categoryService.findByName(name)
        );
    }

    @PutMapping
    public ResponseEntity<GlobalResponse<Void>> update(
            @RequestBody CategoryRequest request,
            @RequestParam(name = "id") Long id
    ) {
        return ResponseEntity.ok(
                categoryService.update(request, id)
        );
    }

    @DeleteMapping
    public ResponseEntity<GlobalResponse<Void>> delete(
            @RequestParam(name = "id") Long id
    ) {
        return ResponseEntity.ok(
                categoryService.delete(id)
        );
    }

}
