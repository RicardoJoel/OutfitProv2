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

import pe.edu.upc.spring.model.Color;
import pe.edu.upc.spring.service.IColorService;

@Controller
@RequestMapping("/colors")
public class ColorController {

	@Autowired
	private IColorService srvColor;
	
	@RequestMapping("/")
	public String irColor(Map<String, Object> model) {
		model.put("color", new Color());
		model.put("listaColors", srvColor.findAll());
		return "colorList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("color", new Color());
		model.put("listaColors", srvColor.findAll());
		return "colorList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Color color) throws ParseException {
		List<Color> listaColors;
		listaColors = srvColor.findByName(color.getName());		
		if (listaColors.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaColors", listaColors);
		return "colorList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("color", new Color());
		return "color";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Color color, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "color";
		}
		else {
			boolean flag = srvColor.insert(color);
			if (flag) {
				return "redirect:/colors/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar el color.");
				return "redirect:/colors/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Color> objColor = srvColor.findById(id);
		if (objColor == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar el color.");
			return "redirect:/colors/listar";
		}
		else {
			model.addAttribute("color", objColor);
			return "color";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				srvColor.delete(id);
				model.put("listaColors", srvColor.findAll());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar el color.");
			model.put("listaColors", srvColor.findAll());
		}
		return "redirect:/colors/listar";
	}
}
