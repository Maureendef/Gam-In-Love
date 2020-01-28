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

import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Jeu;
import co.simplon.gaminlove.model.Photo;
import co.simplon.gaminlove.repository.GeekRepository;
import co.simplon.gaminlove.repository.PhotoRepository;
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
@RequestMapping(path = "/photo")
@Api(tags = "API pour les opérations CRUD sur les Photos.")
@CrossOrigin("*")
public class PhotoController {

	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private GeekRepository geekRepository;

	/**
	 * Crée une nouvelle photo avec l'url spécifié et l'enregistre en base.
	 * 
	 * @param url
	 * @return la photo stockée en base (avec l'id à jour si généré)
	 */
	@PostMapping(path = "/add/{geek}/{url}")
	@ApiOperation(value = "Crée une nouvelle photo avec l'url spécifié et l'enregistre en base.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public Photo addNew(@PathVariable Geek geek, @PathVariable String url) {
		Photo newPhoto = new Photo();
		newPhoto.setUrl(url);		
		Optional<Geek> optGeek = geekRepository.findById(geek.getId());
		Geek updateGeek = geekRepository.findById(geek.getId()).get();
		if (optGeek.isPresent()) {
			updateGeek.getPhotos().add(newPhoto);
		}
		return photoRepository.save(newPhoto);
	}
	
	public ResponseEntity<Photo> addNew(@PathVariable int id, @RequestBody Photo photo) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			photoRepository.save(photo);
			optGeek.get().getPhotos().add(photo);
			geekRepository.save(optGeek.get());
		}
		return ResponseEntity.ok(photo);
	}

	/**
	 * Retourne toutes les photos.
	 * 
	 * @return une liste de photo
	 */
	@GetMapping(path = "/all")
	@ApiOperation(value = "Retourne toutes les photos.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public @ResponseBody Iterable<Photo> getAll() {
		return photoRepository.findAll();
	}

	/**
	 * Retourne la photo pour l'id spécifié.
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping(path = "/get/{id}")
	@ApiOperation(value = "Retourne la photo pour l'id spécifié.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public ResponseEntity<Photo> getOne(@PathVariable int id) {
		Optional<Photo> optPhoto = photoRepository.findById(id);
		if (optPhoto.isPresent()) {
			return ResponseEntity.ok(optPhoto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Supprime la photo pour l'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del/{id}")
	@ApiOperation(value = "Supprime la photo pour l'id spécifié.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message="Succès"),
			@ApiResponse(code = 400, message="Mauvaise Requête"),
			@ApiResponse(code = 401, message="Echec Authentification"),
			@ApiResponse(code = 403, message="Accès Refusé"),
			@ApiResponse(code = 500, message="Problème Serveur")})
	public void delOne(@PathVariable int id) {
		Optional<Photo> optPhoto = photoRepository.findById(id);
		if (optPhoto.isPresent()) {
			photoRepository.deleteById(id);
			System.out.println("Jeu supprimé");
		} else {
			System.out.println("Pas de jeu à supprimer");
		}
	}

}