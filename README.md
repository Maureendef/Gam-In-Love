![logo](https://github.com/Maureendef/Gam-In-Love/blob/master/docs/Maquette/logo2.png)

<!-- blank line -->
----
<!-- blank line -->

## Nous allons vous proposer de réaliser votre rêve, à savoir : trouver l'amour!
**Vous êtes un(e) geek(ette) et vous en chercher un(e), n'allez pas plus loin Gam'in Love est fait pour vous.**

Notre application web sera basée sur un modèle de rencontre en ligne traditionnel. Vous aurez la possibilité de créer un compte, d'y ajouter des photos avant de vous lancer dans la recherche de l'amour :revolving_hearts:.

Nous vous proposerons en fonction des dons que vous effectuerez sur notre site un certain nombre de like. En cas de like réciproque, le mode chat s'activera. Le reste ne dépend que de vous :smirk:.

Vous pourrez bien sûr, définir _votre ville_, ainsi qu'_un rayon_ dans lequel lancer votre recherche mais également sur des critères bien plus important tel que _le jeu_ sur lequel se trouve votre futur moitié, ainsi que _son classement_ sur ce même jeu :video_game:.

Il faut savoir que nous ne sommes pas une organisation bénévole et dans cet unique but, il vous sera proposé des services additionnels (allant de la simple extension du nombre de like, la possibilité de voir qui vous like, ou encore des services plus "**spéciaux**") contre une certaine somme :moneybag:.

**Voici une maquette du site :** [Cliquez ici!](https://marvelapp.com/jg52b09/screen/63516085)

**Les uses cases du général avec un zoom sur des parties plus spécifique:**
![uses case 1](https://media.discordapp.net/attachments/642305625468174336/642328326677069829/unknown.png "cas général")

![uses case 2](https://media.discordapp.net/attachments/642305625468174336/642328411884355584/unknown.png "Rechercher")

![uses case 3](https://media.discordapp.net/attachments/642305625468174336/642328486194708506/unknown.png "Gestion Compte")

**Le MCD :**
![MCD](https://github.com/Maureendef/Gam-In-Love/blob/master/docs/Diagram/MCD.PNG "MCD")

**Liste des endpoints :**

```
Endpoint de base
/gaminlove

---

Se connecter
/gaminlove/log_in

S'inscrire
/gaminlove/sign_up

---

Créer un profil
/gaminlove/geek/add

Modifier un profil
/gaminlove/geek/update

Supprimer un profil
/gaminlove/geek/del

Faire une recherche
/gaminlove/geek/get

Afficher les geeks
/gaminlove/geek/all

---

Supprimer un album
/gaminlove/album/del

---

Ajouter une photo
/gaminlove/photo/add

Supprimer une photo
/gaminlove/photo/del

Afficher les photos
/gaminlove/photo/all

Rechercher une photo
/gaminlove/photo/get

---

Faire une action (like/superlike/dislike)
/gaminlove/action/add

Supprimer une action
/gaminlove/action/del

Rechercher une action
/gaminlove/action/get

---

Créer un match
/gaminlove/coop/add

Afficher les matchs
/gaminlove/coop/all

Supprimer un match
/gaminlove/coop/del

---

Afficher les mp
/gaminlove/mp/all

Envoyer un mp
/gaminlove/mp/add

Supprimer un mp
/gaminlove/mp/del

Chercher un mp?

---

Afficher les events
/gaminlove/event/all

Rechercher un event
/gaminlove/event/get

Modifier un event
/gaminlove/event/update

Ajouter un event
/gaminlove/event/add

Supprimer un event
/gaminlove/event/del

Participer à un event
/gaminlove/participate_event

---

Supprimer un catalogue
/gaminlove/catalogue/del

---

Afficher les jeux
/gaminlove/jeu/all

Rechercher un jeu
/gaminlove/jeu/get

Modifier un jeu
/gaminlove/jeu/update

Ajouter un jeu
/gaminlove/jeu/add

Supprimer un jeu
/gaminlove/jeu/del
```
