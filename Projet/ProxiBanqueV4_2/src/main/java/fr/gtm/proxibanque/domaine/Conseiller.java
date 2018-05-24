package fr.gtm.proxibanque.domaine;

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

	public Conseiller(String login,	String password) {

		this.login = login;
		this.password = password;

	}

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

	@Override
	public String toString() {
		return "Conseiller [id=" + this.idConseiller + ", login=" + this.login + ", password=" + this.password + ", Nom=" + this.nomConseiller + ", Prenom=" + this.prenomConseiller + "]";
	}



}
