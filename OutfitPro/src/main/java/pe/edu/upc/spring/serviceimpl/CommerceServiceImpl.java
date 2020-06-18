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
	private ICommerceRepository repCommerce;
	
	@Override
	@Transactional
	public boolean insert(Commerce commerce) {
		try {
			return repCommerce.save(commerce) != null;
		}
		catch (Exception ex) {
			System.out.println("Sucedió un roche...");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Commerce commerce) {
		try {
			repCommerce.save(commerce);
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
		repCommerce.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Commerce> findById(int id) {
		return repCommerce.findById(id);
	}

	@Override
	@Transactional
	public List<Commerce> findAll() {
		return repCommerce.findAll();
	}

	@Override
	@Transactional
	public List<Commerce> findByName(String name) {
		return repCommerce.findByName(name);
	}

}
