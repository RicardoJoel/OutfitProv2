package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Clothing;

@Repository
public interface IClothingRepository extends JpaRepository<Clothing, Integer> {
	@Query("from Clothing r where r.name like %:name%")
	List<Clothing> findByName(@Param("name") String name);
}
