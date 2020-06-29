package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Color;

public interface IColorService {
	public boolean insert(Color color);
	public void delete(int id);
	public Optional<Color> findById(int id);
	List<Color> findAll();
	List<Color> findByName(String name);
}
