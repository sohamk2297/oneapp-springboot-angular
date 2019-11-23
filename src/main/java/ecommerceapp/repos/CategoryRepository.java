package ecommerceapp.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ecommerceapp.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{

	@Query("SELECT c FROM Category c WHERE c.name = ?1")
	public Category findByName(String name);
}
