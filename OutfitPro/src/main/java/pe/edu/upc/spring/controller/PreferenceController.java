package pe.edu.upc.spring.controller;

import java.text.ParseException;
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
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.model.Preference;
import pe.edu.upc.spring.service.IMarkService;
import pe.edu.upc.spring.service.IPreferenceService;
import pe.edu.upc.spring.service.IClothingTypeService;
import pe.edu.upc.spring.service.ICustomerService;

@Controller
@RequestMapping("/preference")
public class PreferenceController {

	@Autowired
	private IPreferenceService srvPreference;
	@Autowired
	private IMarkService srvMark;
	@Autowired
	private ICustomerService srvCustomer;
	@Autowired
	private IClothingTypeService srvClothingType;
	
	@RequestMapping("/bienvenido")
	public String irPreferenceBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irPreference(Map<String, Object> model) {
		model.put("listaPreferences", srvPreference.findAll());
		return "listPreference";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaMarks", srvMark.findAll());
		model.addAttribute("listaCustomers", srvCustomer.findAll());
		model.addAttribute("listaClothingTypes", srvClothingType.findAll());
		model.addAttribute("preference", new Preference());
		model.addAttribute("mark", new Mark());
		model.addAttribute("customer", new Customer());
		model.addAttribute("clothingType", new ClothingType());
		return "preference";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Preference objPreference, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaCustomers", srvCustomer.findAll());
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			return "preference";
		}
		else {
			boolean flag = srvPreference.insert(objPreference);
			if (flag) {
				return "redirect:/preference/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/preference/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Preference objPreference, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/preference/listar";
		}
		else {
			boolean flag = srvPreference.update(objPreference);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/preference/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/preference/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Preference> objPreference = srvPreference.findById(id);
		if (objPreference == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un roche");
			return "redirect:/preference/listar";
		}
		else {
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaCustomers", srvCustomer.findAll());
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			if (objPreference.isPresent())
				objPreference.ifPresent(o -> model.addAttribute("preference", o));
			return "preference";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvPreference.delete(id);
				model.put("listaPreferences", srvPreference.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrió un roche");
			model.put("listaPreferences", srvPreference.findAll());
		}
		return "listPreference";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPreferences", srvPreference.findAll());
		return "listPreference";
	}

}
