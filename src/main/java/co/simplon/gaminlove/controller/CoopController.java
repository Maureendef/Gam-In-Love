package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Coop;
import co.simplon.gaminlove.repository.CoopRepository;

/**
 * Le controller qui permet d'acceder au CRUD de la table Match
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path="/coop")
@CrossOrigin("*")
public class CoopController {

	@Autowired
	private CoopRepository matchRepository;
	
	/**
	 * Crée un nouveau match avec le type spécifié et l'enregistre en base.
	 * 
	 * @param action, émetteur, récepteur
	 * @return l'action est stockée en base (avec l'id auto-générée)
	 */
	@RequestMapping(path = "/add/{emetteur}/{recepteur}")
	public Coop addNew(@PathVariable Geek emetteur, @PathVariable Geek recepteur) {
		Coop newMatch = new Coop();
		newMatch.setEmetteur(emetteur);
		newMatch.setRecepteur(recepteur);
		return matchRepository.save(newMatch);		
	}
	
	/**
	 * Retourne tous les matchs de la base.
	 * 
	 * @return une liste d'action
	 */
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Coop> getAll(){
		return matchRepository.findAll();		
	}
	
	/**
	 * Retourne le match pour l'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del/{id}")
	public void delOne(@PathVariable int id) {
		Optional<Coop> optMatch = matchRepository.findById(id);
		if (optMatch.isPresent() ) {
			matchRepository.deleteById(id);
			System.out.println("Action supprimée");
		} else {
			System.out.println("Pas d'action à supprimer");
		}
	}
	
	
}
