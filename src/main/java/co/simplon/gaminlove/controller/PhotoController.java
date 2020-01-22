package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Album;
import co.simplon.gaminlove.model.Photo;
import co.simplon.gaminlove.repository.AlbumRepository;
import co.simplon.gaminlove.repository.PhotoRepository;

/**
 * Le controller qui permet d'acceder au CRUD de la table Jeu
 * 
 * @author Maureen, Nicolas, Virgile
 *
 */
@RestController
@RequestMapping(path = "/photo")
@CrossOrigin("*")
public class PhotoController {

	@Autowired
	private PhotoRepository photoRepository;
	private AlbumRepository albumRepository;

	/**
	 * Crée une nouvelle photo avec l'url spécifié et l'enregistre en base.
	 * 
	 * @param url album
	 * @return la photo stockée en base (avec l'id à jour si généré)
	 */
	@RequestMapping(path = "/add/{album}/{url}")
	public Photo addNew(@PathVariable Album album, @PathVariable String url) {

		Photo newPhoto = new Photo();
		newPhoto.setUrl(url);

		Optional<Album> optAlbum = albumRepository.findById(album.getId());
		Album updateAlbum = albumRepository.findById(album.getId()).get();
		if (optAlbum.isPresent()) {
			updateAlbum.getPhotos().add(newPhoto);

		}

		return photoRepository.save(newPhoto);
	}

	/**
	 * Retourne toutes les photos
	 * 
	 * @return une liste de photo
	 */
	@GetMapping(path = "/all")
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
	public ResponseEntity<Photo> getOne(@PathVariable int id) {
		Optional<Photo> optPhoto = photoRepository.findById(id);
		if (optPhoto.isPresent()) {
			return ResponseEntity.ok(optPhoto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Supprime la photo pour l'id spécifié spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del/{id}")
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