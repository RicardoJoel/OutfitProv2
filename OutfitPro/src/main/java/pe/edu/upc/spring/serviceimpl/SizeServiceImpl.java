package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.ISizeRepository;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Size;
import pe.edu.upc.spring.service.ISizeService;

@Service
public class SizeServiceImpl implements ISizeService {

	@Autowired
	private ISizeRepository rpsSize;
	
	@Override
	@Transactional
	public boolean insert(Size size) {
		try {
			return rpsSize.save(size) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsSize.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Size> findById(int id) {
		return rpsSize.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Size> findAll() {
		return rpsSize.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Size> findByName(String name) {
		return rpsSize.findByName(name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Size> findByClothingType(ClothingType clothingType) {
		return rpsSize.findByClothingType(clothingType);
	}
}
