package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Assessor;

@Repository
public interface IAssessorRepository extends JpaRepository<Assessor, Integer> {
	@Query("from Assessor a where a.enabled = true order by a.name")
	List<Assessor> findAll();
	
	@Query("from Assessor a where lower(a.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.name")
	List<Assessor> findByName(@Param("name") String name);
}
