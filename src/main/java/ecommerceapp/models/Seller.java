package ecommerceapp.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seller extends User{
	
	private String name;
	private String address;
	private String dateOfRegistration;
	private Double rating;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
 	private Collection<Product> productCatalog;
	
	public Collection<Product> getProductCatalog() {
		return productCatalog;
	}
	public void setProductCatalog(Collection<Product> productCatalog) {
		this.productCatalog = productCatalog;
	}
	public String getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
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
	
	

}
 