package fr.gtm.proxibanque.domaine;

import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"comptesClient"})
public class ClientProxi {
	
	// Proprietées

	private Integer idClient;
	
	
	private String nomClient;
	
	
	private String prenomClient;
	

	private String emailClient;
	

	private String adresseClient;
	

	private Conseiller conseiller;
	

	private List<Compte> comptesClient;

	/**
	 * Constructeur vide
	 */
	public ClientProxi() {
		super();
	}


	public ClientProxi(Integer idClient, String nomClient, String prenomClient, String emailClient,
			String adresseClient) {
		super();
		this.idClient = null;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.adresseClient = adresseClient;
	}
	
	public ClientProxi(Integer idClient, String nomClient, String prenomClient, String emailClient,
			String adresseClient, Compte comptesClient) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.adresseClient = adresseClient;
		this.comptesClient = new ArrayList<Compte>();
	}

	
	public Integer getIdClient() {
		return idClient;
	}


	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}


	public String getNomClient() {
		return nomClient;
	}


	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}


	public String getPrenomClient() {
		return prenomClient;
	}


	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}


	public String getEmailClient() {
		return emailClient;
	}


	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}


	public String getAdresseClient() {
		return adresseClient;
	}


	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}

	
	public Conseiller getConseiller() {
		return conseiller;
	}


	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}


	public List<Compte> getComptesClient() {
		return comptesClient;
	}


	public void setComptesClient(List<Compte> comptesClient) {
		this.comptesClient = comptesClient;
	}

	
	
	public String toString() {
		return this.prenomClient+" "+this.nomClient;
	}
	


	
	

}
