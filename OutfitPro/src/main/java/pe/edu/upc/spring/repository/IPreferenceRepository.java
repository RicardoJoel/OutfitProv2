package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Preference;

@Repository
public interface IPreferenceRepository extends JpaRepository<Preference, Integer> {
	@Query("from Preference a where lower(a.mark.name) like lower(concat('%',:name,'%'))")
	List<Preference> findByMark(@Param("name") String name);
	
	@Query("from Preference a where lower(a.customer.name) like lower(concat('%',:name,'%'))")
	List<Preference> findByCustomer(@Param("name") String name);
	
	@Query("from Preference a where lower(a.clothingType.name) like lower(concat('%',:name,'%'))")
	List<Preference> findByClothingType(@Param("name") String name);
}
