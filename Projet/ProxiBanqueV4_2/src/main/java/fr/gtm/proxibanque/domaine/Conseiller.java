package fr.gtm.proxibanque.domaine;

import fr.gtm.proxibanque.domaine.ClientProxi;

import java.util.ArrayList;
import java.util.List;

public class Conseiller {

	private Integer idConseiller;

	private String nomConseiller;

	private String prenomConseiller;

	private String login;

	private String password;

	private List<ClientProxi> clientsConseiller;

	public Conseiller() {
		super();
	}

	public Conseiller(Integer idConseiller, String nomConseiller, String prenomConseiller, String login,
			String password) {
		super();
		this.idConseiller = idConseiller;
		this.nomConseiller = nomConseiller;
		this.prenomConseiller = prenomConseiller;
		this.login = login;
		this.password = password;
		this.clientsConseiller = new ArrayList<ClientProxi>();
	}

	public Integer getIdConseiller() {
		return idConseiller;
	}

	public void setIdConseiller(Integer idConseiller) {
		this.idConseiller = idConseiller;
	}

	public String getNomConseiller() {
		return nomConseiller;
	}

	public void setNomConseiller(String nomConseiller) {
		this.nomConseiller = nomConseiller;
	}

	public String getPrenomConseiller() {
		return prenomConseiller;
	}

	public void setPrenomConseiller(String prenomConseiller) {
		this.prenomConseiller = prenomConseiller;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ClientProxi> getClientsConseiller() {
		return clientsConseiller;
	}

	public void setClientsConseiller(List<ClientProxi> clientsConseiller) {
		this.clientsConseiller = clientsConseiller;
	}

}
