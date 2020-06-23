package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Discount;

public interface IDiscountService {
	public boolean insert(Discount discount);
	public void delete(int id);
	public Optional<Discount> findById(int id);
	List<Discount> findAll();
	List<Discount> findByName(String name);
}
