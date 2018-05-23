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
 * les operations liees a la gestion des clients. Pour effectuer les traitements
 * correspondant, elle consomme le WebService.
 * 
 * @author Stagiaire
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

		ConseillerService.LOGGER.info(conseiller.getPrenomConseiller()+" "+conseiller.getNomConseiller()+" Entree dans la methode obtenirListClientsConseiller");

		List<ClientProxi> listeClients = null;
		WebResource webResource = client
				.resource(this.debutUrl + "/obtenirListeClientsConseiller/" + conseiller.getIdConseiller());
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		retour = response.getEntity(String.class);
		try {
			listeClients = mapper.readValue(retour, new TypeReference<List<ClientProxi>>() {
			});
			
		} catch (IOException e) {
			e.printStackTrace();
			ConseillerService.LOGGER.error(conseiller.getPrenomConseiller()+" "+conseiller.getNomConseiller()+" Erreur : renvoi d'exception et echec de la methode");
			return null;
		}
		ConseillerService.LOGGER.info(conseiller.getPrenomConseiller()+" "+conseiller.getNomConseiller()+" Methode reussie : liste renvoyee");
		return listeClients;
	}

}
