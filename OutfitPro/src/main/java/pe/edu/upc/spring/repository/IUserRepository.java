package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
	
	@Query("from User a where a.enabled = true order by a.name")
	List<User> findAll();
	
	@Query("from User a where lower(a.name) like lower(concat('%',:name,'%')) and a.enabled = true order by a.name")
	List<User> findByName(@Param("name") String name);
}
