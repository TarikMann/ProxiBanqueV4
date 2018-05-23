package fr.gtm.proxibanque.interfaces;

import java.util.List;

import fr.gtm.proxibanque.domaine.ClientProxi;

public interface IClientService {

	public boolean creerClient(ClientProxi clientProxi); 		
	public ClientProxi obtenirClient(Integer idClientProxi);
	public boolean modifierClient(ClientProxi clientProxi);
	public boolean supprimerClient(ClientProxi clientProxi);
	public List<ClientProxi> obtenirClientsBanque();
			
}
