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

import pe.edu.upc.spring.model.Discount;
import pe.edu.upc.spring.service.IDiscountService;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

	@Autowired
	private IDiscountService srvDiscount;
	
	@RequestMapping("/")
	public String irDiscount(Map<String, Object> model) {
		model.put("discount", new Discount());
		model.put("listaDiscounts", srvDiscount.findAll());
		return "discountList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("discount", new Discount());
		model.put("listaDiscounts", srvDiscount.findAll());
		return "discountList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Discount discount) throws ParseException {
		List<Discount> listaDiscounts;
		listaDiscounts = srvDiscount.findByName(discount.getName());		
		if (listaDiscounts.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaDiscounts", listaDiscounts);
		return "discountList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("discount", new Discount());
		return "discount";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Discount discount, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "discount";
		}
		else {
			boolean flag = srvDiscount.insert(discount);
			if (flag) {
				return "redirect:/discounts/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar el descuento.");
				return "redirect:/discounts/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Discount> objDiscount = srvDiscount.findById(id);
		if (objDiscount == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar el descuento.");
			return "redirect:/discounts/listar";
		}
		else {
			model.addAttribute("discount", objDiscount);
			return "discount";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvDiscount.delete(id);
				model.put("listaDiscounts", srvDiscount.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar el descuento.");
			model.put("listaDiscounts", srvDiscount.findAll());
		}
		return "redirect:/discounts/listar";
	}
}
