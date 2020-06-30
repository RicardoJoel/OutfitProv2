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

import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Assessor;
import pe.edu.upc.spring.service.IClothingTypeService;
import pe.edu.upc.spring.service.IAssessorService;

@Controller
@RequestMapping("/assessors")
public class AssessorController {

	@Autowired
	private IAssessorService srvAssessor;
	@Autowired
	private IClothingTypeService srvClothingType;
	
	@RequestMapping("/")
	public String irAssessor(Map<String, Object> model) {
		model.put("assessor", new Assessor());
		model.put("listaAssessors", srvAssessor.findAll());
		return "assessorList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("assessor", new Assessor());
		model.put("listaAssessors", srvAssessor.findAll());
		return "assessorList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Assessor assessor) throws ParseException {
		List<Assessor> listaAssessors = srvAssessor.findByName(assessor.getName());		
		if (listaAssessors.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaAssessors", listaAssessors);
		return "assessorList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaClothingTypes", srvClothingType.findAll());
		model.addAttribute("clothingType", new ClothingType());
		model.addAttribute("assessor", new Assessor());
		return "assessor";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Assessor assessor, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			return "assessor";
		}
		else {
			boolean flag = srvAssessor.insert(assessor);
			if (flag) {
				return "redirect:/assessors/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar el asesor.");
				return "redirect:/assessors/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Assessor> objAssessor = srvAssessor.findById(id);
		if (objAssessor == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar el asesor.");
			return "redirect:/assessors/listar";
		}
		else {
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			model.addAttribute("assessor", objAssessor);
			return "assessor";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvAssessor.delete(id);
				model.put("listaAssessors", srvAssessor.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar el asesor.");
			model.put("listaAssessors", srvAssessor.findAll());
		}
		return "redirect:/assessors/listar";
	}
}
