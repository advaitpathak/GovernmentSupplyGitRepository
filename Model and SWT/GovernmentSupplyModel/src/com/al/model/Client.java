package com.al.model;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * Client here is Government Client which places an order
 * @Client class is a model class used to store client information in objects 
 *  ClientId is a primary key used in database
 *  clientName is Name of client
 * clientEmail is a email id given for particular client
 * clientContactNo is used to store mobile number
 * 
 */

public class Client implements Serializable
{
	private int clientId;
	private String clientName;
	private String clientEmail;
	private String clientPassword;
	private String	clientContactNo;
	
	/**
	 * Client is a parameterized constructor with following parameters
	 * @param clientId is a unique id(primary key) in the database
	 * @param clientName
	 * @param clientEmail
	 * @param clientPassword
	 * @param clientContactNo
	 */
	public Client(int clientId, String clientName, String clientEmail, String clientPassword, String clientContactNo) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
		this.clientContactNo = clientContactNo;
	}
	/**
	 * Default constructor
	 */
	
	public Client() {
		super();
	}
	/**
	 * 
	 * @return returns client Id
	 * 
	 */
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * 
	 * @return client name
	 * @see com.al.model.Client#setClientName(String)
	 */
	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * 
	 * @return clientEmail
	 * @see com.al.model.Client#setClientEmail(String)
	 */
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	/***
	 * 
	 * @return Client Passsword
	 * @see com.al.model.Client#setClientPassword(String)
	 */
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	/**
	 * 
	 * @return Client Contact No
	 * @see com.al.model.Client#setClientContactNo(String)
	 */
	public String getClientContactNo() {
		return clientContactNo;
	}
	public void setClientContactNo(String clientContactNo) {
		this.clientContactNo = clientContactNo;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientEmail=" + clientEmail
				+ ", clientPassword=" + clientPassword + ", clientContactNo=" + clientContactNo + "]";
	}
	/**
	 * Checks whether entry is already is present
	 */
	public boolean equals(Object ob)
	{
		try
		{
			Client client = (Client) ob;
			if(this.clientId == client.clientId)
				return true;
			return false;
			
		}
		catch(Exception ex)
		{
			return super.equals(ob);
		}
	}
	
}
