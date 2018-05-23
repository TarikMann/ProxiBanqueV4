package fr.gtm.proxibanque.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Compte;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.service.ClientService;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties({ "comptesClient" })

@ManagedBean

public class ClientProxiBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Proprietés
	// =======================================================
	private ClientProxi clientProxi = new ClientProxi() ;
	private Conseiller conseiller;
	private List<Compte> comptesClient;
	private List<ClientProxi> listClient;

	// constructeurs
	// =======================================================

	/**
	 * no-arg constructeur
	 */
	public ClientProxiBean() {
		super();
	}

	/**
	 * constructeur
	 */

	// getters & setters
	// =======================================================

	public Conseiller getConseiller() {
		return this.conseiller;
	}

	public ClientProxi getClientProxi() {
		return this.clientProxi;
	}

	public void setClientProxi(ClientProxi clientProxi) {
		this.clientProxi = clientProxi;
	}

	public List<ClientProxi> getListClient() {
		return this.listClient;
	}

	public void setListClient(List<ClientProxi> listClient) {
		this.listClient = listClient;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public List<Compte> getComptesClient() {
		return this.comptesClient;
	}

	public void setComptesClient(List<Compte> comptesClient) {
		this.comptesClient = comptesClient;
	}

	// méthodes
	// =======================================================
	@PostConstruct
	public void init() {
		this.conseiller = new Conseiller(1, "tata", "titi", "toto", "tutu");
		ClientService clientService = new ClientService();
		this.listClient = clientService.obtenirClientsBanque();
		System.out.println("La liste des clients : " + this.listClient);
	}

	/**
	 * méthode qui permet de créer un client en base de données via un appel à
	 * la méthode creerClient() de clientProxiService
	 *
	 * @param clientProxi
	 *            : objet de type ClientProxi
	 * @return boolean qui valide ou non la création en base
	 */
	public void creerClient(ClientProxi monClient) {

		ClientService clientService = new ClientService();
		System.out.println("Nom Client ajout : " + monClient.getNomClient());
		this.clientProxi.setConseiller(this.conseiller);
		clientService.creerClient(this.clientProxi);
	}

	// ------------

	/**
	 * méthode qui permet de lire les informations d'un client en base de données
	 * via un appel à la méthode obtenirClient() de clientProxiService
	 *
	 * @param Integer
	 *            IdClientProxi : identifiant de clientProxi
	 * @return clientProxi : objet de type ClientProxi
	 */
	public ClientProxi obtenirClient(Integer idClientProxi) {
		ClientService clientService = new ClientService();
		return clientService.obtenirClient(idClientProxi);
		// TODO vérifier le nommage de clientProxiService et ses méthodes
	}

	// ------------

	/**
	 * méthode qui permet de modifier les informations d'un client en base de
	 * données via un appel à la méthode modifierClient() de clientProxiService
	 *
	 * @param clientProxi
	 *            : objet de type ClientProxi
	 * @return boolean qui valide ou non la modification en base
	 */
	public boolean modifierClient(ClientProxi clientProxi) {
		ClientService clientService = new ClientService();
		return clientService.modifierClient(clientProxi);
		// TODO vérifier le nommage de clientProxiService et ses méthodes
	}

	// ------------

	/**
	 * méthode qui permet de supprimer un client en base de données via un appel
	 * à la méthode supprimerClient() de clientProxiService
	 *
	 * @param clientProxi
	 *            : objet de type ClientProxi
	 * @return boolean qui valide ou non la suppression en base
	 */

	public boolean supprimerClient(ClientProxi clientProxi) {
		ClientService clientService = new ClientService();
		return clientService.supprimerClient(clientProxi);
		// TODO vérifier le nommage de clientProxiService et ses méthodes
	}

	// ------------

	/**
	 * méthode qui permet de lire la liste des clients de la banque via un appel à
	 * la méthode obtenirClientsBanque() de clientProxiService
	 *
	 * @param aucun
	 *            paramètre
	 * @return liste des clients de la banque
	 */

	public Object obtenirClientsBanque() {
		ClientService clientService = new ClientService();
		System.out.println("On test la liste");
		System.out.println(this.listClient);
		this.listClient = clientService.obtenirClientsBanque();
		System.out.println(this.listClient);
		return "liste-clients.xhtml";
		// TODO vérifier le nommage de clientProxiService et ses méthodes

	}

}