package jana60.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jana60.model.Image;
import jana60.model.Pizza;
import jana60.repository.ImageRepository;
import jana60.repository.PizzaRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository repo;
	
	@Autowired
	private PizzaRepository pizzaRepo;
	
	public List<Image> getImagesByPizzaId(Integer pizzaId) {
		//ogli lista immag a partire dall id della pizza in questione
		Pizza pizza = pizzaRepo.findById(pizzaId).get();
		//dal repo im mi prendo la lista di immagini
		return repo.findByPizza(pizza);
		
	}
}
