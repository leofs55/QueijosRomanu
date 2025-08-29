package lest.dev.CommerceMail.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lest.dev.CommerceMail.entity.Category;
import lest.dev.CommerceMail.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        try {
            if (category.getName() == null || category.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
            }
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar categoria: " + e.getMessage());
        }
    }

    public Category findById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID não pode ser nulo");
            }
            return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria com ID " + id + " não encontrada"));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar categoria por ID: " + e.getMessage());
        }
    }

    public List<Category> findAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as categorias: " + e.getMessage());
        }
    }

    public Category updateCategory(Long id, Category category) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID da categoria não pode ser nulo para atualização");
            }
            if (category.getName() == null || category.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
            }
            
            if (!categoryRepository.existsById(id)) {
                throw new RuntimeException("Categoria com ID " + id + " não encontrada");
            }
            category.setId(id);
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void deleteCategory(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID não pode ser nulo");
            }
            
            if (!categoryRepository.existsById(id)) {
                throw new RuntimeException("Categoria com ID " + id + " não encontrada");
            }

            if (categoryRepository.existsProductInCategory(id)) {
                throw new RuntimeException("Categoria não pode ser deletada pois possui produtos associados");
            }
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar categoria: " + e.getMessage());
        }
    }
}
