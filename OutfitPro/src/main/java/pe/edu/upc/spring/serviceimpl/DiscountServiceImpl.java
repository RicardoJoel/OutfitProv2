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
	private IDiscountRepository rpsDiscount;
	
	@Override
	@Transactional
	public boolean insert(Discount discount) {
		try {
			return rpsDiscount.save(discount) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsDiscount.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Discount> findById(int id) {
		return rpsDiscount.findById(id);
	}

	@Override
	@Transactional
	public List<Discount> findAll() {
		return rpsDiscount.findAll();
	}

	@Override
	@Transactional
	public List<Discount> findByName(String name) {
		return rpsDiscount.findByName(name);
	}
}
