package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Customer;

public interface ICustomerService {
	public boolean insert(Customer customer);
	public boolean update(Customer customer);
	public void delete(int id);
	public Optional<Customer> findById(int id);
	List<Customer> findAll();
	List<Customer> findByName(String name);
}
