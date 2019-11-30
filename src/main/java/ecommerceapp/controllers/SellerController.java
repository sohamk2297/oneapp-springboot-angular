package ecommerceapp.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.Category;
import ecommerceapp.models.DetailField;
import ecommerceapp.models.Product;
import ecommerceapp.models.Seller;
import ecommerceapp.repos.CategoryRepository;
import ecommerceapp.repos.ProductRepository;
import ecommerceapp.repos.SellerRepository;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping(path = "/add" ,consumes = "application/json")
	public @ResponseBody Seller addSeller(@RequestBody Seller seller) {
		return sellerRepository.save(seller);
	}

	@SuppressWarnings("unchecked")
	@PostMapping(path = "/sell",consumes = "application/json")
	public @ResponseBody Product addProduct(@RequestParam String sellerName, @RequestBody(required = true) Map<String, Object> map) {
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
			Seller seller = sellerRepository.findByName(sellerName);
			if(seller != null)
			{
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
				
				Collection<Seller> sellers = product.getSellers();
				if(sellers == null)
				{
					sellers = new ArrayList<Seller>();
				}

				seller.getProductCatalog().add(product);
				sellers.add(seller);
				sellerRepository.save(seller);
				product.setSellers(sellers);
				product.setDetailFields(detailFields);
				return productRepository.save(product);
			}
			else
			{
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
