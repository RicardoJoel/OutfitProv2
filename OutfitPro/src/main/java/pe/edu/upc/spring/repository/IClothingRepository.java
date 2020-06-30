package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Clothing;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Color;
import pe.edu.upc.spring.model.Commerce;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.model.Size;

@Repository
public interface IClothingRepository extends JpaRepository<Clothing, Integer> {
	@Query("from Clothing a where a.enabled = true order by a.name")
	List<Clothing> findAll();
	
	@Query("from Clothing a where lower(a.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.name")
	List<Clothing> findByName(@Param("name") String name);
	
	@Query("from Clothing a where a.size = :size and a.enabled = true order by a.name")
	List<Clothing> findBySize(@Param("size") Size size);
	
	@Query("from Clothing a where a.color = :color and a.enabled = true order by a.name")
	List<Clothing> findByColor(@Param("color") Color color);
	
	@Query("from Clothing a where a.mark = :mark and a.enabled = true order by a.name")
	List<Clothing> findByMark(@Param("mark") Mark mark);
	
	@Query("from Clothing a where a.commerce = :commerce and a.enabled = true order by a.name")
	List<Clothing> findByCommerce(@Param("commerce") Commerce commerce);
	
	@Query("from Clothing a where a.clothingType = :clothingType and a.enabled = true order by a.name")
	List<Clothing> findByClothingType(@Param("clothingType") ClothingType clothingType);
}
