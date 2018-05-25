package fr.gtm.proxibanque.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Compte;
import fr.gtm.proxibanque.domaine.Transaction;

/**
 * Classe de service utilisee par la couche de presentation, en ce qui concerne
 * les operations liees a la gestion des comptes. Pour effectuer les traitements
 * correspondant, elle consomme le WebService.
 *
 * @author groupe 1
 *
 */
public class CompteService {
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
	private static final Logger LOGGER = LoggerFactory.getLogger(CompteService.class);

	/**
	 * propriete etant la base fixe de l'url utilisee pour consommer le WebService
	 * concernant le client (concatenee dans les methodes avec des suffixes adaptes
	 * aux operations realisees)
	 */
	private String debutUrl = "http://192.168.1.70:8080/webservice_1.0/api/compteWS";

	/**
	 * methode qui s'adresse au WebService pour recuperer une liste de tous les
	 * comptes de la banque
	 *
	 * @return liste d'objets de type Compte (tous compte quelque soit le client ou
	 *         le conseiller associé)
	 */
	public List<Compte> obtenirComptesBanque() {
		// log pour le debut de la methode
		CompteService.LOGGER.info("gerant Entree dans la methode obtenirComptesBanque");
		// declaration de la liste d'objets Compte obtenue au cours de la methode
		// et retournee en fin de methode
		List<Compte> listeComptes = null;
		// construction d'une requete HTTP
		// (notamment l'URL qui ne contient pas de
		// parametre ici car le traitement demande n'est pas associe a un client
		// particulier)
		WebResource webResource = this.client.resource(this.debutUrl + "/obtenirComptesBanque");
		// pas d'ajout de JSON a la requete ici car on n'envoit pas d'objet au WS
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// extraction du JSON de la reponse HTTP
		this.retour = response.getEntity(String.class);
		try {
			// conversion du JSON recupere en objet de type liste de Compte
			listeComptes = this.mapper.readValue(this.retour, new TypeReference<List<Compte>>() {
			});
		} catch (IOException e) {
			// creation d'un log d'erreur en cas de catch
			CompteService.LOGGER.info("gerant erreur : catch");
			e.printStackTrace();
			return null;
		}
		if (listeComptes.equals(null)) {
			// log d'erreur dans le cas ou la liste obtenue est nulle
			CompteService.LOGGER.info("gerant erreur : List<Compte> retournee = NULL");
		} else {
			// log de reussite de la methode si la liste obtenue est non nul (vide ou non)
			CompteService.LOGGER.info("gerant Liste obtenue");
			if (listeComptes.size() == 0) {
				// log d'info dans le cas ou la liste obtenue est vide
				CompteService.LOGGER.info("gerant info : List<Compte> retournee est vide (taille = 0)");
			}
		}
		// retour de l'objet obtenu
		return listeComptes;
	}

