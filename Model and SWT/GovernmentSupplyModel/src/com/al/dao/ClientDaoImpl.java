package com.al.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.al.connection.DBConnection;
import com.al.model.Client;

/**
 * 
 * @author Administrator
 *	@ClientDaoImpl implements @ClientDao interface to perform the CRUD operations 
 */
public class ClientDaoImpl implements ClientDao {

	private static List<Client> allclientList = new ArrayList<Client>();
	
	/**
	 * 
	 * getClient() method fetches Client with respective Client Id
	 */
	@Override
	public Client getClient(int clientId)
	{	
		allclientList = new ArrayList<Client>();
		try
		{
			Client dummy = new Client();
			dummy.setClientId(clientId);
			if(allclientList.contains(dummy))
			{
				int index = allclientList.indexOf(clientId);
				return allclientList.get(index);
			}
	
			Connection connection = DBConnection.getConnection();
			String sqlQuery="select * from "+TABLEClient+" where "+COLclientId+"="+"?";
			
			/**
			 * establish connection and execute the sql query
			 */
			
			PreparedStatement preparedStatement;
	
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, clientId);
			preparedStatement.executeQuery();
			ResultSet resultSet = preparedStatement.getResultSet();
		
			/**
			 * Until there is data in resultSet
			 */
			if(resultSet.next()==false)
			{
				return null;
			}
			String clientName = resultSet.getString(COLclientName);
			String clientEmail = resultSet.getString(COLclientEmail);
			String clientPassword = resultSet.getString(COLclientPassword);
			String clientContactNo = resultSet.getString(COLclientContactNo);
		
			/**
			 * Store above client data in type Client Object
			 */
			Client  client =new Client(clientId,clientName,clientEmail,clientPassword,clientContactNo);
			/**
			 * Add Client Object in to List
			 */
			allclientList.add(client);
			
			return client;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @getAllClient() method returns list of all clients
	 */
	@Override
	public List<Client> getAllClient()
	{		
		allclientList = new ArrayList<Client>();
		try
		{	
			Connection connection=DBConnection.getConnection();
			String sqlQuery="select * from "+TABLEClient;
			/**
			 * Creates Connection with database and Execute Query
			 */
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.executeQuery();
			ResultSet resultSet = preparedStatement.getResultSet();
			/**
			 * Until resultSet is not null
			 */
			while(resultSet.next())
			{
				int clientId = resultSet.getInt(COLclientId);
				String clientName = resultSet.getString(COLclientName);
				String clientEmail = resultSet.getString(COLclientEmail);
				String clientPassword = resultSet.getString(COLclientPassword);
				String clientContactNo = resultSet.getString(COLclientContactNo);
				
				/**
				 * Make client object and set above parameters to it 
				 * Add this client object to list
				 */
				Client client =new Client(clientId,clientName,clientEmail,clientPassword,clientContactNo);
				allclientList.add(client);
			}
			return allclientList;
			
		}
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
		return null;
	}

	/**
	 * @addClient() method adds client by taking argument as object client
	 * If client already exists throw  ClientExistException
	 */
	@Override
	public void addClient(Client client) throws ClientExistException
	{
		try
		{
			Connection connection = DBConnection.getConnection();
			String sqlQuery = "insert into "+TABLEClient+" values (?,?,?,?,?)";
			
			
		/**
		 * Creates Connection and executes Query.
		 */
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, client.getClientId());
			preparedStatement.setString(2, client.getClientName());
			preparedStatement.setString(3, client.getClientEmail());
			preparedStatement.setString(4, client.getClientPassword());
			preparedStatement.setString(5,  client.getClientContactNo());
		
			preparedStatement.executeUpdate();
			allclientList.add(client);
		} 
		catch (SQLException e)
		{	
			e.printStackTrace();
		}
	}

	/**
	 * UpdateClient method  updates particular clients data
	 */
	@Override
	public void updateClient(Client client)
	{
		int temp_clientId = client.getClientId();
		try
		{
			Connection connection=DBConnection.getConnection();
			String sqlQuery="update "+TABLEClient+" set "+COLclientName+" = ? ,"+COLclientEmail+" = ?,"+COLclientPassword+" = ?,"+COLclientContactNo+" = ? where "+COLclientId+" ="+temp_clientId;
		/**
		 * Creates connection and execute sql query
		 */
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, client.getClientName());
			preparedStatement.setString(2, client.getClientEmail());
			preparedStatement.setString(3, client.getClientPassword());
			preparedStatement.setString(4, client.getClientContactNo());
		
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
		}
	}

	/**
	 * deleteClient method deletes client with particular Id
	 */
	@Override
	public void deleteClient(Client client)
	{
		try
		{
			int temp_clientId = client.getClientId();
			Connection connection=DBConnection.getConnection();
			String sqlQuery="delete "+TABLEClient+" where "+COLclientId+" = "+temp_clientId;
			/**
			 * Creates connection and execute sql query
			 */
			PreparedStatement pst = connection.prepareStatement(sqlQuery);
			pst.executeUpdate();
	
			Client dummy=new Client();
			dummy.setClientId(client.getClientId());
			
			/*
			 * Remove from list if it is present in  list
			 */
			
			if(allclientList.contains(dummy))
			{
				allclientList.remove(dummy);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}