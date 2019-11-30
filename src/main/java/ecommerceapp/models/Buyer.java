package ecommerceapp.models;


import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Buyer extends User{

	 

	private String firstname;
	private String lastname;
	private String dob;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getUsername();
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		super.setUsername(username);
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		super.setEmail(email);
	}

	@Override
	public String getEncryptedPassword() {
		// TODO Auto-generated method stub
		return super.getEncryptedPassword();
	}

	@Override
	public void setEncryptedPassword(String encryptedPassword) {
		// TODO Auto-generated method stub
		super.setEncryptedPassword(encryptedPassword);
	}

	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Buyer [firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob + ", cart=" + cart + "]";
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}
