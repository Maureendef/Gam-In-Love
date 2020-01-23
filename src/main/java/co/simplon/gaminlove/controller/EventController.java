package co.simplon.gaminlove.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Event;
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.repository.EventRepository;

/**
 * 
 * @author Nicolas Congard
 *
 */

@RestController
@RequestMapping(path = "/event")
@CrossOrigin("*")
public class EventController {

	
	//permet d'initialiser le repo, par le mécanisme d'injection de dependance 
	@Autowired
	private EventRepository eventRepository;

	/**
	 * Creation d'un nouvel event
	 * 
	 * @param nom
	 * @param lieu
	 * @param date
	 * @return un event qui vient d'etre cree
	 */
	@RequestMapping("/add")
	public Event addNew(@RequestParam String nom, @RequestParam String lieu, @RequestParam Date date) {
		Event newEvent = new Event();
		newEvent.setNom(nom);
		newEvent.setLieu(lieu);
		newEvent.setDate(date);
		return eventRepository.save(newEvent);
	}

	/**
	 * Ajoute un geek dans la liste des participants
	 * 
	 * @param event
	 * @param geek
	 * @return un event a jour
	 */
	@GetMapping(path = "/participate/{event}/{geek}")
	public Event addParticipant(@PathVariable Event event, @PathVariable Geek geek) {
		Event updateEvent = eventRepository.findById(event.getId()).get();
		updateEvent.getListeParticipant().add(geek);
		eventRepository.save(updateEvent);
		return updateEvent;
	}

	/**
	 * 
	 * @return la liste des events
	 */
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Event> getAll() {
		return eventRepository.findAll();
	}

	/**
	 * Cherche un event selon l'id
	 * 
	 * @param id
	 * @return un event s'il existe ou une erreur
	 */
	@GetMapping("/get")
	public ResponseEntity<Event> getOne(@RequestParam int id) {
		Optional<Event> optEvent = eventRepository.findById(id);
		if (optEvent.isPresent()) {
			return ResponseEntity.ok(optEvent.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Cherche un event selon le nom
	 * 
	 * @param nom
	 * @return un event s'il existe ou une erreur
	 */
	@GetMapping("/get/name")
	public ResponseEntity<Event> getName(@RequestParam String nom) {
		Optional<Event> optEvent = eventRepository.findByNom(nom);
		if (optEvent.isPresent()) {
			return ResponseEntity.ok(optEvent.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Mise a jour de l'event
	 * 
	 * @param id
	 * @param listeParticipant
	 * @param nom
	 * @param lieu
	 * @param date
	 * @return l'event a jour
	 */
	@GetMapping(path = "/update")
	public Event updateOne(@PathVariable int id, @PathVariable Collection<Geek> listeParticipant,
			@PathVariable String nom, @PathVariable String lieu, @PathVariable Date date) {
		Optional<Event> optEvent = eventRepository.findById(id);
		Event updateEvent = eventRepository.findById(id).get();
		if (optEvent.isPresent()) {
			updateEvent.setListeParticipant(listeParticipant);
			updateEvent.setNom(nom);
			updateEvent.setLieu(lieu);
			updateEvent.setDate(date);
			return eventRepository.save(updateEvent);
		} else {
			return updateEvent;
		}
	}

	/**
	 * Supprime l'event via son id
	 * 
	 * @param id
	 */
	@RequestMapping("/del/{id}")
	public void delOne(@PathVariable int id) {
		Optional<Event> optEvent = eventRepository.findById(id);
		if (optEvent.isPresent()) {
			eventRepository.deleteById(id);
			System.out.println("Action supprimée");
		} else {
			System.out.println("Pas d'action à supprimer");
		}
	}

}
