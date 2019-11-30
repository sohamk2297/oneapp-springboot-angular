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
	
	

}
