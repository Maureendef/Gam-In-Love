package co.simplon.gaminlove.controller;

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

import co.simplon.gaminlove.model.Event;
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.repository.EventRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */

@RestController
@RequestMapping(path = "/event")
@Api(tags = "API pour les opérations CRUD sur les Events.")
@CrossOrigin("*")
public class EventController {

	
	//permet d'initialiser le repo, par le mécanisme d'injection de dépendance 
	@Autowired
	private EventRepository eventRepository;

	/**
	 * Création d'un nouvel event.
	 * 
	 * @param nom
	 * @param lieu
	 * @param date
	 * @return un event qui vient d'etre crée
	 */
	@PostMapping("/")
	@ApiOperation(value = "Création d'un nouvel event.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	/*public Event addNew(@RequestBody String nom, @RequestBody String lieu, @RequestBody Date date) {
		Event newEvent = new Event();
		newEvent.setNom(nom);
		newEvent.setLieu(lieu);
		newEvent.setDate(date);
		return eventRepository.save(newEvent);
	}*/
	
	public ResponseEntity<Event> addNew(@RequestBody Event event) {
		eventRepository.save(event);
		return ResponseEntity.ok(event);
	}

	/**
	 * Ajoute un geek à la liste des participants
	 * 
	 * @param id
	 * @param geek
	 * @return un event a jour
	 */
	@PostMapping(path = "/participation/{id}")
	@ApiOperation(value = "Ajoute un geek à la liste des participants")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public Event addParticipant(@PathVariable int id, @RequestBody Geek geek) {
		Event updateEvent = eventRepository.findById(id).get();
		updateEvent.getListeParticipant().add(geek);
		eventRepository.save(updateEvent);
		return updateEvent;
	}
	
	/**
	 * Supprime un geek à la liste des participants
	 * 
	 * @param id
	 * @param geek
	 * @return un event a jour
	 */
	@DeleteMapping(path = "/participation/{id}")
	@ApiOperation(value = "Supprime un geek à la liste des participants")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public Event removeParticipant(@PathVariable int id, @RequestBody Geek geek) {
		Event updateEvent = eventRepository.findById(id).get();
		updateEvent.getListeParticipant().remove(geek);
		eventRepository.save(updateEvent);
		return updateEvent;
	}
	
	/**
	 * Affiche tous les events.
	 * @return la liste des events
	 */
	@GetMapping(path = "/")
	@ApiOperation(value = "Affiche tous les events.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public @ResponseBody Iterable<Event> getAll() {
		return eventRepository.findAll();
	}

	/**
	 * Cherche un event selon l'id.
	 * 
	 * @param id
	 * @return un event s'il existe ou une erreur
	 */
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Cherche un event selon l'id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public ResponseEntity<Event> getOne(@PathVariable int id) {
		Optional<Event> optEvent = eventRepository.findById(id);
		return optEvent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Cherche un event selon le nom.
	 * 
	 * @param nom
	 * @return un event s'il existe ou une erreur
	 */
	@GetMapping(path = "/name")
	@ApiOperation(value = "Cherche un event selon le nom.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public ResponseEntity<Event> getName(@RequestBody String nom) {
		Optional<Event> optEvent = eventRepository.findByNom(nom);
		return optEvent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Mise a jour de l'event
	 * 
	 * @param l'event à modifier et son id
	 * @return l'event a jour
	 */
	@PatchMapping(path = "/{id}")
	@ApiOperation(value = "Mise a jour de l'event")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
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
	 * Supprime l'event via son id
	 * 
	 * @param id
	 */
	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Supprime l'event via son id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public HttpStatus delOne(@PathVariable int id) {
		Optional<Event> optEvent = eventRepository.findById(id);
		if (optEvent.isPresent()) {
			eventRepository.deleteById(id);
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

}
