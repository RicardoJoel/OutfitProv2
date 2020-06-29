package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IClothingTypeRepository;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.service.IClothingTypeService;

@Service
public class ClothingTypeServiceImpl implements IClothingTypeService {

	@Autowired
	private IClothingTypeRepository rpsClothingType;
	
	@Override
	@Transactional
	public boolean insert(ClothingType clothingType) {
		try {
			return rpsClothingType.save(clothingType) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		rpsClothingType.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<ClothingType> findById(int id) {
		return rpsClothingType.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ClothingType> findAll() {
		return rpsClothingType.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<ClothingType> findByName(String name) {
		return rpsClothingType.findByName(name);
	}
}
