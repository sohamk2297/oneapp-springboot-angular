package ecommerceapp.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ecommerceapp.models.Details;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer>{

}
