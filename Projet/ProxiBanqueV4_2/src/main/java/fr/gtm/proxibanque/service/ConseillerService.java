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
import fr.gtm.proxibanque.domaine.Conseiller;

/**
 * Classe de service utilisee par la couche de presentation, en ce qui concerne
 * les operations liees a la gestion des conseillers. Pour effectuer les
 * traitements correspondant, elle consomme le WebService.
 * 
 * @author groupe 1
 *
 */
public class ConseillerService {

	private ObjectMapper mapper = new ObjectMapper();
	Client client = Client.create();
	String retour = null;

	/**
	 * propriete etant la base fixe de l'url utilisee pour consommer le WebService
	 * concernant le conseiller (concatenee dans les methodes avec des suffixes
	 * adaptes aux operations realisees)
	 */
	private String debutUrl = "http://192.168.1.70:8080/webservice_1.0/api/conseillerWS";

	private static final Logger LOGGER = LoggerFactory.getLogger(ConseillerService.class);

	// TODO methode authentification

	/**
	 * methode qui s'adresse au WebService pour recuperer une liste des clients
	 * affectes a un conseiller donne
	 * 
	 * @return liste d'objets ClientProxi contenant chacun les informations sur un
	 *         client
	 * @param objet
	 *            Conseiller contenant l'identifiant du conseiller permettant de
	 *            filtrer les clients de la liste retournee
	 */
	public List<ClientProxi> obtenirListeClientsConseiller(Conseiller conseiller) {
		// log pour le debut de la methode
		ConseillerService.LOGGER.info(conseiller.getPrenomConseiller() + " " + conseiller.getNomConseiller()
				+ " Entree dans la methode obtenirListClientsConseiller");
		// declaration d'un d'une liste de clients pour recuperer la liste renvoyee par
		// le webservice et qui sera retournee par la methode
		List<ClientProxi> listeClients = null;
		// construction d'une requete HTTP
		// (notamment l'URL qui contient l'identifiant du conseiller)
		// pas d'ajout de JSON a la requete ici car on n'envoit pas d'objet au WS
		WebResource webResource = client
				.resource(this.debutUrl + "/obtenirListeClientsConseiller/" + conseiller.getIdConseiller());
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// extraction du JSON de la reponse HTTP
		retour = response.getEntity(String.class);
		try {
			// conversion du JSON recupere en objet de type liste
			listeClients = mapper.readValue(retour, new TypeReference<List<ClientProxi>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
			// creation d'un log d'erreur en cas de catch
			ConseillerService.LOGGER.error(conseiller.getPrenomConseiller() + " " + conseiller.getNomConseiller()
					+ " Erreur : renvoi d'exception et echec de la methode");
			return null;
		}
		// log de reussite de la methode
		ConseillerService.LOGGER.info(conseiller.getPrenomConseiller() + " " + conseiller.getNomConseiller()
				+ " Methode reussie : liste renvoyee");
		return listeClients;
	}

	/**
	 * methode d'authentification qui consomme me WebService pour verifier que le
	 * login et le mot de passe entres sont conformes a la valeur presente en base
	 * de donnee (dans le cas ou le login correspond a un conseiller dans la base de
	 * donnee)
	 * 
	 * @param conseiller
	 *            objet Conseiller contenant le login et le mot d epasse saisis
	 * @return booleen qui indique si le mot de passe saisi correspond au login
	 *         saisi
	 */
	public boolean authentification(Conseiller conseiller) {
		// log pour le debut de la methode
		ConseillerService.LOGGER.info("conseiller n " + conseiller.getPrenomConseiller() + " "
				+ conseiller.getNomConseiller() + " Entree dans la methode creerClients");
		// declaration d'un objet Conseiller pour recuperer le conseiller renvoye par le
		// webservice
		Conseiller conseillerRetour;
		// declaration de la chaine de caractere JSON envoyeee dans la requete au WS
		String envoie = null;
		try {
			// ecriture de la chaine JSON par conversion de l'objet Conseiller avec Jackson
			envoie = mapper.writeValueAsString(conseiller);
			// construction d'une requete HTTP
			// (notamment l'URL qui ne contient pas de
			// parametre ici)
			WebResource webResource = client.resource(this.debutUrl + "/authentification");
			// ajout du JSOn a la requete
			// envoi de la requete au webservice
			// recuperation de la reponse
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, envoie);
			// extraction du JSON de la reponse HTTP
			retour = response.getEntity(String.class);
			// conversion du JSON recupere en objet de type Boolean
			conseillerRetour = mapper.readValue(retour, Conseiller.class);

		} catch (IOException e) {
			e.printStackTrace();
			// creation d'un log d'erreur en cas de catch
			ConseillerService.LOGGER.info("conseiller n " + conseiller.getPrenomConseiller() + " "
					+ conseiller.getNomConseiller() + " erreur : catch");
			// retour de la valeur FAUX en cas de catch
			return false;
		}
		// retour de la methode en fonction de l'objet Conseiller obtenu
		if (conseillerRetour.equals(null)) {
			// log d'erreur dans le cas ou le booleen obtenu a la valeur FAUX
			ConseillerService.LOGGER.info("conseiller n " + conseiller.getPrenomConseiller() + " "
					+ conseiller.getNomConseiller() + " erreur : conseiler retourne = null");
			return false;
		} else {
			// log de reussite de la methode si le booleen obtenu a la valeur VRAI
			ConseillerService.LOGGER.info("conseiller n " + conseiller.getPrenomConseiller() + " "
					+ conseiller.getNomConseiller() + " conseiller authentifie");
			return true;
		}
	}
}
