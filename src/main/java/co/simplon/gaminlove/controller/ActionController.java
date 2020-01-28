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

import co.simplon.gaminlove.model.Action;
import co.simplon.gaminlove.repository.ActionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Le controller qui permet d'acceder au CRUD de la table Action
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path = "/action")
@Api(tags = "API pour les opérations CRUD sur les Actions.")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
		@ApiResponse(code = 400, message = "Mauvaise Requête"),
		@ApiResponse(code = 401, message = "Echec Authentification"),
		@ApiResponse(code = 403, message = "Accès Refusé"), 
		@ApiResponse(code = 500, message = "Problème Serveur") })
@CrossOrigin("*")
public class ActionController {

	// permet d'initialiser le repo, par le mécanisme d'injection de dépendance
	// (IOC)
	@Autowired
	private ActionRepository actionRepository;

	/**
	 * Crée une nouvelle action avec le type spécifié.
	 * 
	 * @param action récupère un objet Json du front
	 * @return l'action est stockée en base (avec l'id auto-générée)
	 */
	@PostMapping(path = "/")
	@ApiOperation(value = "Crée une nouvelle action avec le type spécifié.")
	public ResponseEntity<Action> addNew(@RequestBody Action action) {
		actionRepository.save(action);
		return ResponseEntity.ok(action);
	}
	
	/**
	 * Retourne toutes les actions.
	 * 
	 * @return une liste d'action
	 */
	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne toutes les actions.")
	public @ResponseBody Iterable<Action> getAll() {
		return actionRepository.findAll();
	}

	/**
	 * Retourne l'action pour l'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Retourne l'action pour l'id spécifié.")
	public ResponseEntity<Action> getOne(@PathVariable int id) {
		Optional<Action> optAction = actionRepository.findById(id);
		return optAction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Supprime l'action pour l'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Supprime l'action pour l'id spécifié.")
	public HttpStatus delOne(@PathVariable int id) {
		Optional<Action> optAction = actionRepository.findById(id);
		if (optAction.isPresent()) {
			actionRepository.deleteById(id);
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}
}
