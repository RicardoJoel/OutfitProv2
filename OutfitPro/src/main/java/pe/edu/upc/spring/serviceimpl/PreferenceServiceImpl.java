package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IPreferenceRepository;
import pe.edu.upc.spring.model.Preference;
import pe.edu.upc.spring.service.IPreferenceService;

@Service
public class PreferenceServiceImpl implements IPreferenceService {

	@Autowired
	private IPreferenceRepository rpsPreference;
	
	@Override
	@Transactional
	public boolean insert(Preference preference) {
		try {
			return rpsPreference.save(preference) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsPreference.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Preference> findById(int id) {
		return rpsPreference.findById(id);
	}

	@Override
	@Transactional
	public List<Preference> findAll() {
		return rpsPreference.findAll();
	}
	
	@Override
	@Transactional
	public List<Preference> findByMark(String mark) {
		return rpsPreference.findByMark(mark);
	}

	@Override
	@Transactional
	public List<Preference> findByCustomer(String customer) {
		return rpsPreference.findByCustomer(customer);
	}
	
	@Override
	@Transactional
	public List<Preference> findByClothingType(String clothingType) {
		return rpsPreference.findByClothingType(clothingType);
	}
}
