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

import pe.edu.upc.spring.model.Size;
import pe.edu.upc.spring.service.ISizeService;

@Controller
@RequestMapping("/sizes")
public class SizeController {

	@Autowired
	private ISizeService srvSize;
	
	@RequestMapping("/")
	public String irSize(Map<String, Object> model) {
		model.put("size", new Size());
		model.put("listaSizes", srvSize.findAll());
		return "sizeList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("size", new Size());
		model.put("listaSizes", srvSize.findAll());
		return "sizeList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Size size) throws ParseException {
		List<Size> listaSizes = srvSize.findByName(size.getName());		
		if (listaSizes.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaSizes", listaSizes);
		return "sizeList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("size", new Size());
		return "size";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Size size, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "size";
		}
		else {
			boolean flag = srvSize.insert(size);
			if (flag) {
				return "redirect:/sizes/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar la talla.");
				return "redirect:/sizes/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Size> objSize = srvSize.findById(id);
		if (objSize == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar la talla.");
			return "redirect:/sizes/listar";
		}
		else {
			model.addAttribute("size", objSize);
			return "size";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvSize.delete(id);
				model.put("listaSizes", srvSize.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar la talla.");
			model.put("listaSizes", srvSize.findAll());
		}
		return "redirect:/sizes/listar";
	}
}
