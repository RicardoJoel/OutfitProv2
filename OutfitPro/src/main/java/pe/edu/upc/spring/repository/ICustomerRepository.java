package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByUsername(String username);
	
	@Query("from Customer a where a.enabled = true order by a.name")
	List<Customer> findAll();
	
	@Query("from Customer a where lower(a.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.name")
	List<Customer> findByName(@Param("name") String name);
}
