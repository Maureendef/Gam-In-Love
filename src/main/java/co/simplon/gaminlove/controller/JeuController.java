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
 * Le controller qui permet d'acceder au CRUD de la table Jeu
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path = "/jeu")
@Api(tags = "API pour les opérations CRUD sur les Jeux.")
@CrossOrigin("*")
public class JeuController {

	@Autowired
	private JeuRepository jeuRepository;

	@Autowired
	private GeekRepository geekRepository;

	/**
	 * Crée un nouveau jeu avec le nom spécifié pour le geek sélectionné.
	 * 
	 * @param nom & rang
	 * @return le jeu stocké en base (avec l'id à jour si généré)
	 */
	@PostMapping(path = "/{id}")
	@ApiOperation(value = "Crée un nouveau jeu avec le nom spécifié pour le geek sélectionné.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
			@ApiResponse(code = 400, message = "Mauvaise Requête"),
			@ApiResponse(code = 401, message = "Echec Authentification"),
			@ApiResponse(code = 403, message = "Accès Refusé"),
			@ApiResponse(code = 500, message = "Problème Serveur") })
	public ResponseEntity<Jeu> addNew(@PathVariable int id, @RequestBody Jeu jeu) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			jeuRepository.save(jeu);
			optGeek.get().getJeux().add(jeu);
			geekRepository.save(optGeek.get());
		}
		return ResponseEntity.ok(jeu);
	}

	/**
	 * Retourne tous les jeux.
	 * 
	 * @return une liste de jeux
	 */
	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne tous les jeux.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
			@ApiResponse(code = 400, message = "Mauvaise Requête"),
			@ApiResponse(code = 401, message = "Echec Authentification"),
			@ApiResponse(code = 403, message = "Accès Refusé"),
			@ApiResponse(code = 500, message = "Problème Serveur") })
	public @ResponseBody Iterable<Jeu> getAll() {
		return jeuRepository.findAll();
	}

	/**
	 * Retourne les jeux selon le nom spécifié.
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping(path = "/nom")
	@ApiOperation(value = "Retourne les jeux selon le nom spécifié.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
			@ApiResponse(code = 400, message = "Mauvaise Requête"),
			@ApiResponse(code = 401, message = "Echec Authentification"),
			@ApiResponse(code = 403, message = "Accès Refusé"),
			@ApiResponse(code = 500, message = "Problème Serveur") })
	public Collection<Jeu> getName(@RequestBody String nom) {
		Collection<Jeu> optJeu = jeuRepository.findAllByNom(nom);
		return optJeu;
	}

	/**
	 * Retourne le jeu selon le nom spécifié et le met à jour.
	 * 
	 * @param nom et rang
	 * @return jeu à jour
	 */
	@PatchMapping(path = "/{id}")
	@ApiOperation(value = "Retourne le jeu selon le nom spécifié et le met à jour.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
			@ApiResponse(code = 400, message = "Mauvaise Requête"),
			@ApiResponse(code = 401, message = "Echec Authentification"),
			@ApiResponse(code = 403, message = "Accès Refusé"),
			@ApiResponse(code = 500, message = "Problème Serveur") })
	public ResponseEntity<Jeu> updateOne(@PathVariable int id, @RequestBody Jeu jeuInput) {
		Optional<Jeu> optJeu = jeuRepository.findById(id);
		if (optJeu.isPresent()) {
			Jeu jeu = optJeu.get();
			if (jeuInput.getNom() != null) {
				jeu.setNom(jeuInput.getNom());
			}
			if (jeuInput.getRang() != null) {
				jeu.setRang(jeuInput.getRang());
			}
			jeuRepository.save(jeu);
			return ResponseEntity.ok(jeu);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Supprime le jeu d'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{idGeek}/{idJeu}")
	@ApiOperation(value = "Supprime le jeu d'id spécifié.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
			@ApiResponse(code = 400, message = "Mauvaise Requête"),
			@ApiResponse(code = 401, message = "Echec Authentification"),
			@ApiResponse(code = 403, message = "Accès Refusé"),
			@ApiResponse(code = 500, message = "Problème Serveur") })
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
}
