package fr.gtm.proxibanque.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.gtm.proxibanque.domaine.Compte;
import fr.gtm.proxibanque.service.CompteService;

@ManagedBean
//@SessionScoped
public class VirementBean {

	private Integer idCompteDebiteur;
	private Integer idCompteCrediteur;
	private Double sommeVirement;
	CompteService compteService = new CompteService();









	public VirementBean(Integer idCompteDebiteur, Integer idCompteCrediteur, Double sommeVirement) {
		super();
		this.idCompteDebiteur = idCompteDebiteur;
		this.idCompteCrediteur = idCompteCrediteur;
		this.sommeVirement = sommeVirement;
	}




	public VirementBean() {
	}




	//Get & Set
	public Integer getIdCompteDebiteur() {
		return this.idCompteDebiteur;
	}
	public void setIdCompteDebiteur(Integer idCompteDebiteur) {
		this.idCompteDebiteur = idCompteDebiteur;
	}
	public Integer getIdCompteCrediteur() {
		return this.idCompteCrediteur;
	}
	public void setIdCompteCrediteur(Integer idCompteCrediteur) {
		this.idCompteCrediteur = idCompteCrediteur;
	}
	public Double getSommeVirement() {
		return this.sommeVirement;
	}
	public void setSommeVirement(Double sommeVirement) {
		this.sommeVirement = sommeVirement;
	}



	public Object  virement(VirementBean monVirement ) {
		System.out.println("on est dans le virement");
		System.out.println("Id Crediteur  : "+ monVirement.idCompteCrediteur + "  / ID Debiteur : " + monVirement.idCompteDebiteur + " / Somme : " + monVirement.sommeVirement);

		Compte compte1 = new Compte();
		Compte compte2 = new Compte();
		compte1.setIdCompte(monVirement.idCompteCrediteur);
		compte2.setIdCompte(monVirement.idCompteDebiteur);
		System.out.println("Compte crediteur : " + compte1.getIdCompte());
		System.out.println("Compte Debiteur : "+compte2.getIdCompte());



		boolean recap = this.compteService.virement(compte1, compte2, this.sommeVirement);
		System.out.println(recap);
		FacesContext context = FacesContext.getCurrentInstance();
		if (recap == true) {
			context.addMessage("messagevalid", new FacesMessage("Virement Effectué"));
			return "liste-clients.xhtml";
		}else {

			context.addMessage("messageError", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Virement  non Effectué","Virement"));
			//		System.out.println("ETatVirement : " + etatVirement);
			return "virement.xhtml";
		}

		//		System.out.println("ETatVirement : " + etatVirement);

	}


}
