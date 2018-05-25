README.TXT
======================================================================================

Bienvenue sur le fichier d'instructions d'installation de votre solution web Proxibanque 4.0.
Afin de profiter de manière optimale de l'application, merci de suivre la procédure suivante.


Contenu du dossier de livraison
--------------------------------------------------------------------------------------

Avant de débuter, assurez-vous que le dossier de livraison contient les fichiers suivants :
	- webservice_1.0.war
	- ProxiBanqueV4_2.war
	- wsbdd.sql
	
A des fins de test de l'application, vous trouverez dans le dossier livraison-groupe1/test un fichier nommé wsbdd.sql : script de création du jeu de données dans la base.	
	
	
Pré-requis de configuration
--------------------------------------------------------------------------------------
- JDK 9 installé sur la machine cible [ téléchargé depuis: http://www.oracle.com/technetwork/java/javase/downloads/index.html ]
- Serveur d'application web : tomcat 9.0 [ téléchargé depuis https://tomcat.apache.org/download-90.cgi ]
- Serveur WAMP 3.0.4 installé (avec serveur de gestion de base de données : MySql)[ téléchargé depuis http://www.wampserver.com/en/ ]
- Eclipse paramétré sur la JDK 9.0.4 [ téléchargé depuis http://www.eclipse.org/ ]
- Maven 3.5.3 minimum installé [ téléchargé depuis https://maven.apache.org/download.cgi ]


Execution sur le serveur d'applications Tomcat
--------------------------------------------------------------------------------------

lancement 
-----
	- lancer votre serveur de base de données WAMP en double-cliquant sur le fichier "wampmanager.exe" du répertoire d'installation WAMP.

	
lancement du serveur d'application web
-----
	- lancer le serveur d'applications web tomcat en double-cliquant le fichier "startup.bat" du répertoire "bin" d'installation de tomcat.
	
	
installation de l'application et du webservice
-----	
	- copier le fichier webservice_1.0.war du dossier de livraison.
	- coller le fichier webservice_1.0.war dans le dossier '/webapps' du répertoire d'installation du serveur tomcat.
	- s'assurer que le fichier webservice_1.0.war collé est bien décompressé : Un dossier webservice_1.0 doit apparaître.
	
	- copier le fichier ProxiBanqueV4_2.war du dossier de livraison.
	- coller le fichier ProxiBanqueV4_2.war dans le dossier '/webapps' du répertoire d'installation du serveur tomcat.
	- s'assurer que le fichier ProxiBanqueV4_2.war collé est bien décompressé : Un dossier ProxiBanqueV4_2 doit apparaître.
	
lancement du webservice
-----

	- ouvrir un Navigateur Web et entrer http://localhost:8080/webservice_1.0 dans la barre d'adresse. Une page de bienvenue s'affiche.
	
chargement de la base de données
-----
	
	- lancer phpmynadmin via l'entrée de vos codes utilisateur et mot de passe.
	- assurez vous que l'accès à votre gestionnaire de bases phpmyadmin est paramétré comme suit => utilisateur : "root", mot de passe : "root".
		Dans le cas contraire, modifier le mot de passe en suivant la procédure suivante, à partir de l'accueil de phpmyadmin :
			> cliquer sur comptes utilisateurs 
			> cliquer sur "root" dans la table des comptes utilisateurs 
			> cliquer sur "éditer les privilèges" dans la table
			> cliquer sur modifier le mot de passe
			> saisir le mot de passe
			> cliquer sur "Exécuter"	
	- vérifier qu'une base de données nommée "wsbdd" a été créée.
	- cliquer sur la base wsbdd dans le menu latéral à gauche de l'écran.
	- cliquer sur l'onglet "importer" > sélectionner le script "wsbdd.sql" disponible dans le dossier de livraison > cliquer sur "Exécuter". une erreur apparaît, l'ignorer.
	

lancement de l'application
-----
	- ouvrir un Navigateur Web et entrer http://localhost:8080/proxibanqueV4_2 dans la barre d'adresse.
		Vous serez dirigé directement vers la page d'authentification de l'application. Entrez les paramètres suivants pour accéder à l'application :
					utilisateur 1 pré-enregistré : 
						> identifiant "dubcons"
						> mot de passe "123"


maintenance de l'application éventuelle
--------------------------------------------------------------------------------------
	- pour consulter les logs de l'application, ouvrir l'exploreur de fichiers et aller sur C:\Proxibanque	


Copyright and licensing information
--------------------------------------------------------------------------------------

- les droits de l'application web livrée sont détenus par :
		Inès HEDI
		Beatriz MORENO-ORTEGA
		Salah-Heddine BENAZOUZ
		Frédéric BATAULT
		Tarik MANNOU
		Mathieu TRICOIRE
		
- utilisation exclusivement réservée à la société fictive Proxibanque.



Contact information
--------------------------------------------------------------------------------------

- contacter la société fictive GTM Etudiants pour support.
