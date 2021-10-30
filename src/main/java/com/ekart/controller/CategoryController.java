package com.ekart.controller;

import com.ekart.dto.CategoryDto;
import com.ekart.exception.RecordNotFoundException;
import com.ekart.model.Category;
import com.ekart.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${apiPrefix}/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        List<CategoryDto> categoryResponse = categories.stream()
                                                       .map(category -> modelMapper.map(category, CategoryDto.class))
                                                       .collect(Collectors.toList());
        return new ResponseEntity<List<CategoryDto>>(categoryResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws RecordNotFoundException {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<Category>(category, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category newCategory = categoryService.saveCategory(category);
        return new ResponseEntity<Category>(newCategory, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteCategoryById(@PathVariable Long id) throws RecordNotFoundException {
        categoryService.deleteCategoryById(id);
        return HttpStatus.FORBIDDEN;
    }
}
