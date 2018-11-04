package com.Service;

import java.util.List;

import com.al.dao.ClientExistException;
import com.al.model.Client;

public class ClientService implements ClientServiceInterface {
	/**
	 * Method @ Client getClient(int clientId)
	 * return Client with ClientId from Client table through ClientDao
	 */
	@Override
	public Client getClient(int clientId) {
		
		return clientDao.getClient(clientId);
	}
	/**
	 * Method @ List<Client> getAllClient()
	 * return all Clients from Client table through ClientDao
	 */
	@Override
	public List<Client> getAllClient() {
		// TODO Auto-generated method stub
		return clientDao.getAllClient();
	}
	/**
	 * Method @ addClient(Client client)
	 * adds Client into Client table through ClientDao
	 */
	@Override
	public void addClient(Client client) throws ClientExistException {
		
		clientDao.addClient(client);
		
	}
	/**
	 * Method @ updateClient(Client client)
	 * updates details of Client into Client table through ClientDao
	 */
	
	@Override
	public void updateClient(Client client) {
		clientDao.updateClient(client);
		
	}
	/**
	 * Method @ deleteClient(Client client)
	 * Deletes Client from Client table through ClientDao
	 */
	@Override
	public void deleteClient(Client client) {
		clientDao.deleteClient(client);
		
	}

	
}
