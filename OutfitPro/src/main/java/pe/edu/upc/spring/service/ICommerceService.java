package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Commerce;

public interface ICommerceService {
	public boolean insert(Commerce commerce);
	public boolean update(Commerce commerce);
	public void delete(int id);
	public Optional<Commerce> findById(int id);
	List<Commerce> findAll();
	List<Commerce> findByName(String name);
}
