package ecommerceapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.Buyer;
import ecommerceapp.repos.BuyerRepository;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@PostMapping(path = "/add" ,consumes = "application/json")
	public @ResponseBody Buyer addBuyer(@RequestBody Buyer buyer) {
//		buyer.setEncryptedPassword(buyer.getEncryptedPassword());
		return buyerRepository.save(buyer);
	}
	
}
