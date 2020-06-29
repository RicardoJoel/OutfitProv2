package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IColorRepository;
import pe.edu.upc.spring.model.Color;
import pe.edu.upc.spring.service.IColorService;

@Service
public class ColorServiceImpl implements IColorService {

	@Autowired
	private IColorRepository rpsColor;
	
	@Override
	@Transactional
	public boolean insert(Color color) {
		try {
			return rpsColor.save(color) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsColor.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Color> findById(int id) {
		return rpsColor.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Color> findAll() {
		return rpsColor.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Color> findByName(String name) {
		return rpsColor.findByName(name);
	}
}
