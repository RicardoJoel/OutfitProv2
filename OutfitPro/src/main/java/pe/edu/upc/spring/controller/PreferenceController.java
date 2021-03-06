package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Preference;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.service.IPreferenceService;
import pe.edu.upc.spring.service.IClothingTypeService;
import pe.edu.upc.spring.service.ICustomerService;
import pe.edu.upc.spring.service.IMarkService;

@Controller
@RequestMapping("/preferences")
public class PreferenceController {

	@Autowired
	private IPreferenceService srvPreference;
	@Autowired
	private IMarkService srvMark;
	@Autowired
	private ICustomerService srvCustomer;
	@Autowired
	private IClothingTypeService srvClothingType;
	
	@RequestMapping("/")
	public String irPreference(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = srvCustomer.findByUsername(auth.getName());
		model.put("listaPreferences", srvPreference.findByCustomer(customer));
		model.put("preference", new Preference());
		return "preferenceList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = srvCustomer.findByUsername(auth.getName());
		model.put("listaPreferences", srvPreference.findByCustomer(customer));
		model.put("preference", new Preference());
		return "preferenceList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Preference preference) throws ParseException {
		List<Preference> listaPreferences;
		listaPreferences = srvPreference.findByName(preference.getClothingType().getName());		
		if (listaPreferences.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaPreferences", listaPreferences);
		return "preferenceList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaMarks", srvMark.findAll());
		model.addAttribute("listaClothingTypes", srvClothingType.findAll());
		model.addAttribute("mark", new Mark());
		model.addAttribute("clothingType", new ClothingType());
		model.addAttribute("preference", new Preference());
		return "preference";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Preference preference, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			return "preference";
		}
		else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Customer customer = srvCustomer.findByUsername(auth.getName());
			preference.setCustomer(customer);
			if (srvPreference.insert(preference)) {
				return "redirect:/preferences/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar la preferencia.");
				return "redirect:/preferences/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Preference> objPreference = srvPreference.findById(id);
		if (objPreference == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar la preferencia.");
			return "redirect:/preferences/listar";
		}
		else {
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			model.addAttribute("preference", objPreference);
			return "preference";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvPreference.delete(id);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Customer customer = srvCustomer.findByUsername(auth.getName());
				model.put("listaPreferences", srvPreference.findByCustomer(customer));
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar la preferencia.");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Customer customer = srvCustomer.findByUsername(auth.getName());
			model.put("listaPreferences", srvPreference.findByCustomer(customer));
		}
		return "redirect:/preferences/listar";
	}
}
