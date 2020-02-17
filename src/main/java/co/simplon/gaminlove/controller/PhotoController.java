package co.simplon.gaminlove.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
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
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.model.Photo;
import co.simplon.gaminlove.repository.GeekRepository;
import co.simplon.gaminlove.repository.PhotoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Le controller qui gère les endpoint de l'entité Photo
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */

@RestController
@RequestMapping(path = "/photo")
@Api(tags = "API pour les opérations CRUD sur les Photos.")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Succès"),
		@ApiResponse(code = 400, message = "Mauvaise Requête"),
		@ApiResponse(code = 401, message = "Echec Authentification"),
		@ApiResponse(code = 403, message = "Accès Refusé"), @ApiResponse(code = 500, message = "Problème Serveur") })
@CrossOrigin("*")
public class PhotoController {

	private final PhotoRepository photoRepository;

	private final GeekRepository geekRepository;

	public PhotoController(PhotoRepository photoRepository, GeekRepository geekRepository) {
		this.photoRepository = photoRepository;
		this.geekRepository = geekRepository;
	}

	/**
	 * Ajoute une photo pour le Geek.
	 * 
	 * @param photo un objet photo sous forme Json et l'id du Geek
	 * @return la photo crée (avec id auto-généré)
	 */

	@PostMapping(path = "/{id}")
	@ApiOperation(value = "Ajoute une photo pour le Geek.")
	public ResponseEntity<Photo> addNew(@PathVariable int id, @RequestBody Photo photo) {
		Optional<Geek> optGeek = geekRepository.findById(id);
		if (optGeek.isPresent()) {
			photo.setGeekPhoto(optGeek.get());
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

	@GetMapping(path = "/album/{id}")
	@ApiOperation(value = "Retourne toutes les photos.")
	public @ResponseBody ArrayList<Photo> getAllForGeek(@PathVariable int id) {
		return photoRepository.findForGeek(id);
	}

	/**
	 * Retourne la photo pour l'id spécifié.
	 * 
	 * @param id d'une photo
	 * @return une ou des photos si elle(s) existe(nt).
	 */

	@GetMapping(path = "/")
	@ApiOperation(value = "Retourne la photo pour l'id spécifié.")
	public ResponseEntity<Photo> getOne(@PathVariable int id) {
		Optional<Photo> optPhoto = photoRepository.findById(id);
		return optPhoto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Supprime la photo pour l'id spécifié.
	 * 
	 * @param idGeek,idPhoto du geek et de la photo
	 * @return code la requête (200 => OK)
	 */

	@DeleteMapping("/{idGeek}/{idPhoto}")
	@ApiOperation(value = "Supprime la photo pour l'id spécifié.")
	public HttpStatus delOne(@PathVariable int idGeek, @PathVariable int idPhoto) {
		Optional<Geek> optGeek = geekRepository.findById(idGeek);
		Optional<Photo> optPhoto = photoRepository.findById(idPhoto);
		if (optPhoto.isPresent() && optGeek.isPresent()) {
			optGeek.get().getPhotos().remove(optPhoto.get());
			geekRepository.save(optGeek.get());
			System.out.println(idPhoto);
			photoRepository.deleteById(idPhoto);
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

}
