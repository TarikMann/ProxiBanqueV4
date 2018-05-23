package fr.gtm.proxibanque.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.interfaces.IClientService;

/**
 * Classe de service utilisee par la couche de presentation, en ce qui concerne
 * les operations liees a la gestion des clients. Pour effectuer les traitements
 * correspondant, elle consomme le WebService.
 * @author groupe 1
 *
 */
public class ClientService {

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
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	
	
	/**
	 * propriete etant la base fixe de l'url utilisee pour consommer le WebService
	 * concernant le client (concatenee dans les methodes avec des suffixes adaptes
	 * aux operations realisees)
	 */
	private String debutUrl = "http://192.168.1.70:8080/webservice_1.0/api/clientWS";

	
	
	/**
	 * Methode qui s'adresse au WebService pour ajouter un client dans la base de
	 * donnees
	 * 
	 * @param clientProxi
	 *            objet client contenant les informations du client a ajouter
	 * @return booleen qui indique si l'ajout a bien ete effectuee
	 */
	
	
	
	public boolean creerClient(ClientProxi clientProxi) {
		// log pour le debut de la methode
		ClientService.LOGGER.info("conseiller n� " + clientProxi.getConseiller().getIdConseiller()
				+ " Entree dans la methode creerClients");
		// declaration d'un objet contenant une propriete booleene
		Boolean cree;
		// declaration de la chaine de caractere JSON envoyeee dans la requete au WS
		String envoie = null;
		try {
			// ecriture de la chaine JSON par conversion de l'objet clientProxi avec Jackson
			envoie = mapper.writeValueAsString(clientProxi);
			// construction d'une requete HTTP
			// (notamment l'URL qui ne contient pas de
			// parametre ici)
			WebResource webResource = client.resource(this.debutUrl + "/creerClient");
			// ajout du JSOn a la requete
			// envoi de la requete au webservice
			// recuperation de la reponse
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, envoie);
			// extraction du JSON de la reponse HTTP
			retour = response.getEntity(String.class);
			// conversion du JSON recupere en objet de type Boolean
			cree = mapper.readValue(retour, Boolean.class);

		} catch (IOException e) {
			e.printStackTrace();
			// creation d'un log d'erreur en cas de catch
			ClientService.LOGGER
					.info("conseiller n� " + clientProxi.getConseiller().getIdConseiller() + " erreur : catch");
			// retour de la valeur FAUX en cas de catch
			return false;
		}
		// retour de la methode en fonction de la valeur du booleen obtenu
		if (cree.equals(false)) {
			// log d'erreur dans le cas ou le booleen obtenu a la valeur FAUX
			ClientService.LOGGER.info("conseiller n� " + clientProxi.getConseiller().getIdConseiller()
					+ " erreur : booleen retourne = FAUX");
			return false;
		} else {
			// log de reussite de la methode si le booleen obtenu a la valeur VRAI
			ClientService.LOGGER
					.info("conseiller n� " + clientProxi.getConseiller().getIdConseiller() + " client cree");
			return true;
		}
	}

	
	
	/**
	 * Methode qui s'adresse au WebService pour recuperer les information sur un
	 * client a partir de son identifiant
	 * 
	 * @param idClient
	 *            identifiant du client dont on souhaite les informations
	 * @return objet Clientproxi contenant les informations sur le client
	 */
	public ClientProxi obtenirClient(Integer idClient) {
		// log pour le debut de la methode
		ClientService.LOGGER.info(" Entree dans la methode obtenirClient");
		// declaration de l'objet a renvoyer
		ClientProxi clientProxi = null;
		// construction d'une requete HTTP (notamment l'URL contenant la valeur de
		// l'identifiant du client)
		WebResource webResource = client.resource(this.debutUrl + "/obtenirClient/" + idClient);
		// pas d'ajout de JSOn a la requete ici (envoi d'un parametre au WS, pas d'un
		// objet)
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// extraction du JSON de la reponse HTTP
		retour = response.getEntity(String.class);
		try {
			// conversion du JSON recupere en objet de type ClientProxi
			clientProxi = mapper.readValue(retour, ClientProxi.class);
		} catch (IOException e) {
			e.printStackTrace();
			// creation d'un log d'erreur en cas de catch
			ClientService.LOGGER.info(" erreur : catch");
			// retour d'un objet nul en cas de catch
			return null;
		}
		if (clientProxi.equals(null)) {
			// log d'erreur dans le cas ou l'objet obtenu est nul
			ClientService.LOGGER.info(" erreur : ClientProxi retourne = NULL");
		} else {
			// log de reussite de la methode dans le cas ou l'objet obtenu est non nul
			ClientService.LOGGER
					.info("conseiller n� " + clientProxi.getConseiller().getIdConseiller() + " client obtenu");
		}
		// retour de l'objet obtenu
		return clientProxi;

	}

	
	
	/**
	 * Methode qui s'adresse au WebService pour modifier un client present dans la
	 * base de donnees
	 * 
	 * @param clientProxi
	 *            objet ClientProxi contenant les nouvelles informations du client a
	 *            modifier
	 * @return booleen qui indique si la modification a bien ete effectuee
	 */
	public boolean modifierClient(ClientProxi clientAller) {
		// log pour le debut de la methode
		ClientService.LOGGER.info("conseiller n� " + clientAller.getConseiller().getIdConseiller()
				+ " Entree dans la methode modifierClients");
		// declaration de l'objet ClientProxi obtenu au cours de la methode
		ClientProxi clientRetour = null;
		// declaration de la chaine de caractere JSON envoyeee dans la requete au WS
		String envoie = null;
		try {
			// ecriture de la chaine JSON par conversion de l'objet clientProxi avec Jackson
			envoie = mapper.writeValueAsString(clientAller);
			
			// construction d'une requete HTTP (notamment l'URL contenant la valeur de
			// l'identifiant du client)
			WebResource webResource = client.resource(this.debutUrl + "/modifierClient/" + clientAller.getIdClient());
			// ajout du JSOn a la requete (ici on envoit a la fois l'id dans l'url et
			// l'objet dans le JSON)
			// envoi de la requete au webservice
			// recuperation de la reponse
			ClientResponse response = webResource.type("application/json").put(ClientResponse.class, envoie);
			// extraction du JSON de la reponse HTTP
			retour = response.getEntity(String.class);
			
			// conversion du JSON recupere en objet de type ClientProxi
			clientRetour = mapper.readValue(retour, ClientProxi.class);
		} catch (IOException e) {
			e.printStackTrace();
			// creation d'un log d'erreur en cas de catch
			ClientService.LOGGER
					.info("conseiller n� " + clientAller.getConseiller().getIdConseiller() + " erreur : catch");
			// retour de la valeur FAUX en cas de catch
			return false;
		}
		if (clientRetour.equals(null)) {

			// log d'erreur dans le cas ou l'objet obtenu est nul
			ClientService.LOGGER
					.info("conseiller n� " + clientAller.getConseiller().getIdConseiller() + " erreur : ClientProxi retourne = NULL");
			// retour de la valeur FAUX si l'objet obtenu est nul
			return false;
		} else {
			if (clientRetour.getPrenomClient().equals(clientAller.getPrenomClient()) && clientRetour.getNomClient().equals(clientAller.getNomClient()) && clientRetour.getAdresseClient().equals(clientAller.getAdresseClient()) && clientRetour.getEmailClient().equals(clientAller.getEmailClient())) {
				// log de reussite de la methode si l'objet obtenu est non nul
				ClientService.LOGGER
						.info("conseiller n� " + clientAller.getConseiller().getIdConseiller() + " client modifie");
				// retour de la valeur VRAI si l'objet obtenu n'est pas nul
				return true;
			} else {
				// log d'erreur dans le cas ou l'objet obtenu different de l'objet envoye
				ClientService.LOGGER.info("conseiller n� " + clientAller.getConseiller().getIdConseiller()
						+ " erreur : ClientProxi retourne different de celui envoye");
				return false;
			}
		}

	}

	
	
	/**
	 * methode qui s'adresse au WebService pour suprimmer un client de la base de
	 * donnees
	 * 
	 * @param clientProxi
	 *            objet ClientProxi contenant l'identifiant du client a supprimer
	 * @return boolean qui indique si la suppression a bien ete realisee
	 */
	public boolean supprimerClient(ClientProxi clientAller) {
		// log pour le debut de la methode
		ClientService.LOGGER.info("conseiller n� " + clientAller.getConseiller().getIdConseiller()
				+ " Entree dans la methode creerClients");
		// declaration de l'objet ClientProxi obtenu au cours de la methode
		Boolean supprime = false;
		// construction d'une requete HTTP (notamment l'URL contenant la valeur de
		// l'identifiant du client)
		WebResource webResource = client.resource(this.debutUrl + "/supprimerClient/" + clientAller.getIdClient());
		// pas d'ajout de JSON a la requete ici car on n'envoit pas d'objet au WS
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
		// extraction du JSON de la reponse HTTP
		retour = response.getEntity(String.class);
		try {
			// conversion du JSON recupere en objet de type ClientProxi
			supprime = mapper.readValue(retour, Boolean.class);
		} catch (IOException e) {
			e.printStackTrace();
			// creation d'un log d'erreur en cas de catch
			ClientService.LOGGER
					.info("conseiller n� " + clientAller.getConseiller().getIdConseiller() + " erreur : catch");
			// retour de la valeur FAUX en cas de catch
			return false;
		}
		if (supprime.equals(null)) {
			// log d'erreur dans le cas ou l'objet Boolean obtenu est nul
			ClientService.LOGGER.info("conseiller n� " + clientAller.getConseiller().getIdConseiller()
					+ " erreur : booleen retourne = NULL");
			// retour de la valeur FAUX si le booleen obtenu est nul
			return false;
		} else {
			if(supprime.equals(true)) {
			// log de reussite de la methode si l'objet obtenu est non nul
			ClientService.LOGGER
					.info("conseiller n� " + clientAller.getConseiller().getIdConseiller() + " client supprime");
			// retour de la valeur VRAI si le booleen obtenu est VRAI
			return true;
			}
			else {
				ClientService.LOGGER
				.error("conseiller n� " + clientAller.getConseiller().getIdConseiller() + " client non supprime");
		// retour de la valeur FAUX si le booleen obtenu est FAUX
		return false;
			}
		}
	}

	
	
	/**
	 * methode qui s'adresse au WebService pour recuperer une liste de l'integralite
	 * des clients de la banque (tous conseillers confondus)
	 * 
	 * @return liste d'objets ClientProxi contenant chacun les informations sur un
	 *         client
	 */
	public List<ClientProxi> obtenirClientsBanque() {
		// log pour le debut de la methode
		ClientService.LOGGER.info("gerant Entree dans la methode obtenirClientsBanque");
		// declaration de la liste d'objets ClientProxi obtenue au cours de la methode
		// et retournee en fin de methode
		List<ClientProxi> listeClients = null;
		// construction d'une requete HTTP
		// (notamment l'URL qui ne contient pas de
		// parametre ici car le traitement demande n'est pas associe a un client
		// particulier)
		WebResource webResource = client.resource(this.debutUrl+"/obtenirClientsBanque");
		// pas d'ajout de JSON a la requete ici car on n'envoit pas d'objet au WS
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// extraction du JSON de la reponse HTTP
		retour = response.getEntity(String.class);
		try {
			// conversion du JSON recupere en objet de type liste de ClientProxi
			listeClients = mapper.readValue(retour, new TypeReference<List<ClientProxi>>() {
			});
		} catch (IOException e) {
			// creation d'un log d'erreur en cas de catch
			ClientService.LOGGER.info("gerant erreur : catch");
			e.printStackTrace();
			return null;
		}
		if (listeClients.equals(null)) {
			// log d'erreur dans le cas ou la liste obtenue est nulle
			ClientService.LOGGER.info("gerant erreur : List<ClientProxi> retournee = NULL");
		} else {
			// log de reussite de la methode si la liste obtenue est non nul (vide ou non)
			ClientService.LOGGER.info("gerant Liste obtenue");
			if (listeClients.size() == 0) {
				// log d'info dans le cas ou la liste obtenue est vide
				ClientService.LOGGER.info("gerant info : List<ClientProxi> retournee est vide (taille = 0)");
			}
		}
		// retour de l'objet obtenu
		return listeClients;
	}
}
