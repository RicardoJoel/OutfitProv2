package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.model.Preference;

@Repository
public interface IPreferenceRepository extends JpaRepository<Preference, Integer> {
	@Query("from Preference a where a.mark = :mark")
	List<Preference> findByMark(@Param("mark") Mark mark);
	
	@Query("from Preference a where a.customer = :customer")
	List<Preference> findByCustomer(@Param("customer") Customer customer);
	
	@Query("from Preference a where a.clothingType = :clothingType")
	List<Preference> findByClothingType(@Param("clothingType") ClothingType clothingType);
}
