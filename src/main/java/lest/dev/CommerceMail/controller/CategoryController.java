package lest.dev.CommerceMail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import lest.dev.CommerceMail.dto.request.category.CategoryCreateRequest;
import lest.dev.CommerceMail.dto.request.category.CategoryUpdateRequest;
import lest.dev.CommerceMail.dto.response.category.CategoryCreateResponse;
import lest.dev.CommerceMail.dto.response.category.CategoryUpdateResponse;
import lest.dev.CommerceMail.dto.response.category.CategoryDeleteResponse;
import lest.dev.CommerceMail.dto.response.category.CategoryResponse;
import java.util.List;
import lest.dev.CommerceMail.mapper.category.CategoryCreateMapper;
import lest.dev.CommerceMail.mapper.category.CategoryMapper;
import lest.dev.CommerceMail.mapper.category.CategoryUpdateMapper;
import lest.dev.CommerceMail.mapper.category.CategoryDeleteMapper;
import lombok.RequiredArgsConstructor;  
import lest.dev.CommerceMail.service.CategoryService;
import lest.dev.CommerceMail.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import lest.dev.CommerceMail.dto.response.user.JWTUser;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CategoryCreateResponse> createCategory(@Valid @RequestBody CategoryCreateRequest category, @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validatePermissionUser(jwtUser);

        return ResponseEntity.ok(CategoryCreateMapper.map(
            categoryService.createCategory(CategoryCreateMapper.map(category))));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> findAllCategories() {
        return ResponseEntity.ok(categoryService.findAll().stream()
                .map(CategoryMapper::map)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(CategoryMapper.map(categoryService.findById(id)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryUpdateResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateRequest category, @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validatePermissionUser(jwtUser);

        return ResponseEntity.ok(CategoryUpdateMapper.map(
            categoryService.updateCategory(id, CategoryUpdateMapper.map(category))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryDeleteResponse> deleteCategory(@PathVariable Long id, @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validatePermissionUser(jwtUser);

        categoryService.deleteCategory(id);
        return ResponseEntity.ok(CategoryDeleteMapper.map("Categoria deletada com sucesso"));
    }

}
