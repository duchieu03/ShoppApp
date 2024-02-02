package com.example.shopapp.service.category;

import com.example.shopapp.core.response.ApiRes;
import com.example.shopapp.dto.PageDTO;
import com.example.shopapp.dto.request.CategoryRequest;
import com.example.shopapp.model.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    ApiRes<String> createCategory(CategoryRequest categoryRequest);

    ApiRes<Page<Category>> findCategory(String name, PageDTO pageDTO);

    ApiRes<Category> getCategoryById(Long id);

    ApiRes<String> updateCategory(Long id ,CategoryRequest category);

    ApiRes<String> deleteCategory(Long id);
}
