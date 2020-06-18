package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.ICustomerRepository;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository repCustomer;
	
	@Override
	@Transactional
	public boolean insert(Customer customer) {
		try {
			return repCustomer.save(customer) != null;
		}
		catch (Exception ex) {
			System.out.println("Sucedió un roche...");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Customer customer) {
		try {
			repCustomer.save(customer);
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
		repCustomer.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Customer> findById(int id) {
		return repCustomer.findById(id);
	}

	@Override
	@Transactional
	public List<Customer> findAll() {
		return repCustomer.findAll();
	}

	@Override
	@Transactional
	public List<Customer> findByName(String name) {
		return repCustomer.findByName(name);
	}

}
