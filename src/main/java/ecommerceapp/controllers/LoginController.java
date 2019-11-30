package ecommerceapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.User;
import ecommerceapp.repos.UserRepository;

@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/login")
	public @ResponseBody boolean userLogin(@RequestParam String username,@RequestParam String password) {
		Optional<User> optional = userRepository.findById(username);
		if(optional.isPresent())
		{
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);
			return bCryptPasswordEncoder.matches(password, optional.get().getEncryptedPassword());
		}
		else
		{
			return false;
		}
	}
}
