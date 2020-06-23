package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.ICommerceRepository;
import pe.edu.upc.spring.model.Commerce;
import pe.edu.upc.spring.service.ICommerceService;

@Service
public class CommerceServiceImpl implements ICommerceService {

	@Autowired
	private ICommerceRepository rpsCommerce;
	
	@Override
	@Transactional
	public boolean insert(Commerce commerce) {
		try {
			return rpsCommerce.save(commerce) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsCommerce.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Commerce> findById(int id) {
		return rpsCommerce.findById(id);
	}

	@Override
	@Transactional
	public List<Commerce> findAll() {
		return rpsCommerce.findAll();
	}

	@Override
	@Transactional
	public List<Commerce> findByName(String name) {
		return rpsCommerce.findByName(name);
	}
}
