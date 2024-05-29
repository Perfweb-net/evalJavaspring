Pour lancer ce projet :

1) Assurez-vous d'avoir Docker installé sur votre système.

2) Exécutez la commande ddev start pour démarrer l'environnement de développement.

3) Démarrez l'application QuizzApplication.

Après avoir suivi ces étapes, deux URL seront disponibles :

GET /quizz/question/{playerId} : Cette URL vous retournera le pays dont vous devez trouver la capitale il faut forcément le playerId si vous êtes nouveau joueur mettez 0.
POST /quizz/response : Cette URL nécessite deux paramètres (id (du pays), playerId(mettez 0 si vous n'avez encore jamais répondu un playerId vous seras retourner) et answer) et vous indiquera si votre réponse est correcte ou non.

Vous pouvez alors interagir avec ces URL pour jouer au quizz et tester vos connaissances sur les capitales des pays.