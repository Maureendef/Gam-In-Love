package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Photo;
import co.simplon.gaminlove.repository.GeekRepository;

/**
 * Le controller qui permet d'acceder au CRUD de la table Geek
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path = "/geek")
public class GeekController {
	
	// permet d'initialiser le repo, par le mécanisme d'injection de dépendance
	// (IOC)
	@Autowired
	private GeekRepository geekRepository;

	/**
	 * Crée un nouveau geek avec le nom spécifié et l'enregistre en base.
	 * 
	 * @param nom
	 * @return le geek stocké en base (avec l'id à jour si généré)
	 */
	@RequestMapping(path = "/add/{age}/{pseudo}/{lieu}/{sexe}/{compte}/{email}")
	public Geek addNew(@PathVariable int age, @PathVariable String pseudo, @PathVariable String lieu, @PathVariable String sexe, @PathVariable String compte, @PathVariable String email) {
		Geek newGeek = new Geek();
		newGeek.setAge(age);
		newGeek.setPseudo(pseudo);
		newGeek.setLieu(lieu);
		newGeek.setSexe(sexe);
		newGeek.setCompte(compte);
		newGeek.setEmail(email);
		return geekRepository.save(newGeek);
	}

	/**
	 * Retourne tous les geek de la base.
	 * 
	 * @return une liste de geek
	 */
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Geek> getAll() {
		return geekRepository.findAll();
	}

	/**
	 * Retourne le geek d'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Geek> getOne(@PathVariable int id) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			return ResponseEntity.ok(optGeek.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Retourne le geek d'id spécifié et le met à jour.
	 * 
	 * @param id, age, lieu, compte
	 * @return geek à jour
	 */
	@GetMapping(path = "/update/{id}/{age}/{lieu}/{compte}")
	public Geek updateOne(@PathVariable int id, @PathVariable int age, @PathVariable String lieu, @PathVariable String compte) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		Geek updateGeek = geekRepository.findById(id).get();
		if (optGeek.isPresent()) {
			updateGeek.setAge(age);
			updateGeek.setLieu(lieu);
			updateGeek.setCompte(compte);
			return geekRepository.save(updateGeek);
		} else {
			return updateGeek;
		}
	}

	/**
	 * Supprime le geek d'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del/{id}")
	public void delOne(@PathVariable int id) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			geekRepository.deleteById(id);
			System.out.println("Geek supprimé");
		} else {
			System.out.println("Pas de geek à supprimer");
		}
	}
}
