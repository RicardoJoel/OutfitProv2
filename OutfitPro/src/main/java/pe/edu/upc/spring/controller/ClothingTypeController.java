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
import pe.edu.upc.spring.service.IClothingTypeService;

@Controller
@RequestMapping("/clothingType")
public class ClothingTypeController {

	@Autowired
	private IClothingTypeService srvClothingType;
	
	@RequestMapping("/bienvenido")
	public String irClothingTypeBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irClothingType(Map<String, Object> model) {
		model.put("listaClothingTypes", srvClothingType.findAll());
		return "listClothingType";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("clothingType", new ClothingType());
		return "clothingType";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid ClothingType objClothingType, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "clothingType";
		}
		else {
			boolean flag = srvClothingType.insert(objClothingType);
			if (flag) {
				return "redirect:/clothingType/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/clothingType/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid ClothingType objClothingType, BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/clothingType/listar";
		}
		else {
			boolean flag = srvClothingType.update(objClothingType);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/clothingType/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un roche");
				return "redirect:/clothingType/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<ClothingType> objClothingType = srvClothingType.findById(id);
		if (objClothingType == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un roche");
			return "redirect:/clothingType/listar";
		}
		else {
			model.addAttribute("clothingType", objClothingType);
			return "clothingType";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvClothingType.delete(id);
				model.put("listaClothingTypes", srvClothingType.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrió un roche");
			model.put("listaClothingTypes", srvClothingType.findAll());
		}
		return "listClothingType";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaClothingTypes", srvClothingType.findAll());
		return "listClothingType";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("clothingType", new ClothingType());
		return "searchClothingType";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute ClothingType clothingType) throws ParseException {
		List<ClothingType> listaClothingTypes;
		clothingType.setName(clothingType.getName());
		listaClothingTypes = srvClothingType.findByName(clothingType.getName());
		
		if (listaClothingTypes.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaClothingTypes", listaClothingTypes);
		return "searchClothingType";
	}
}
