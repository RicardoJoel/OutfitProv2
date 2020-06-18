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
@RequestMapping("/mark")
public class MarkController {

	@Autowired
	private IMarkService srvMark;
	
	@RequestMapping("/bienvenido")
	public String irMarkBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irMark(Map<String, Object> model) {
		model.put("listaMarks", srvMark.findAll());
		return "listMark";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("mark", new Mark());
		return "mark";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Mark objMark, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "mark";
		}
		else {
			boolean flag = srvMark.insert(objMark);
			if (flag) {
				return "redirect:/mark/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/mark/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Mark objMark, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/mark/listar";
		}
		else {
			boolean flag = srvMark.update(objMark);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/mark/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/mark/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Mark> objMark = srvMark.findById(id);
		if (objMark == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un roche");
			return "redirect:/mark/listar";
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
			model.put("mensaje","Ocurrió un roche");
			model.put("listaMarks", srvMark.findAll());
		}
		return "listMark";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMarks", srvMark.findAll());
		return "listMark";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("mark", new Mark());
		return "searchMark";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Mark mark) throws ParseException {
		List<Mark> listaMarks;
		mark.setName(mark.getName());
		listaMarks = srvMark.findByName(mark.getName());
		
		if (listaMarks.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMarks", listaMarks);
		return "searchMark";
	}
}
