package ecommerceapp.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ecommerceapp.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>{

}
