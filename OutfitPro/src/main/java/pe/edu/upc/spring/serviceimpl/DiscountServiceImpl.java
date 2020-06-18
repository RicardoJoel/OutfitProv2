package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IDiscountRepository;
import pe.edu.upc.spring.model.Discount;
import pe.edu.upc.spring.service.IDiscountService;

@Service
public class DiscountServiceImpl implements IDiscountService {

	@Autowired
	private IDiscountRepository repDiscount;
	
	@Override
	@Transactional
	public boolean insert(Discount discount) {
		try {
			return repDiscount.save(discount) != null;
		}
		catch (Exception ex) {
			System.out.println("Sucedió un roche...");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Discount discount) {
		try {
			repDiscount.save(discount);
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
		repDiscount.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Discount> findById(int id) {
		return repDiscount.findById(id);
	}

	@Override
	@Transactional
	public List<Discount> findAll() {
		return repDiscount.findAll();
	}

	@Override
	@Transactional
	public List<Discount> findByName(String name) {
		return repDiscount.findByName(name);
	}

}
