package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Clothing;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Color;
import pe.edu.upc.spring.model.Commerce;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.model.Size;

public interface IClothingService {
	public boolean insert(Clothing clothing);
	public void delete(int id);
	public Optional<Clothing> findById(int id);
	List<Clothing> findAll();
	List<Clothing> findByName(String name);
	List<Clothing> findBySize(Size size);
	List<Clothing> findByColor(Color color);
	List<Clothing> findByMark(Mark mark);
	List<Clothing> findByCommerce(Commerce commerce);
	List<Clothing> findByClothingType(ClothingType clothingType);
}
