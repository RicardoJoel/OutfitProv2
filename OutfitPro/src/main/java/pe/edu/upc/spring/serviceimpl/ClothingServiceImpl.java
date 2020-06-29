package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IClothingRepository;
import pe.edu.upc.spring.model.Clothing;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Color;
import pe.edu.upc.spring.model.Commerce;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.model.Size;
import pe.edu.upc.spring.service.IClothingService;

@Service
public class ClothingServiceImpl implements IClothingService {

	@Autowired
	private IClothingRepository rpsClothing;
	
	@Override
	@Transactional
	public boolean insert(Clothing clothing) {
		try {
			return rpsClothing.save(clothing) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsClothing.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Clothing> findById(int id) {
		return rpsClothing.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clothing> findAll() {
		return rpsClothing.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clothing> findByName(String name) {
		return rpsClothing.findByName(name);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clothing> findBySize(Size size) {
		return rpsClothing.findBySize(size);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clothing> findByColor(Color color) {
		return rpsClothing.findByColor(color);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Clothing> findByMark(Mark mark) {
		return rpsClothing.findByMark(mark);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clothing> findByCommerce(Commerce commerce) {
		return rpsClothing.findByCommerce(commerce);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clothing> findByClothingType(ClothingType clothingType) {
		return rpsClothing.findByClothingType(clothingType);
	}

}
