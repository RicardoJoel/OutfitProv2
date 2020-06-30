package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Date;
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

import pe.edu.upc.spring.model.Consulting;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.model.Assessor;
import pe.edu.upc.spring.service.IConsultingService;
import pe.edu.upc.spring.service.IAssessorService;
import pe.edu.upc.spring.service.ICustomerService;

@Controller
@RequestMapping("/consultings")
public class ConsultingController {

	@Autowired
	private IConsultingService srvConsulting;
	@Autowired
	private IAssessorService srvAssessor;
	@Autowired
	private ICustomerService srvCustomer;
	
	@RequestMapping("/")
	public String irConsulting(Map<String, Object> model) {
		model.put("consulting", new Consulting());
		model.put("listaConsultings", srvConsulting.findAll());
		return "consultingList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("consulting", new Consulting());
		model.put("listaConsultings", srvConsulting.findAll());
		return "consultingList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaAssessors", srvAssessor.findAll());
		model.addAttribute("listaCustomers", srvCustomer.findAll());
		model.addAttribute("assessor", new Assessor());
		model.addAttribute("customer", new Customer());
		model.addAttribute("consulting", new Consulting());
		return "consulting";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Consulting consulting, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaAssessors", srvAssessor.findAll());
			model.addAttribute("listaCustomers", srvCustomer.findAll());
			return "consulting";
		}
		else {
			if (consulting.getInitDate() == null)
				consulting.setInitDate(new Date());
			else
				consulting.setEndDate(new Date());
			boolean flag = srvConsulting.insert(consulting);
			if (flag) {
				return "redirect:/consultings/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar la asesoría.");
				return "redirect:/consultings/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Consulting> objConsulting = srvConsulting.findById(id);
		if (objConsulting == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar la asesoría.");
			return "redirect:/consultings/listar";
		}
		else {
			model.addAttribute("listaAssessors", srvAssessor.findAll());
			model.addAttribute("listaCustomers", srvCustomer.findAll());
			model.addAttribute("consulting", objConsulting);
			return "consulting";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvConsulting.delete(id);
				model.put("listaConsultings", srvConsulting.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar la asesoría.");
			model.put("listaConsultings", srvConsulting.findAll());
		}
		return "redirect:/consultings/listar";
	}
}
