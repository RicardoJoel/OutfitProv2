package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Discount;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount, Integer> {
	@Query("from Discount a where lower(a.name) like lower(concat('%',:name,'%'))")
	List<Discount> findByName(@Param("name") String name);
}
