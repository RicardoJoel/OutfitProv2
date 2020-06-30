package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IAssessorRepository;
import pe.edu.upc.spring.model.Assessor;
import pe.edu.upc.spring.service.IAssessorService;

@Service
public class AssessorServiceImpl implements IAssessorService {

	@Autowired
	private IAssessorRepository repAssessor;
	
	@Override
	@Transactional
	public boolean insert(Assessor assessor) {
		try {
			return repAssessor.save(assessor) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		repAssessor.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Assessor> findById(int id) {
		return repAssessor.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Assessor> findAll() {
		return repAssessor.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Assessor> findByName(String name) {
		return repAssessor.findByName(name);
	}

}
