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

import jana60.model.Ingrediente;
import jana60.model.Pizza;
import jana60.repository.IngredienteRepository;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
	
	@Autowired
	private IngredienteRepository repo;
	
	
	//creo nuovo ingr
	@GetMapping
	public String ingredientiPizza(Model model) {
		// ingredienti è preso dalla lista che sta nel model di pizza
		model.addAttribute("ingredienti", repo.findAll());
		//per la form di creazione instanzio new Ingrediente
		model.addAttribute("newIng", new Ingrediente());
		return"/ingredienti";
	}

	
	//riceve ogetto Ingrediente e lo salvo
	@PostMapping("/edit")
	public String salvaIng(@Valid @ModelAttribute("newIng") Ingrediente formIngrediente, BindingResult br, Model model) {
		 if (br.hasErrors()) {
		      // ricarico la pagina
		      model.addAttribute("ingredienti", repo.findAll());
		      return "ingredienti";

		    } else {
		      // salvo la category
		      repo.save(formIngrediente);
		      return "redirect:/ingredienti";
		    }

		  }
	
}
