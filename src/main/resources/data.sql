INSERT INTO gamin_love.geek (id, age, compte, email, lieu, pseudo, sexe) VALUES (1, 18, 'basique', 'nicolas@orange.fr', 'Paris', 'nicodu75', 'homme');
INSERT INTO gamin_love.geek (id, age, compte, email, lieu, pseudo, sexe) VALUES (2, 19, 'basique', 'jeannine@orange.fr', 'Lyon', 'jjdu69', 'femme');
INSERT INTO gamin_love.geek (id, age, compte, email, lieu, pseudo, sexe) VALUES (3, 20, 'premium', 'bob@orange.fr', 'Pau', 'paupau', 'homme');
INSERT INTO gamin_love.geek (id, age, compte, email, lieu, pseudo, sexe) VALUES (4, 21, 'premium', 'laura@orange.fr', 'Lens', 'laulau', 'femme');

INSERT INTO gamin_love.jeux (id, nom, rang) VALUES (2, 'gta', 'cinq_etoiles');
INSERT INTO gamin_love.jeux (id, nom, rang) VALUES (3, 'rocket_league', 'silver');
INSERT INTO gamin_love.jeux (id, nom, rang) VALUES (4, 'sudoku2000', 'master');
INSERT INTO gamin_love.jeux (id, nom, rang) VALUES (5, 'fifa', 'debutant');

INSERT INTO gamin_love.geek_jeux (geek_id, jeux_id) VALUES (1, 2);
INSERT INTO gamin_love.geek_jeux (geek_id, jeux_id) VALUES (2, 3);
INSERT INTO gamin_love.geek_jeux (geek_id, jeux_id) VALUES (3, 4);
INSERT INTO gamin_love.geek_jeux (geek_id, jeux_id) VALUES (4, 5);

INSERT INTO gamin_love.coop (id, emetteur_id, recepteur_id) VALUES (1, 2, 3);

INSERT INTO gamin_love.photo (id, url) VALUES (1, 'toto_a_la_plage');
INSERT INTO gamin_love.photo (id, url) VALUES (2, 'ouioui.png');

INSERT INTO gamin_love.geek_photos (geek_id, photos_id) VALUES (1, 1);
INSERT INTO gamin_love.geek_photos (geek_id, photos_id) VALUES (1, 2);

INSERT INTO gamin_love.event (id, date, lieu, nom) VALUES (1, '2020-05-04 22:00:00', 'paris', 'parismanga');
INSERT INTO gamin_love.event (id, date, lieu, nom) VALUES (2, '2020-11-14 23:00:00', 'toulouse', 'toulousemanga');

INSERT INTO gamin_love.event_liste_participant (event_id, liste_participant_id) VALUES (1, 3);
INSERT INTO gamin_love.event_liste_participant (event_id, liste_participant_id) VALUES (1, 2);