	/**
	 * methode qui s'adresse au WebService pour recuperer une liste des comptes
	 * possedes par un client donne
	 *
	 * @return liste d'objets Compte contenant chacun les informations sur un compte
	 *         du client
	 * @param objet
	 *            ClientProxi contenant l'identifiant du client permettant de
	 *            filtrer les comptes de la liste retournee
	 */
	public List<Compte> obtenirListeComptesClient(ClientProxi clientProxi) {
		// log pour le debut de la methode
		CompteService.LOGGER.info(clientProxi.getConseiller().getPrenomConseiller() + " "
				+ clientProxi.getConseiller().getNomConseiller() + " Entree dans la methode obtenirListComptesClients");
		// declaration de la liste d'objets Compte obtenue au cours de la methode
		// et retournee en fin de methode
		List<Compte> listeComptes = null;
		// construction d'une requete HTTP
		// (notamment l'URL qui contient l'identifiant du client dont on veut les
		// comptes)
		WebResource webResource = this.client.resource(this.debutUrl + "/obtenirComptesClient/" + clientProxi.getIdClient());
		// pas d'ajout de JSON a la requete ici car on n'envoit pas d'objet au WS
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// extraction du JSOn de la reponse HTTP
		this.retour = response.getEntity(String.class);
		try {
			// conversion du JSON en objet liste
			listeComptes = this.mapper.readValue(this.retour, new TypeReference<List<Compte>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
			// log d'erreur en cas de catch
			CompteService.LOGGER.error(clientProxi.getConseiller().getPrenomConseiller() + " "
					+ clientProxi.getConseiller().getNomConseiller()
					+ " Erreur : renvoi d'exception et echec de la methode");
			return null;
		}
		// log de reussite
		CompteService.LOGGER.info(clientProxi.getConseiller().getPrenomConseiller() + " "
				+ clientProxi.getConseiller().getNomConseiller() + " Methode reussie : liste renvoyee");
		return listeComptes;
	}

	/**
	 * methode utilisant le WebService pour effectuer un virement entre deux comptes
	 * donnes
	 *
	 * @param compte1
	 *            objet compte contenant l'identifiant du compte emetteur
	 * @param compte2
	 *            objet compte contenant l'identifiant du compte destinataire
	 * @param montant
	 *            valeur du montant transfere
	 * @return boolean indiquant si le virement a pu avoir lieu ou non
	 */
	public boolean virement(Compte compte1, Compte compte2, Double montant) {
		// log pour le debut de la methode
		Transaction transaction = new Transaction(null, null, compte1.getIdCompte(), compte2.getIdCompte(), montant);
		//		CompteService.LOGGER.info(compte1.getClient().getConseiller().getPrenomConseiller() + " "
		//				+ compte1.getClient().getConseiller().getNomConseiller() + " Entree dans la methode virement");
		// declaration du boolen obtenue au cours de la methode
		Boolean virementFait = false;
		// Déclaration du flux JSON envoye au WebService
		String envoie = null;
		try {
			// construction d'une requete HTTP
			// (notamment l'URL qui ne contient pas de
			// parametre ici )
			envoie = this.mapper.writeValueAsString(transaction);
			// construction d'une requete HTTP
			// (notamment l'URL qui ne contient pas de parametre ici)
			WebResource webResource = this.client.resource(this.debutUrl + "/virement");
			// ajout du JSON a la requete
			// envoi de la requete au webservice
			// recuperation de la reponse
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, envoie);
			// extraction du JSON de la reponse HTTP
			this.retour = response.getEntity(String.class);
			// Conversion du JSON en onjet de type Booleen
			virementFait = this.mapper.readValue(this.retour, Boolean.class);
		} catch (IOException e) {
			e.printStackTrace();
			// log en cas de catch
			//			CompteService.LOGGER.error(compte1.getClient().getConseiller().getPrenomConseiller() + " "
			//					+ compte1.getClient().getConseiller().getNomConseiller()
			//					+ " Erreur : renvoi d'exception et echec de la methode");
			return false;
		}

		if (virementFait.equals(true)) {
			// log de reussite si le Boleen obtenu est VRAI
			//			CompteService.LOGGER.info(compte1.getClient().getConseiller().getPrenomConseiller() + " "
			//					+ compte1.getClient().getConseiller().getNomConseiller() + "virement reussi");
			return true;
		} else {
			// log d'echec si le Booleen obtenu est FAUX
			//			CompteService.LOGGER.info(compte1.getClient().getConseiller().getPrenomConseiller() + " "
			//					+ compte1.getClient().getConseiller().getNomConseiller()
			//					+ "echec du virement (booleen obtenu = FAUX)");
			return false;
		}
	}

	/**
	 * methode qui utilise le WebService pour obtenir une listedes comptes qui sont
	 * a decouvert (dans toute la banque)
	 *
	 * @return liste d'objets de type Compte
	 */
	public List<Compte> obtenirComptesDecouvert() {
		// log pour le debut de la methode
		CompteService.LOGGER.info("gerant Entree dans la methode obtenirComptesDecouvert");
		// declaration de la liste d'objets ClientProxi obtenue au cours de la methode
		// et retournee en fin de methode
		List<Compte> listeComptes = null;
		// construction d'une requete HTTP
		// (notamment l'URL qui ne contient pas de
		// parametre ici car le traitement demande n'est pas associe a un client
		// particulier)
		WebResource webResource = this.client.resource(this.debutUrl + "/obtenirComptesDecouvert");
		// pas d'ajout de JSON a la requete ici car on n'envoit pas d'objet au WS
		// envoi de la requete au webservice
		// recuperation de la reponse
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// extraction du JSON de la reponse HTTP
		this.retour = response.getEntity(String.class);
		try {
			// conversion du JSON recupere en objet de type liste de Compte
			listeComptes = this.mapper.readValue(this.retour, new TypeReference<List<Compte>>() {
			});
		} catch (IOException e) {
			// creation d'un log d'erreur en cas de catch
			CompteService.LOGGER.info("gerant erreur : catch");
			e.printStackTrace();
			return null;
		}
		if (listeComptes.equals(null)) {
			// log d'erreur dans le cas ou la liste obtenue est nulle
			CompteService.LOGGER.info("gerant erreur : List<Compte> retournee = NULL");
		} else {
			// log de reussite de la methode si la liste obtenue est non nul (vide ou non)
			CompteService.LOGGER.info("gerant Liste obtenue");
			if (listeComptes.size() == 0) {
				// log d'info dans le cas ou la liste obtenue est vide
				CompteService.LOGGER.info("gerant info : List<Compte> retournee est vide (taille = 0)");
			}
		}
		// retour de l'objet obtenu
		return listeComptes;
	}

}
