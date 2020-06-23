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
	private IMarkRepository rpsMark;
	
	@Override
	@Transactional
	public boolean insert(Mark mark) {
		try {
			return rpsMark.save(mark) != null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		rpsMark.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Mark> findById(int id) {
		return rpsMark.findById(id);
	}

	@Override
	@Transactional
	public List<Mark> findAll() {
		return rpsMark.findAll();
	}

	@Override
	@Transactional
	public List<Mark> findByName(String name) {
		return rpsMark.findByName(name);
	}
}
