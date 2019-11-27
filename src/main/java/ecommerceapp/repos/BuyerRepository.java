package ecommerceapp.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ecommerceapp.models.Buyer;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Integer>{

}
