package ecommerceapp.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ecommerceapp.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>{

	@Query(value = "SELECT p FROM Product p WHERE p.name LIKE %?1%")
	public Iterable<Product> search(String namelike);
	
}
