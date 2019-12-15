package ecommerceapp.viewcontrollers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class MainViewController {

	RestTemplate restTemplate = new RestTemplate();
	Logger logger = LoggerFactory.getLogger(MainViewController.class);

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(path = "/signup", method = RequestMethod.GET)
	public String signup()
	{
		return "signup";
	}
	
	@RequestMapping(path = "/createAccount", method = RequestMethod.POST)
	public String createAccount(
			@RequestParam String username, 
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String firstname,
			@RequestParam String lastname,
			@RequestParam String dob,
			@RequestParam String sellername,
			@RequestParam String selleraddress,
			@RequestParam String type) throws RestClientException, URISyntaxException{
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("username",username);
		map.put("password",password);
		map.put("email",email);
		map.put("firstname",firstname);
		map.put("lastname",lastname);
		map.put("sellername",sellername);
		map.put("selleraddress",selleraddress);
		map.put("type", type);
		
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(restTemplate.postForObject(new URI("http://localhost:8080/signup"),map, Boolean.class));
	
		return "done";
		
		
	}

	@RequestMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("status","unknown");
		return "login";
	}

	@RequestMapping("/verifylogin")
	public String verify(@RequestParam String username,@RequestParam String password,Model model) throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/login");
		builder.queryParam("username", username);
		builder.queryParam("password", password);

		System.out.println(builder.build().toUriString());
		RequestEntity requestEntity = RequestEntity.post(builder.build().toUri()).build();
		System.out.println(requestEntity.getUrl());
		ResponseEntity<Boolean> entity = restTemplate.exchange(requestEntity, Boolean.class);
		if(entity.getBody() == true)
		{
			logger.info("Redirecting to home page");
			return "index";
		}
		else
		{
			model.addAttribute("status","wrong");
			return "login";
		}
	}

}
