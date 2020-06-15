package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ua.edu.sumdu.j2ee.zykov.dao.CategoryDAO;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestCategoryService {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryDAO categoryDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_save_category_it_should_return_category() {
        Category category = new Category("Smartphones", "/img/smartphone_category.png");
        categoryService.addCategory(category);
        verify(categoryDAO, times(1)).save(category);
    }

    @Test
    public void get_all_categories() {
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category("All", "All description", null);
        Category category2 = new Category("Notebook", "Notebook description", category1);
        Category category3 = new Category("Computer", "Computer description", category1);
        Category category4 = new Category("Ware", "Ware description", null);
        Category category5 = new Category("Hardware", "Hardware description", category4);
        Category category6 = new Category("Software", "Software description", category4);
        Category category7 = new Category("Forstudy", "Forstudy description", category3);
        Category category8 = new Category("Formusic", " description", category7);
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);
        categories.add(category7);
        categories.add(category8);
        when(categoryDAO.findAll()).thenReturn(categories);
        List<Category> categoriesList = categoryService.getAll();
        assertEquals(8, categoriesList.size());
        verify(categoryDAO, times(1)).findAll();
    }

    @Test
    public void get_by_id_category() {
        when(categoryDAO.findById(1)).thenReturn(new Category("All", "All description", null));
        Category category = categoryService.getById(1);
        assertEquals("All", category.getTitle());
        assertEquals("All description", category.getImageToPath());
        assertNull(category.getParent());
    }

    @Test
    public void when_update_category_it_should_return_category() {
        Category category = new Category(1, "All", "All description", null);
        categoryService.updateCategory(category);
        verify(categoryDAO, times(1)).update(category);
    }

    @Test
    public void when_delete_category_it_should_return_category() {
        Category category = new Category(1, "All", "All description", null);
        categoryService.deleteCategory(category);
        verify(categoryDAO, times(1)).delete(category);
    }
}
