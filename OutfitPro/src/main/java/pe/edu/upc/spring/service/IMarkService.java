package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Mark;

public interface IMarkService {
	public boolean insert(Mark mark);
	public void delete(int id);
	public Optional<Mark> findById(int id);
	List<Mark> findAll();
	List<Mark> findByName(String name);
}
