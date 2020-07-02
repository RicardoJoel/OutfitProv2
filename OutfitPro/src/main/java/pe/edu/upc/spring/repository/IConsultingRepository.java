package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Assessor;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.model.Consulting;

@Repository
public interface IConsultingRepository extends JpaRepository<Consulting, Integer> {
	@Query("from Consulting a where a.enabled = true order by a.dateTime desc")
	List<Consulting> findAll();
	
	@Query("from Consulting a where lower(a.assessor.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.dateTime desc")
	List<Consulting> findByName(@Param("name") String name);
	
	@Query("from Consulting a where a.assessor = :assessor and a.enabled = true order by a.dateTime desc")
	List<Consulting> findByAssessor(@Param("assessor") Assessor assessor);
	
	@Query("from Consulting a where a.customer = :customer and a.enabled = true order by a.dateTime desc")
	List<Consulting> findByCustomer(@Param("customer") Customer customer);
}
