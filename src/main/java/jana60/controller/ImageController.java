package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Image;
import jana60.service.ImageService;

@Controller 
@RequestMapping("/image")
public class ImageController {
			//dovrà gestire la lista di immagini e aggiungerne di nuove

	@Autowired
	private ImageService service;
	
	@GetMapping("/{pizzaId}")
	public String pizzaImages(@PathVariable("pizzaId") Integer pizzaId) {
		List<Image> images = service.getImagesByPizzaId(pizzaId);
		
		return"/imagList";
	}

}
