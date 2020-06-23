package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Preference;

public interface IPreferenceService {
	public boolean insert(Preference preference);
	public void delete(int id);
	public Optional<Preference> findById(int id);
	List<Preference> findAll();
	List<Preference> findByMark(String mark);
	List<Preference> findByCustomer(String customer);
	List<Preference> findByClothingType(String clothingType);
}
