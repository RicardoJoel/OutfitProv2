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

import pe.edu.upc.spring.model.Clothing;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Commerce;
import pe.edu.upc.spring.model.Discount;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.service.IClothingService;
import pe.edu.upc.spring.service.IClothingTypeService;
import pe.edu.upc.spring.service.ICommerceService;
import pe.edu.upc.spring.service.IDiscountService;
import pe.edu.upc.spring.service.IMarkService;

@Controller
@RequestMapping("/clothing")
public class ClothingController {

	@Autowired
	private IClothingService srvClothing;
	@Autowired
	private IMarkService srvMark;
	@Autowired
	private ICommerceService srvCommerce;
	@Autowired
	private IDiscountService srvDiscount;
	@Autowired
	private IClothingTypeService srvClothingType;
	
	@RequestMapping("/bienvenido")
	public String irClothingBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irClothing(Map<String, Object> model) {
		model.put("listaClothings", srvClothing.findAll());
		return "listClothing";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaMarks", srvMark.findAll());
		model.addAttribute("listaCommerces", srvCommerce.findAll());
		model.addAttribute("listaDiscounts", srvDiscount.findAll());
		model.addAttribute("listaClothingTypes", srvClothingType.findAll());
		model.addAttribute("mark", new Mark());
		model.addAttribute("commerce", new Commerce());
		model.addAttribute("discount", new Discount());
		model.addAttribute("clothingType", new ClothingType());
		model.addAttribute("clothing", new Clothing());
		return "clothing";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Clothing objClothing, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaCommerces", srvCommerce.findAll());
			model.addAttribute("listaDiscounts", srvDiscount.findAll());
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			return "clothing";
		}
		else {
			boolean flag = srvClothing.insert(objClothing);
			if (flag) {
				return "redirect:/clothing/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/clothing/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Clothing objClothing, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/clothing/listar";
		}
		else {
			boolean flag = srvClothing.update(objClothing);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/clothing/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/clothing/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Clothing> objClothing = srvClothing.findById(id);
		if (objClothing == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un roche");
			return "redirect:/clothing/listar";
		}
		else {
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaCommerces", srvCommerce.findAll());
			model.addAttribute("listaDiscounts", srvDiscount.findAll());
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			model.addAttribute("clothing", objClothing);
			return "clothing";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvClothing.delete(id);
				model.put("listaClothings", srvClothing.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrió un roche");
			model.put("listaClothings", srvClothing.findAll());
		}
		return "listClothing";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaClothings", srvClothing.findAll());
		return "listClothing";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("clothing", new Clothing());
		return "searchClothing";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Clothing clothing) throws ParseException {
		List<Clothing> listaClothings;
		clothing.setName(clothing.getName());
		listaClothings = srvClothing.findByName(clothing.getName());
		
		if (listaClothings.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaClothings", listaClothings);
		return "searchClothing";
	}
}
