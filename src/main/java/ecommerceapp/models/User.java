package ecommerceapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	
	@Id
	protected String username;
	

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", encryptedPassword=" + encryptedPassword + "]";
	}
	protected String email;
	protected String encryptedPassword;
	
	
	public String getUsername() {
		return username; 
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword =  encryptedPassword;
	}

	
}
