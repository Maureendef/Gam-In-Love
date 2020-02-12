package co.simplon.gaminlove.controller;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.simplon.gaminlove.model.Event;
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.repository.EventRepository;
import co.simplon.gaminlove.repository.GeekRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Le controller qui gère les endpoint de l'entité Event
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */

@RestController
@RequestMapping(path = "/event")
@Api(tags = "API pour les opérations CRUD sur les Events.")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
		@ApiResponse(code = 400, message = "Mauvaise Requête"),
		@ApiResponse(code = 401, message = "Echec Authentification"),
		@ApiResponse(code = 403, message = "Accès Refusé"), @ApiResponse(code = 500, message = "Problème Serveur") })
@CrossOrigin("*")
public class EventController {

	// permet d'initialiser le repo, par le mécanisme d'injection de dépendance
	private final EventRepository eventRepository;
	private final GeekRepository geekRepository;

	public EventController(EventRepository eventRepository, GeekRepository geekRepository) {
		this.eventRepository = eventRepository;
		this.geekRepository = geekRepository;
	}

	/**
	 * Crée un nouvel event.
	 * 
	 * @param event un objet event sous forme Json
	 * @return un event crée (avec id auto-généré).
	 */

	@PostMapping("/")
	@ApiOperation(value = "Création d'un nouvel event.")
	public ResponseEntity<Event> addNew(@RequestBody Event event) {
		eventRepository.save(event);
		return ResponseEntity.ok(event);
	}

	/**
	 * Ajoute un event dans la collection du geek sélectionné.
	 * 
	 * @param idEvent,idGeek de l'event et du geek à lier.
	 * @return le Geek avec sa collection à jour si généré.
	 */

	@PostMapping(path = "/{idEvent}/{idGeek}")
	@ApiOperation(value = "Ajoute un event dans la collection du geek sélectionné.")
	public ResponseEntity<Geek> addParticipant(@PathVariable int idEvent, @PathVariable int idGeek) {
		Optional<Geek> optGeek = geekRepository.findById(idGeek);
		Optional<Event> optEvent = eventRepository.findById(idEvent);
		if (optGeek.isPresent() && optEvent.isPresent()) {
			optEvent.get().getGeekParticipant().add(optGeek.get());
			eventRepository.save(optEvent.get());
			return ResponseEntity.ok(optGeek.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Affiche tous les events.
	 * 
	 * @return la liste des events
	 */
	@GetMapping(path = "/")
	@ApiOperation(value = "Affiche tous les events.")
	public @ResponseBody Iterable<Event> getAll() {
		return eventRepository.findAll();
	}

	/**
	 * Cherche un event selon l'id.
	 * 
	 * @param id de l'event
	 * @return un event s'il existe.
	 */
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Cherche un event selon l'id.")
	public ResponseEntity<Event> getOne(@PathVariable int id) {
		return eventRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Cherche un event selon le nom.
	 * 
	 * @param nom de l'event
	 * @return un ou plusieurs event(s) s'il(s) existe(nt).
	 */
	@GetMapping(path = "/nom")
	@ApiOperation(value = "Cherche un event selon le nom.")
	public ResponseEntity<Collection<Event>> getName(@RequestBody String nom) {
		Collection<Event> optEvent = eventRepository.findAllByNom(nom);
		if (optEvent.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(optEvent);
		}
	}

	/**
	 * Met a jour de l'event
	 * 
	 * @param id l'event à modifier et son id
	 * @return l'event a jour
	 */
	@PatchMapping(path = "/{id}")
	@ApiOperation(value = "Mise a jour de l'event")
	public ResponseEntity<Event> updateOne(@PathVariable int id, @RequestBody Event eventInput) {
		Optional<Event> optEvent = eventRepository.findById(id);
		if (optEvent.isPresent()) {
			Event updateEvent = optEvent.get();
			if (eventInput.getNom() != null) {
				updateEvent.setNom(eventInput.getNom());
			}
			if (eventInput.getLieu() != null) {
				updateEvent.setLieu(eventInput.getLieu());
			}
			if (eventInput.getDate() != null) {
				updateEvent.setDate(eventInput.getDate());
			}
			eventRepository.save(updateEvent);
			return ResponseEntity.ok(updateEvent);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Supprime l'event d'id spécifié de la collection du geek.
	 * 
	 * @param idEvent,idGeek de l'event et du geek
	 * @return code de la requête (200 => OK)
	 */
	@DeleteMapping("/{idEvent}/{idGeek}")
	@ApiOperation(value = "Supprime l'event d'id spécifié.")
	public HttpStatus delOne(@PathVariable int idGeek, @PathVariable int idEvent) {
		Optional<Geek> optGeek = geekRepository.findById(idGeek);
		Optional<Event> optEvent = eventRepository.findById(idEvent);
		if (optGeek.isPresent() && optEvent.isPresent()) {
			optGeek.get().getEvent().remove(optEvent.get());
			geekRepository.save(optGeek.get());
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

	/**
	 * Supprime l'event via son id
	 * 
	 * @param id de l'event
	 * @return code de la requête (200 => OK)
	 */
	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Supprime l'event via son id.")
	public HttpStatus suppr(@PathVariable int id) {
		Optional<Event> optEvent = eventRepository.findById(id);
		if (optEvent.isPresent()) {
			eventRepository.deleteById(id);
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}
}
