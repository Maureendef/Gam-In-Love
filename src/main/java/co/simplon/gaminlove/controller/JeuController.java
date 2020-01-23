package co.simplon.gaminlove.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Jeu;
import co.simplon.gaminlove.repository.JeuRepository;

/**
 * Le controller qui permet d'acceder au CRUD de la table Jeu
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path = "/jeu")
@CrossOrigin("*")
public class JeuController {
	@Autowired
	private JeuRepository jeuRepository;

	/**
	 * Crée un nouveau jeu avec le nom spécifié et l'enregistre en base.
	 * 
	 * @param nom & rang
	 * @return le jeu stocké en base (avec l'id à jour si généré)
	 */
	@RequestMapping(path = "/add/{nom}/{rang}")
	public Jeu addNew(@PathVariable String nom, @PathVariable String rang) {
		Jeu newJeu = new Jeu();
		newJeu.setNom(nom);
		newJeu.setRang(rang);
		return jeuRepository.save(newJeu);
	}

	/**
	 * Retourne tous les jeux
	 * 
	 * @return une liste de jeux
	 */
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Jeu> getAll() {
		return jeuRepository.findAll();
	}

	/**
	 * Retourne les jeux selon le nom spécifié.
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping(path = "/get/{nom}")
	public Collection<Jeu> getName(@PathVariable String nom) {
		Collection<Jeu> optJeu = jeuRepository.findAllByNom(nom);
		return optJeu;

	}

	/**
	 * Retourne le jeu selon le nom spécifié et le met à jour.
	 * 
	 * @param nom et rang
	 * @return jeu à jour
	 */
	@GetMapping(path = "/update/{id}/{nom}/{rang}")
	public Jeu updateOne(@PathVariable int id, @PathVariable String nom, @PathVariable String rang) {
		Optional<Jeu> optJeu = jeuRepository.findById(id);
		Jeu updateJeu = jeuRepository.findById(id).get();
		if (optJeu.isPresent()) {
			updateJeu.setNom(nom);
			updateJeu.setRang(rang);
			return jeuRepository.save(updateJeu);
		} else {
			return updateJeu;
		}
	}

	/**
	 * Supprime le jeu d'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del/{id}")
	public void delOne(@PathVariable int id) {
		Optional<Jeu> optJeu = jeuRepository.findById(id);
		if (optJeu.isPresent()) {
			jeuRepository.deleteById(id);
			System.out.println("Jeu supprimé");
		} else {
			System.out.println("Pas de jeu à supprimer");
		}
	}

}
