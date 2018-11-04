package com.al.dao;

import java.util.List;

import com.al.model.Client;

/**
 * 
 * @author Administrator
 *interface @ClientDao
 */
public interface ClientDao
{
	String TABLEClient ="Client",COLclientId="clientId",COLclientName= "clientName";
	String COLclientEmail = "clientEmail",COLclientPassword = "clientPassword", COLclientContactNo = "clientContactNo";
	 
	Client getClient(int clientId);
	List<Client> getAllClient();
	void addClient(Client client)throws ClientExistException;
	void updateClient(Client client);
	void deleteClient(Client client);
}
