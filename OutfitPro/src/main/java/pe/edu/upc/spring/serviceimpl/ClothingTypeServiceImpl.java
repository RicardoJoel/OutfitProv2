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
	private IClothingTypeRepository repClothingType;
	
	@Override
	@Transactional
	public boolean insert(ClothingType clothingType) {
		try {
			return repClothingType.save(clothingType) != null;
		}
		catch (Exception ex) {
			System.out.println("Sucedió un roche...");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(ClothingType clothingType) {
		try {
			repClothingType.save(clothingType);
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
		repClothingType.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<ClothingType> findById(int id) {
		return repClothingType.findById(id);
	}

	@Override
	@Transactional
	public List<ClothingType> findAll() {
		return repClothingType.findAll();
	}

	@Override
	@Transactional
	public List<ClothingType> findByName(String name) {
		return repClothingType.findByName(name);
	}

}
