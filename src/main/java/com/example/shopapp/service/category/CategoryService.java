package com.example.shopapp.service.category;

import com.example.shopapp.core.response.ApiRes;
import com.example.shopapp.core.response.code.ClientCode;
import com.example.shopapp.dto.PageDTO;
import com.example.shopapp.dto.request.CategoryRequest;
import com.example.shopapp.exception.ApiException;
import com.example.shopapp.model.Category;
import com.example.shopapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public ApiRes<String> createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .build();
        categoryRepository.save(category);
        return ApiRes.success("Create category successful");
    }

    @Override
    public ApiRes<Page<Category>> findCategory(String name, PageDTO pageDTO) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDTO.getSort()), pageDTO.getSortBy());
        Pageable pageable = PageRequest.of(pageDTO.getPage(), pageDTO.getSize(), sort);
        Page<Category> categories = categoryRepository.findCategory(name, pageable);
        return ApiRes.success(categories);
    }

    @Override
    public ApiRes<Category> getCategoryById(Long id) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ClientCode.CATEGORY_NOT_FOUND));
        return ApiRes.success(category);
    }

    @Override
    public ApiRes<String> updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ClientCode.CATEGORY_NOT_FOUND));
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return ApiRes.success("Update category successful");
    }

    @Override
    public ApiRes<String> deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ClientCode.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
        return ApiRes.success("Delete category successful");
    }
}
