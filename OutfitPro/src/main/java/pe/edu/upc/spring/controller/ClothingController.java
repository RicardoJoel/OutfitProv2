package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.model.Clothing;
import pe.edu.upc.spring.model.ClothingType;
import pe.edu.upc.spring.model.Color;
import pe.edu.upc.spring.model.Commerce;
import pe.edu.upc.spring.model.Discount;
import pe.edu.upc.spring.model.Mark;
import pe.edu.upc.spring.model.Size;
import pe.edu.upc.spring.service.IClothingService;
import pe.edu.upc.spring.service.IClothingTypeService;
import pe.edu.upc.spring.service.IColorService;
import pe.edu.upc.spring.service.ICommerceService;
import pe.edu.upc.spring.service.IDiscountService;
import pe.edu.upc.spring.service.IMarkService;
import pe.edu.upc.spring.service.ISizeService;

@Controller
@RequestMapping("/clothings")
public class ClothingController {

	@Autowired
	private IClothingService srvClothing;
	@Autowired
	private ISizeService srvSize;
	@Autowired
	private IColorService srvColor;
	@Autowired
	private IMarkService srvMark;
	@Autowired
	private IDiscountService srvDiscount;
	@Autowired
	private ICommerceService srvCommerce;
	@Autowired
	private IClothingTypeService srvClothingType;
	
	@RequestMapping("/")
	public String irClothing(Map<String, Object> model) {
		model.put("clothing", new Clothing());
		model.put("listaClothings", srvClothing.findAll());
		return "clothingList";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("clothing", new Clothing());
		model.put("listaClothings", srvClothing.findAll());
		return "clothingList";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Clothing clothing) throws ParseException {
		List<Clothing> listaClothings = srvClothing.findByName(clothing.getName());		
		if (listaClothings.isEmpty()) {
			model.put("mensaje", "No se encontraron registros.");
		}
		model.put("listaClothings", listaClothings);
		return "clothingList";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaSizes", srvSize.findAll());
		model.addAttribute("listaColors", srvColor.findAll());
		model.addAttribute("listaMarks", srvMark.findAll());
		model.addAttribute("listaDiscounts", srvDiscount.findAll());
		model.addAttribute("listaCommerces", srvCommerce.findAll());
		model.addAttribute("listaClothingTypes", srvClothingType.findAll());
		model.addAttribute("size", new Size());
		model.addAttribute("color", new Color());
		model.addAttribute("mark", new Mark());
		model.addAttribute("discount", new Discount());
		model.addAttribute("commerce", new Commerce());
		model.addAttribute("clothingType", new ClothingType());
		model.addAttribute("clothing", new Clothing());
		return "clothing";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Clothing clothing, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaSizes", srvSize.findAll());
			model.addAttribute("listaColors", srvColor.findAll());
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaDiscounts", srvDiscount.findAll());
			model.addAttribute("listaCommerces", srvCommerce.findAll());
			model.addAttribute("listaClothingTypes", srvClothingType.findAll());
			return "clothing";
		}
		else {
			if (clothing.getDiscount().getId() == 0)
				clothing.setDiscount(null);
			boolean flag = srvClothing.insert(clothing);
			if (flag) {
				return "redirect:/clothings/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrió un error mientras se intentaba guardar la prenda.");
				return "redirect:/clothings/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Clothing> objClothing = srvClothing.findById(id);
		if (objClothing == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error mientras se intentaba recuperar la prenda.");
			return "redirect:/clothings/listar";
		}
		else {
			model.addAttribute("listaSizes", srvSize.findAll());
			model.addAttribute("listaColors", srvColor.findAll());
			model.addAttribute("listaMarks", srvMark.findAll());
			model.addAttribute("listaDiscounts", srvDiscount.findAll());
			model.addAttribute("listaCommerces", srvCommerce.findAll());
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
			model.put("mensaje", "Ocurrió un error mientras se intentaba eliminar la prenda.");
			model.put("listaClothings", srvClothing.findAll());
		}
		return "redirect:/clothings/listar";
	}
	
	@RequestMapping("/sizesByClothingType") @ResponseBody
	public List<Size> sizesByClothingType(@RequestParam(value="clothingTypeId", required=true) int clothingTypeId) 
			throws ParseException {
		List<Size> listaSizes = new ArrayList<Size>();
		Optional<ClothingType> clothingType = srvClothingType.findById(clothingTypeId);
		if (clothingType != null)
			listaSizes = srvSize.findByClothingType(clothingType.get());
		return listaSizes;
	}
}