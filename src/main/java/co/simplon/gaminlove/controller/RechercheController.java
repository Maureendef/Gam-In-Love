package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Recherche;
import co.simplon.gaminlove.repository.RechercheRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/recherche")
@Api(tags = "API pour les opérations CRUD sur les Recherches.")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
		@ApiResponse(code = 400, message = "Mauvaise Requête"),
		@ApiResponse(code = 401, message = "Echec Authentification"),
		@ApiResponse(code = 403, message = "Accès Refusé"), @ApiResponse(code = 500, message = "Problème Serveur") })
@CrossOrigin("*")
public class RechercheController {

	// permet d'initialiser le repo, par le mécanisme d'injection de dépendance
	// (IOC)
	@Autowired
	private RechercheRepository rechercheRepository;

	/**
	 * Cree une nouvelle recherche et l'enregistre en base.
	 * 
	 * @param recherche recuperee via un objet JSON du front
	 * @return la recherche stockee en base (avec l'id a jour si genere)
	 */
	@PostMapping(path = "/")
	@ApiOperation(value = "Crée une nouvelle recherche.")
	public ResponseEntity<Recherche> addRecherche(@RequestBody Recherche recherche) {
		rechercheRepository.save(recherche);
		return ResponseEntity.ok(recherche);
	}

	/**
	 * Retourne toutes les recherches de la base.
	 * 
	 * @return une liste de recherche
	 */
	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne toutes les recherches.")
	public @ResponseBody Iterable<Recherche> getAll() {
		return rechercheRepository.findAll();
	}

	/**
	 * Supprime la recherche pour l'id specifie.
	 * 
	 * @param id de la recherche a supprimer
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Supprime la recherche pour l'id spécifié.")
	public HttpStatus delOne(@PathVariable int id) {
		Optional<Recherche> optRecherche = rechercheRepository.findById(id);
		if (optRecherche.isPresent()) {
			rechercheRepository.deleteById(id);
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

}
