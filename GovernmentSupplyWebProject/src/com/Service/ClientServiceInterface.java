package com.Service;

import java.util.List;

import com.al.dao.ClientDao;
import com.al.dao.ClientDaoImpl;
import com.al.dao.ClientExistException;
import com.al.model.Client;
/**
 * 
 * @author Administrator
 * @Interface ClientServiceInterface
 *
 */
public interface ClientServiceInterface {
	
	ClientDao clientDao=new ClientDaoImpl();
	
	Client getClient(int clientId);
	List<Client> getAllClient();
	void addClient(Client client)throws ClientExistException;
	void updateClient(Client client);
	void deleteClient(Client client);
	

}
