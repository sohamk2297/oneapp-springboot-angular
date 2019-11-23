package ecommerceapp.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import ecommerceapp.models.Details;
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
	public @ResponseBody Product addProduct(@RequestBody Map<String, Object> map) {
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
		
			/*
			 * Map<String, String> detailstemp = new HashMap<String, String>(); Map<String,
			 * String> map2 = ((Map<String, String>) map.get("details")); for(String key :
			 * map2.keySet()) { detailstemp.put(key,map2.get(key)); } Details details = new
			 * Details(); details.setDetailsMap(detailstemp); product.setDetails(details);
			 */
		Collection<DetailField> detailFields = new ArrayList<DetailField>();
		Map<String,String> map2 = ((Map<String, String>) map.get("details"));
		for(String key: map2.keySet())
		{
			DetailField detailField = new DetailField();
			detailField.setField(key);
			detailField.setValue(map2.get(key));
			detailFields.add(detailField);
		}
		Details details = new Details();
		details.setDetailFields(detailFields);
		product.setDetails(details);
		return productRepository.save(product);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
