package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Pizza;
import jana60.repository.PizzaRepository;

@Controller
@RequestMapping("/")
public class PizzaController {
	
	@Autowired
	private PizzaRepository repo;
	
	
	@GetMapping("/pizza") 
	public String listaPizze(Model model){
		List<Pizza> lPizza = (List<Pizza>)repo.findAll();
		model.addAttribute("listaPizze", lPizza);
		return "pizza";
	}
	
	@GetMapping("/add")
	//creo metodo per funzionamento della form, bottone
	public String creaPizza(Model model) {
		model.addAttribute("nuovaPizza", new Pizza());
		return "crea";
	}
	
}
