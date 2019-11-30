package ecommerceapp.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ecommerceapp.models.Seller;

public interface SellerRepository extends CrudRepository<Seller, String>{

	@Query("SELECT s FROM Seller s WHERE s.name = ?1")
	public Seller findByName(String sellerName);
}
