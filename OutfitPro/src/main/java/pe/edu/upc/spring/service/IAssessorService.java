package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Assessor;

public interface IAssessorService {
	public boolean insert(Assessor assessor);
	public void delete(int id);
	public Optional<Assessor> findById(int id);
	List<Assessor> findAll();
	List<Assessor> findByName(String name);
}
