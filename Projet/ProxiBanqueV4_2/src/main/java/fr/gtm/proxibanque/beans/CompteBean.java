package fr.gtm.proxibanque.beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Compte;
import fr.gtm.proxibanque.service.ClientService;
import fr.gtm.proxibanque.service.CompteService;

@ManagedBean
@SessionScoped
public class CompteBean {

	private Compte compte = new Compte() ;

	private ClientProxi client ;
	private List<Compte> listeComptes;
	private List<Compte> listeTousComptes;
	private ClientService clientService;
	private CompteService compteService = new CompteService();

	// ===========================================================

	public CompteBean() {
		super();
	}



	public CompteBean(Compte compte, ClientProxi client, List<Compte> listeComptes, List<Compte> listeTousComptes,
			ClientService clientService, CompteService compteService, Map<String, Object> sessionMap) {
		super();
		this.compte = compte;
		this.client = client;
		this.listeComptes = listeComptes;
		this.listeTousComptes = listeTousComptes;
		this.clientService = clientService;
		this.compteService = compteService;
		this.sessionMap = sessionMap;
	}



	public CompteBean(Compte compte, ClientProxi client, List<Compte> listeComptes, ClientService clientService) {
		super();
		this.compte = compte;
		this.client = client;
		this.listeComptes = listeComptes;
		this.clientService = clientService;

	}



	// ===========================================================




	public List<Compte> getListeTousComptes() {
		return this.listeTousComptes;
	}



	public void setListeTousComptes(List<Compte> listeTousComptes) {
		this.listeTousComptes = listeTousComptes;
	}



	public ClientProxi getClient() {
		return this.client;
	}

	public Compte getCompte() {
		return this.compte;
	}



	public void setCompte(Compte compte) {
		this.compte = compte;
	}



	public ClientService getClientService() {
		return this.clientService;
	}



	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}



	public void setClient(ClientProxi client) {
		this.client = client;
	}

	public List<Compte> getListeComptes() {
		return this.listeComptes;
	}

	public void setListeComptes(List<Compte> listeComptesClient) {
		this.listeComptes = listeComptesClient;
	}

	// =======================================================

	@PostConstruct
	public void init() {
		System.out.println("La liste des comptes : " +this.listeComptes);
		this.listeTousComptes = this.compteService.obtenirComptesBanque();
		System.out.println("Lister tous les comptes : " + this.listeTousComptes);

	}

	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	// =======================================================
	public Object obtenirlisteDesCompte(ClientProxi monClient) {

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

		// recuperer l'idClient

		System.out.println("val recup dans obtenir ListeDesCompte : " + monClient);
		this.client = monClient;



		// remplir liste comptes
		this.listeComptes = this.compteService.obtenirListeComptesClient(monClient);
		System.out.println("La liste des comptes" + this.listeComptes);

		return "liste-comptes-client";
	}


	public Object affichagevirement(ClientProxi monClient) {


		// recuperer l'idClient

		System.out.println("val recup dans obtenir ListeDesCompte : " + monClient);
		// Cast de String a Integer
		Integer IdClient = monClient.getIdClient();
		// Remplir l'objet ClientProxi
		System.out.println("val idClient dans obtenir ListeDesCompte : " + IdClient);


		// remplir liste comptes
		this.listeComptes = this.compteService.obtenirListeComptesClient(monClient);
		System.out.println("La liste des comptes" + this.listeComptes);

		return "virement";
	}


	@Override
	public String toString() {
		return this.compte.getNumCompte() + " " + this.compte.getTypeCompte()+ "" + this.compte.getSoldeCompte();
	}

}
