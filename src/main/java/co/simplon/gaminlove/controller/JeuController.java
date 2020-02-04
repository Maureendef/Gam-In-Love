package co.simplon.gaminlove.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import co.simplon.gaminlove.model.Jeu;
import co.simplon.gaminlove.repository.GeekRepository;
import co.simplon.gaminlove.repository.JeuRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Le controller qui gère les endpoint de l'entité Jeu
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */

@RestController
@RequestMapping(path = "/jeu")
@Api(tags = "API pour les opérations CRUD sur les Jeux.")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
		@ApiResponse(code = 400, message = "Mauvaise Requête"),
		@ApiResponse(code = 401, message = "Echec Authentification"),
		@ApiResponse(code = 403, message = "Accès Refusé"), @ApiResponse(code = 500, message = "Problème Serveur") })
@CrossOrigin("*")
public class JeuController {

	// permet d'initialiser le repo, par le mécanisme d'injection de dépendance
	@Autowired
	private JeuRepository jeuRepository;

	@Autowired
	private GeekRepository geekRepository;

	/**
	 * Création d'un nouveau jeu.
	 * 
	 * @param jeu un objet event sous forme Json
	 * @return le jeu crée (avec id auto-généré)
	 */

	@PostMapping("/")
	@ApiOperation(value = "Création d'un nouveau jeu.")
	public ResponseEntity<Jeu> addNew(@RequestBody Jeu jeu) {
		jeuRepository.save(jeu);
		return ResponseEntity.ok(jeu);
	}

	/**
	 * Ajoute un jeu dans la collection du geek sélectionné.
	 * 
	 * @param idJeu du jeu et idGeek du geek à lier.
	 * @return le Geek avec sa collection à jour si généré.
	 */

	@PostMapping(path = "/{idJeu}/{idGeek}")
	@ApiOperation(value = "Ajoute un jeu dans la collection du geek sélectionné.")
	public ResponseEntity<Geek> addGame(@PathVariable int idJeu, @PathVariable int idGeek) {
		Optional<Geek> optGeek = geekRepository.findById(idGeek);
		Optional<Jeu> optJeu = jeuRepository.findById(idJeu);
		if (optGeek.isPresent() && optJeu.isPresent()) {
			optGeek.get().getJeux().add(optJeu.get());
			geekRepository.save(optGeek.get());
			return ResponseEntity.ok(optGeek.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Affiche tous les jeux.
	 * 
	 * @return une liste de jeux
	 */

	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne tous les jeux.")
	public @ResponseBody Iterable<Jeu> getAll() {
		return jeuRepository.findAll();
	}

	/**
	 * Cherche un jeu selon l'id.
	 * 
	 * @param id du jeu
	 * @return un event s'il existe ou une erreur
	 */

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Cherche un jeu selon l'id.")
	public ResponseEntity<Jeu> getOne(@PathVariable int id) {
		Optional<Jeu> optJeu = jeuRepository.findById(id);
		return optJeu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Retourne le(s) jeu(x) selon le nom spécifié.
	 * 
	 * @param nom du jeu
	 * @return un ou plusieurs jeu(x) s'il(s) existe(nt).
	 */

	@GetMapping(path = "/nom")
	@ApiOperation(value = "Retourne les jeux selon le nom spécifié.")
	public ResponseEntity<Collection<Jeu>> getName(@RequestBody String nom) {
		Collection<Jeu> optJeu = jeuRepository.findAllByNom(nom);
		if (!optJeu.isEmpty()) {
			return ResponseEntity.ok(optJeu);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Retourne le jeu selon le nom spécifié et le met à jour.
	 * 
	 * @param id du jeu
	 * @return jeu à jour
	 */

	@PatchMapping(path = "/{id}")
	@ApiOperation(value = "Retourne le jeu selon le nom spécifié et le met à jour.")
	public ResponseEntity<Jeu> updateOne(@PathVariable int id, @RequestBody Jeu jeuInput) {
		Optional<Jeu> optJeu = jeuRepository.findById(id);
		if (optJeu.isPresent()) {
			Jeu jeu = optJeu.get();
			if (jeuInput.getNom() != null) {
				jeu.setNom(jeuInput.getNom());
			}
			jeuRepository.save(jeu);
			return ResponseEntity.ok(jeu);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Supprime le jeu d'id spécifié de la collection du geek.
	 * 
	 * @param idJeu du jeu et idGeek du Geek
	 * @return code la requête (200 => OK)
	 */

	@DeleteMapping("/{idJeu}/{idGeek}")
	@ApiOperation(value = "Supprime le jeu d'id spécifié.")
	public HttpStatus delOne(@PathVariable int idGeek, @PathVariable int idJeu) {
		Optional<Geek> optGeek = geekRepository.findById(idGeek);
		Optional<Jeu> optJeu = jeuRepository.findById(idJeu);
		if (optGeek.isPresent() && optJeu.isPresent()) {
			optGeek.get().getJeux().remove(optJeu.get());
			geekRepository.save(optGeek.get());
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

	/**
	 * Supprime le jeu d'id spécifié.
	 * 
	 * @param id du jeu
	 * @return code la requête (200 => OK)
	 */

	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Ajoute un jeu dans la collection du geek sélectionné.")
	public HttpStatus addNew(@PathVariable int id) {
		Optional<Jeu> optJeu = jeuRepository.findById(id);
		if (optJeu.isPresent()) {
			jeuRepository.deleteById(id);
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}
}
