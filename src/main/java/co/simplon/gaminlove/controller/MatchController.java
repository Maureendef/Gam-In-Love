package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Match;
import co.simplon.gaminlove.repository.MatchRepository;

@RestController
@RequestMapping(path="/match")
@CrossOrigin("*")
public class MatchController {

	@Autowired
	private MatchRepository matchRepository;
	
	@RequestMapping(path = "/add/{geekEmetteur}/{geekRecepteur}")
	public Match addNew(@PathVariable Geek geekEmetteur, @PathVariable Geek geekRecepteur) {
		Match newMatch = new Match();
		newMatch.setGeekEmetteur(geekEmetteur);
		newMatch.setGeekRecepteur(geekRecepteur);
		return matchRepository.save(newMatch);		
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Match> getAll(){
		return matchRepository.findAll();		
	}
		
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
