package fr.gtm.proxibanque.domaine;

public class Compte {

	private Integer idCompte;
	private String numCompte;
	private String typeCompte;
	private Double soldeCompte;
	private Double decouvertMaxCompte;
	private ClientProxi client;

	public Compte() {
		super();
	}

	public Compte(Integer idCompte, String numCompte, String typeCompte, Double soldeCompte,
			Double decouvertMaxCompte) {
		super();
		this.idCompte = idCompte;
		this.numCompte = numCompte;
		this.typeCompte = typeCompte;
		this.soldeCompte = soldeCompte;
		this.decouvertMaxCompte = decouvertMaxCompte;
	}

	public Compte(Integer idCompte) {
		this.idCompte = idCompte;
	}

	public Integer getIdCompte() {
		return this.idCompte;
	}

	public void setIdCompte(Integer idCompte) {
		this.idCompte = idCompte;
	}

	public String getNumCompte() {
		return this.numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public String getTypeCompte() {
		return this.typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public Double getSoldeCompte() {
		return this.soldeCompte;
	}

	public void setSoldeCompte(Double soldeCompte) {
		this.soldeCompte = soldeCompte;
	}

	public Double getDecouvertMaxCompte() {
		return this.decouvertMaxCompte;
	}

	public void setDecouvertMaxCompte(Double decouvertMaxCompte) {
		this.decouvertMaxCompte = decouvertMaxCompte;
	}

	public ClientProxi getClient() {
		return this.client;
	}

	public void setClient(ClientProxi client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return this.numCompte + " " + this.typeCompte + "" + this.soldeCompte;
	}

}
