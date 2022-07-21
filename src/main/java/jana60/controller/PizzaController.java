package jana60.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	//crea nuovo ogetto pizza vuoto da aggiungere nella tramite la form
	@GetMapping("/add")
	//creo metodo per funzionamento della form, bottone
	public String creaPizza(Model model) {
		model.addAttribute("nuovaPizza", new Pizza());
		return "crea";
	}
	
	  @PostMapping("/add")
	  public String salva(@Valid @ModelAttribute("creaPizza") Pizza formPizza, BindingResult br) {
	    // testo se ci sono errori di validazione
	    if (br.hasErrors()) {
	      // se ci sono errori non salvo il book su database ma ritorno alla form precaricata
	      return "crea";
	    } else {
	      // se non ci sono errori salvo il book che arriva dalla form
	      repo.save(formPizza);
	      return "redirect:/"; // non cercare un template, ma fai la HTTP redirect a quel path
	    }
	  }
}
