package fr.gtm.proxibanque.domaine;


import java.util.Date;




public class Transaction {
	
	
	private Integer idTransaction;
	
	private Date date;
	
	private Integer idCompteEmetteur;
	
	private Integer idCompteBeneficiaire;
	
	private Double montant;

	
	public Transaction() {
		super();
	}


	public Transaction(Integer idTransaction, Date date, Integer idCompteEmmeteur,
			Integer idCompteBeneficiaire, Double montant) {
		super();
		this.idTransaction = idTransaction;
		this.date = date;
		this.idCompteEmetteur = idCompteEmmeteur;
		this.idCompteBeneficiaire = idCompteBeneficiaire;
		this.montant = montant;
	}


	public Integer getIdTransaction() {
		return idTransaction;
	}


	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Integer getIdCompteEmetteur() {
		return idCompteEmetteur;
	}


	public void setIdCompteEmmetteur(Integer idCompteEmmeteur) {
		this.idCompteEmetteur = idCompteEmmeteur;
	}


	public Integer getIdCompteBeneficiaire() {
		return idCompteBeneficiaire;
	}


	public void setIdCompteBeneficiaire(Integer idCompteBeneficiaire) {
		this.idCompteBeneficiaire = idCompteBeneficiaire;
	}


	public Double getMontant() {
		return montant;
	}


	public void setMontant(Double montant) {
		this.montant = montant;
	}
	
	

}
