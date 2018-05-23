package fr.gtm.proxibanque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.service.ConseillerService;

@ManagedBean
public class ConseillerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Proprietés
	// =======================================================
	private Conseiller conseiller = new Conseiller(1, "xxx", "xxx", "xxx", "xxx");

	private Integer idConseiller;
	private String nomConseiller;
	private String prenomConseiller;
	private String login;
	private String password;
	private List<ClientProxi> clientsConseiller;

	public ConseillerService conseillerService;

	// constructeurs
	// =======================================================

	/**
	 * no-arg constructeur
	 */
	public ConseillerBean() {
		super();
	}

	/**
	 * constructeur
	 */
	public ConseillerBean(Integer idConseiller, String nomConseiller, String prenomConseiller, String login,
			String password) {
		super();
		this.idConseiller = idConseiller;
		this.nomConseiller = nomConseiller;
		this.prenomConseiller = prenomConseiller;
		this.login = login;
		this.password = password;
		this.clientsConseiller = new ArrayList<ClientProxi>();
	}

	// getters & setters
	// =======================================================

	public Integer getIdConseiller() {
		return this.idConseiller;
	}

	public void setIdConseiller(Integer idConseiller) {
		this.idConseiller = idConseiller;
	}

	public String getNomConseiller() {
		return this.nomConseiller;
	}

	public void setNomConseiller(String nomConseiller) {
		this.nomConseiller = nomConseiller;
	}

	public String getPrenomConseiller() {
		return this.prenomConseiller;
	}

	public void setPrenomConseiller(String prenomConseiller) {
		this.prenomConseiller = prenomConseiller;
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

	public List<ClientProxi> getClientsConseiller() {
		return this.clientsConseiller;
	}

	public void setClientsConseiller(List<ClientProxi> clientsConseiller) {
		this.clientsConseiller = clientsConseiller;
	}

	// méthodes
	// =======================================================

	/**
	 * méthode qui permet l'authentification de l'utilisateur via un appel à la
	 * méthode authentification() de conseillerService
	 *
	 * @param login
	 *            et password de l'utilisateur
	 * @return boolean qui valide ou non l'authentification
	 */
	// public String authentification() {
	//
	// String casNavigation = "null";
	// Integer ret = 0;
	// ret = conseillerService.authentification(login, password);
	// if (ret = 1) {
	// // validation de l'authentification
	// return casNavigation = "succes";
	// } else {
	// return "echec";
	// }
	// }

	// ------------

	/**
	 * méthode qui permet la lecture de la liste des clients d'un conseillervia un
	 * appel à la méthode authentification() de conseillerService
	 *
	 * @param Integer
	 *            id du conseiller idConseiller dont on souhaite lire la liste des
	 *            clients
	 * @return boolean qui valide ou non l'authentification
	 */
	public void obtenirListeClientsConseiller() {
		ConseillerService conseillerService = new ConseillerService();
		this.clientsConseiller = conseillerService.obtenirListeClientsConseiller(this.conseiller);

	}

}