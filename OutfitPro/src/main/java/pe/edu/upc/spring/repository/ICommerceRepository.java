package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Commerce;

@Repository
public interface ICommerceRepository extends JpaRepository<Commerce, Integer> {
	@Query("from Commerce a where lower(a.name) like lower(concat('%',:name,'%'))")
	List<Commerce> findByName(@Param("name") String name);
}
