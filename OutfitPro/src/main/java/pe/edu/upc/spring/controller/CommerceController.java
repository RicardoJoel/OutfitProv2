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
@RequestMapping("/commerce")
public class CommerceController {

	@Autowired
	private ICommerceService srvCommerce;
	
	@RequestMapping("/bienvenido")
	public String irCommerceBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irCommerce(Map<String, Object> model) {
		model.put("listaCommerces", srvCommerce.findAll());
		return "listCommerce";
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
				return "redirect:/commerce/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/commerce/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Commerce objCommerce, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/commerce/listar";
		}
		else {
			boolean flag = srvCommerce.update(objCommerce);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/commerce/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/commerce/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Commerce> objCommerce = srvCommerce.findById(id);
		if (objCommerce == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un roche");
			return "redirect:/commerce/listar";
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
			model.put("mensaje","Ocurrió un roche");
			model.put("listaCommerces", srvCommerce.findAll());
		}
		return "listCommerce";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCommerces", srvCommerce.findAll());
		return "listCommerce";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("commerce", new Commerce());
		return "searchCommerce";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Commerce commerce) throws ParseException {
		List<Commerce> listaCommerces;
		commerce.setName(commerce.getName());
		listaCommerces = srvCommerce.findByName(commerce.getName());
		
		if (listaCommerces.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaCommerces", listaCommerces);
		return "searchCommerce";
	}
}
