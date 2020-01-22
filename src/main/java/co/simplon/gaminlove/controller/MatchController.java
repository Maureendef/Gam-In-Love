package co.simplon.gaminlove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.gaminlove.repository.MatchRepository;

@RestController
@RequestMapping(path="/match")
@CrossOrigin("*")
public class MatchController {

	@Autowired
	private MatchRepository matchRepository;
	
}
