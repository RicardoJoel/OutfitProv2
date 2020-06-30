package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Mark;

@Repository
public interface IMarkRepository extends JpaRepository<Mark, Integer> {
	@Query("from Mark a where a.enabled = true order by a.name")
	List<Mark> findAll();
	
	@Query("from Mark a where lower(a.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.name")
	List<Mark> findByName(@Param("name") String name);
}
