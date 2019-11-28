package ecommerceapp.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.Category;
import ecommerceapp.models.DetailField;
import ecommerceapp.models.Product;
import ecommerceapp.repos.CategoryRepository;
import ecommerceapp.repos.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping(path = "search/{namelike}")
	public Iterable<Product> search(@PathVariable String namelike) {
		
		return productRepository.search(namelike);
		
	}
	
	@GetMapping(path = "/{id}")
	public @ResponseBody Product getById(@PathVariable String pid) {
		try
		{
			Integer id = Integer.parseInt(pid);
			Optional<Product> optional = productRepository.findById(id);
			if(optional.isPresent()) 
			{
				return optional.get();
			}
			else
			{
				return null;
			}
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@GetMapping("/all")
	public Iterable<Product> getAll() {
		return productRepository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(path = "/add",consumes = "application/json")
	public @ResponseBody Product addProduct(@RequestBody(required = true) Map<String, Object> map) {
		try
		{
		Product product = new Product();
		product.setName((String) map.get("name")); 
		
		String cattemp = (String) map.get("category");
		Category category = null;
		if(categoryRepository.findByName(cattemp) == null)
		{
			category = new Category();
			category.setName(cattemp);
		}
		else
		{
			category = categoryRepository.findByName(cattemp);	
		}
		product.setCategory(category);
		product.setPrice(Double.parseDouble((String) map.get("price")));
		product.setStock(Integer.parseInt((String) map.get("stock")));
		
			
		Collection<DetailField> detailFields = new ArrayList<DetailField>();
		Map<String,String> map2 = ((Map<String, String>) map.get("details"));
		for(String key: map2.keySet())
		{ 
			DetailField detailField = new DetailField();
			detailField.setField(key);
			detailField.setValue(map2.get(key));
			detailField.setProduct(product);
			detailFields.add(detailField);
		}
			
		product.setDetailFields(detailFields);
		return productRepository.save(product);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
