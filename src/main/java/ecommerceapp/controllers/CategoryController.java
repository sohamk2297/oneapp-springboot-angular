package ecommerceapp.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.Category;
import ecommerceapp.models.Product;
import ecommerceapp.repos.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(path = "/{catname}")
	public @ResponseBody Collection<Product> getByCategory(@PathVariable String catname) {		
		Category category = categoryRepository.findByName(catname);
		if ( category !=null)
			return category.getProducts();
		else
		{
			Collection<Product> collection = new ArrayList<Product>();
			List<Category> categories = categoryRepository.findBySimilarName(catname);
			for(Category category2 : categories)
			{
				for(Product product : category2.getProducts())
				{
					collection.add(product);
				}
			}
			return collection;
		}
	}
	
}
