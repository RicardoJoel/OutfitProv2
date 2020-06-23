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

import pe.edu.upc.spring.model.Commerce;
import pe.edu.upc.spring.service.ICommerceService;

@Controller
@RequestMapping("/commerces")
public class CommerceController {

	@Autowired
	private ICommerceService srvCommerce;
	
	@RequestMapping("/")
	public String irCommerce(Map<String, Object> model) {
		model.put("commerce", new Commerce());
		model.put("listaCommerces", srvCommerce.findAll());
		return "commerceList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("commerce", new Commerce());
		model.put("listaCommerces", srvCommerce.findAll());
		return "commerceList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Commerce commerce) throws ParseException {
		List<Commerce> listaCommerces;
		listaCommerces = srvCommerce.findByName(commerce.getName());		
		if (listaCommerces.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaCommerces", listaCommerces);
		return "commerceList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("commerce", new Commerce());
		return "commerce";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Commerce objCommerce, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "commerce";
		}
		else {
			boolean flag = srvCommerce.insert(objCommerce);
			if (flag) {
				return "redirect:/commerces/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar el comercio.");
				return "redirect:/commerces/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Commerce> objCommerce = srvCommerce.findById(id);
		if (objCommerce == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar el comercio.");
			return "redirect:/commerces/listar";
		}
		else {
			model.addAttribute("commerce", objCommerce);
			return "commerce";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvCommerce.delete(id);
				model.put("listaCommerces", srvCommerce.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar el comercio.");
			model.put("listaCommerces", srvCommerce.findAll());
		}
		return "redirect:/commerces/listar";
	}
}
