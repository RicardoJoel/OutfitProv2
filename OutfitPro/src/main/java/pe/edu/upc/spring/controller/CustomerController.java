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
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.service.IClothingTypeService;
import pe.edu.upc.spring.service.ICustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ICustomerService srvCustomer;
	@Autowired
	private IClothingTypeService srvClothingType;
	
	@RequestMapping("/")
	public String irCustomer(Map<String, Object> model) {
		model.put("customer", new Customer());
		model.put("listaCustomers", srvCustomer.findAll());
		return "customerList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("customer", new Customer());
		model.put("listaCustomers", srvCustomer.findAll());
		return "customerList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Customer customer) throws ParseException {
		List<Customer> listaCustomers = srvCustomer.findByName(customer.getName());		
		if (listaCustomers.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaCustomers", listaCustomers);
		return "customerList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaClothingTypes", srvClothingType.findAll());
		model.addAttribute("clothingType", new ClothingType());
		model.addAttribute("customer", new Customer());
		return "customer";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Customer customer, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			return "customer";
		}
		else {
			boolean flag = srvCustomer.insert(customer);
			if (flag) {
				return "redirect:/customers/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar el cliente.");
				return "redirect:/customers/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Customer> objCustomer = srvCustomer.findById(id);
		if (objCustomer == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar el cliente.");
			return "redirect:/customers/listar";
		}
		else {
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			model.addAttribute("customer", objCustomer);
			return "customer";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvCustomer.delete(id);
				model.put("listaCustomers", srvCustomer.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar el cliente.");
			model.put("listaCustomers", srvCustomer.findAll());
		}
		return "redirect:/customers/listar";
	}
}
