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
import co.simplon.gaminlove.model.Match;
import co.simplon.gaminlove.repository.MatchRepository;

/**
 * Le controller qui permet d'acceder au CRUD de la table Match
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path="/match")
@CrossOrigin("*")
public class MatchController {

	@Autowired
	private MatchRepository matchRepository;
	
	/**
	 * Crée un nouveau match avec le type spécifié et l'enregistre en base.
	 * 
	 * @param action, émetteur, récepteur
	 * @return l'action est stockée en base (avec l'id auto-générée)
	 */
	@RequestMapping(path = "/add/{emetteur}/{recepteur}")
	public Match addNew(@PathVariable Geek emetteur, @PathVariable Geek recepteur) {
		Match newMatch = new Match();
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
	public @ResponseBody Iterable<Match> getAll(){
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
		Optional<Match> optMatch = matchRepository.findById(id);
		if (optMatch.isPresent() ) {
			matchRepository.deleteById(id);
			System.out.println("Action supprimée");
		} else {
			System.out.println("Pas d'action à supprimer");
		}
	}
	
	
}
