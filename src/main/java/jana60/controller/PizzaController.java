package jana60.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Ingrediente;
import jana60.model.Pizza;
import jana60.repository.IngredienteRepository;
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
		//x aggiungere ingredienti nella form crea nuova pizza
		model.addAttribute("Ilist", new Ingrediente()); 
		return "crea";
	}
	
	/*
	 * con questo controllo il tasto crea, crea e salva effettivamente la pizza, uso quindi
	 * @modelattrib nuovaPizza che è l'attributo che ho passato ad html pizza per creare la lista di pizze
	 * Pizza che è il model Pizza che ha gli attributi ancora vuoti 
	 * @valid fa in modo che se l utente inserisce i campi in modo errato(@notEmpty nel model per esempio) non si spacchi il programma ma gestisce l errore.
	 *binddingresult che è la mappa in cui finiscono gli eventuali errori nell'inserimento dei dati, li legge con i suoi metodi e ritorna un true o false
	 *se non sisono errori la nuova pizza viene salvata nel formPizza, tramite metodo del repository
	 * 
	 * redirect/ fa vedere il libro nel controller /pizza cioe nell html dove si vede la lista di pizze
	 */
	  @PostMapping("/add")
	  public String salva(@Valid @ModelAttribute("nuovaPizza") Pizza formPizza, BindingResult br) {
	    // testo se ci sono errori di validazione
	    if (br.hasErrors()) {
	      // se ci sono errori non salvo la pizza su database ma ritorno alla form precaricata
	      return "crea";
	    } else {
	      // se non ci sono errori salvo la pizza che arriva dalla form
	      repo.save(formPizza);
	       // non cercare un template, ma fai la HTTP redirect a quel path
	    }return "redirect:/pizza";
	  }
	  
	  
	  //aggiungo metodo cancella pizza 
	  /*@pathvariabile si riferisce al path della rishiesta 8080/path/variab
	   * */
	  @GetMapping("/delete/{id}")
	  public String delete(@PathVariable("id") Integer pizzaId) {
		  repo.deleteById(pizzaId);
		  return "redirect:/pizza";
		  
	  }
	  
	  //creo form per modificare pizza esistente
	  @GetMapping("/edit/{id}")
	  public String edit(@PathVariable("id") Integer pizzaId, Model model) {
  	    Optional<Pizza> result = repo.findById(pizzaId);
  	    // controllo se il Book con quell'id è presente
  	    if (result.isPresent()) {
  	      // preparo il template con al form passandogli il book trovato su db

  	      model.addAttribute("pizza", result.get());
  	      return "/edit";
  	    }else{
  	      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
    	          "Pizza con id " + pizzaId + " not present");
    	    }
	  }
	  
	  
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
}  
	  
