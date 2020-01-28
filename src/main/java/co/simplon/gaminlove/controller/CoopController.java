package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Coop;
import co.simplon.gaminlove.repository.CoopRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Le controller qui permet d'acceder au CRUD de la table Match
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path="/coop")
@Api(tags = "API pour les opérations CRUD sur les Coop.")
@CrossOrigin("*")
public class CoopController {

	@Autowired
	private CoopRepository coopRepository;
	
	/**
	 * Crée un nouveau match avec le type spécifié.
	 * 
	 * @param action, émetteur, récepteur
	 * @return l'action est stockée en base (avec l'id auto-générée)
	 */
	@PostMapping(path = "/")
	@ApiOperation(value = "Crée un nouveau match avec le type spécifié.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public ResponseEntity<Coop> addNew(@RequestBody Coop coop) {
		coopRepository.save(coop);
		return ResponseEntity.ok(coop);
	}
	
	/**
	 * Retourne tous les matchs.
	 * 
	 * @return une liste d'action
	 */
	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne tous les matchs")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public @ResponseBody Iterable<Coop> getAll(){
		return coopRepository.findAll();		
	}
	
	/**
	 * Retourne le match pour l'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "Retourne le match pour l'id spécifié")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public ResponseEntity<Coop> delOne(@PathVariable int id) {
		Optional<Coop> optCoop = coopRepository.findById(id);
		return optCoop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
