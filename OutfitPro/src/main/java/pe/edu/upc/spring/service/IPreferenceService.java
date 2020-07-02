package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.model.Preference;

public interface IPreferenceService {
	public boolean insert(Preference preference);
	public void delete(int id);
	public Optional<Preference> findById(int id);
	List<Preference> findAll();
	List<Preference> findByName(String name);
	List<Preference> findByMark(Mark mark);
	List<Preference> findByCustomer(Customer customer);
	List<Preference> findByClothingType(ClothingType clothingType);
}
