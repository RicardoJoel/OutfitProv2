package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Size;

public interface ISizeService {
	public boolean insert(Size size);
	public void delete(int id);
	public Optional<Size> findById(int id);
	List<Size> findAll();
	List<Size> findByName(String name);
}
