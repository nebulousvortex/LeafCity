package ru.vortex.physics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.physics.model.shop.Category;
import ru.vortex.physics.model.shop.Duration;
import ru.vortex.physics.model.user.Role;
import ru.vortex.physics.repository.CategoryRepository;
import ru.vortex.physics.repository.RoleRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category findCategory(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }
    public void createCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
