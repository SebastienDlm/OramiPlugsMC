# Projet Personnel - Plugin Minecraft : OramiPlugs

Un projet personnel visant à créer un plugin Minecraft pour des serveurs en 1.21.10.  
À ce jour, ce plugin possède des commandes pour des administrateurs/staff ainsi que des events nécessaires au bon fonctionnement des commandes.

## Commandes :
### /heal < Player >:
    description: Permet de remplir sa barre de vie ou celle d'un joueur
    permission: oramiplugs.heal

### /feed < Player >:
    description: Permet de remplir sa barre de nourriture ou celle d'un joueur
    permission: oramiplugs.feed

### /day:
    description: Permet de mettre le jour
    permission: oramiplugs.day
    aliases:
    - jour

### /night:
    description: Permet de mettre la nuit
    permission: oramiplugs.night
    aliases:
    - nuit

### /broadcast [ Message ]:
    description: Permet de faire une annonce avec ou sans couleurs avec le Minecraft Color Code
    permission: oramiplugs.broadcast
    aliases:
    - bc
    - alert
    - brc

### /information [ Player ]:
    description: Permet de donner les infos d'un joueur
    permission: oramiplugs.info
    aliases:
    - i
    - info

### /suicide:
    description: Permet de mourir

### /sun:
    description: Permet de mettre un beau soleil
    permission: oramiplugs.sun
    aliases:
    - soleil

### /rain:
    description: Permet de mettre une belle pluie
    permission: oramiplugs.rain
    aliases:
    - pluie

### /clear < Player >:
    description: Permet de supprimer son inventaire ou celui d'un joueur
    permission: oramiplugs.clear

### /kill [ Player ]:
    description: Permet de tuer un utilisateur
    permission: oramiplugs.kill

### /msg [ Player ] [ Message ]:
    description: Permet de communiquer entre deux joueurs sans que d'autres personnes ne voient les messages. Compatible Minecraft Color Code.
    aliases:
    - m
    - message

### /fly:
    description: Permet de s'envoler si on ne vole pas et d'arrêter de voler si on vole
    permission: oramiplugs.fly
    aliases:
    - flying
    - f

### /inventory [ Player ]:
    description: Permet de voir l'inventaire d'un joueur
    permission: oramiplugs.inventory
    aliases:
    - invsee
    - inventaire
    - inv

### /enderchest < Player >:
    description: Permet de voir son enderchest ou celui d'un joueur
    permission: oramiplugs.enderchest
    aliases:
    - ec

### /staff:
    description: Permet de passer en mode staff
    events: Impossibilité de casser des blocs, de récupérer des objets au sol, de modifier son inventaire, de tuer des mobs et de se faire tuer. L'inventaire du joueur est sauvegardé jusqu'au prochain arrêt ou reload du serveur. Si le joueur se déconnecte, il reste en mode staff tant que le serveur garde en mémoire l'inventaire.
    fonctionnalité: Un inventaire qui inclut ...
    permission: oramiplugs.staff
    aliases:
    - mod

### /gamemode [ survival/creative/adventure/spectator ] < Player >:
    description: Permet de changer son mode de jeu ou celui d'un joueur
    information: Il est actuellement impossible de changer le gamemode d'un joueur ! En cours de développement !
    permission: oramiplugs.gamemode
    aliases:
    - gm

### /vanish:
    description: Permet de se cacher des autres joueurs hors staff
    events: Impossibilité de casser des blocs, de récupérer des objets au sol, de modifier son inventaire, de tuer des mobs et de se faire tuer. L'état est sauvegardé jusqu'au prochain arrêt ou reload du serveur.
    permission: oramiplugs.vanish
    aliases:
    - v

### /freeze [ Player ]:
    description: Permet de geler un joueur ayant commis une infraction
    permission: oramiplugs.freeze

### /report [ Player ]:
    description: Permet de signaler un joueur
    fonctionnalité: Un inventaire s'ouvre permettant de sélectionner la raison du report
    aliases:
    - signale

### /kick [ Player ] [ Message ]:
    description: Permet d'expulser un joueur avec un message. Compatible Minecraft Color Code.
    permission: oramiplugs.kick

## Événements :
- Message de connexion
- Message de déconnexion

*Ce projet sert de démonstration de compétences en développement Java en utilisant Maven avec la librairy Spigot.*
