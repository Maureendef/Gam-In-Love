package co.simplon.gaminlove.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.repository.GeekRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * Le controller qui gère les endpoint de l'entité Geek
 *
 * @author Maureen, Nicolas, Virgile
 */

@RestController
@RequestMapping(path = "/geek")
@Api(tags = "API pour les opérations CRUD sur les Geeks.")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
		@ApiResponse(code = 400, message = "Mauvaise Requête"),
		@ApiResponse(code = 401, message = "Echec Authentification"),
		@ApiResponse(code = 403, message = "Accès Refusé"), @ApiResponse(code = 500, message = "Problème Serveur") })
@CrossOrigin("*")
public class GeekController {

	// permet d'initialiser le repo, par le mécanisme d'injection de dépendance
	// (IOC)
	private final GeekRepository geekRepository;

	public GeekController(GeekRepository geekRepository) {
		this.geekRepository = geekRepository;
	}

	/**
	 * Crée un nouveau geek avec le nom spécifié et l'enregistre en base.
	 *
	 * @param geek un objet geek sous forme Json
	 * @return le Geek crée (avec l'id à jour si généré)
	 */

	@PostMapping(path = "/")
	@ApiOperation(value = "Crée un nouveau geek avec le nom spécifié.")
	public HttpStatus addNew(@RequestBody Geek geek) {
		Optional<Geek> optGeekPseudo = geekRepository.findByPseudo(geek.getPseudo());
		if (optGeekPseudo.isPresent()) {
			return HttpStatus.CONFLICT;
		}
		geekRepository.save(geek);
		return HttpStatus.OK;
	}

	/**
	 * Retourne un geek en fonction de son token
	 * 
	 * @param token pour identifier le geek
	 * @return le geek en question
	 */
	@GetMapping(path = "auth")
	@ApiOperation(value = "Retourne un geek en fonction de son token")
	public @ResponseBody Geek auth(@RequestParam String token) {
		return geekRepository.findByToken(token).get();
	}

	/**
	 * Retourne tous les geek de la base.
	 *
	 * @return une liste de geek
	 */

	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne tous les Geeks.")
	public @ResponseBody Iterable<Geek> getAll() {
		return geekRepository.findAll();
	}

	/**
	 * Retourne le geek pour l'id spécifié.
	 *
	 * @param id du geek à retourner.
	 * @return le statut de la requête.
	 */

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Retourne le Geek pour l'id spécifié.")
	public ResponseEntity<Geek> getOne(@PathVariable int id) {
		return geekRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Retourne le geek pour l'id spécifié et le met à jour.
	 *
	 * @param id du geek à modifier et un objet geek contenant les informations à
	 *           mettre à jour.
	 * @return le statut de la requête
	 */

	@PatchMapping(path = "/{id}")
	@ApiOperation(value = "Retourne le Geek pour l'id spécifié et le met à jour.")
	public ResponseEntity<Geek> updateOne(@PathVariable int id, @RequestBody Geek geekInput) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			Geek geek = optGeek.get();
			if (geekInput.getAge() != 0) {
				geek.setAge(geekInput.getAge());
			}
			if (geekInput.getPseudo() != null) {
				geek.setPseudo(geekInput.getPseudo());
			}
			if (geekInput.getVille() != null) {
				geek.setVille(geekInput.getVille());
			}
			if (geekInput.getSexe() != null) {
				geek.setSexe(geekInput.getSexe());
			}
			if (geekInput.getTypeCompte() != null) {
				geek.setTypeCompte(geekInput.getTypeCompte());
			}
			if (geekInput.getEmail() != null) {
				geek.setEmail(geekInput.getEmail());
			}
			geekRepository.save(geek);
			return ResponseEntity.ok(geek);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Supprime le geek pour l'id spécifié.
	 *
	 * @param id du geek à supprimer
	 * @return code de la requête (200 => OK)
	 */

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Supprime le Geek pour l'id spécifié.")
	public HttpStatus suppr(@PathVariable int id) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			geekRepository.deleteById(id);
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}
}
