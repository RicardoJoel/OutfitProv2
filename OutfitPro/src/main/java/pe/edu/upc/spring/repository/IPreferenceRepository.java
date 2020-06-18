package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Preference;

@Repository
public interface IPreferenceRepository extends JpaRepository<Preference, Integer> {
	@Query("from Preference r where r.mark.name like %:mark%")
	List<Preference> findByMark(@Param("mark") String mark);
	
	@Query("from Preference r where r.customer.name like %:customer%")
	List<Preference> findByCustomer(@Param("customer") String customer);
	
	@Query("from Preference r where r.clothingType.name like %:clothingType%")
	List<Preference> findByClothingType(@Param("clothingType") String clothingType);
}
