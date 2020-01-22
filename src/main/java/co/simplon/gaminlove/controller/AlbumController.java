package co.simplon.gaminlove.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.model.Album;
import co.simplon.gaminlove.repository.AlbumRepository;

@RestController
@RequestMapping(path = "/album")
@CrossOrigin("*")

public class AlbumController {
	@Autowired
	private AlbumRepository albumRepository;


	/**
	 * supprime l'album pour l'id spécifié.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del/{id}")
	public void delOne(@PathVariable int id) {
		Optional<Album> optAlbum = albumRepository.findById(id);
		if (optAlbum.isPresent()) {
			albumRepository.deleteById(id);
			System.out.println("Catalogue de photos supprimée");
		} else {
			System.out.println("Pas d'album à supprimer");
		}
	}
}
