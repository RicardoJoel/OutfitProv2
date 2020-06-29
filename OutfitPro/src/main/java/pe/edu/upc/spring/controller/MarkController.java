package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.service.IMarkService;

@Controller
@RequestMapping("/marks")
public class MarkController {

	@Autowired
	private IMarkService srvMark;
	
	@RequestMapping("/")
	public String irMark(Map<String, Object> model) {
		model.put("mark", new Mark());
		model.put("listaMarks", srvMark.findAll());
		return "markList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("mark", new Mark());
		model.put("listaMarks", srvMark.findAll());
		return "markList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Mark mark) throws ParseException {
		List<Mark> listaMarks;
		listaMarks = srvMark.findByName(mark.getName());		
		if (listaMarks.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaMarks", listaMarks);
		return "markList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("mark", new Mark());
		return "mark";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Mark mark, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "mark";
		}
		else {
			boolean flag = srvMark.insert(mark);
			if (flag) {
				return "redirect:/marks/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar la marca.");
				return "redirect:/marks/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Mark> objMark = srvMark.findById(id);
		if (objMark == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar la marca.");
			return "redirect:/marks/listar";
		}
		else {
			model.addAttribute("mark", objMark);
			return "mark";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvMark.delete(id);
				model.put("listaMarks", srvMark.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar la marca.");
			model.put("listaMarks", srvMark.findAll());
		}
		return "redirect:/marks/listar";
	}
}
