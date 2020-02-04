package co.simplon.gaminlove.service;

import co.simplon.gaminlove.model.Action;
import co.simplon.gaminlove.model.Geek;
import co.simplon.gaminlove.repository.ActionRepository;
import co.simplon.gaminlove.repository.GeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Le service action, une classe intermédiaire entre le repository (DAO) et le contrôleur
 *
 * @author Maureen, Nicolas, Virgile
 *
 */
@Service //indique à spring qu'on est dans la couche Service
@Transactional //ordonne à spring de traiter toutes les méthodes publiques en mode transactionnel (tout ou rien)
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private GeekRepository geekRepository;

}
