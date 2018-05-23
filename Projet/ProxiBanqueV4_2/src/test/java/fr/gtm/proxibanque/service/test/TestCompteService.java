package fr.gtm.proxibanque.service.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Compte;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.service.CompteService;

/**
 * @author Groupe1 
 *
 */
public class TestCompteService {
	
	//private static CompteService compteService;
	private Compte compte1;
	private Compte compte2;
	
	CompteService compteService = new CompteService();
	
	

	

	/**
	 * Methode de test qui permet de verifier que lors d'un virement l'emetteur à bien le debit qui diminue
	 */
	@Test
	public void testVirementSoldeDebiteurDim() {
		Conseiller conseiller1 = new Conseiller(1,"nomConseillerTest","prenomConseillerTest",null,null);
		Conseiller conseiller2 = new Conseiller(2,"nomConseillerTest","prenomConseillerTest",null,null);
		ClientProxi client1 = new ClientProxi(1,null,null,null,null);
		ClientProxi client2 = new ClientProxi(2,null,null,null,null);
		client1.setConseiller(conseiller1);
		client2.setConseiller(conseiller2);
		Compte compte1= new Compte(1, "C1234", "Courant", 10000.0, 1000.0);
		Compte compte2= new Compte(3, "C2456", "Courant", 15000.0, 1000.0);
		compte1.setClient(client1);
		compte2.setClient(client2);
		List<Compte> listeCompteavant = compteService.obtenirComptesBanque();
		
		Double solde1avant= listeCompteavant.get(0).getSoldeCompte();  
		Double solde2avant= listeCompteavant.get(2).getSoldeCompte();
		
		
		boolean r = compteService.virement (compte1, compte2, 150.0);
		
		List<Compte> listeCompteapres = compteService.obtenirComptesBanque();
		Double solde1apres= listeCompteapres.get(1).getSoldeCompte();  
		Double solde2apres= listeCompteapres.get(3).getSoldeCompte();
		boolean r2 = compteService.virement (compte2, compte1, 150.0);
				assertFalse(solde1avant-solde1apres == 0.0);
		
	}

	/**
	 * Methode de test qui permet de verifier que lors d'un virement le crediteur à bien le compte qui augmente
	 */
	@Test
	public void testVirementSoldeCreditAug() {
		Conseiller conseiller1 = new Conseiller(1,"nomConseillerTest","prenomConseillerTest",null,null);
		Conseiller conseiller2 = new Conseiller(2,"nomConseillerTest","prenomConseillerTest",null,null);
		ClientProxi client1 = new ClientProxi(1,null,null,null,null);
		ClientProxi client2 = new ClientProxi(2,null,null,null,null);
		client1.setConseiller(conseiller1);
		client2.setConseiller(conseiller2);
		Compte compte1= new Compte(1, "C1234", "Courant", 10000.0, 1000.0);
		Compte compte2= new Compte(3, "C2456", "Courant", 15000.0, 1000.0);
		compte1.setClient(client1);
		compte2.setClient(client2);
		
		List<Compte> listeCompteavant = compteService.obtenirComptesBanque();
		
		Double solde1avant= listeCompteavant.get(0).getSoldeCompte();  
		Double solde2avant= listeCompteavant.get(2).getSoldeCompte();
		
		
		boolean r = compteService.virement (compte1, compte2, 150.0);
		
		List<Compte> listeCompteapres = compteService.obtenirComptesBanque();
		Double solde1apres= listeCompteapres.get(1).getSoldeCompte();  
		Double solde2apres= listeCompteapres.get(3).getSoldeCompte();
		boolean r1 = compteService.virement (compte2, compte1, 150.0);
				assertFalse(solde2avant-solde2apres == 0.0);
		
	}
	
	/**
	 * Methode de test qui permet de verifier que lors d'un virement  d'un somme tres eleve, 
	 * le virement n'est pas faite et le compte emeteur n'est pas debiter
	 */
	@Test
	public void testVirementSoldeDebiteurInsufPasDebit() {
		Conseiller conseiller1 = new Conseiller(1,"nomConseillerTest","prenomConseillerTest",null,null);
		Conseiller conseiller2 = new Conseiller(2,"nomConseillerTest","prenomConseillerTest",null,null);
		ClientProxi client1 = new ClientProxi(1,null,null,null,null);
		ClientProxi client2 = new ClientProxi(2,null,null,null,null);
		client1.setConseiller(conseiller1);
		client2.setConseiller(conseiller2);
		Compte compte1= new Compte(1, "C1234", "Courant", 10000.0, 1000.0);
		Compte compte2= new Compte(3, "C2456", "Courant", 15000.0, 1000.0);
		compte1.setClient(client1);
		compte2.setClient(client2);
		List<Compte> listeCompteavant = compteService.obtenirComptesBanque();
		
		Double solde1avant= listeCompteavant.get(0).getSoldeCompte();  
		Double solde2avant= listeCompteavant.get(2).getSoldeCompte();
		
		
		boolean r = compteService.virement (compte1, compte2, 1000000.0);
		
		List<Compte> listeCompteapres = compteService.obtenirComptesBanque();
		Double solde1apres= listeCompteapres.get(1).getSoldeCompte();  
		Double solde2apres= listeCompteapres.get(3).getSoldeCompte();
		
				assertTrue(solde1avant-solde1apres < 0.1);
		
		
	}

	/**
	 * Methode de test qui permet de verifier que lors d'un virement  d'un somme tres eleve, 
	 * le virement n'est pas faite et le compte crediteur n'est pas crediter
	 */
	@Test
	public void testVirementSoldeDebiteurInsufPasCredit() {
		Conseiller conseiller1 = new Conseiller(1,"nomConseillerTest","prenomConseillerTest",null,null);
		Conseiller conseiller2 = new Conseiller(2,"nomConseillerTest","prenomConseillerTest",null,null);
		ClientProxi client1 = new ClientProxi(1,null,null,null,null);
		ClientProxi client2 = new ClientProxi(2,null,null,null,null);
		client1.setConseiller(conseiller1);
		client2.setConseiller(conseiller2);
		Compte compte1= new Compte(1, "C1234", "Courant", 10000.0, 1000.0);
		Compte compte2= new Compte(3, "C2456", "Courant", 15000.0, 1000.0);
		compte1.setClient(client1);
		compte2.setClient(client2);
		List<Compte> listeCompteavant = compteService.obtenirComptesBanque();
		
		Double solde1avant= listeCompteavant.get(0).getSoldeCompte();  
		Double solde2avant= listeCompteavant.get(2).getSoldeCompte();
		
		
		boolean r = compteService.virement (compte1, compte2, 1000000.0);
		
		List<Compte> listeCompteapres = compteService.obtenirComptesBanque();
		Double solde1apres= listeCompteapres.get(1).getSoldeCompte();  
		Double solde2apres= listeCompteapres.get(3).getSoldeCompte();
		
				assertTrue(solde2avant-solde2apres < 0.1);
		
		
	}
//	@Test
//	public void testVirement() {
//		
//		
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testObtenirComptesDecouvert() {
//		fail("Not yet implemented");
//	}

}
