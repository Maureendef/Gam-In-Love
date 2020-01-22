package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.MP;
import co.simplon.gaminlove.repository.MPRepository;

@RestController
@RequestMapping(path = "/mp")
public class MPController {
	
	private MPRepository mpRepository;
	/**
	 * Crée un MP et l'enregistre en base.
	 * 
	 * @param geekEmetteur & geekRecepteur
	 * @return le MP stocké en base (avec l'id à jour si généré)
	 */
	@RequestMapping(path = "/add/{geekEmetteur}/{geekRecepteur}")
	public MP addNew(@PathVariable Geek geekEmetteur, @PathVariable Geek geekRecepteur) {
		MP newMP = new MP();
		newMP.setGeekEmetteur(geekEmetteur);
		newMP.setGeekRecepteur(geekRecepteur);


		return mpRepository.save(newMP);
	}	
	/**
	 * Retourne tous Mp de la base
	 * 
	 * @return une liste de MP
	 */
	@GetMapping(path = "/allmp")
	public @ResponseBody Iterable<MP> getAll() {
		return mpRepository.findAll();
	}
	/**
	 * Retourne le mp d'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<MP> getOne(@PathVariable int id) {
		Optional<MP> optMP = mpRepository.findById(id);
		if (optMP.isPresent()) {
			return ResponseEntity.ok(optMP.get());
		} else {
			return ResponseEntity.notFound().build();
		}s
	}
	@RequestMapping("/del/{id}")
	public void delOne(@PathVariable int id) {
		Optional<MP> optMP = mpRepository.findById(id);
		if (optMP.isPresent()) {
			mpRepository.deleteById(id);
			System.out.println("Message supprimé");
		} else {
			System.out.println("Pas de message à supprimer");
		}
	}
	
}
