package ecommerceapp.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ecommerceapp.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
