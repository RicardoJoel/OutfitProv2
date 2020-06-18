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
	private IPreferenceRepository repPreference;
	
	@Override
	@Transactional
	public boolean insert(Preference preference) {
		try {
			return repPreference.save(preference) != null;
		}
		catch (Exception ex) {
			System.out.println("Sucedió un roche...");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Preference preference) {
		try {
			repPreference.save(preference);
			return true;
		}
		catch (Exception ex) {
			System.out.println("Sucedió un roche...");
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		repPreference.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Preference> findById(int id) {
		return repPreference.findById(id);
	}

	@Override
	@Transactional
	public List<Preference> findAll() {
		return repPreference.findAll();
	}
	
	@Override
	@Transactional
	public List<Preference> findByMark(String mark) {
		return repPreference.findByMark(mark);
	}

	@Override
	@Transactional
	public List<Preference> findByCustomer(String customer) {
		return repPreference.findByCustomer(customer);
	}
	
	@Override
	@Transactional
	public List<Preference> findByClothingType(String clothingType) {
		return repPreference.findByClothingType(clothingType);
	}
}
