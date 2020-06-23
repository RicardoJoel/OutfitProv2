package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Clothing;

public interface IClothingService {
	public boolean insert(Clothing clothing);
	public void delete(int id);
	public Optional<Clothing> findById(int id);
	List<Clothing> findAll();
	List<Clothing> findByName(String name);
	List<Clothing> findByMark(String mark);
	List<Clothing> findByCommerce(String commerce);
	List<Clothing> findByClothingType(String clothingType);
}
