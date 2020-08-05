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
import ua.edu.sumdu.j2ee.zykov.model.Image;

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

    /*@Test
    public void when_save_category_it_should_return_category() {
        Image image = new Image("image1.png");
        image.setId(1);
        Category category = new Category("Smartphones", image);
        categoryService.addCategory(category);
        verify(categoryDAO, times(1)).save(category);
    }

    @Test
    public void get_all_categories() {
        Image image = new Image("image001.png");
        image.setId(1);
        Image image1 = new Image("image002.png");
        image1.setId(2);
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category("All", null, null);
        Category category2 = new Category("Notebook", null, category1);
        Category category3 = new Category("Computer", image, category1);
        Category category4 = new Category("Ware", image1, null);
        Category category5 = new Category("Hardware",  null, category4);
        Category category6 = new Category("Software", image1, category4);
        Category category7 = new Category("Forstudy", image, category3);
        Category category8 = new Category("Formusic", null, category7);
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
        Category parent = categoryService.getById(1);
        Image image = new Image("image001.png");
        image.setId(1);
        when(categoryDAO.findById(3)).thenReturn(new Category("Computer",  image, null));
        Category category = categoryService.getById(3);
        assertEquals("Computer", category.getTitle());
        assertEquals(image, category.getImage());
        assertEquals(parent, category.getParent());
    }

    @Test
    public void when_update_category_it_should_return_category() {
        Image image = new Image("image001.png");
        image.setId(1);
        Category category = new Category(1, "Computer", image, null);
        categoryService.updateCategory(category);
        verify(categoryDAO, times(1)).update(category);
    }

    @Test
    public void when_delete_category_it_should_return_category() {
        Image image = new Image("image001.png");
        image.setId(1);
        Category category = new Category(1, "Computer", image, null);
        categoryService.deleteCategory(category);
        verify(categoryDAO, times(1)).delete(category);
    }*/
}
