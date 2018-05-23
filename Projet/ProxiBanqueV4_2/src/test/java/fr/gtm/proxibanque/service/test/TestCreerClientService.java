package fr.gtm.proxibanque.service.test;

import static org.junit.Assert.*;

import java.io.Closeable;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.gtm.proxibanque.domaine.ClientProxi;
import fr.gtm.proxibanque.domaine.Conseiller;
import fr.gtm.proxibanque.service.ClientService;
import fr.gtm.proxibanque.service.CompteService;
import fr.gtm.proxibanque.service.ConseillerService;

public class TestCreerClientService {

	private ClientProxi client = new ClientProxi();

	ClientService clientService = new ClientService();

	/**
	 * Methode de test qui permet de verifier qu'un client est bien creer et qu'on a
	 * bien une reponse positif en retour
	 */
	@Test
	public void testCreerClientRetourV() {

		ClientProxi client = new ClientProxi(null, "f", " te", " te", "te ");
		Conseiller conseiller = new Conseiller();
		conseiller.setNomConseiller("DUBOIS");
		conseiller.setPrenomConseiller("Constance");
		conseiller.setIdConseiller(1);
		client.setConseiller(conseiller);
		boolean retourCreer = clientService.creerClient(client);
		boolean retourSupprimer = clientService.supprimerClient(client);
		assertTrue(retourCreer);

	}

	// @Test
	// public void testCreerClientEnBase() {
	// int numTest = clientService.obtenirClientsBanque().size();
	// ClientProxi client = new ClientProxi(null, "testA"+numTest, "testB"+numTest,
	// "testC"+numTest, "testD"+numTest);
	// Conseiller conseiller = new Conseiller();
	// conseiller.setNomConseiller("DUBOIS");
	// conseiller.setPrenomConseiller("Constance");
	// conseiller.setIdConseiller(1);
	// client.setConseiller(conseiller);
	// boolean retourCreer = clientService.creerClient(client);
	// boolean estEnBase = clientService.obtenirClientsBanque().contains(client);
	//
	// boolean retourSupprimer = clientService.supprimerClient(client);
	// assertTrue(estEnBase);
	//
	// }

}
