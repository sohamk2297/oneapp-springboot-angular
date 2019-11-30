package ecommerceapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.Seller;
import ecommerceapp.repos.SellerRepository;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@PostMapping(path = "/add" ,consumes = "application/json")
	public @ResponseBody Seller addSeller(@RequestBody Seller seller) {
		return sellerRepository.save(seller);
	}

}
