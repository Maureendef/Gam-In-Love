package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.repository.GeekRepository;

/**
 * Le controller qui permet d'acceder au CRUD de la table Geek
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path = "/geek")
@CrossOrigin("*")
public class GeekController {
	
	// permet d'initialiser le repo, par le mécanisme d'injection de dépendance
	// (IOC)
	@Autowired
	private GeekRepository geekRepository;

	/**
	 * Crée un nouveau geek avec le nom spécifié et l'enregistre en base.
	 * 
	 * @param geek récupère un objet Json du front
	 * @return le geek stocké en base (avec l'id à jour si généré)
	 */
	@PostMapping(path="/")
	public ResponseEntity<Geek> addNew(@RequestBody Geek geek) {
		geekRepository.save(geek);
		return ResponseEntity.ok(geek);
	}

	/**
	 * Retourne tous les geek de la base.
	 * 
	 * @return une liste de geek
	 */
	@GetMapping(path = "/")
	public @ResponseBody Iterable<Geek> getAll() {
		return geekRepository.findAll();
	}

	/**
	 * Retourne le geek d'id spécifié.
	 * 
	 * @param id du geek à retourner.
	 * @return le statut de la requête.
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<Geek> getOne(@PathVariable int id) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		return optGeek.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
// 		Equivalent à :
//		if (optGeek.isPresent()) {
//			return ResponseEntity.ok(optGeek.get());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
	}

	/**
	 * Retourne le geek d'id spécifié et le met à jour.
	 * 
	 * @param id du geek à modifier et un objet geek contenant les informations à mettre à jour.
	 * @return le statut de la requête
	 */
	@PatchMapping(path = "{id}")
	public ResponseEntity<Geek> updateOne(@PathVariable int id, @RequestBody Geek geek) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			geekRepository.save(geek);
			return ResponseEntity.ok(geek);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Supprime le geek d'id spécifié.
	 * 
	 * @param id du geek à supprimer
	 * @return
	 */
	@DeleteMapping("/{id}")
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
