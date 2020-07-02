package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Size;

@Repository
public interface ISizeRepository extends JpaRepository<Size, Integer> {
	@Query("from Size a where a.enabled = true order by a.name")
	List<Size> findAll();
	
	@Query("from Size a where lower(a.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.name")
	List<Size> findByName(@Param("name") String name);
}
