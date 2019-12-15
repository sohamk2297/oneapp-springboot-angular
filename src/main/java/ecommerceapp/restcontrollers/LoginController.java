package ecommerceapp.restcontrollers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.User;
import ecommerceapp.repos.UserRepository;

@RestController
@RequestMapping("/")
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping(path = "login")
	public Boolean userLogin(@RequestParam String username,@RequestParam String password) {
		logger.debug(username + " "+password);
		
		Optional<User> optional = userRepository.findById(username);
		if(optional.isPresent())
		{
			return bCryptPasswordEncoder.matches(password, optional.get().getEncryptedPassword());
		}
		else
		{
			return false;
		}
	}
}
