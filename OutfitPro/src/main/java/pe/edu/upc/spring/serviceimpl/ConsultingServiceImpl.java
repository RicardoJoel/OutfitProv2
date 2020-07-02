package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IConsultingRepository;
import pe.edu.upc.spring.model.Assessor;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.model.Consulting;
import pe.edu.upc.spring.service.IConsultingService;

@Service
public class ConsultingServiceImpl implements IConsultingService {

	@Autowired
	private IConsultingRepository rpsConsulting;
	
	@Override
	@Transactional
	public boolean insert(Consulting consulting) {
		try {
			return rpsConsulting.save(consulting) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsConsulting.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Consulting> findById(int id) {
		return rpsConsulting.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Consulting> findByName(String name) {
		return rpsConsulting.findByName(name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Consulting> findAll() {
		return rpsConsulting.findAll();
	}

	@Override
	public List<Consulting> findByAssessor(Assessor assessor) {
		return rpsConsulting.findByAssessor(assessor);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Consulting> findByCustomer(Customer customer) {
		return rpsConsulting.findByCustomer(customer);
	}
}
