package ecommerceapp.restcontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import ecommerceapp.models.Buyer;
import ecommerceapp.models.Seller;

@RestController("/")
public class SignupController {
	@Autowired
	private PasswordEncoder encoder;
	@PostMapping(path = "/signup",consumes = "application/json")
	public @ResponseBody boolean signup(@RequestBody Map<String, String> map) {
		RestTemplate restTemplate = new RestTemplate();	
		
		switch ((String)map.get("type")) 
		{
		case "buyer":
			Buyer buyer = new Buyer();
			buyer.setUsername((String) map.get("username"));
			buyer.setEncryptedPassword(encoder.encode((String) map.get("password")));
			buyer.setEmail((String) map.get("email"));
			buyer.setFirstname((String) map.get("firstname"));
			buyer.setLastname((String) map.get("lastname"));			
			buyer.setDob((String)map.get("dob"));
			restTemplate.postForObject("http://localhost:8080/buyer/add",buyer,Buyer.class);
			return true;
		case "seller":
			Seller seller = new Seller();
			seller.setUsername((String) map.get("username"));
			seller.setEncryptedPassword(encoder.encode((String) map.get("password")));
			seller.setEmail((String) map.get("email"));
			seller.setDateOfRegistration((String) map.get("dor"));
			seller.setRating(0.0);
			seller.setName((String) map.get("sellername"));
			seller.setAddress((String) map.get("selleraddress"));
			restTemplate.postForObject("http://localhost:8080/seller/add",seller,Seller.class);
			return true;
		default:
			
			break;
		}
		return false;
		
	}

}
