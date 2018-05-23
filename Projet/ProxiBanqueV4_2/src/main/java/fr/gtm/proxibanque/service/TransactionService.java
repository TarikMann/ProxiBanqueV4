package fr.gtm.proxibanque.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Classe de service utilisee par la couche de presentation, en ce qui concerne
 * les operations liees a la gestion des transactions. Pour effectuer les
 * traitements correspondant, elle consomme le WebService.
 * 
 * @author groupe 1
 *
 */
public class TransactionService {

	/**
	 * Propriete permettant aux methodes de cette classe de convertir des fichiers
	 * JSON en objets java et vis-versa grace au framework Jackson
	 */
	private ObjectMapper mapper = new ObjectMapper();
	/**
	 * Propriete permettant que ce programme soit un client HTTP pour envoyer des
	 * requetes vers le web service consomme et de recevoir les reponses
	 * correspondantes
	 */
	Client client = Client.create();
	/**
	 * propriete correspondant aux chaines de caracteres en format JSON extraites
	 * des reponses HTTP fournies par le webservice consomme (propriete utilisee par
	 * plusieurs methodes)
	 */
	String retour = null;

	/**
	 * Propriete permettant la journalisation (logs) des traitements de cette classe
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	/**
	 * propriete etant la base fixe de l'url utilisee pour consommer le WebService
	 * concernant les transactions (concatenee dans les methodes avec des suffixes
	 * adaptes aux operations realisees)
	 */
	private String debutUrl = "http://192.168.1.70:8080/webservice_1.0/api/transactionWS";

	/**
	 * methode qui s'adresse au WebService pour recuperer l'historique des
	 * transactions par jour
	 * 
	 * @return collection (HashMap) des nombres transactions effectuees dans la
	 *         banque chaque jour durant le mois en cours
	 */
	public Map<Date, Integer> decompterTransactions() {
		// log pour le debut de la methode
		TransactionService.LOGGER.info("gerant Entree dans la methode decompterTransactions");
		// declaration de la map d'objets (Date et entier) obtenue au cours de la
		// methode
		// et retournee en fin de methode
		Map<Date, Integer> map = null;
		// construction d'une requete HTTP
		// (notamment l'URL qui ne contient pas de
		// parametre ici car le traitement demande n'est pas associe a une personne en
		// particulier)
		WebResource webResource = client.resource(this.debutUrl + "/decompterTransactions");
		// pas d'ajout de JSON a la requete ici car on n'envoit pas d'objet au WS
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// extraction du JSON de la reponse HTTP
		retour = response.getEntity(String.class);
		try {
			// conversion du JSON recupere en objet de type liste de ClientProxi
			map = mapper.readValue(retour, new TypeReference<Map<Date, Integer>>() {
			});
		} catch (IOException e) {
			// creation d'un log d'erreur en cas de catch
			TransactionService.LOGGER.info("gerant erreur : catch");
			e.printStackTrace();
			return null;
		}
		if (map.equals(null)) {
			// log d'erreur dans le cas ou la map obtenue est nulle
			TransactionService.LOGGER.info("gerant erreur : ap<Date,Integer> retournee = NULL");
		} else {
			// log de reussite de la methode si la map obtenue est non nulle (vide ou non)
			TransactionService.LOGGER.info("gerant Liste obtenue");
			if (map.size() == 0) {
				// log d'info dans le cas ou la liste obtenue est vide
				TransactionService.LOGGER.info("gerant info : Map<Date,Integer> retournee est vide (taille = 0)");
			}
		}
		// retour de l'objet obtenu
		return map;
	}
}
