package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Mark;

@Repository
public interface IMarkRepository extends JpaRepository<Mark, Integer> {
	@Query("from Mark r where r.name like %:name%")
	List<Mark> findByName(@Param("name") String name);
}
