package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IMarkRepository;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.service.IMarkService;

@Service
public class MarkServiceImpl implements IMarkService {

	@Autowired
	private IMarkRepository repMark;
	
	@Override
	@Transactional
	public boolean insert(Mark mark) {
		try {
			return repMark.save(mark) != null;
		}
		catch (Exception ex) {
			System.out.println("Sucedió un roche...");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Mark mark) {
		try {
			repMark.save(mark);
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
		repMark.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Mark> findById(int id) {
		return repMark.findById(id);
	}

	@Override
	@Transactional
	public List<Mark> findAll() {
		return repMark.findAll();
	}

	@Override
	@Transactional
	public List<Mark> findByName(String name) {
		return repMark.findByName(name);
	}

}
