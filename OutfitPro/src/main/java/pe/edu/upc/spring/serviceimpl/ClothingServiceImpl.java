package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IClothingRepository;
import pe.edu.upc.spring.model.Clothing;
import pe.edu.upc.spring.service.IClothingService;

@Service
public class ClothingServiceImpl implements IClothingService {

	@Autowired
	private IClothingRepository repClothing;
	
	@Override
	@Transactional
	public boolean insert(Clothing clothing) {
		try {
			return repClothing.save(clothing) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		repClothing.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Clothing> findById(int id) {
		return repClothing.findById(id);
	}

	@Override
	@Transactional
	public List<Clothing> findAll() {
		return repClothing.findAll();
	}

	@Override
	@Transactional
	public List<Clothing> findByName(String name) {
		return repClothing.findByName(name);
	}

	@Override
	public List<Clothing> findByMark(String mark) {
		return repClothing.findByMark(mark);
	}

	@Override
	public List<Clothing> findByCommerce(String commerce) {
		return repClothing.findByCommerce(commerce);
	}

	@Override
	public List<Clothing> findByClothingType(String clothingType) {
		return repClothing.findByClothingType(clothingType);
	}
}
