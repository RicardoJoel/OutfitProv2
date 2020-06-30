package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Color;

@Repository
public interface IColorRepository extends JpaRepository<Color, Integer> {
	@Query("from Color a where a.enabled = true order by a.name")
	List<Color> findAll();
	
	@Query("from Color a where lower(a.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.name")
	List<Color> findByName(@Param("name") String name);
}
