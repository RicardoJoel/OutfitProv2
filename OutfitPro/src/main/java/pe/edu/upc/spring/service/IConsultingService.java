package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Assessor;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.model.Consulting;

public interface IConsultingService {
	public boolean insert(Consulting preference);
	public void delete(int id);
	public Optional<Consulting> findById(int id);
	List<Consulting> findAll();
	List<Consulting> findByName(String name);
	List<Consulting> findByAssessor(Assessor assessor);
	List<Consulting> findByCustomer(Customer customer);
}
