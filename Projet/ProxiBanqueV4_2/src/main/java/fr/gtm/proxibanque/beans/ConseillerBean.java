package fr.gtm.proxibanque.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.service.ConseillerService;

@ManagedBean

@SessionScoped
public class ConseillerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Proprietés
	// =======================================================

	public Conseiller conseiller;
	private String login;
	private String password;
	private List<ClientProxi> clientsConseiller;

	public ConseillerService conseillerService = new ConseillerService();
	public ClientProxiBean monClientProxi = new ClientProxiBean();

	// constructeurs
	// =======================================================

	/**
	 * no-arg constructeur
	 */
	public ConseillerBean() {
		super();
		this.conseiller = new Conseiller();
	}

	public ConseillerBean(Conseiller conseiller, String login, String password, List<ClientProxi> clientsConseiller,
			ConseillerService conseillerService) {
		super();
		this.conseiller = conseiller;
		this.login = login;
		this.password = password;
		this.clientsConseiller = clientsConseiller;
		this.conseillerService = conseillerService;
	}

	/**
	 * constructeur
	 */

	// getters & setters
	// =======================================================

	public List<ClientProxi> getClientsConseiller() {
		return this.clientsConseiller;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ConseillerBean(Conseiller conseiller, List<ClientProxi> clientsConseiller) {
		super();
		this.conseiller = conseiller;
		this.clientsConseiller = clientsConseiller;
	}

	public Conseiller getConseiller() {
		return this.conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public void setClientsConseiller(List<ClientProxi> clientsConseiller) {
		this.clientsConseiller = clientsConseiller;
	}

	// méthodes
	// =======================================================

	// ------------

	/**
	 * méthode qui permet la lecture de la liste des clients d'un conseillervia un
	 * appel à la méthode authentification() de conseillerService
	 *
	 *
	 */
	public void obtenirListeClientsConseiller() {
		ConseillerService conseillerService = new ConseillerService();
		this.clientsConseiller = conseillerService.obtenirListeClientsConseiller(this.conseiller);

	}

	public Object deconnexion() {

		return "index";
	}

	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public Object authentification() {
		System.out.println(this.login);
		this.conseiller = new Conseiller(this.login, this.password);

		System.out.println(this.conseiller.getLogin());

		System.out.println("1er conseiller" + this.conseiller.getPassword());
		boolean authentifie = this.conseillerService.authentification(this.conseiller);

		System.out.println(authentifie);
		if (authentifie == true) {

			Conseiller monConseillerUser = this.conseillerService.recuperationConseiller(this.conseiller);
			System.out.println(monConseillerUser.getNomConseiller() + monConseillerUser.getIdConseiller());
			//			this.monClientProxi.obtenirListClient(monConseillerUser);
			this.monClientProxi.obtenirListClient(monConseillerUser);
			// partage du conseiller
			return "liste-clients";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("messageError", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login et mot de passe erroné","Authentification"));

			return "authentification.xhtml";
		}

	}

}