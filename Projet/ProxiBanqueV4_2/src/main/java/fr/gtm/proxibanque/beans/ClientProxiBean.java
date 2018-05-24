package fr.gtm.proxibanque.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Compte;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.service.ClientService;
import fr.gtm.proxibanque.service.ConseillerService;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties({ "comptesClient" })

@ManagedBean
@ViewScoped
public class ClientProxiBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Proprietés
	// =======================================================
	private ClientProxi clientProxi = new ClientProxi() ;
	public Conseiller conseiller  ;
	private List<Compte> comptesClient;
	private List<ClientProxi> listClient  ;

	ClientService clientService = new ClientService();
	ConseillerService conseillerService = new ConseillerService();
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

	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	// méthodes
	// =======================================================
	@PostConstruct
	public void init() {
		this.conseiller = new Conseiller(1, "mouja", "MOULIN", "MOULIN",
				"456");
		// String test = (String) this.sessionMap.get("IdmonConseiller");

		System.out.println("monconseiller recup clientproxi bean " + this.conseiller);
		// System.out.println("recup "+ test);
		this.listClient = this.clientService.obtenirClientsBanque();
		//		System.out.println("La liste des clients initial : " + this.listClient);

	}

	/**
	 * méthode qui permet de créer un client en base de données via un appel à la
	 * méthode creerClient() de clientProxiService
	 *
	 * @param clientProxi
	 *            : objet de type ClientProxi
	 * @return boolean qui valide ou non la création en base
	 */
	public Object creerClient(ClientProxi monClient) {

		System.out.println("Nom Client ajout : " + monClient.getNomClient());
		this.clientProxi.setConseiller(this.conseiller);
		boolean result = this.clientService.creerClient(this.clientProxi);
		FacesContext context = FacesContext.getCurrentInstance();
		//		context.addMessage("messageError", new FacesMessage("CLient Creer"));
		//		return "liste-clients.xhtml";

		if (result == true) {

			context.addMessage("messagevalid", new FacesMessage("CLient Creer"));
			return "liste-clients.xhtml";
		}else {
			context.addMessage("messagevalid", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de création du client ","Creation"));
			return "creation-client.xhtml";
		}
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

		return this.clientService.obtenirClient(idClientProxi);
		// TODO vérifier le nommage de clientProxiService et ses méthodes
	}



	public Object obtenirListClient(Conseiller monConseiller) {
		this.conseiller = monConseiller;
		this.setConseiller(monConseiller);
		System.out.println(this.conseiller);
		this.listClient = this.conseillerService.obtenirListeClientsConseiller(this.conseiller);
		System.out.println("la liste des clients spec" + this.listClient);
		//		this.setListClient(this.listClient);
		return "liste-clients.xhtml";
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
	public Object modifierClient(ClientProxi clientProxirec) {

		// clientProxirec.setConseiller(this.conseiller);
		// System.out.println("Mon Client Modifier : " + clientProxirec);
		this.clientProxi.setConseiller(this.conseiller);
		System.out.println("Mon Client Modifier proxi : " + this.clientProxi);

		boolean result = this.clientService.modifierClient(this.clientProxi);
		FacesContext context = FacesContext.getCurrentInstance();
		if (result == true) {

			context.addMessage("messagevalid", new FacesMessage("CLient Modifier"));
			return "liste-clients.xhtml";
		}else {
			context.addMessage("messagevalid", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de Modification","Modification"));
			return "modification-client.xhtml";
		}
		// TODO vérifier le nommage de clientProxiService et ses méthodes
	}

	// ------------

	/**
	 * méthode qui permet de supprimer un client en base de données via un appel à
	 * la méthode supprimerClient() de clientProxiService
	 *
	 * @param clientProxi
	 *            : objet de type ClientProxi
	 * @return boolean qui valide ou non la suppression en base
	 */

	public Object supprimerClient(ClientProxi clientProxi) {
		ClientService clientService = new ClientService();
		FacesContext context = FacesContext.getCurrentInstance();
		boolean result = this.clientService.supprimerClient(clientProxi);
		if (result == true) {
			context.addMessage("messagevalid", new FacesMessage("Client Supprimer"));

		}else {
			context.addMessage("messageErreur", new FacesMessage("Erreur de Suppression"));
		}
		return "liste-clients.xhtml";
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

		System.out.println(this.conseiller.getNomConseiller());
		ClientService clientService = new ClientService();
		System.out.println("On test la liste");
		System.out.println(this.listClient);
		this.listClient = clientService.obtenirClientsBanque();
		System.out.println(this.listClient);
		return "liste-clients.xhtml";
		// TODO vérifier le nommage de clientProxiService et ses méthodes

	}

	public Object affichermodifierClient(ClientProxi monClient) {
		this.clientProxi = monClient;
		return "modification-client";
	}

}