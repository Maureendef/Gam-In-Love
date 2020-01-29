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
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.repository.CoopRepository;
import co.simplon.gaminlove.repository.GeekRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Le controller qui gère les endpoint de l'entité Coop
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */

@RestController
@RequestMapping(path = "/coop")
@Api(tags = "API pour les opérations CRUD sur les Coop.")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
		@ApiResponse(code = 400, message = "Mauvaise Requête"),
		@ApiResponse(code = 401, message = "Echec Authentification"),
		@ApiResponse(code = 403, message = "Accès Refusé"), @ApiResponse(code = 500, message = "Problème Serveur") })
@CrossOrigin("*")
public class CoopController {

	@Autowired
	private CoopRepository coopRepository;

	@Autowired
	private GeekRepository geekRepository;

	/**
	 * Crée un nouveau match avec le type spécifié.
	 * 
	 * @param un objet coop sous forme Json et l'id du Geek cible
	 * @return la coop crée (avec l'id auto-générée)
	 */

	@PostMapping(path = "/{id}")
	@ApiOperation(value = "Crée un nouveau match avec le type spécifié.")
	public ResponseEntity<Coop> addNew(@PathVariable int id, @RequestBody Coop coop) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			coopRepository.save(coop);
			optGeek.get().getCoop().add(coop);
			geekRepository.save(optGeek.get());
		}
		return ResponseEntity.ok(coop);
	}

	/**
	 * Retourne tous les matchs.
	 * 
	 * @return une liste d'action
	 */

	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne tous les matchs")
	public @ResponseBody Iterable<Coop> getAll() {
		return coopRepository.findAll();
	}

	/**
	 * Retourne le match pour l'id spécifié.
	 * 
	 * @param id
	 * @return l'objet coop si réponse positive
	 */

	@GetMapping("/{id}")
	@ApiOperation(value = "Retourne le match pour l'id spécifié")
	public ResponseEntity<Coop> delOne(@PathVariable int id) {
		Optional<Coop> optCoop = coopRepository.findById(id);
		return optCoop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
