package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.ClothingType;

public interface IClothingTypeService {
	public boolean insert(ClothingType clothingType);
	public boolean update(ClothingType clothingType);
	public void delete(int id);
	public Optional<ClothingType> findById(int id);
	List<ClothingType> findAll();
	List<ClothingType> findByName(String name);
}
