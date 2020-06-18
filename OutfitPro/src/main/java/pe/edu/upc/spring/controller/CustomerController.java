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

import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService srvCustomer;
	
	@RequestMapping("/bienvenido")
	public String irCustomerBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irCustomer(Map<String, Object> model) {
		model.put("listaCustomers", srvCustomer.findAll());
		return "listCustomer";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Customer objCustomer, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "customer";
		}
		else {
			boolean flag = srvCustomer.insert(objCustomer);
			if (flag) {
				return "redirect:/customer/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/customer/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Customer objCustomer, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/customer/listar";
		}
		else {
			boolean flag = srvCustomer.update(objCustomer);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/customer/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/customer/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Customer> objCustomer = srvCustomer.findById(id);
		if (objCustomer == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un roche");
			return "redirect:/customer/listar";
		}
		else {
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
			model.put("mensaje","Ocurrió un roche");
			model.put("listaCustomers", srvCustomer.findAll());
		}
		return "listCustomer";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCustomers", srvCustomer.findAll());
		return "listCustomer";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("customer", new Customer());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Customer customer) throws ParseException {
		List<Customer> listaCustomers;
		customer.setName(customer.getName());
		listaCustomers = srvCustomer.findByName(customer.getName());
		
		if (listaCustomers.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaCustomers", listaCustomers);
		return "buscar";
	}
}
