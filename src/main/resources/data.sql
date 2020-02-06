INSERT INTO gamin_love.geek (id ,age, email, pseudo, sexe,type_compte,ville, password) VALUES (1, 19, 'marjorieti@gmail.com', 'marjorietiteculote', 'femme', 'basique', 'paris', 'titkiki69');
INSERT INTO gamin_love.geek (id, age, email, pseudo, sexe,type_compte,ville, password) VALUES (2, 26, 'Kami@gmail.com', 'kamHot', 'femme', 'Premium++', 'Paris 15e', 'jveuduku123');
INSERT INTO gamin_love.geek (id, age, email, pseudo, sexe,type_compte,ville, password) VALUES (3, 22, 'lucie@gmail.com', 'lululacoku', 'femme', 'Premium', 'Lille', 'azerty123');
INSERT INTO gamin_love.geek (id, age, email, pseudo, sexe,type_compte,ville, password) VALUES (4, 45, 'jean_delaru@gmail.com', 'jeanjean', 'homme', 'basique', 'Lille', 'kukulapraline');
INSERT INTO gamin_love.geek (id, age, email, pseudo, sexe,type_compte,ville, password) VALUES (5, 23, 'olivier@gmail.com', 'twonails', 'homme', 'premium++', 'Paris', 'jemmanger');
INSERT INTO gamin_love.geek (id, age, email, pseudo, sexe,type_compte,ville, password) VALUES (6, 45, 'julien@gmail.com', 'jujulegeek', 'homme', 'basique', 'Nantes', 'jemleschevres');

INSERT INTO gamin_love.event (id, lieu, nom) VALUES (1, 'Paris', 'Paris Game Lover');
INSERT INTO gamin_love.event (id, lieu, nom) VALUES (2, 'Lille', 'Christmas Lover Geek');
INSERT INTO gamin_love.event (id, lieu, nom) VALUES (3, 'Nantes', 'Summer Geek Love');

INSERT INTO gamin_love.action(id, type_action, geek_cible_id, geek_action_id) VALUES (1, 'like', 1, 4);
INSERT INTO gamin_love.action(id, type_action, geek_cible_id, geek_action_id) VALUES (2, 'superlike', 4, 1);
INSERT INTO gamin_love.action(id, type_action, geek_cible_id, geek_action_id) VALUES (3, 'like', 6, 3);

INSERT INTO gamin_love.coop(id, geek_cible_id, geek_coop_id) VALUES (1, 4, 1);

INSERT INTO gamin_love.event_geek_participant(event_id, geek_participant_id) VALUES(2, 2);
INSERT INTO gamin_love.event_geek_participant(event_id, geek_participant_id) VALUES(2, 5);

INSERT INTO gamin_love.geek_jeux(geek_jeux_id, jeux_id) VALUES(1,145);
INSERT INTO gamin_love.geek_jeux(geek_jeux_id, jeux_id) VALUES(4,34);
INSERT INTO gamin_love.geek_jeux(geek_jeux_id, jeux_id) VALUES(4,38);
INSERT INTO gamin_love.geek_jeux(geek_jeux_id, jeux_id) VALUES(4,394);

INSERT INTO gamin_love.mp(id, message, geek_cible_id, geekmp_id) VALUES(1, 'Depuis que je t ai vu je ne dors plus la nuit', 4, 1);

INSERT INTO gamin_love.photo(id, url, geek_photo_id) VALUES(1, 'https://www.babelio.com/users/AVT_Marjorie-Levasseur_1107.jpg',1);
INSERT INTO gamin_love.photo(id, url, geek_photo_id) VALUES(2, 'https://gal.img.pmdstatic.net/fit/http.3A.2F.2Fprd2-bone-image.jpg',2);
INSERT INTO gamin_love.photo(id, url, geek_photo_id) VALUES(3, 'https://cache.magicmaman.com/data/photo/w1000_ci/5g/lucie-lucas-maman-beaute.jpg',3);
INSERT INTO gamin_love.photo(id, url, geek_photo_id) VALUES(4, 'https://www.jecontacte.org/v5/n/o/n/vbth/3226684913_1554990673.jpg',4);
INSERT INTO gamin_love.photo(id, url, geek_photo_id) VALUES(5, 'https://www.leparisien.fr/resizer/ecoe-ebpQ1cC3YlJS5S_cFVUtd8=/932x582/.jpg',5);
INSERT INTO gamin_love.photo(id, url, geek_photo_id) VALUES(6, 'https://math.univ-lille1.fr/~hauseux/img/photo.jpg',6);