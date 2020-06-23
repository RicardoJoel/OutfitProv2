package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Clothing;

@Repository
public interface IClothingRepository extends JpaRepository<Clothing, Integer> {
	@Query("from Clothing a where lower(a.name) like lower(concat('%',:name,'%'))")
	List<Clothing> findByName(@Param("name") String name);
	
	@Query("from Clothing a where lower(a.mark.name) like lower(concat('%',:name,'%'))")
	List<Clothing> findByMark(@Param("name") String name);
	
	@Query("from Clothing a where lower(a.commerce.name) like lower(concat('%',:name,'%'))")
	List<Clothing> findByCommerce(@Param("name") String name);
	
	@Query("from Clothing a where lower(a.clothingType.name) like lower(concat('%',:name,'%'))")
	List<Clothing> findByClothingType(@Param("name") String name);
}
